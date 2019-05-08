package test.org.informationsystem.ismodeler.specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.informationsystem.ismodeler.process.Binding;
import org.informationsystem.ismodeler.process.BoundTransition;
import org.informationsystem.ismodeler.process.MultiSet;
import org.informationsystem.ismodeler.process.ProcessModel;
import org.informationsystem.ismodeler.process.Token;

public class TestProcess implements ProcessModel {
		
	private Map<String, MultiSet<Token>> marking;
	private List<String> places;
	private List<String> transitions;
	
	List<BoundTransition> enabled;
	long counter = 0;
	
	public TestProcess() {
		marking = new HashMap<>();
		MultiSet<Token> tokenset = new MultiSet<>();
		
		// Philosopher 1
		long[] l1 = new long[1];
		l1[0] = counter+1;
		counter += 1;
		tokenset.add(new Token( l1 ) ); 
		// Philosopher 2
		long[] l2 = new long[1];
		l2[0] = counter+1;
		counter +=1;
		tokenset.add(new Token( l2 ) );
		
		marking.put("Philosophers.Philosopher", tokenset);
		marking.put("Philosophers.Human", new MultiSet<Token>());
				
		places = new ArrayList<>();
		places.add("Philosophers.Philosopher");
		places.add("Philosophers.Human");
		
		transitions = new ArrayList<>();
		transitions.add("Philosophers.newPerson");
		transitions.add("Philosophers.newPhilosopher");
		transitions.add("Philosophers.Reads");
		transitions.add("Philosophers.Discuss");
				
		enabled = new ArrayList<>();
		calculateEnabledTransitions();
	}
	

	@Override
	public Map<String, MultiSet<Token>> getCurrentMarking() {
		return marking;
	}

	@Override
	public List<String> getPlaces() {
		return places;
	}

	@Override
	public List<String> getTransitions() {
		return transitions;
	}

	@Override
	public List<BoundTransition> getEnabledTransitions() {
		return enabled;
	}

	@Override
	public boolean fire(BoundTransition transition) {
		// Check if enabled
		if (!enabled.contains(transition)) {
			return false;
		}
		long[] list = new long[1];
		switch(transition.getName()) {
			case "Philosophers.newPerson":
				list[0] = transition.getBinding().get("nu1");
				counter++;
				marking.get("Philosophers.Human").add(new Token(list));
				break;
			case "Philosophers.newPhilosopher":
				list[0] = transition.getBinding().get("nu1");
				counter++;
				marking.get("Philosophers.Philosopher").add(new Token(list));
				break;
			case "Philosophers.Reads":
				break;
			case "Philosophers.Discuss":
				break;
			case "Philosophers.removePhilosopher":
				list[0] = transition.getBinding().get("nu1");
				marking.get("Philosophers.Philosopher").remove(new Token(list));
				break;
		}
		
		calculateEnabledTransitions();
		return true;
	}
	
	private void calculateEnabledTransitions() {
		enabled.clear();
		
		for(Token t: marking.get("Philosophers.Philosopher")) {
			Binding b = new Binding();
			b.set("nu1", counter + 1);
			b.set("p", t.get(0));
			
			enabled.add(new BoundTransition("Philosophers.newPerson", b));			
		}
		
		
		for(Token t: marking.get("Philosophers.Philosopher")) {
			Binding b = new Binding();
			b.set("p", t.get(0));
			
			enabled.add(new BoundTransition("Philosophers.removePhilosopher", b));			
		}
		
		
		// Philosophers.newPhilosopher
		Binding b2 = new Binding();
		b2.set("nu1", counter + 1);
		enabled.add(new BoundTransition("Philosophers.newPhilosopher", b2));
		
		for(Token t1: marking.get("Philosophers.Human")) {
			for (Token t2: marking.get("Philosophers.Philosopher")) {
				Binding b = new Binding();
				b.set("p", t2.get(0));
				b.set("r", t1.get(0));
				enabled.add(new BoundTransition("Philosophers.Reads", b));
				enabled.add(new BoundTransition("Philosophers.Discuss", b));
			}
		}
	}
	
	@Override
	public boolean terminate() {
		return true;
	}
	
	
	
}
