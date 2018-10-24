import org.cpntools.accesscpn.cosimulation.impl.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.security.auth.Subject;
import javax.swing.SwingUtilities;
import javax.ws.rs.core.MultivaluedMap;

import org.cpntools.accesscpn.cosimulation.*;
import org.cpntools.accesscpn.engine.*;
import org.cpntools.accesscpn.engine.highlevel.*;
import org.cpntools.accesscpn.engine.highlevel.InstancePrinter;
import org.cpntools.accesscpn.engine.highlevel.instance.Binding;
import org.cpntools.accesscpn.engine.highlevel.instance.Instance;
import org.cpntools.accesscpn.engine.highlevel.instance.adapter.ModelData;
import org.cpntools.accesscpn.engine.highlevel.instance.adapter.ModelDataAdapterFactory;
import org.cpntools.accesscpn.engine.highlevel.instance.cpnvalues.CPNValue;
import org.cpntools.accesscpn.engine.proxy.ProxyDaemon;
import org.cpntools.accesscpn.engine.proxy.ProxySimulator;
import org.cpntools.accesscpn.model.*;
//import org.cpntools.accesscpn.model.exporter.CPNtoDot;
import org.cpntools.accesscpn.model.importer.DOMParser;
import org.xml.sax.SAXException;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import org.eclipse.emf.ecore.impl.*;
import org.eclipse.emf.common.*;
import static java.nio.file.StandardCopyOption.*;

