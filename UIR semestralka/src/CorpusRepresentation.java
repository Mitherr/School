import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CorpusRepresentation {
	
	private HashMap<String,ArrayList<TextRepresentation>> data;
	private HashSet<String> listOfAllWords;
	
	CorpusRepresentation(HashMap<String,ArrayList<TextRepresentation>> data,HashSet<String> listOfAllWords){
		this.data = data;
		this.listOfAllWords = listOfAllWords;
	}
	
	public HashMap<String, ArrayList<TextRepresentation>> getData() {
		return data;
	}

	public HashSet<String> getListOfAllWords() {
		return listOfAllWords;
	}

}
