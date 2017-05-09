import java.util.ArrayList;

public class Application {
	
	

	public static void main(String[] args) {		
		FileReader fr = new FileReader();
		CorpusRepresentation data = fr.readTextCorpus("czech_text_document_corpus_v10", 1, 1001);
		for(ArrayList<TextRepresentation> Array:data.getData().values()){
			for(TextRepresentation t:Array){
				t.createVector(data.getListOfAllWords());
			}
		}
		System.out.print("wow such shit");
	}

}
