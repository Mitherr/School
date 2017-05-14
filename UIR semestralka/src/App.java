import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	
	Label bottomLabel;
	TextArea textArea;
	
	private static String[] arguments;
	private static Classifier classifier;
	
	public static void main(String[] args){
		
		if(args.length == 5){
			
			FileReader fr = new FileReader();
			
			String[] arg1 = args[0].split("-");
			
			int learningRange1 = 0;
			int learningRange2 = 0;
			
			try{
			learningRange1 = Integer.parseInt(arg1[0]);
			learningRange2 = Integer.parseInt(arg1[1]);
			}catch(NumberFormatException e){
				System.err.println("Chybný rozsah pro trénovací množinu zadejte prosím èísla v rozsahu od 1 do 11954 ve formátu 1-1954");
			}
			
			ArrayList<TextRepresentation> learningData = fr.readTextCorpus("czech_text_document_corpus_v10", learningRange1, learningRange2);
			
			String[] arg2 = args[1].split("-");
			
			int testingRange1 = 0;
			int testingRange2 = 0;
			
			try{
			testingRange1 = Integer.parseInt(arg2[0]);
			testingRange2 = Integer.parseInt(arg2[1]);
			}catch(NumberFormatException e){
				System.err.println("Chybný rozsah pro testovací množinu zadejte prosím èísla v rozsahu od 1 do 11954 ve formátu 1-1954");
			}			
			
			ArrayList<TextRepresentation> testingData = fr.readTextCorpus("czech_text_document_corpus_v10", testingRange1, testingRange2); 	

			TextFeatures feature = null;
			
			String featureType = args[2].toLowerCase();
			
			switch(featureType){
	   			 case "binary":
	   				 feature = new BinaryFeature();
	   				 break;
		         case "filteredbinary": 
		        	 feature = new FilteredBinaryFeature();
		        	 break;
		         case "frequency": 
		        	 feature = new FrequencyFeature();
		        	 break;
		         case "filteredfrequency": 
		        	 feature = new FilteredFrequencyFeature();
		        	 break;
			}
			
			if(feature == null){
				System.err.println("Chybnì napsaný formát pro parametrizaèní algoritmus možné algoritmy jsou: binary, filteredbinary, frequency, filteredfrequency");
			}
			
			String classifierType = args[3].toLowerCase();
			
			Classifier classifier = null;
			
			switch(classifierType){
  			 case "1-nn":
  				 classifier = new NearestNeighbour(learningData,feature);
  				 break;
	         case "naivebayes": 
	        	 BayesRepresentation bayesRep = new BayesRepresentation(learningData);
	        	 classifier = new NaiveBayes(bayesRep,feature);
	        	 break;
			}
			
			if(classifier == null){
				System.err.println("Chybnì napsaný formát pro klasifikaèní algoritmus možné algoritmy jsou: 1-nn, naivebayes");
			}
			
			classifier.classifyData(testingData);	
			classifier.countAccuracy(testingData);
			
			System.out.format("Uspìšnost klasifikátoru je: %.2f%%\n", classifier.getAccuracy());		
			
			classifier.saveData(args[4]);
			
			System.out.println("Data uložena");	
					
			Platform.exit();	
		}
		else if(args.length == 1){
		
		arguments = args;
		launch(args);	
		
		}
		else{
			System.out.println("Špatný poèet argumentù");
			Platform.exit();
		}
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Classification gui");
		
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(getTop());
		borderPane.setCenter(getCenter());
		borderPane.setRight(getRight());
		borderPane.setBottom(getBottom());
		
		primaryStage.setScene(new Scene(borderPane,300,250));
		
		FileReader fr = new FileReader();
		classifier = fr.loadClassifier(arguments[0]);
		
		primaryStage.show();
	}

	private Label getTop() {
		Label topLabel = new Label("Classify your own text:");
		return topLabel;
	}

	private TextArea getCenter() {
		textArea = new TextArea();
		return textArea;
	}
	
	private Button getRight(){
		Button classifyBTN = new Button("Classify");
		classifyBTN.setOnAction(e ->{
			classifyText();
		});
		return classifyBTN;
	}
	
	private void classifyText() {
		ArrayList<TextRepresentation> test = new ArrayList<TextRepresentation>();
		TextRepresentation fromGui = new TextRepresentation(this.textArea.getText());
		test.add(fromGui);
		classifier.classifyData(test);
		String type = test.get(0).getClassType();
		this.bottomLabel.setText("Your text was classified as ---> " + type);
	}

	private Label getBottom(){
		String labelText = "Your text was classified as ---> ";
		this.bottomLabel = new Label(labelText);
		return bottomLabel;
	}
	
}
