import java.util.HashMap;

public class TextRepresentation {

	private HashMap<String,Integer> textMap;
	private String realType;
	private String classType;
	private CzechStemmerAgressive stemmer = new CzechStemmerAgressive();

	TextRepresentation(String name,String text){
		textMap = new HashMap<String,Integer>(20);
		
		String[] temp = name.split("_|\\.");
		this.realType=temp[1];
		
		 String[] st = text.split(" ");
	     for(String token:st) {
	    	 token = token.toLowerCase();
	    	 token = stemmer.stem(token);
	    	 if(token.length()!=0){
	    	 if(!textMap.containsKey(token)){
	    		 textMap.put(token, 1);
	    	 }
	    	 else{
	    		 int help = textMap.get(token)+1;
	    		 textMap.put(token, help);
	    	 }
	    	 }
	     }
	}
	
	TextRepresentation(String text){
		textMap = new HashMap<String,Integer>(20);
		
		 String[] st = text.split(" ");
	     for(String token:st) {
	    	 token = token.toLowerCase();
	    	 token = stemmer.stem(token);
	    	 if(token.length()!=0){
	    	 if(!textMap.containsKey(token)){
	    		 textMap.put(token, 1);
	    	 }
	    	 else{
	    		 int help = textMap.get(token)+1;
	    		 textMap.put(token, help);
	    	 }
	    	 }
	     }
	}
	
	TextRepresentation(HashMap<String,Integer> textMap,String type){
		this.textMap = textMap;
		this.realType = type;
	}
		
	public HashMap<String,Integer> getMap(){
		return this.textMap;
	}
	
	public String getRealType(){
		return this.realType;
	}
	
	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}
	
}
