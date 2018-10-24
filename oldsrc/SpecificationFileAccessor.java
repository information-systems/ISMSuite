import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.cpntools.accesscpn.engine.Simulator;
import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.model.PlaceNode;

public class SpecificationFileAccessor {
	
	public static List<populationType> getTypes(String specificationFileLocation)
	{
		// The list with all the types. Will be returned.
		List<populationType> typesList = new ArrayList<populationType>();
		
		// The file which contains the specification.
		File specificationFile = new File(specificationFileLocation);
		Scanner scanner = null;
		
		// access the file
		try {
			scanner = new Scanner(specificationFile);
		}
		catch (FileNotFoundException e)
		{
			throw new java.lang.Error("Was not able to access the specification file");
		}
		
		// Will check if the types section has been found.
		boolean typesFound = false;
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine(); // Get the next line in the file.
			
			if (line.length() > 0 && line.charAt(0) == '%') {
				// this is a line full of comments
				continue;
			}
			
			if (line.equals("types:"))
			{
				typesFound = true;
				continue;
			}
			
			// If the types section has bene processed. Break the loop.
			if (line.isEmpty() && typesFound == true)
			{
				break;
			}
			
			// If the types section has been found, and the line is not empty. Add the line to the types list.
			if (typesFound && !line.isEmpty())
			{
				populationType pt = new populationType(line);
				typesList.add(pt);
			}
		}
		scanner.close(); // close the scanner.
		return typesList;	
	}
	

	public static List<populationLabel> getLabels(String specificationFileLocation)
	{
		// The list that will contain all the labels.
		List<populationLabel> labelList = new ArrayList<populationLabel>();
		
		// Get the specificationFile.
		File specificationFile = new File(specificationFileLocation);
		Scanner scanner = null;
		
		// access the file
		try {
				scanner = new Scanner(specificationFile);
			}
		catch (FileNotFoundException e)
			{
				throw new java.lang.Error("Was not able to access the specification file");
			}
		// Checks if the labels section has been found.
		boolean labelsFound = false;
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			
			// If the labels section has been found, set the labels found boolean to true.
			if (line.toLowerCase().equals("labels:"))
			{
				labelsFound = true;
				continue;
			}
			
			// If the end of the labels section has been found.
			if (line.isEmpty() && labelsFound == true)
			{
				break;
			}
			
			// If the labels section has been found, and the line is not empty. Add the line to the types list.
			if (labelsFound && !line.isEmpty())
			{
				populationLabel pe = new populationLabel(line);
				labelList.add(pe);
			}
		}
		scanner.close();
		return labelList;
	}
	
	public static InitialPopulation getIntialPopulationMarking(String specificationFileLocation, HighLevelSimulator simulator, List<Instance<PlaceNode>> places) throws Exception
	{
		// The list that will contain all the inititial population lines.
		List<String> initialPopulationMarkingLines = new ArrayList<String>();
		File specificationFile = new File(specificationFileLocation);
		Scanner scanner = null;
		
		// access the file
		try {
				scanner = new Scanner(specificationFile);
			}
		catch (FileNotFoundException e)
			{
				throw new java.lang.Error("Was not able to access the specification file");
			}
		
		boolean initialPopulationFound = false;
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			
			// If the initialpopulation section has been found, set the intial population found boolean to true.
			if (line.toLowerCase().equals("initialpopulation:"))
				{
					initialPopulationFound = true;
					continue;
				}
				
				// If the end of the initial population section has been found.
				if (line.isEmpty() && initialPopulationFound == true)
				{
					break;
				}
					
				// If the types section has been found, and the line is not empty. Add the line to the types list.
				if (initialPopulationFound && !line.isEmpty())
				{
					initialPopulationMarkingLines.add(line);
				}
		}
		
		scanner.close(); // close the scanner.
		
		// Create a new initial population.
		InitialPopulation initialPopulation = new InitialPopulation(initialPopulationMarkingLines, simulator);
		
		return initialPopulation;
	}
}
