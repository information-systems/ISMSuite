package org.informationsystem.ismsuite.prover.model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Literal;
import org.informationsystem.ismsuite.prover.model.literals.Relation;

public interface FirstOrderLogicWorld {

	/**
	 * @param l
	 * @return true if l is contained in this World
	 */
	boolean contains(Literal l);

	/**
	 * 
	 * @return true if the world does not contain any elements
	 */
	boolean isEmpty();
	
	/////////////////////////////////////////////////////////////////////////////////////
	/// Conjectures
	/////////////////////////////////////////////////////////////////////////////////////
	
	Set<Entry<String, Clause>> getConjectures();
	Clause getConjecture(String name);

	/////////////////////////////////////////////////////////////////////////////////////
	/// Types and Elements
	/////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns whether the world contains the given type
	 * @param type
	 * @return true if the world contains the given type
	 */
	boolean containsType(String type);
	
	/**
	 * Whether an element exists in the current world
	 * @param e
	 * @return true if the element is present in the world
	 */
	boolean containsElement(Element e);
	
	/**
	 * Returns a set with all the types in this world
	 * @return set of type names
	 */
	Set<String> getElementTypes();
	
	/**
	 * Finds the type of an element
	 * 
	 * @param element
	 * @return type name
	 */
	String findTypeFor(String element);
	
	
	/**
	 * Returns an iterator to the elements of the given type.
	 *  
	 * @param type
	 * @return the elements in the specified type
	 */
	Iterator<Element> getElementsIn(String type);
	
	/**
	 * Returns the number of elements in a given type.
	 * 
	 * @param type
	 * @return the size of the type.
	 */
	int elementSize(String type);
	
	
	/// Relations

	int relationSize();
	Iterator<Relation> getRelations();

	/**
	 * @param label
	 * @return all Relation Clauses of "type" label
	 */
	Set<Relation> getRelations(String label);

	/**
	 * 
	 * @return all relation labels present in the world
	 */
	Set<String> getRelationLabels();


	
	/////////////////////////////////////////////////////////////////////////////////////
	/// Proving
	/////////////////////////////////////////////////////////////////////////////////////

	
	/**
	 * Whether the world is valid, i.e., that all conjectures are valid.
	 * 
	 * @return true if all Conjectures in this world are valid
	 */
	boolean isValid();

	/**
	 * 
	 * @return all conjecture labels that are invalid
	 */
	List<String> invalidates();

	/**
	 * Returns a list of all invalid conjectures with an explanation for each conjecture
	 * 
	 * @return all invalid conjectures, including an explanation for each conjecture
	 */
	Map<String, Stack<Clause>> invalidateAndExplain();


}