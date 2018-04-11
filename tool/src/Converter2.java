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

import javax.swing.SwingUtilities;

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
		System.out.println("Waiting for CPN Tools");
		final ProxySimulator ps = pd.getNext();
		
		// Check the model's syntax
		System.out.println("Waiting for syntax check");
		while (ps.getPetriNet() == null)
			Thread.sleep(1000);
		
		// Get the (coloured) petrinet
		final PetriNet petriNet = ps.getPetriNet();

		while (ps.getSimulator() == null)
			Thread.sleep(1000);
		
		// Create a HighLevelSimulator.
		final HighLevelSimulator simulator = ps.getSimulator();
		
		// Create a ModelData unit.
		final ModelData modelData = (ModelData) ModelDataAdapterFactory.getInstance().adapt(petriNet, ModelData.class);
		
		// Get a list of transitions within the model.
		List<Instance<Transition>> transitionInstances = modelData.getAllTransitionInstances();
		
		// Get a list of all places within the model.
		List<Instance<PlaceNode>> places = modelData.getAllPlaceNodeInstances();
		
		// Create a random. This will random chose a transition to fire.
	    Random random = new Random();
	   
	    Multimap<String, String> population = CPNAccessor.mapInitialPopulation(simulator, places);
	    
	    String standardPopulationLocation = "population.ax";
	        
	    // Create the initial population.
	    for (String v : population.values())
	    {
	    	String key = v.substring(v.indexOf("("), v.indexOf(")"));
	    	PopulationAccessor.addToPopulation("fof" + key + ",axiom,(" + v + ")).", 
	    										standardPopulationLocation);	    	
	    }
	    
	    // Create folders to store temporary population and constraint files.
	    new File(System.getProperty("user.dir") + "\\temporaryPopulationFiles").mkdir();
	    new File(System.getProperty("user.dir") + "\\temporaryConstraintFiles").mkdir();
	    
	    File tempPopulationDirectory = new File("temporaryPopulationFiles");
	    File standardPopulationFile  = new File(standardPopulationLocation);
	    List<Binding> validBindings = new ArrayList<Binding>();
	    List<String> validBindingsString = new ArrayList<String>();
	    String bindingToExecute = null;
	    
	    HashMap<String,File> Binding_File_map = new HashMap<String,File>(); 
	    
	    while (BigInteger.valueOf(100).compareTo(simulator.getStep()) != 0)
		{

			List<Instance<Transition>> enabledTransitions = CPNAccessor.getEnabledTransitions(transitionInstances, simulator);

			for (Instance<Transition> Transition : enabledTransitions)
			{

				List<Binding> bindings = simulator.getBindings(Transition);

				for (Binding binding : bindings)
					{	
						
						File tempPopulationFile = File.createTempFile("populationFile", ".ax",tempPopulationDirectory);
						
						Files.copy(standardPopulationFile.toPath(), tempPopulationFile.toPath(),REPLACE_EXISTING);
						
						String bindingString = binding.toString();
						
						String entity = bindingString.substring(bindingString.indexOf("{") + 1, bindingString.indexOf("}")- 1);
						
						entity = entity.replaceAll("[^0-9]+"," ");
						
						List<String> entityList = Arrays.asList(entity.trim().split(" "));
						
						PopulationAccessor.updatePopulation(Transition.toString(), tempPopulationFile.getCanonicalPath(), population,entityList);
						
						if (PopulationAccessor.validate(tempPopulationFile.getCanonicalPath(), "constraints.p") == true)
						{
							validBindingsString.add(binding.toString());
							validBindings.add(binding);
							Binding_File_map.put(binding.toString(), tempPopulationFile);
						}
						else
							tempPopulationFile.delete();
					}
			}
			if (validBindings.isEmpty())
				throw new ArithmeticException("There are no valid transitions/bindings to fire");
			
			UI test2 = new UI(); // Let the constructor do the job
			test2.showUI(validBindingsString);
			 
			 while (test2.defSelectedItem == null | test2.defSelectedItem == "null")
			 {
				 Thread.sleep(1000);
			 }
			 
			bindingToExecute = test2.defSelectedItem;
			System.out.println(bindingToExecute);
			
			//bindingToExecute = validBindings.get(random.nextInt(validBindings.size()));
			
			simulator.execute(CPNAccessor.findBindingByName(bindingToExecute,validBindings) );
			
			validBindings.clear();
			validBindingsString.clear();
			
			Files.copy(Binding_File_map.get(bindingToExecute).toPath(), standardPopulationFile.toPath(),REPLACE_EXISTING);
			
			// Remove all the temporary population files.
			for (File f : Binding_File_map.values() )
					{
						f.delete();
					}
			Binding_File_map.clear();
		}
	    standardPopulationFile.deleteOnExit();
}
	
	
	
	

}