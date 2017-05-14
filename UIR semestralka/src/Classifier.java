import java.util.ArrayList;

public interface Classifier {
	
	public void classifyData(ArrayList<TextRepresentation> testingData);
	
	public double getAccuracy();

	public void saveData(String fileName);

	void countAccuracy(ArrayList<TextRepresentation> testingData);
	
}
