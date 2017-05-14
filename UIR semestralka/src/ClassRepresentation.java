import java.util.HashMap;
import java.util.Map;


public class ClassRepresentation {
	private HashMap<String,Integer> classMap;
	private String name;
	private int numberOfTexts;
	private int vocabulary;
	
	ClassRepresentation(String name){
		HashMap<String,Integer> classMap = new HashMap<String,Integer>();
		this.classMap = classMap;
		this.numberOfTexts = 0;
		this.name = name;
		this.vocabulary = 0;
	}
	
	public void addTextRepresentation(TextRepresentation textRep){		
		for(Map.Entry<String,Integer> e: textRep.getMap().entrySet()){
			this.classMap.merge(e.getKey(), e.getValue(), Integer::sum);
		}
		this.numberOfTexts++;
	}
	
	public void countVocabulary(TextFeatures feature){
		int count = 0;
		
		for(String s:this.getClassMap().keySet()){
				count += feature.returnOccurence(this, s);
		}
		
		this.vocabulary = count;		
	}
	
	public int getNumberOfTexts(){
		return this.numberOfTexts;
	}
	
	public HashMap<String, Integer> getClassMap() {
		return classMap;
	}

	public String getName() {
		return name;
	}
	
	public int getVocabulary(TextFeatures feature){
		if(this.vocabulary == 0){
			countVocabulary(feature);
		}
		return this.vocabulary;
	}


}
