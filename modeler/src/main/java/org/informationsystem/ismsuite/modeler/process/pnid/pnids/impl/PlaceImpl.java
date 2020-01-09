/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNIDMarking;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Place;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PlaceImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.PlaceImpl#getInitialMarking <em>Initial Marking</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlaceImpl extends org.pnml.tools.epnk.pnmlcoremodel.impl.PlaceImpl implements Place {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EntityTypeLabel type;

	/**
	 * The cached value of the '{@link #getInitialMarking() <em>Initial Marking</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialMarking()
	 * @generated
	 * @ordered
	 */
	protected PNIDMarking initialMarking;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnidsPackage.Literals.PLACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntityTypeLabel getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(EntityTypeLabel newType, NotificationChain msgs) {
		EntityTypeLabel oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PnidsPackage.PLACE__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(EntityTypeLabel newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PnidsPackage.PLACE__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PnidsPackage.PLACE__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnidsPackage.PLACE__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PNIDMarking getInitialMarking() {
		return initialMarking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitialMarking(PNIDMarking newInitialMarking, NotificationChain msgs) {
		PNIDMarking oldInitialMarking = initialMarking;
		initialMarking = newInitialMarking;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PnidsPackage.PLACE__INITIAL_MARKING, oldInitialMarking, newInitialMarking);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInitialMarking(PNIDMarking newInitialMarking) {
		if (newInitialMarking != initialMarking) {
			NotificationChain msgs = null;
			if (initialMarking != null)
				msgs = ((InternalEObject)initialMarking).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PnidsPackage.PLACE__INITIAL_MARKING, null, msgs);
			if (newInitialMarking != null)
				msgs = ((InternalEObject)newInitialMarking).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PnidsPackage.PLACE__INITIAL_MARKING, null, msgs);
			msgs = basicSetInitialMarking(newInitialMarking, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnidsPackage.PLACE__INITIAL_MARKING, newInitialMarking, newInitialMarking));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PnidsPackage.PLACE__TYPE:
				return basicSetType(null, msgs);
			case PnidsPackage.PLACE__INITIAL_MARKING:
				return basicSetInitialMarking(null, msgs);
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
			case PnidsPackage.PLACE__TYPE:
				return getType();
			case PnidsPackage.PLACE__INITIAL_MARKING:
				return getInitialMarking();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PnidsPackage.PLACE__TYPE:
				setType((EntityTypeLabel)newValue);
				return;
			case PnidsPackage.PLACE__INITIAL_MARKING:
				setInitialMarking((PNIDMarking)newValue);
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
			case PnidsPackage.PLACE__TYPE:
				setType((EntityTypeLabel)null);
				return;
			case PnidsPackage.PLACE__INITIAL_MARKING:
				setInitialMarking((PNIDMarking)null);
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
			case PnidsPackage.PLACE__TYPE:
				return type != null;
			case PnidsPackage.PLACE__INITIAL_MARKING:
				return initialMarking != null;
		}
		return super.eIsSet(featureID);
	}

} //PlaceImpl
