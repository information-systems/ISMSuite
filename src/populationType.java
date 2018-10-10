
public class populationType {
	private  String name;
	private  String representativeChars;
	
	public populationType(String line)
	{
		line = line.replaceAll("\\s", ""); // Remove all the whitespaces from the line.
		this.name = line.substring(0, line.indexOf("{")); // Get the name of the type.
		this.representativeChars = line.substring(line.indexOf("{") + 1, line.indexOf("}")); // Get the char(s) that represent the type
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getRepresentativeChars()
	{
		return representativeChars;
	}
}
