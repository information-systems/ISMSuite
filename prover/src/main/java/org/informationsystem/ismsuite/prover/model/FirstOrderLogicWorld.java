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

	Set<Entry<String, Clause>> getConjectures();

	boolean containsType(String type);

	Iterator<Element> getElementsIn(String type);

	int elementSize(String type);

	Iterator<String> getElementLabels();

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

	/**
	 * @param l
	 * @return true if l is contained in this World
	 */
	boolean contains(Literal l);

	Clause getConjecture(String name);

	/**
	 * 
	 * @return true if all Conjectures in this world are valid
	 */
	boolean isValid();

	/**
	 * 
	 * @return all conjecture labels that are invalid
	 */
	List<String> invalidates();

	Map<String, Stack<Clause>> invalidateAndExplain();

	String toString();

	boolean isEmpty();

	Set<String> getElementTypes();
	
	String findTypeFor(String element);

}