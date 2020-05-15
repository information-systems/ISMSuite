package org.informationsystem.ismsuite.modeler.process.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable;
import org.pnml.tools.epnk.helpers.FlatAccess;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;
import org.pnml.tools.epnk.pnmlcoremodel.Transition;

public class PNIDSyntaxChecker {

	public static boolean checkPetrinet(PetriNet petrinet) {
		if (!(petrinet.getType() instanceof PNID)) {
			return false;
		}
		
		return giveErrorsFor(petrinet).isEmpty();
	}
	
	public static List<SyntaxError> giveErrorsFor(PetriNet petrinet) {
		List<SyntaxError> errors = new ArrayList<>();
		
		FlatAccess flat = FlatAccess.getFlatAccess(petrinet);
		
		for (org.pnml.tools.epnk.pnmlcoremodel.Place p : flat.getPlaces()) {
			if (p instanceof Place) {
				giveErrorsFor(flat, (Place) p, errors); 
			} else {
				// Notice that in theory, this error should not be possible.
				errors.add(new SyntaxError(p,"Unexpected Class"));
			}
		}
		
		for (org.pnml.tools.epnk.pnmlcoremodel.Transition t: flat.getTransitions()) {
			giveErrorsFor(flat, t, errors);
		}
		
		return errors;
	}
	
	/**
	 * Checks if for place p each connected arc has a proper inscription, i.e. the size of each
	 * arc inscription should equal the size of the place type.
	 * @param p
	 * @return
	 */
	public static boolean checkPlace(FlatAccess flat, Place p) {
		List<SyntaxError> errors = new ArrayList<>();
		giveErrorsFor(flat, p, errors);
		
		return errors.isEmpty();
	}
	
	private static void giveErrorsFor(FlatAccess flat, Place p, List<SyntaxError> errors) {
		if ((p.getId() == null) || p.getId().equals("")) {
			errors.add(new MissingIDError(p));
		}
			
		int size = 0;
		if ((p.getType() != null) && (p.getType().getStructure() != null) ) {
			size = p.getType().getStructure().getEntityType().size();
		}
		
		// Check each token whether it has the same length
		// As soon as we find such a token, we stop looking...
		if (p.getInitialMarking()!= null && p.getInitialMarking().getStructure() != null) {
			for(Token t: p.getInitialMarking().getStructure().getToken() ) {
				if (t.getEntity() == null) {
					if (size > 0) {
						errors.add(new WrongTokenTypeError(p));
						break;
					}
				} else {
					if (t.getEntity().size() != size) {
						errors.add(new WrongTokenTypeError(p));
						break;
					}
				}
			}
		}
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc a: flat.getIn(p)) {
			if (a instanceof Arc) {
				validatePlaceArc((Arc) a, size, errors);
			}
		}
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc a: flat.getOut(p)) {
			if (a instanceof Arc) {
				validatePlaceArc((Arc) a, size, errors);
			}
		}
	}
	
	private static void validatePlaceArc(Arc arc, int expectedSize, List<SyntaxError> errors) {
		int arcsize = 0;
		if (arc.getInscription() != null && arc.getInscription().getStructure() != null) {
			arcsize = arc.getInscription().getStructure().getVariable().size();
		}
		if (arcsize != expectedSize) {
			// add an error
			errors.add(new ArcInscriptionLengthError(arc, expectedSize, arcsize));
		};
	}
	
	/**
	 * Checks for a transition if all arc inscriptions confirm on the variable typing 
	 * @param t
	 * @return
	 */
	public static boolean checkTransition(FlatAccess flat, Transition t) {
		List<SyntaxError> errors = new ArrayList<>();
		giveErrorsFor(flat, t, errors);
		
		return errors.isEmpty();
	}
	
	private static void giveErrorsFor(FlatAccess flat, Transition t, List<SyntaxError> errors) {
		if ((t.getId() == null) || t.getId().equals("")) {
			errors.add(new MissingIDError(t));
		}
		Map<String, String> varTypes = new HashMap<>();
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc a: flat.getIn(t)) {
			if (a instanceof Arc) {
				giveErrorsFor(flat, (Arc) a, t, varTypes, errors);
			}
		}
		
		for(org.pnml.tools.epnk.pnmlcoremodel.Arc a: flat.getOut(t)) {
			if (a instanceof Arc) {
				giveErrorsFor(flat, (Arc) a, t, varTypes, errors);
			}
		}
	}
	
	
	/**
	 * Checks an arc on the varTypes hash map. If the var is not there, it is added
	 * Otherwise, the type should match.
	 * @param a
	 * @param p
	 * @param varTypes
	 * @param errors 
	 * @return
	 */
	private static void giveErrorsFor(FlatAccess flat, Arc a, Transition t, Map<String, String> varTypes, List<SyntaxError> errors) {
		
		
		org.pnml.tools.epnk.pnmlcoremodel.Place p = null;
		if (a.getTarget() instanceof PlaceNode ) {
			p = flat.resolve((PlaceNode) a.getTarget());
		} else if (a.getSource() instanceof PlaceNode ) {
			p = flat.resolve((PlaceNode) a.getSource());
		}
		
		if (p == null) {
			errors.add(new MissingReference(a));
			return;
		}
		
		Place place = null;
		EList<EntityType> types = null;
		
		if (p instanceof Place) {				
			place = (Place) p;
			
			if ((place.getType()!= null) && ( place.getType().getStructure() != null) ) {
				types = place.getType().getStructure().getEntityType();
			}
		}
		
		// If the arc inscription is not set, the place type has to be empty
		if ((a.getInscription() == null) || (a.getInscription().getStructure() == null) || (a.getInscription().getStructure().getVariable() == null) ) {
			if(types != null) {
				errors.add(new ArcInscriptionTypeError(t));
			}
			return;
		}
		
		// If the place does not contain any types, the variable list should be empty
		if (types == null) {
			if(!a.getInscription().getStructure().getVariable().isEmpty()) {
				errors.add(new ArcInscriptionTypeError(t));
			}
			return;
		}
		
		assert (place != null);
		
		// EList<Variable> variables = a.getInscription().getStructure().getVariable();
		
		// Extra check on sizes, note that this error is already handled :-)
		if (a.getInscription().getStructure().getVariable().size() != types.size()) {
			return;
		};
		
		// Now take the corresponding elements per index, and check with the varTypes
		// If the var is not yet in, add it.
		for(int i = 0; i < a.getInscription().getStructure().getVariable().size(); i++) {
			String var = a.getInscription().getStructure().getVariable().get(i).getText();
			String typ = types.get(i).getText();
			
			if (varTypes.containsKey(var)) {
				if (!varTypes.get(var).equals(typ)) {
					errors.add(new ArcInscriptionTypeError(t));
					return;
				};
			} else {
				varTypes.put(var, typ);
			}
		}
	}
	
}