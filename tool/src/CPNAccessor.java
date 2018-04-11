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
	
	// This method is currently not used and needs to be refactored.
	public static List<Instance<Transition>> GetFireableTransitions(List<Instance<Transition>> TransitionList, HighLevelSimulator simulator) throws Exception
	{
		// Get the enabled transitions
		List<Instance<Transition>> EnabledTransitions = getEnabledTransitions(TransitionList, simulator);
		
		// Create list that will contain the accepted transitions.
		List<Instance<Transition>> AcceptedTransitions = new ArrayList<Instance<Transition>>();
		
		// For every enabled transition, check if it is also accepted.
		for (Instance<Transition> EnabledTransitionInstance : EnabledTransitions)
		{
			// Assume that filename is the same as the transition name.
			String filename = EnabledTransitionInstance.toString();
			
			// Check if the transition is valid.		
			// If the transition is not valid, continue to the next transition.
			if (CheckValid(filename) == false)
			{
				continue;	
			}
			AcceptedTransitions.add(EnabledTransitionInstance);								
		}
		if (AcceptedTransitions.isEmpty())
			throw new java.lang.Error("No accepted transitions could be found");
		
		return AcceptedTransitions;	
	}
	
	public static boolean CheckValid(String filename) throws Exception
	{
		boolean result = false;
		Runtime rt = Runtime.getRuntime();
		
		// The filelocation should point to the location that contains all the ptpt files.
		String command_and_filelocation = "E\\PROVER\\eprover --auto " + filename;
		
	
		// Execute the eprover command. Add the filename to the command.
		Process pr = rt.exec(command_and_filelocation);
		// Get the code. 0 = Proof found, 1 = No proof found.		
		int code = pr.waitFor(); 

		/* BufferedReader stdInput = new BufferedReader(new 
			     InputStreamReader(pr.getInputStream()));

			BufferedReader stdError = new BufferedReader(new 
			     InputStreamReader(pr.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) {
			    System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
			    System.out.println(s);
			}
		*/
		
		// Proof has been found.
		if (code == 0)
			result = true;
			
		// Proof has not been found (code = 1) or there is an error (other code than 0 or 1).
		else
			result = false;
		
		return result;
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
		throw new java.lang.Error("No place with this name  could be found.");
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
		throw new java.lang.Error("No transition with this name could be found.");
	}
	
	public static Binding findBindingByName(String name, List<Binding> allBindings )
	{
		for (Binding b : allBindings)
		{
			if (b.toString().equals(name))
			{
				return b;
			}
		}
		throw new java.lang.Error("No binding with this name could be found.");
	}
	
	public static Multimap<String,String> mapInitialPopulation(HighLevelSimulator simulator, List<Instance<PlaceNode>> places) throws Exception
	{
		HashMultimap<String, String> initialPopulation = HashMultimap.create();
		
		List<String> tracks 		= new ArrayList<String>(Arrays.asList(simulator.getMarking(findPlaceByName("Student.Education_Track",places)).split("\\++")));
		List<String> administrators = new ArrayList<String>(Arrays.asList(simulator.getMarking(findPlaceByName("Student.Administrator",places)).split("\\++")));
		List<String> courses 		= new ArrayList<String>(Arrays.asList(simulator.getMarking(findPlaceByName("Student.Course",places)).split("\\++")));
		List<String> managers 		= new ArrayList<String>(Arrays.asList(simulator.getMarking(findPlaceByName("Student.Manager",places)).split("\\++")));
		List<String> lecturers		= new ArrayList<String>(Arrays.asList(simulator.getMarking(findPlaceByName("Student.Lecturer",places)).split("\\++")));
		
		// Add the track IDs as key with the related axiom.
		for (String t : tracks)
		{
			String track = t.substring(t.indexOf("`") + 1,t.length());
			initialPopulation.put(track, "track(s" + track + ")");
		}
		
		// Add the administrator IDs as key with the related axiom.
		for (String a : administrators)
		{
			String administrator = a.substring(a.indexOf("`") + 1,a.length());
			initialPopulation.put(administrator, "administrator(s" + administrator + ")");
			initialPopulation.put(administrator, "person(s" + administrator + ")");
		}
		
		// Add the courses IDs as key with the related axiom.
		for (String c : courses)
		{
			String course = c.substring(c.indexOf("`") + 1,c.length());
			initialPopulation.put(course, "course(s" + course + ")");
		}
		
		// Add the manager IDs as key with the related axiom.
		for (String m : managers)
		{
			String manager = m.substring(m.indexOf("`") + 1,m.length());
			initialPopulation.put(manager, "manager(s" + manager + ")");
			initialPopulation.put(manager, "person(s" + manager + ")");
		}		
		
		// Add the lecturer IDs as key with the related axioms.
		for (String l : lecturers)
		{
			String lecturer = l.substring(l.indexOf("`") + 1,l.length());
			initialPopulation.put(lecturer, "lecturer(s" + lecturer + ")");
			initialPopulation.put(lecturer, "person(s" + lecturer + ")");
		}
		
		return initialPopulation;
	}
}
