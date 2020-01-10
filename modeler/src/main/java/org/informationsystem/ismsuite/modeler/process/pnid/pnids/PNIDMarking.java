/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;

import org.pnml.tools.epnk.structuredpntypemodel.StructuredLabel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PNID Marking</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking#getStructure <em>Structure</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getPNIDMarking()
 * @model
 * @generated
 */
public interface PNIDMarking extends StructuredLabel {
	/**
	 * Returns the value of the '<em><b>Structure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Structure</em>' containment reference.
	 * @see #setStructure(TokenBag)
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getPNIDMarking_Structure()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TokenBag getStructure();

	/**
	 * Sets the value of the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking#getStructure <em>Structure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Structure</em>' containment reference.
	 * @see #getStructure()
	 * @generated
	 */
	void setStructure(TokenBag value);

} // PNIDMarking
