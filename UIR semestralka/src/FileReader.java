import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class FileReader {

	ArrayList<TextRepresentation> readTextCorpus(String path, int startIndex, int endIndex) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		File file;
		
		startIndex--;
		endIndex--;

		ArrayList<TextRepresentation> data = new ArrayList<TextRepresentation>();

		if (startIndex < 0 || startIndex > endIndex) {
			return null;
		}
		if (endIndex > listOfFiles.length) {
			return null;
		}

		for (int i = startIndex; i <= endIndex; i++) {
			file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".txt")) {
				Scanner scanner;
				try {
					scanner = new Scanner(file, "UTF-8");
					String text = scanner.useDelimiter("\\A").next();
					scanner.close();
					TextRepresentation textRep = new TextRepresentation(file.getName(), text);
					data.add(textRep);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	Classifier loadClassifier(String path){
		
		Scanner sc = null;
		
		try {
			sc = new Scanner(new FileInputStream(new File(path)), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		sc.useLocale(Locale.US);
		
		String setting[] = null;
		
		if(sc.hasNextLine()){
			setting = sc.nextLine().split(" ");;
		}else{
			System.err.println("Chybný soubor");
			return null;
		}
		
		String classifierType = setting[0];
		classifierType = classifierType.trim();
		String featureType = setting[1];
		featureType = featureType.trim();
		
		TextFeatures feature = null;
		
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
		
		Classifier classifier = null;
		
		
		if(classifierType.equals("1-nn")){
			 ArrayList<TextRepresentation> learningData = loadLearningData(sc);
			 classifier = new NearestNeighbour(learningData,feature);
		}
		
		if(classifierType.equals("naivebayes")){
	       	 ArrayList<PropabilityTable> propTable = loadPropabilityTable(sc);
	       	 classifier = new NaiveBayes(propTable,feature);
		}		
		
		return classifier;
	}

	private ArrayList<PropabilityTable> loadPropabilityTable(Scanner sc) {
		ArrayList<PropabilityTable> propTable = new ArrayList<PropabilityTable>();
		
		int numberOfEntries;
		String textType;
		PropabilityTable temp;
		
		while(sc.hasNext()){
			numberOfEntries = sc.nextInt();
			sc.nextLine();
			textType = sc.nextLine();
			HashMap<String,Double> propMap = new HashMap<String,Double>();
			for(int i=0;i<numberOfEntries;i++){
				propMap.put(sc.next(), sc.nextDouble());
				sc.nextLine();
			}
			temp = new PropabilityTable(textType,propMap);
			propTable.add(temp);
		}
		
		return propTable;
	}

	private ArrayList<TextRepresentation> loadLearningData(Scanner sc) {
		ArrayList<TextRepresentation> learningData = new ArrayList<TextRepresentation>();
				
		int numberOfEntries;
		String textType;
		TextRepresentation temp;
		
		while(sc.hasNext()){
			numberOfEntries = sc.nextInt();
			sc.nextLine();
			textType = sc.nextLine();
			HashMap<String,Integer> textMap = new HashMap<String,Integer>();
			for(int i=0;i<numberOfEntries;i++){
				textMap.put(sc.next(), sc.nextInt());
				sc.nextLine();
			}
			temp = new TextRepresentation(textMap,textType);
			learningData.add(temp);
		}
		
		return learningData;
	}
	
	
}
