/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place#getType <em>Type</em>}</li>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place#getInitialMarking <em>Initial Marking</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getPlace()
 * @model
 * @generated
 */
public interface Place extends org.pnml.tools.epnk.pnmlcoremodel.Place {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' containment reference.
	 * @see #setType(EntityTypeLabel)
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getPlace_Type()
	 * @model containment="true"
	 * @generated
	 */
	EntityTypeLabel getType();

	/**
	 * Sets the value of the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place#getType <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' containment reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(EntityTypeLabel value);

	/**
	 * Returns the value of the '<em><b>Initial Marking</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Marking</em>' containment reference.
	 * @see #setInitialMarking(PNIDMarking)
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getPlace_InitialMarking()
	 * @model containment="true"
	 * @generated
	 */
	PNIDMarking getInitialMarking();

	/**
	 * Sets the value of the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place#getInitialMarking <em>Initial Marking</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Marking</em>' containment reference.
	 * @see #getInitialMarking()
	 * @generated
	 */
	void setInitialMarking(PNIDMarking value);

} // Place
