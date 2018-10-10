import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.cpntools.accesscpn.engine.highlevel.HighLevelSimulator;
import org.cpntools.accesscpn.engine.highlevel.instance.Binding;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.model.PlaceNode;
import org.cpntools.accesscpn.model.Transition;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class CPNAccessor {

	public static List<Instance<Transition>> getEnabledTransitions(List<Instance<Transition>> TransitionList, HighLevelSimulator simulator ) throws Exception
	{
		// Create a list that keeps the enabled transitions.
		List<Instance<Transition>> EnabledTransitions = new ArrayList<Instance<Transition>>();
		
		// Check if every transition is enabled. If it is, add to the EnabledTransitions list.
		for (Instance<Transition> transitionInstance : TransitionList) 
		{	
			if (simulator.isEnabled(transitionInstance) == true)
				EnabledTransitions.add(transitionInstance);	
		}
		return EnabledTransitions;	
	}
	
	
	
	// Find a place by its name.
	public static Instance<PlaceNode> findPlaceByName(String name, List<Instance<PlaceNode>> allPlaces)
	{	
		for (Instance<PlaceNode> p : allPlaces)
		{
			if (p.toString().equals(name))
			{
				return p;
			}
		}
		throw new java.lang.Error("No place with the name: " + name + " could be found." + " All possible places are: " + allPlaces);
	}
	
	// Find a transition by its name.
	public static Instance<Transition> findTransitionByName(String name, List<Instance<Transition>> allTransitions)
	{
		for (Instance<Transition> t : allTransitions)
		{
			if (t.toString().equals(name))
			{
				return t;
			}
		}
		throw new java.lang.Error("No transition with this name could be found:" + name + ". All possible transitions are: " + allTransitions);
	}
	
	// find a binding by its name.
	public static Binding findBindingByName(String name, List<Binding> allBindings )
	{
		for (Binding b : allBindings)
		{
			if (b.toString().equals(name))
			{
				return b;
			}
		}
		throw new java.lang.Error("No binding with this name could be found: " + name + ". All possible bindings are: " + allBindings);
	}
	
	// Get the initial population as a List<String>
	public static List<String> mapInitialPopulation(HighLevelSimulator simulator, String specificationFilePath) throws Exception
	{
		List<String> initialPopulation = new ArrayList<String>();
		List<Instance<PlaceNode>> places = simulator.getAllPlaceInstances();
		
		List<String> initialPopulationShortStatements = SpecificationFileAccessor.getIntialPopulationMarking(specificationFilePath, simulator, places).getShortEntityPopulationList();
		
		for(String initialPopulationMarkingStatement : initialPopulationShortStatements)
		{
			initialPopulation.add(initialPopulationMarkingStatement);
		}
		
		return initialPopulation;
	}
	
	public static void addInitialPopulationToPopulationFile(HighLevelSimulator simulator, String specificationFilePath, String populationFilePath) throws Exception
	{
		List<Instance<PlaceNode>> places = simulator.getAllPlaceInstances();
		
		// Get all the initial population marking statements. This already contains the complete TPTP statement (Here: Tff(name,axiom, statement) )
		List<String> initialPopulationMarkingStatements = SpecificationFileAccessor.getIntialPopulationMarking(specificationFilePath, simulator, places).getInitialPopulationMarkingStatements();
		
		// Add each statement to the file.
		for(String initialPopulationMarkingStatement : initialPopulationMarkingStatements)
		{
			PopulationAccessor.addToPopulation(initialPopulationMarkingStatement, populationFilePath);
		}
		
		
		
		List<populationLabel> labels = SpecificationFileAccessor.getLabels(specificationFilePath);
		
		// Add the label-type statements. I.e. the statements that state which labels are possible in the population and of which types they consist.
		for (populationLabel label : labels)
		{
			String typesInEntity = new String();
			
			// Implode the strings with '*' as seperator.
			for (int i = 0; i < label.getRequiredTypes().size(); i++)
			{
				// If i in the last in the list. Do not add the seperator.
				if (i == label.getRequiredTypes().size() - 1)
				{
					typesInEntity += label.getRequiredTypes().get(i);
					break;
				}
				
				typesInEntity += label.getRequiredTypes().get(i) + " * ";
			}
			PopulationAccessor.addToPopulation("tff(" + label.getName() + "_type" + ",type," + label.getName() + ": " + "(" + typesInEntity + ")" + " > $o).", populationFilePath);
		}
		
		// Add the statements that specify which types are possible in the population.
		for (populationType type : SpecificationFileAccessor.getTypes(specificationFilePath))
		{
			PopulationAccessor.addToPopulation("tff(" + type.getName() + "_type" + ",type," + type.getRepresentativeChars() + ": $tType).", populationFilePath);
		}
		
	}
}
