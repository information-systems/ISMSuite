/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableSequence#getVariable <em>Variable</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getVariableSequence()
 * @model
 * @generated
 */
public interface VariableSequence extends EObject {
	/**
	 * Returns the value of the '<em><b>Variable</b></em>' containment reference list.
	 * The list contents are of type {@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' containment reference list.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getVariableSequence_Variable()
	 * @model containment="true"
	 * @generated
	 */
	EList<Variable> getVariable();

} // VariableSequence
