/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Token</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token#getEntity <em>Entity</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getToken()
 * @model
 * @generated
 */
public interface Token extends EObject {
	/**
	 * Returns the value of the '<em><b>Entity</b></em>' containment reference list.
	 * The list contents are of type {@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity</em>' containment reference list.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getToken_Entity()
	 * @model containment="true"
	 * @generated
	 */
	EList<Entity> getEntity();

} // Token