public class Converter2 {
	public static void main(String[] args) throws Exception {	
		// Start the proxy
		System.out.println("Starting proxy");
		final ProxyDaemon pd = ProxyDaemon.getDefaultInstance();
		
		// Wait for a CPN Tools client to start and a model to be loaded.
		System.out.println("Start up CPN Tools with the specific net to continue.");
		final ProxySimulator ps = pd.getNext();
		
		// Check the model's syntax
		System.out.println("Waiting for syntax check");
		while (ps.getPetriNet() == null)
			Thread.sleep(500);
		
		// Get the (coloured) petrinet. It is required to create one, even if one is not used.
		final PetriNet petriNet = ps.getPetriNet();

		while (ps.getSimulator() == null)
			Thread.sleep(500);
		
		// Create a HighLevelSimulator.
		final HighLevelSimulator simulator = ps.getSimulator();
		
		// Get a list of transitions within the model.
		List<Instance<Transition>> transitionInstances = simulator.getAllTransitionInstances();
	   
		File specificationFile = new File("specification.txt");
		
		// population list. Will contain the population TPTP statements. 
		//Used to check for uniqueness of statements. And to fill the buckets in the GUI.
	    List<String > population = CPNAccessor.mapInitialPopulation(simulator, specificationFile.getCanonicalPath());
	    
	    // The location of the population file.
	    String standardPopulationLocation = "population.ax";
	    
	    // Clear the population before writing to it.
	    FileWriter fw = new FileWriter(standardPopulationLocation);
	    fw.write("");
	    fw.close();
	    
	    // Create the initial population.
	    CPNAccessor.addInitialPopulationToPopulationFile(simulator, specificationFile.getCanonicalPath(), standardPopulationLocation);
	    
	    
	    // Create folders if they do not already exist, to store temporary population and constraint files.
	    new File(System.getProperty("user.dir") + "\\temporaryPopulationFiles").mkdir();
	    new File(System.getProperty("user.dir") + "\\temporaryConstraintFiles").mkdir();
	     
	    // Create the location (Map) to the temporary population file.
	    File tempPopulationDirectory = new File("temporaryPopulationFiles");
	    
	    // Create the file that will contain the real-time / current population.
	    File standardPopulationFile  = new File(standardPopulationLocation);
	    
	    // Create a list that will contain all the valid bindings.
	    List<Binding> validBindings = new ArrayList<Binding>();
	    List<String> validBindingsString = new ArrayList<String>();
	    
	    // Set the UI to visible.
	    UI.invokeUI(validBindingsString, population, specificationFile.getCanonicalPath());
	    
	    // Create the string that will contain the transition that will be executed.
	    String bindingToExecute = new String();
	    
	    // Create the HashMap that will map a binding on a specific temporary file.
	    HashMap<String,File> Binding_File_map = new HashMap<String,File>(); 
	    
	    // Create the HashMap that will map a binding on a specific List of Strings that contains the population.s
	    HashMap<String,List<String>> Binding_ListString_map = new HashMap<String,List<String>>();
	    	    
	    
	    // Defines the stop condition. i.e. valueOf(N) => N = the amount of allowed transitions executions.
	    while (true)
		{		
	    	// Get the enabled transitions.
			List<Instance<Transition>> enabledTransitions = CPNAccessor.getEnabledTransitions(transitionInstances, simulator);
			
			for (Instance<Transition> Transition : enabledTransitions)
			{
				
				// Get the bindings for the transition.
				List<Binding> bindings = simulator.getBindings(Transition);

				for (Binding binding : bindings)
					{	
						// Create a temporary population file. This will contain the population after a specific transition is executed
						File tempPopulationFile = File.createTempFile("populationFile", ".ax",tempPopulationDirectory);
						tempPopulationFile.deleteOnExit();
						
						// Copy the real-time/current population to the temporary population.
						Files.copy(standardPopulationFile.toPath(), tempPopulationFile.toPath(),REPLACE_EXISTING);
					    
						// Update the population file and get a population multimap for checking uniqueness.
						List<String> updatedTempPopulation = PopulationAccessor.updatePopulation(Transition.toString(), tempPopulationFile.getCanonicalPath(), population, binding, specificationFile.getCanonicalPath());
						
						// Check if there are population statement that are not unique.
						if (PopulationAccessor.checkUniqueness(updatedTempPopulation) == false)
							continue;
						
						// Check if the adjusted population (and therefore the transition) is VALID.
						// If so, add to the list with valid bindings.
						if ( PopulationAccessor.validate(tempPopulationFile.getCanonicalPath(), specificationFile.getCanonicalPath(), "constraints.p") == true)
						{		
							
							validBindingsString.add(binding.toString());
							validBindings.add(binding);				
							
							// Store the binding and the related temporary population file.
							Binding_File_map.put(binding.toString(), tempPopulationFile);
							
							// Store the binding and the related temporary population Multimap			
							Binding_ListString_map.put(binding.toString(), new ArrayList<>(updatedTempPopulation));
							updatedTempPopulation.clear();

						}
						else
						{
							// tempPopulationFile.delete();
							updatedTempPopulation.clear();
						}
							
					}
			}
			if (validBindings.isEmpty())
				throw new java.lang.Error("There are no valid transitions/bindings to fire");
			
			// Update the UI here.
			UI.updateUIList(validBindingsString, population, specificationFile.getCanonicalPath());
			 
			// While no valid binding has been selected or no valid binding has been selected for execution (i.e. after one pressed the execute b
			 while (UI.bindingToExecute == null | UI.selectedBinding == null)
			 {
				Thread.sleep(500);
			 }
			
			// Get the binding to execute.
			bindingToExecute = UI.bindingToExecute;
			
			// Reset the binding to execute and the selected binding.
			// And set the check if the binding is executed to false.
			UI.bindingToExecute = null;
			UI.selectedBinding = null;
			//UI.bindingExecuted  = false;
			
			// Execute the selected transition.
			simulator.execute(CPNAccessor.findBindingByName(bindingToExecute,validBindings) );
			
			// Clear the lists with valid bindings.
			validBindings.clear();
			validBindingsString.clear();
			
			// Overwrite the old real-time/current population with the population that relates to the executed valid binding.
			Files.copy(Binding_File_map.get(bindingToExecute).toPath(), standardPopulationFile.toPath(),REPLACE_EXISTING);
			
			// Overwrite the old real-time/current population multimap with the population multimap that relates to the executed valid binding.	
			population.removeAll(population);
			population = Binding_ListString_map.get(bindingToExecute);
			
			// Remove all the temporary population files
			for (File f : Binding_File_map.values() )
					{
						// f.delete();
					}
			
			// Clear the binding to temporary file hashmap.
			Binding_File_map.clear();
			
			// Clear the binding to multimap hashmap
			Binding_ListString_map.clear();
		}
	  
}
	
	
	
	

}