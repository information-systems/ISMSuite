/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;

import org.pnml.tools.epnk.structuredpntypemodel.StructuredLabel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable Inscription Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableInscriptionLabel#getStructure <em>Structure</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getVariableInscriptionLabel()
 * @model
 * @generated
 */
public interface VariableInscriptionLabel extends StructuredLabel {
	/**
	 * Returns the value of the '<em><b>Structure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Structure</em>' containment reference.
	 * @see #setStructure(VariableSequence)
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getVariableInscriptionLabel_Structure()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VariableSequence getStructure();

	/**
	 * Sets the value of the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.VariableInscriptionLabel#getStructure <em>Structure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Structure</em>' containment reference.
	 * @see #getStructure()
	 * @generated
	 */
	void setStructure(VariableSequence value);

} // VariableInscriptionLabel
