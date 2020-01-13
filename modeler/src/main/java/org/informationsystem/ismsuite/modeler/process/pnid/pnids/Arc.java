/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arc</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc#getInscription <em>Inscription</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getArc()
 * @model
 * @generated
 */
public interface Arc extends org.pnml.tools.epnk.pnmlcoremodel.Arc {
	/**
	 * Returns the value of the '<em><b>Inscription</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inscription</em>' containment reference.
	 * @see #setInscription(VariableInscriptionLabel)
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getArc_Inscription()
	 * @model containment="true"
	 * @generated
	 */
	VariableInscriptionLabel getInscription();

	/**
	 * Sets the value of the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc#getInscription <em>Inscription</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inscription</em>' containment reference.
	 * @see #getInscription()
	 * @generated
	 */
	void setInscription(VariableInscriptionLabel value);

} // Arc
