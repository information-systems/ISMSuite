/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Token Bag</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.TokenBagImpl#getToken <em>Token</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TokenBagImpl extends EObjectImpl implements TokenBag {
	/**
	 * The cached value of the '{@link #getToken() <em>Token</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToken()
	 * @generated
	 * @ordered
	 */
	protected EList<Token> token;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TokenBagImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnidsPackage.Literals.TOKEN_BAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Token> getToken() {
		if (token == null) {
			token = new EObjectContainmentEList<Token>(Token.class, this, PnidsPackage.TOKEN_BAG__TOKEN);
		}
		return token;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PnidsPackage.TOKEN_BAG__TOKEN:
				return ((InternalEList<?>)getToken()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PnidsPackage.TOKEN_BAG__TOKEN:
				return getToken();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PnidsPackage.TOKEN_BAG__TOKEN:
				getToken().clear();
				getToken().addAll((Collection<? extends Token>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PnidsPackage.TOKEN_BAG__TOKEN:
				getToken().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PnidsPackage.TOKEN_BAG__TOKEN:
				return token != null && !token.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TokenBagImpl
