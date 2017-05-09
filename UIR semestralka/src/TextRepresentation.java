import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class TextRepresentation {

	private HashMap<String,Integer> textMap;
	private String type;
	private int[] vector;

	TextRepresentation(String name,String text){
		textMap = new HashMap<String,Integer>(20);
		
		String[] temp = name.split("_|\\.");
		this.type=temp[1];
		
		 StringTokenizer st = new StringTokenizer(text);
	     while (st.hasMoreTokens()) {
	    	 String token = st.nextToken();
	    	 if(!textMap.containsKey(token)){
	    		 textMap.put(token, 1);
	    	 }
	    	 else{
	    		 int help = textMap.get(token)+1;
	    		 textMap.put(token, help);
	    	 }
	     }
	}
	
	public void createVector(HashSet<String> set){
		int[] vec = new int[set.size()];
		int i = 0;
		
		for(String s:set){
			if(textMap.containsKey(s)){
				vec[i] = textMap.get(s);
			}
			else{
				vec[i] = 0;
			}
			i++;
		}
		this.vector = vec;
	}
	
	public HashMap<String,Integer> getMap(){
		return this.textMap;
	}
	
	public String getType(){
		return this.type;
	}
	
	public int[] getVector() {
		return vector;
	}

	public void setVector(int[] vector) {
		this.vector = vector;
	}
	
}
