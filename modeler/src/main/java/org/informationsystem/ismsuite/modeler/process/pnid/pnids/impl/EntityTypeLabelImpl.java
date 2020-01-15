/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeLabel;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage;
import org.informationsystem.ismsuite.modeler.process.util.VariableSequenceParser;
import org.pnml.tools.epnk.structuredpntypemodel.impl.StructuredLabelImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity Type Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl.EntityTypeLabelImpl#getStructure <em>Structure</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EntityTypeLabelImpl extends StructuredLabelImpl implements EntityTypeLabel {
	/**
	 * The cached value of the '{@link #getStructure() <em>Structure</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStructure()
	 * @generated
	 * @ordered
	 */
	protected EntityTypeSequence structure;
	
	/**
	 * @generated NOT
	 * This method parses a string to retrieve the entity types as a comma-separated list.
	 */
	@Override
	public EObject parse(String input) {
		EntityTypeSequence seq = PnidsFactory.eINSTANCE.createEntityTypeSequence();
		/*
		for(String s: SequenceParser.getInstance().parse(input)) {
			EntityType e = PnidsFactory.eINSTANCE.createEntityType();
			e.setText(s);
			seq.getEntityType().add(e);
		}
		*/
		return seq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityTypeLabelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnidsPackage.Literals.ENTITY_TYPE_LABEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EntityTypeSequence getStructure() {
		return structure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStructure(EntityTypeSequence newStructure, NotificationChain msgs) {
		EntityTypeSequence oldStructure = structure;
		structure = newStructure;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE, oldStructure, newStructure);
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
	public void setStructure(EntityTypeSequence newStructure) {
		if (newStructure != structure) {
			NotificationChain msgs = null;
			if (structure != null)
				msgs = ((InternalEObject)structure).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE, null, msgs);
			if (newStructure != null)
				msgs = ((InternalEObject)newStructure).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE, null, msgs);
			msgs = basicSetStructure(newStructure, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE, newStructure, newStructure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE:
				return basicSetStructure(null, msgs);
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
			case PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE:
				return getStructure();
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
			case PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE:
				setStructure((EntityTypeSequence)newValue);
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
			case PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE:
				setStructure((EntityTypeSequence)null);
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
			case PnidsPackage.ENTITY_TYPE_LABEL__STRUCTURE:
				return structure != null;
		}
		return super.eIsSet(featureID);
	}

} //EntityTypeLabelImpl
