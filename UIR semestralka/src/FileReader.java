import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class FileReader {

	CorpusRepresentation readTextCorpus(String path, int startIndex, int endIndex) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		File file;
		
		startIndex--;
		endIndex--;

		HashMap<String,ArrayList<TextRepresentation>> data = new HashMap <String,ArrayList<TextRepresentation>>();

		if (startIndex < 0 || startIndex > endIndex) {
			return null;
		}
		if (endIndex > listOfFiles.length) {
			return null;
		}
		
		HashSet<String> listOfAllWords = new HashSet<String>();

		for (int i = startIndex; i <= endIndex; i++) {
			file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".txt")) {
				Scanner scanner;
				try {
					scanner = new Scanner(file, "UTF-8");
					String text = scanner.useDelimiter("\\A").next();
					scanner.close();
					TextRepresentation textRep = new TextRepresentation(file.getName(), text);
					listOfAllWords.addAll(textRep.getMap().keySet());
					if(data.containsKey(textRep.getReal_Type())){
						data.get(textRep.getReal_Type()).add(textRep);
					}
					else{
						ArrayList<TextRepresentation> textArray = new ArrayList<TextRepresentation>();
						textArray.add(textRep);
						data.put(textRep.getReal_Type(), textArray);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		CorpusRepresentation corpus = new CorpusRepresentation(data,listOfAllWords);
		return corpus;
	}

}
