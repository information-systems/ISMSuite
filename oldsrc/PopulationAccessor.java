import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.cpntools.accesscpn.engine.highlevel.instance.Binding;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.model.Transition;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class PopulationAccessor {

	public static List<String> updatePopulation(String transitionName, String populationFileLocation, List<String> population, Binding binding, String specificationFileLocation) throws Exception
	{
		// Initialize the list that will hold the population.
		List<String> populationTemp = new ArrayList<String>(population);
		// Specificy the dateFormat
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		// Create a new date (i.e. the current date)
		Date date = new Date();
		
		// Based on the transition that gets fired, create a transition Info object.
		TransitionInfo transitionInfo = new TransitionInfo(transitionName, binding, specificationFileLocation);
		
		// Get the specification that relates to the specific transition / binding.
		List<OperatorSpecification> specificationList = transitionInfo.getSpecifications();
		
		for (OperatorSpecification specification : specificationList)
		{
			String argumentsUnderscore = new String(); // String that will have underscores between elements.
			String argumentsComma	   = new String(); // String that will have commas between elements.
			for (int i = 0; i < specification.getValueTypes().size(); i++)
			{
				// If the argument contains an '@', get the text after the '@' and use this as type.
				if (specification.getValueTypes().get(i).contains("@"))
				{
					String dateType = specification.getValueTypes().get(i).substring(specification.getValueTypes().get(i).indexOf("@") + 1);
					
					// Replace the argument with the current time and the given date type
					specification.getValueTypes().set(i, dateType + dateFormat.format(date));
				}
				
				// If it is the last item in the arguments list, do not add a comma or an underscore.
				if (i == specification.getValueTypes().size() - 1)
				{
					argumentsUnderscore += specification.getValueTypes().get(i);
					argumentsComma += specification.getValueTypes().get(i);
					break;
				}
				argumentsUnderscore += specification.getValueTypes().get(i) + "_";
				argumentsComma += specification.getValueTypes().get(i) + ",";
			}
			
			// If an remove operator has been stated.
			if (specification.getOperator().equals("remove"))
			{
				// Delete all the statements inthe file that contain the argument. 
				removeFromPopulation(specification.getLabel() + "(" + argumentsComma +")" ,populationFileLocation);
				
				// Remove the statement from the population list (Note: Not equal to the file).
				List<String> populationFillerList = new ArrayList<String>();
				for (String value : populationTemp)
				{
					if (value.contains(specification.getLabel() + "(" + argumentsComma + ")" )) {
						continue;
					}
					
					populationFillerList.add(value);
				}
				populationTemp = populationFillerList;
			}
				
			// If the add operator has been stated in the specification file.
			if (specification.getOperator().equals("add"))
			{
				// If a type needs to be added.
				if (specification.getLabel().equals("type") && specification.getValueTypes().size() == 1)
					addToPopulation("tff(" + argumentsUnderscore + "_type" + ",type," + argumentsComma + ": " + argumentsComma.replaceAll("\\d", "").replaceAll("_", "") + ").", populationFileLocation);
				
				// Add the 'normal' axiom to the file.
				else
				{
					addToPopulation("tff(" + argumentsUnderscore + "_" + specification.getLabel()  + ",axiom," + specification.getLabel() + "(" + argumentsComma + ")).", populationFileLocation);
					populationTemp.add(specification.getLabel() + "(" + argumentsComma + ")");
				}
			}
			
		}
		
		return populationTemp;
	}
	
	// Get the entities from the specific type, e.g. student(Rick) returns Rick.
	public static List<String> getLabelEntities(String populationFilelocation, String labelAndValueTypes) throws Exception
	{
		List<String> returnList = new ArrayList<String>();
		// Read the file.
		try (BufferedReader reader = new BufferedReader(new FileReader(populationFilelocation)))
		{
			String newline;
			// Read the file line by line.
			while ((newline = reader.readLine()) != null) {
				// Create a start and end index to get the specific entities.
				int startIndexSubstring = newline.indexOf(labelAndValueTypes);
				int endIndexSubstring = newline.indexOf(")");
				
				//If the start and index are not -1, i.e. they do exist. Then get the entities.
				if (startIndexSubstring != -1 && endIndexSubstring != -1)
				{
				startIndexSubstring += labelAndValueTypes.length();
				returnList.add(newline.substring(startIndexSubstring, endIndexSubstring ));				
				}
			}
			reader.close();
		}
		return returnList;		
	}
	
	// Add the statement to the population.
	public static void addToPopulation(String statement, String populationFileLocation) throws Exception
	{
		FileWriter fw = new FileWriter(populationFileLocation, true);
		fw.write(statement + "\r\n");
		fw.close();		
	}
		
	// Remove the lines that contain the statement from the population.
	public static void removeFromPopulation(String statement, String populationFileLocation) throws Exception
	{
		// Get the file.
		File file = new File(populationFileLocation);
		
		// Get the contents of the file as a list..
		List<String> content_of_file = new ArrayList<>(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
		
		List<String> content_of_fileTemp = new ArrayList<String>();
		// For every line within the file, check if it contains the statement. If it does, remove the statement from the list.
		for (int l = 0; l < content_of_file.size(); l++)
		{
			if (content_of_file.get(l).contains(statement))
				continue;
			
			content_of_fileTemp.add(content_of_file.get(l));
		}
		
		// Write to the file.
		Files.write(file.toPath(),content_of_fileTemp, StandardCharsets.UTF_8);
	}
	
	public static boolean validate(String populationFileLocation, String specificationFileLocation, String constraintsFileLocation) throws Exception
	{		
		// Create a list that will contain all the created constraints.
		List<String> constraintList = new ArrayList<String>();
			
		// get the mentioned population file (i.e. file within the same directory). And use this to get the absoluteFile
		File populationAbsoluteFile = new File(populationFileLocation);
		
		// Get the absolute constraints file
		File constraintsAbsoluteFile = new File(constraintsFileLocation);
		
		// Create the scanner
		
		Scanner scanner = null;
		
		// access the constraints file
		try {
			
			scanner = new Scanner(constraintsAbsoluteFile);
		}
		catch (FileNotFoundException e)
		{
			throw new java.lang.Error("Was not able to access the constraints file");
		}
		
		// read everyline from the constraints file.
		while (scanner.hasNextLine())
		{		
			String newline = scanner.nextLine();
			// Read the file line by line.
			if (!newline.isEmpty())
				constraintList.add(newline);			
		}
		// Close the scanner.
		scanner.close();
		
		// Create the directory that will contain all the temporary constraint files.
		File tempConstraintDirectory = new File("temporaryConstraintFiles");
		
		// Create temporary file.
		File constraintTempFile = File.createTempFile("constraintFile", ".p",tempConstraintDirectory);		
		
		// If the file exists and it is possible to write in file, start writing
		if (constraintTempFile.exists() && constraintTempFile.canWrite())
		{
			PrintWriter writer = new PrintWriter(constraintTempFile.getCanonicalPath(), "UTF-8");
			writer.println("include(\'" + populationAbsoluteFile.getAbsolutePath() + "\')."); // Add the include TPTP statement to the temporary constraints file.
			
			// Get all the types in the specification file.
			List<populationType> types = SpecificationFileAccessor.getTypes(specificationFileLocation);
			
			for (populationType type : types)
			{
				// Get the values.
				List<String> typeValues = PopulationAccessor.getLabelEntities(populationFileLocation, type.getName() + "(");
				
				// Create the not equal statement from a specific label.
				String notEqualStatement = PopulationAccessor.stateEntitiesNotEqual(typeValues);
				
				// If the notequalStatement is not equal to null or is not empty, write to the file.
				if (notEqualStatement != null && !notEqualStatement.isEmpty())
					writer.println("tff(all" + type.getName() + "unique,axiom," + notEqualStatement + ").");
			}
				
			// Write the allEntityStatement, i.e. if an entity exist, it should be one of the following.
			for (String allEntityStatement : PopulationAccessor.createAllEntityStatements(populationFileLocation, specificationFileLocation))
			{
				if (allEntityStatement.isEmpty())
					continue;
				
				writer.println(allEntityStatement);
			}
			
			// Write all the constraints to the temporary constraint file.
			for (String constraint : constraintList)
			{ 
				if (constraint.isEmpty() )
					continue;
				
				writer.println(constraint);
			}
			writer.close();
		}
			else
				throw new java.lang.Error("It was not possible to create a temporary constraint file and write to it.");
		
		// Check if constraint can be proven.
		// If not valid return false.	
		if (PopulationAccessor.CheckValid(constraintTempFile.getPath()))
		{ 
			
			// constraintTempFile.delete();
		}
		// If not valid, delete the file and return that the transition is not valid.
		else
		{
			// constraintTempFile.delete();
			return false;
		}
		return true;	
	}
	
	// Create the statement that entities are not equal. (Recursive function)
	public static String stateEntitiesNotEqual(List<String> allEntities)
	{
		String returnedString = new String();
		
		// Check if the list is not empty and that it is bigger than 1.
		if (allEntities.size() > 1)
		{		
			// State that the first entity in the list is not equal to all the other entities in the list.
			for (int i = 1; i < allEntities.size(); i++)
			{
				// If it is the last in the list, do not add an & at the end.
				if (i == allEntities.size() - 1)
				{
						returnedString += allEntities.get(0) + " != " + allEntities.get(i);
				}
					else
						returnedString += allEntities.get(0) + " != " + allEntities.get(i) + " & ";
			}
		
		// Remove the first entity in the list.
		allEntities.remove(allEntities.get(0));
		
		// If the size of the list is greater than 1, call the function again, which makes it recursive.
		if (allEntities.size() != 1)
			returnedString += " & " + stateEntitiesNotEqual(allEntities);
				
		return returnedString;
		}
		
		// If the list is empty or the list its size is equal to 1, return nothing.
		return null;
	}
	
	// Create the statements for every entity that define each possible entity for a given label.
	public static List<String> createAllEntityStatements(String populationFileLocation, String specificationFileLocation) throws Exception
	{
		List<String> AllEntityStatements = new ArrayList<String>();
		for (populationLabel entity : SpecificationFileAccessor.getLabels(specificationFileLocation))
		{
			// Get the existing entities from the population.
			List<String> populationEntities = getLabelEntities(populationFileLocation, entity.getName() + "(");
			
			if (populationEntities.isEmpty() )
				continue;
						
			// Get the individual relation definitions.
			List<String> relationDefinitions = entity.getRelationDefinition();
			
			// Get the individual required types.
			List<String> requiredTypes = entity.getRequiredTypes();
			
			// The size of the required types and the relation definitions should be equal.
			if (requiredTypes.size() != relationDefinitions.size())
			{
				System.out.println("Entity: " + entity.getName());
				System.out.println("Required: " + requiredTypes);
				System.out.println("Found: " + relationDefinitions);
				throw new java.lang.Error("The required types length is not equal to the relationDefinitions length" + entity + " requiredtypes: " + requiredTypes.size() + "relationdefinition " + relationDefinitions.size());			
			}
			
			// Create (and start the string) that relates the type to the relation definition.
			// E.g. P = person, p is the type. It makes [P: p]
			String relationAndTypes = "[";
			String relationDefinition = new String();
			List<String> equalStatements = new ArrayList<String>();	
			
			// Create the string that combines the relation definition with the related type.
			for (int i = 0; i < relationDefinitions.size(); i++)
			{
				if (i == relationDefinitions.size() -1) {
					relationAndTypes += relationDefinitions.get(i) + ": " + requiredTypes.get(i);
					relationDefinition += relationDefinitions.get(i);
				}
							
				else
				{
					relationAndTypes += relationDefinitions.get(i) + ": " + requiredTypes.get(i) + ",";
					relationDefinition += relationDefinitions.get(i) + ",";
				}
					
			}
			relationAndTypes += "]";
			
			// Create the part of the statement that maps the value to the relation definition. E.g. P = p21 => i.e. P is person 21.
			for (String populationEntity : populationEntities)
			{
				String[] populationEntityParts = populationEntity.split(",");
						
				if (populationEntityParts.length != relationDefinitions.size())
				{
					System.out.println("For entity: " + entity.getName());
					System.out.println(Arrays.toString(populationEntityParts) + " & " + relationDefinitions );
					throw new java.lang.Error("The length is not equal to each other: population <> relationdefinitions.");
				}
				
				String temp = "(";
				for (int k = 0; k < relationDefinitions.size(); k++ )
				{
					if (k == relationDefinitions.size() - 1)
						temp += relationDefinitions.get(k) + " = " + populationEntityParts[k];
					else
						temp += relationDefinitions.get(k) + " = " + populationEntityParts[k] + " & ";		
				}
				
				temp += ")";
				
				equalStatements.add(temp);
			}
			
			// Create the part of the statement that states which values it could be. E.g.  (P = 10) | (P = 11) | (P = 12) etc.
			String equalStatement = new String();
			
			for (int e = 0; e < equalStatements.size(); e++ )
			{
				if (e == equalStatements.size() - 1)
					equalStatement += equalStatements.get(e);
					
				else
					equalStatement += equalStatements.get(e) + " | ";
			}
			
			// Create the total statement.
			AllEntityStatements.add("tff(all_" + entity.getName() + ",axiom,( ! " + relationAndTypes + " : (" + entity.getName() + "(" + relationDefinition + ") " + "=> (" + equalStatement + ")))).");
			
		}
		
		return AllEntityStatements;		
	}
	
	public static boolean checkUniqueness(List<String> population) {
		
		// Create HashSet that only can contain unique values.
		Set<String> set = new HashSet<String>();
		
		// Add all the values from  the Multimap to the hashset.
		for (String s : population)
		    set.add(s);
		
		// Check if the size of the multimap values is equal to the size of set.
		// If it is not, there are not unique values in the multimap.
		boolean allUnique = set.size() == population.size();
		
		return allUnique;
			
	}
	
	public static boolean CheckValid(String filename) throws Exception
	{
		boolean result = false;
		Runtime rt = Runtime.getRuntime();
		
		// The filelocation should point to the location that contains all the ptpt files.
		String command_and_filelocation = "E\\PROVER\\eprover --auto " + filename;

		// Execute the eprover command. Add the filename to the command.
		Process pr = rt.exec(command_and_filelocation);	
		
		// Get the standard output and (if one exists) the error from the console.
		BufferedReader stdOutput = new BufferedReader(new 
			     InputStreamReader(pr.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
			     InputStreamReader(pr.getErrorStream()));
		
			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdOutput.readLine()) != null) {
			    System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			    System.out.println(pr.waitFor());
			}
		
		int code = pr.waitFor();
		
		// No proof has been found.
		if (code == 1)
			result = true;
			
		// Proof has been been found (code = 0) or there is an error (other code than 0 or 1).
		else
			result = false;
		
		return result;
	}
	
}
