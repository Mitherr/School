import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BayesRepresentation implements Iterable<ClassRepresentation> {
	
	private HashMap<String,ClassRepresentation> bayesRep;
	private HashSet<String> listOfWords;
	private int size;
	
	BayesRepresentation(ArrayList<TextRepresentation>data){
		HashMap<String,ClassRepresentation> bayesRep = new HashMap<String,ClassRepresentation>();
		HashSet<String> listOfWords = new HashSet<String>();
		
		for(TextRepresentation textRep:data){
			if(bayesRep.containsKey(textRep.getRealType())){
				bayesRep.get(textRep.getRealType()).addTextRepresentation(textRep);
				listOfWords.addAll(textRep.getMap().keySet());
			}
			else{
				ClassRepresentation classRep = new ClassRepresentation(textRep.getRealType());
				classRep.addTextRepresentation(textRep);
				bayesRep.put(textRep.getRealType(), classRep);
				listOfWords.addAll(textRep.getMap().keySet());
			}
		}
		this.listOfWords = listOfWords;
		this.bayesRep =  bayesRep;
		this.size = data.size();
	}
	
	public HashMap<String,ClassRepresentation> getBayesRepresentation(){
		return this.bayesRep;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public HashSet<String> getWords(){
		return this.listOfWords;
	}

	@Override
	public Iterator<ClassRepresentation> iterator() {
		return this.bayesRep.values().iterator();
	}
	

}
