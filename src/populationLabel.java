import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class populationLabel {

	private String name;
	private List<String> relationDefinition = new ArrayList<String>();
	private List<String> requiredTypes = new ArrayList<String>();
	
	public populationLabel(String line)
	{
		// Remove all the spaces within the line.
		line = line.replaceAll("\\s", "");

		this.name = line.substring(0, line.indexOf("[")); // Get the name
		
		// Get the relation definition, e.g P in person [P] {p}.
		this.relationDefinition = Arrays.asList(line.substring(line.indexOf("[") + 1, line.indexOf("]")).toUpperCase().split(","));
		
		// Get the required types, e.g. p in person [P] {p}.
		this.requiredTypes = Arrays.asList(line.substring(line.indexOf("{") + 1, line.indexOf("}")).split(","));
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<String> getRelationDefinition()
	{
		return relationDefinition;
	}
	
	public List<String> getRequiredTypes()
	{
		return requiredTypes;
	}
}
