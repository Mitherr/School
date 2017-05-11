import java.util.HashSet;
import java.util.Set;

public class PositionAndFrequencyFeature implements TextFeatures {

	@Override
	public double returnDistance(TextRepresentation first, TextRepresentation second) {
		Set<String> keys = new HashSet<String>();
		keys.addAll(first.getMap().keySet());
		keys.addAll(second.getMap().keySet());
		
		int appearence1 = 0;
		int appearence2 = 0;
		
		double distance = 0;
		int x = 0;
		
		for(String key:keys){
			
			appearence1 = 0;
			appearence2 = 0;
			
			if(first.getMap().containsKey(key)){
				appearence1 = first.getMap().get(key);
			}
			
			if(second.getMap().containsKey(key)){
				appearence2 = second.getMap().get(key);
			}
			
			x = appearence1 - appearence2;
			
			distance += x*x;
			
		}
		
		return Math.sqrt(distance);	
	}
	
	

}
