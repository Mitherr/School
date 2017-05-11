import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class TextRepresentation {

	private String name;
	private HashMap<String,Integer> textMap;
	private String real_type;
	private String class_type;

	TextRepresentation(String name,String text){
		this.name = name;
		textMap = new HashMap<String,Integer>(20);
		
		String[] temp = name.split("_|\\.");
		this.real_type=temp[1];
		
		 StringTokenizer st = new StringTokenizer(text);
	     while (st.hasMoreTokens()) {
	    	 String token = st.nextToken().toLowerCase();
	    	 if(!textMap.containsKey(token)){
	    		 textMap.put(token, 1);
	    	 }
	    	 else{
	    		 int help = textMap.get(token)+1;
	    		 textMap.put(token, help);
	    	 }
	     }
	}
	
	public String getName(){
		return this.name;
	}
		
	public HashMap<String,Integer> getMap(){
		return this.textMap;
	}
	
	public String getReal_Type(){
		return this.real_type;
	}
	
	public String getClass_type() {
		return class_type;
	}

	public void setClass_type(String class_type) {
		this.class_type = class_type;
	}
	
}
