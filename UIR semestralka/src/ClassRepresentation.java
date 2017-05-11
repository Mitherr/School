import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassRepresentation {
	
	HashMap<String,Integer> classMap;
	private String name;
	
	ClassRepresentation(ArrayList<TextRepresentation> texts){
		HashMap<String,Integer> classMap = new HashMap<String,Integer>();
		
		for(TextRepresentation textRep:texts){
			for(Map.Entry<String, Integer> e : textRep.getMap().entrySet()){
				classMap.merge(e.getKey(), e.getValue(), Integer:sum);
			}
		}
		this.classMap = classMap;
		this.name = texts.get(0).getReal_Type();
	}

}
