import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class CorpusRepresentation implements Iterable<ArrayList<TextRepresentation>> {
	
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

	@Override
	public Iterator<ArrayList<TextRepresentation>> iterator() {
		return this.getData().values().iterator();
	}

}
