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

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Type Sequence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeSequenceImpl#getEntityType <em>Entity Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityTypeSequenceImpl extends EObjectImpl implements EntityTypeSequence {
	/**
	 * The cached value of the '{@link #getEntityType() <em>Entity Type</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntityType()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityType> entityType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityTypeSequenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnidsPackage.Literals.ENTITY_TYPE_SEQUENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EntityType> getEntityType() {
		if (entityType == null) {
			entityType = new EObjectContainmentEList<EntityType>(EntityType.class, this, PnidsPackage.ENTITY_TYPE_SEQUENCE__ENTITY_TYPE);
		}
		return entityType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PnidsPackage.ENTITY_TYPE_SEQUENCE__ENTITY_TYPE:
				return ((InternalEList<?>)getEntityType()).basicRemove(otherEnd, msgs);
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
			case PnidsPackage.ENTITY_TYPE_SEQUENCE__ENTITY_TYPE:
				return getEntityType();
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
			case PnidsPackage.ENTITY_TYPE_SEQUENCE__ENTITY_TYPE:
				getEntityType().clear();
				getEntityType().addAll((Collection<? extends EntityType>)newValue);
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
			case PnidsPackage.ENTITY_TYPE_SEQUENCE__ENTITY_TYPE:
				getEntityType().clear();
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
			case PnidsPackage.ENTITY_TYPE_SEQUENCE__ENTITY_TYPE:
				return entityType != null && !entityType.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EntityTypeSequenceImpl
