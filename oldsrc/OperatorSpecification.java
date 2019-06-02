import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.cpntools.accesscpn.engine.highlevel.instance.Binding;
import org.cpntools.accesscpn.engine.highlevel.instance.ValueAssignment;

public class OperatorSpecification {
	private String label = new String();
	private List<String> valueTypes = new ArrayList<String>();
	private enum operatorEnum { add, remove };
	private operatorEnum operator;
	
	// Line is a trimmed string, i.e. no spaces.
	public OperatorSpecification(String line, Binding binding) throws InterruptedException
	{
		List<String> argumentsTemp = new ArrayList<String>();
		
		// Trimming the line is not necessary anymore. This has been done in the TransitionInfo class.
		
		String[] specificationParts = line.split("\\("); // Split on. E.g. add(person(p)) will return add, person and p))
		specificationParts[2] = specificationParts[2].replace(")", "").replace(".", ""); // Remove the '))' in the last part after the split.
		
		// Check if the operator is an add or a remove.
		if (specificationParts[0].equals("add"))
			operator = operatorEnum.add;
		
		if (specificationParts[0].equals("remove"))
			operator = operatorEnum.remove;
		
		label = specificationParts[1]; // get the labels.
		argumentsTemp = Arrays.asList(specificationParts[2].split(",")); // Split on comma, By doing this you get all the arguments in a list.
		
		for (String arg : argumentsTemp)
		{
			// If the first two letters are nu and the third is a digit. Then the statement states that the entity needs to be upped by the found digit.
			if (arg.length() > 3 && arg.substring(0,2).equals("nu") && Character.isDigit(arg.charAt(2))) 
			{
				String number = arg.replaceAll("[^\\d]", ""); // Remove all the non digits.
				String type = getValueTypesOfCounterArgument(arg); // Get the type of the argument.
				
				// Add the argument to the list. E.g. p18 initialy has become p19 and gets added.
				valueTypes.add( type  + (Integer.parseInt(binding.getValueAssignment("counter").getValue()) + Integer.parseInt(number) ));
				continue;
			}
			
			// The arguments refers to the current date time.
			if (arg.contains("@"))
			{
				valueTypes.add(arg);
				continue;
			}
			
			// A cast to another type is requested, e.g. a{p} the value assignment of a gets converter to the type p.
			if (arg.contains("{") && arg.contains("}"))
			{
				String argumentToGetValueFrom = arg.substring(0, arg.indexOf("{")); // Get the type from which you need to get the value from.
				String argumentToChangeToo = arg.substring(arg.indexOf("{") + 1, arg.indexOf("}")); // Get the type to which you need to change to.
				valueTypes.add(argumentToChangeToo + binding.getValueAssignment(argumentToGetValueFrom).getValue()); // Add the argument.
				continue;
			}
			ValueAssignment value = binding.getValueAssignment(arg);
			if (value != null) {
				valueTypes.add(arg + value.getValue());
			} else {
				System.out.println("I expected a value for: " + arg);
			}
		}
	}
	
	public List<String> getValueTypes()
	{
		return valueTypes;
	}
	
	public String getLabel()
	{
		return label;
	}
	
	public String getOperator()
	{
		return operator.toString();
	}
	
	private static String getValueTypesOfCounterArgument(String arg)
	{
		Boolean found = false;
		String returnString = new String();
		
		// search from the index of the chars "nu" in the string to the length of the string.
		// If the number is found, set the found to true.
		// Then start looking for the type to which the argument needs to be casted to.
		// This should be the char(s) after the number, so this for-loop sets the returnString to the char(s) after the number.
		for (int i = arg.indexOf("nu"); i < arg.length(); i++)
		{
			if (Character.isDigit(arg.charAt(i)))
			{
				found = true;
			}
			
			if (Character.isLetter(arg.charAt(i)) && found)
			{
				returnString = arg.substring(i, arg.length());
				found = false;
				break;
			}
		}
		
		return returnString;

	}
	
}
