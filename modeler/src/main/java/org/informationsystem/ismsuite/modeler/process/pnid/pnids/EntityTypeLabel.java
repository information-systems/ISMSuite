/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;

import org.pnml.tools.epnk.structuredpntypemodel.StructuredLabel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Type Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel#getStructure <em>Structure</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getEntityTypeLabel()
 * @model
 * @generated
 */
public interface EntityTypeLabel extends StructuredLabel {
	/**
	 * Returns the value of the '<em><b>Structure</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Structure</em>' containment reference.
	 * @see #setStructure(EntityTypeSequence)
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getEntityTypeLabel_Structure()
	 * @model containment="true"
	 * @generated
	 */
	EntityTypeSequence getStructure();

	/**
	 * Sets the value of the '{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel#getStructure <em>Structure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Structure</em>' containment reference.
	 * @see #getStructure()
	 * @generated
	 */
	void setStructure(EntityTypeSequence value);

} // EntityTypeLabel
