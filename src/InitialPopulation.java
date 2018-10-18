import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.model.PlaceNode;

public class InitialPopulation {
	private List<String> populationLinesList  = new ArrayList<String>();
	private List<String> shortEntityPopulationList = new ArrayList<String>();
	public InitialPopulation(List<String> initialPopulationFileLines, HighLevelSimulator simulator) throws Exception
	{
		List<Instance<PlaceNode>> places = simulator.getAllPlaceInstances();
		
		for (String line : initialPopulationFileLines)
		{
			line = line.replaceAll("\\s", ""); // Replace all spaces in the line.

			
			String[] initialPopulationParts = line.split("\\("); // Split the line on '('
			initialPopulationParts[2] = initialPopulationParts[2].replace(")", "").replace(".", ""); // Split thereafter on ')'
			
			String placeName = initialPopulationParts[0]; // Get the name of the place
			String label = initialPopulationParts[1]; // Get the label
			String type = initialPopulationParts[2]; // Get the type
			
			// For each marking in the specified place, add the TPTP (TFF) statements.
			for (String marking : Arrays.asList(simulator.getMarking(CPNAccessor.findPlaceByName(placeName, places)).split("\\++")))
			{
				String m = marking.substring(marking.indexOf("`") + 1,marking.length()); // Get the marking as a string.
				
				// If the marking contains a ',' it means that it contains a tuple. 
				if (m.contains(","))
				{
					// Remove the the brackets from the marking.
					m = m.replaceAll("\\(", "");
					m = m.replaceAll("\\)", "");
					String tempString = new String();
					
					// Create the string that will represent the tuple values. E.g. tuple (p,t) becomes p_t
					for (int i = 0; i < m.split(",").length; i++)
					{
						if (i == m.split(",").length)
							tempString += m.split(",")[i];
							
						tempString += m.split(",")[i] + "_";
					}
					m = tempString;
				}
				// Create the tff statements and the short version for the population list.
				populationLinesList.add("tff(" + type + m +  "_type" + ",type," + type + m + ": " + type + ").");
				populationLinesList.add("tff(" + type + m + ",axiom," + label + "(" + type + m + ")).");
				shortEntityPopulationList.add(label + "(" + type + m + ")");
			}
		}
	}
	
	public List<String> getInitialPopulationMarkingStatements()
	{
		return populationLinesList;
	}
	
	public List<String> getShortEntityPopulationList()
	{
		return shortEntityPopulationList;
	}
}
