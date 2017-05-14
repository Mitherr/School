import java.util.HashSet;
import java.util.Set;

public class FilteredBinaryFeature implements TextFeatures {
		
		private static final Set<String> filteredWords = new HashSet<String>();
		static {		
			filteredWords.add("a");
			filteredWords.add("i");
			filteredWords.add("ani");
			filteredWords.add("nebo");
			filteredWords.add("�i");
			filteredWords.add("ale");
			filteredWords.add("av�ak");
			filteredWords.add("v�ak");
			filteredWords.add("le�");
			filteredWords.add("n�br�");
			filteredWords.add("naopak");
			filteredWords.add("jenom�e");
			filteredWords.add("jen�e");
			filteredWords.add("ba");
			filteredWords.add("dokonce");
			filteredWords.add("anebo");
			filteredWords.add("bu�");
			filteredWords.add("toti�");
			filteredWords.add("v�dy�");
			filteredWords.add("nebo�");
			filteredWords.add("proto");
			filteredWords.add("tedy");
		}
		

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
				
				if(!filteredWords.contains(key)){
				appearence1 = 0;
				appearence2 = 0;
				
				if(first.getMap().containsKey(key)){
					appearence1 = 1;
				}
				
				if(second.getMap().containsKey(key)){
					appearence2 = 1;
				}
				
				x = appearence1 - appearence2;
				
				distance += x*x;
				}
				
			}
			
			return Math.sqrt(distance);	
		}
		
		@Override
		public double returnOccurence(ClassRepresentation classRep,String key){
			if(filteredWords.contains(key)){
				return 0;
			}
			return 1;
		}
		
		@Override
		public String toString(){
			return "filteredbinary";
		}

	}
