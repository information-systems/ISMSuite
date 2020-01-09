/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Token Bag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag#getToken <em>Token</em>}</li>
 * </ul>
 *
 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getTokenBag()
 * @model
 * @generated
 */
public interface TokenBag extends EObject {
	/**
	 * Returns the value of the '<em><b>Token</b></em>' containment reference list.
	 * The list contents are of type {@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Token</em>' containment reference list.
	 * @see org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage#getTokenBag_Token()
	 * @model containment="true"
	 * @generated
	 */
	EList<Token> getToken();

} // TokenBag
