/**
 */
package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PnidsimulatorPackage;

import org.pnml.tools.epnk.annotations.netannotations.impl.ObjectAnnotationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Place Marking Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PlaceMarkingAnnotationImpl#getText <em>Text</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PlaceMarkingAnnotationImpl extends ObjectAnnotationImpl implements PlaceMarkingAnnotation {
	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final int TEXT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected int text = TEXT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlaceMarkingAnnotationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnidsimulatorPackage.Literals.PLACE_MARKING_ANNOTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getText() {
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setText(int newText) {
		int oldText = text;
		text = newText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PnidsimulatorPackage.PLACE_MARKING_ANNOTATION__TEXT, oldText, text));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAnnotationAttribute() {
		EClass eClass = this.eClass();
		Object feature = eClass.getEStructuralFeature("text");
		if (feature instanceof EAttribute) {
			return (EAttribute) feature;
		}
		feature = eClass.getEStructuralFeature("value");
		if (feature instanceof EAttribute) {
			return (EAttribute) feature;
		}
				
		for (EAttribute attribute: eClass.getEAllAttributes()) {
			return attribute;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PnidsimulatorPackage.PLACE_MARKING_ANNOTATION__TEXT:
				return getText();
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
			case PnidsimulatorPackage.PLACE_MARKING_ANNOTATION__TEXT:
				setText((Integer)newValue);
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
			case PnidsimulatorPackage.PLACE_MARKING_ANNOTATION__TEXT:
				setText(TEXT_EDEFAULT);
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
			case PnidsimulatorPackage.PLACE_MARKING_ANNOTATION__TEXT:
				return text != TEXT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (text: ");
		result.append(text);
		result.append(')');
		return result.toString();
	}

} //PlaceMarkingAnnotationImpl
