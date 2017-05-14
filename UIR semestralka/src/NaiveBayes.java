import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class NaiveBayes implements Classifier {
	
	private ArrayList<PropabilityTable> Bayes;
	private TextFeatures feature;
	private double accuracy;
	
	NaiveBayes(BayesRepresentation learningData,TextFeatures features){
		int numberOfClasses = learningData.getSize();
		
		ArrayList<PropabilityTable> Bayes = new ArrayList<PropabilityTable>();
		
		double occurence;
		double propability;
		for(ClassRepresentation classRep: learningData){
			HashMap<String,Double> propabilities = new HashMap<String,Double>();
			
			propability = (double)classRep.getNumberOfTexts() / numberOfClasses;
			propabilities.put("CLASS", propability);
			
			for(String s:learningData.getWords()){
				occurence = 0;
				if(classRep.getClassMap().containsKey(s)){
					occurence = features.returnOccurence(classRep, s);
				}
				propability = (occurence + 1) / (learningData.getWords().size() + classRep.getVocabulary(features));
				propabilities.put(s,propability);
			}
			PropabilityTable propTable = new PropabilityTable(classRep.getName(),propabilities);
			Bayes.add(propTable);
		}
		this.feature = features;
		this.Bayes = Bayes;
	}
	
	NaiveBayes(ArrayList<PropabilityTable> Bayes,TextFeatures features){
		this.Bayes = Bayes;
		this.feature = features;
	}
	
	@Override
	public void classifyData(ArrayList<TextRepresentation> testingData){
		double tempMaxPropability;
		double tempPropability;
		
		
			for(TextRepresentation testingRep:testingData){
				tempMaxPropability = -Double.MAX_VALUE;
					for(PropabilityTable propabilities:Bayes){
						tempPropability = 0;
						for(String s: testingRep.getMap().keySet()){
							if(propabilities.getPropTable().containsKey(s)){
								tempPropability += Math.log(propabilities.getPropTable().get(s));
							}
						}
						tempPropability += Math.log(propabilities.getPropTable().get("CLASS"));
						if(tempPropability > tempMaxPropability){
							tempMaxPropability = tempPropability;
							testingRep.setClassType(propabilities.getClassName());
						}
					}
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
	
	
	@Override
	public void saveData(String filename){	
		try {
			BufferedWriter out = new BufferedWriter
				    (new OutputStreamWriter(new FileOutputStream(filename+".txt"),"UTF-8"));
			
			String className = "naivebayes " + this.feature.toString();
			
			out.write(className);
			out.newLine();
			
			for(PropabilityTable probTab : this.Bayes){
				out.write(String.valueOf(probTab.getPropTable().size()));
				out.newLine();
				out.write(probTab.getClassName());
				out.newLine();
				for(Entry<String, Double> e:probTab.getPropTable().entrySet()){
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
	public double getAccuracy() {
		return this.accuracy;
	}
	
	
	
	

}
