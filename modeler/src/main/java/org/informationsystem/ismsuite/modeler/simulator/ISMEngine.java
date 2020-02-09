package org.informationsystem.ismsuite.modeler.simulator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.informationsystem.ismsuite.pnidprocessor.petrinet.MarkedPetriNet;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.informationsystem.ismsuite.processengine.process.Token;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.model.OperationException;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;

public class ISMEngine {

	private World initialWorld;
	private Specification specification;
	private MarkedPetriNet petriNet;
	
	public ISMEngine(World world, Specification specification, MarkedPetriNet net) {
		this.initialWorld = world;
		this.specification = specification;
		this.petriNet = net;
	}
	
	
	
	private World currentWorld;
	
	private String error;
	
	public String getError() {
		return error;
	}
	
	public boolean initializeWorld() {
		currentWorld = (World) initialWorld.clone();
		
		String msg = "";
		
		System.out.println(specification);
		
		for(Entry<String, MultiSet<Token>> bag : petriNet.getMarking().map().entrySet()) {
			Transaction transaction = specification.getPlace(bag.getKey());
			if (transaction != null && !bag.getValue().isEmpty()) {
				// msg += "Transaction found for: " + bag.getKey() + "\n"; 
				System.out.println("Transaction: " + bag.getKey());
				for(Token token : bag.getValue().getUnique()) {
					if (token.size() == transaction.variableSize()) {
						Map<Variable, Element> valuation = new HashMap<>();
						
						org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place p = petriNet.getPlace(bag.getKey());
						
						for(int i = 0 ; i < token.size() ; i++) {
							String varLabel = p.getType().getStructure().getEntityType().get(i).getText();
							Variable var = transaction.getVariable(varLabel);
							
							Element elem = new Element(token.get(i), var.getType());
							System.out.print("nr: " + i + ": ");
							System.out.print(var.getLabel());
							System.out.print(" -> ");
							System.out.println(elem.getLabel());
							
							valuation.put(var, elem);
						}
						try {
							if (!transaction.apply(valuation, currentWorld)) {
								msg += "Something went wrong in execution " + bag.getKey() + "\n";
							}
						} catch(OperationException e) {
							msg += "* Error on place " + bag.getKey() +":\n"; 
							msg += e.getMessage();
							msg += "\n";
						}
					} else {
						msg += "* Variable signature of place " + bag.getKey() + " is incorrect.\n";
					}
				}
			} else {
				msg += "* No transaction found for place: " + bag.getKey() + "\n";
			}
		}
	}
	
	
}
