import java.util.ArrayList;

public class NearestNeighbour {

	CorpusRepresentation learningData;
	TextFeatures features;
	double accuracy;
	
	NearestNeighbour(CorpusRepresentation learningData,TextFeatures features){
		this.learningData = learningData;
		this.accuracy = 0;
		this.features = features;
	}
	
	public void classifyData(CorpusRepresentation testingData){
		double tempDifference = 0;
		double difference = Integer.MAX_VALUE;
		
		
		for(ArrayList<TextRepresentation> testing:testingData){
			for(TextRepresentation testingRep:testing){
				tempDifference = 0;
				difference = Integer.MAX_VALUE;
				for(ArrayList<TextRepresentation> learning:learningData){
					for(TextRepresentation learningRep:learning){
						tempDifference = features.returnDistance(testingRep, learningRep);
						if(tempDifference<difference){
							difference = tempDifference;
							testingRep.setClass_type(learningRep.getReal_Type());
						}
					}
				}
			}
		}
		
		double correct = 0;
		double uncorrect = 0;
		double acc = 0;
		
		for(ArrayList<TextRepresentation> testing:testingData){
			for(TextRepresentation testingRep:testing){
				if(testingRep.getReal_Type().equals(testingRep.getClass_type())){
					correct++;
				}
				else{
					uncorrect++;
				}
				}
			}
		
		acc = correct/(correct+uncorrect);
		
		this.accuracy=acc*100;
	}

	public TextFeatures getFeatures() {
		return features;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setFeatures(TextFeatures features) {
		this.features = features;
	}
	
}
