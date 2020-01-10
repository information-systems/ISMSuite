/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity Type Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence#getEntityType <em>Entity Type</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getEntityTypeSequence()
 * @model
 * @generated
 */
public interface EntityTypeSequence extends EObject {
	/**
	 * Returns the value of the '<em><b>Entity Type</b></em>' containment reference list.
	 * The list contents are of type {@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Type</em>' containment reference list.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getEntityTypeSequence_EntityType()
	 * @model containment="true"
	 * @generated
	 */
	EList<EntityType> getEntityType();

} // EntityTypeSequence
