import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import org.cpntools.accesscpn.engine.highlevel.instance.Binding;

import java.util.AbstractMap.SimpleEntry;

public class TransitionInfo {
	
	private List<OperatorSpecification> operatorSpecificationList = new ArrayList<OperatorSpecification>();
	public TransitionInfo(String transitionName, Binding binding, String specificationFileLocation) throws FileNotFoundException, InterruptedException
	{
		// Get the specification file.
		File specificationFile = new File(specificationFileLocation);
		Scanner scanner = new Scanner(specificationFile);
		
		// Boolean which checks if the transition section can be found.
		boolean transitionFound = false; 
		
		while (scanner.hasNextLine())
		{
			String line = scanner.nextLine(); // Get the next line of the scanner.
			line = line.replaceAll("\\s", ""); // Remove all the whitespaces.
			line = line.toLowerCase(); // Set the line to lowercase.
			
			// If the line contains 'transition:' with the name of the transition, set the transition found boolean to true
			if (line.contains("transition:" + transitionName.toLowerCase()))
			{
				transitionFound = true;
				continue;
			}
			
			// If the end of the transition section has been found, break the loop.
			if (line.isEmpty() && transitionFound)
			{
				break;
			}
			
			// If the types section has been found, and the line is not empty. Add the line to the types list.
			if (transitionFound && !line.isEmpty())
			{
				// Create specification and add to list.
				OperatorSpecification spec = new OperatorSpecification(line, binding);
				operatorSpecificationList.add(spec);			
			}
		}
		scanner.close();
		
	}
	
	public List<OperatorSpecification> getSpecifications()
	{
		return operatorSpecificationList;
	}
}
