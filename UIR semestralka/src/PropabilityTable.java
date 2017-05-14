import java.util.HashMap;

public class PropabilityTable {

	private HashMap<String,Double> propTable;
	private String className;
	
	PropabilityTable(String className,HashMap<String,Double> propTable){
		this.propTable = propTable;
		this.className = className;
	}

	public HashMap<String, Double> getPropTable() {
		return propTable;
	}

	public String getClassName() {
		return className;
	}
	
	
}

