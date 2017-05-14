import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map.Entry;

public class NearestNeighbour implements Classifier {

	ArrayList<TextRepresentation> learningData;
	TextFeatures feature;
	double accuracy;
	
	NearestNeighbour(ArrayList<TextRepresentation>learningData,TextFeatures features){
		this.learningData = learningData;
		this.accuracy = 0;
		this.feature = features;
	}
	
	@Override
	public void classifyData(ArrayList<TextRepresentation> testingData){
		double tempDifference = 0;
		double difference = Integer.MAX_VALUE;
		
		
			for(TextRepresentation testingRep:testingData){
				tempDifference = 0;
				difference = Integer.MAX_VALUE;
					for(TextRepresentation learningRep:learningData){
						tempDifference = feature.returnDistance(testingRep, learningRep);
						if(tempDifference<difference){
							difference = tempDifference;
							testingRep.setClassType(learningRep.getRealType());
						}
					}
				}
		
		double correct = 0;
		double uncorrect = 0;
		double acc = 0;
		
			for(TextRepresentation testingRep:testingData){
				if(testingRep.getRealType().equals(testingRep.getClassType())){
					correct++;
				}
				else{
					uncorrect++;
				}
				}
		
		acc = correct/(correct+uncorrect);
		
		this.accuracy=acc*100;
	}
	
	@Override
	public void saveData(String filename){	
		try {
			BufferedWriter out = new BufferedWriter
				    (new OutputStreamWriter(new FileOutputStream(filename+".txt"),"UTF-8"));
			
			String className = "1-nn " + this.feature.toString();
			
			out.write(className);
			out.newLine();
			
			for(TextRepresentation textRep : this.learningData){
				out.write(String.valueOf(textRep.getMap().size()));
				out.newLine();
				out.write(textRep.getRealType());
				out.newLine();
				for(Entry<String, Integer> e:textRep.getMap().entrySet()){
					out.write(e.getKey());
					out.write(" ");
					out.write(e.getValue().toString());
					out.newLine();
				}
			}
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void countAccuracy(ArrayList<TextRepresentation> testingData){

		double correct = 0;
		double uncorrect = 0;
		double acc = 0;
		
			for(TextRepresentation testingRep:testingData){
				if(testingRep.getRealType().equals(testingRep.getClassType())){
					correct++;
				}
				else{
					uncorrect++;
				}
				}
		
		acc = correct/(correct+uncorrect);
		
		this.accuracy=acc*100;
	}

	public TextFeatures getFeatures() {
		return feature;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setFeatures(TextFeatures features) {
		this.feature = features;
	}
	
}
