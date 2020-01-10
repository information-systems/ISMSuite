/**
 */
package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PnidsimulatorFactoryImpl extends EFactoryImpl implements PnidsimulatorFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PnidsimulatorFactory init() {
		try {
			PnidsimulatorFactory thePnidsimulatorFactory = (PnidsimulatorFactory)EPackage.Registry.INSTANCE.getEFactory(PnidsimulatorPackage.eNS_URI);
			if (thePnidsimulatorFactory != null) {
				return thePnidsimulatorFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PnidsimulatorFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PnidsimulatorFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PnidsimulatorPackage.TRANSITION_ACTIVATION_ANNOTATION: return createTransitionActivationAnnotation();
			case PnidsimulatorPackage.PLACE_MARKING_ANNOTATION: return createPlaceMarkingAnnotation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TransitionActivationAnnotation createTransitionActivationAnnotation() {
		TransitionActivationAnnotationImpl transitionActivationAnnotation = new TransitionActivationAnnotationImpl();
		return transitionActivationAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PlaceMarkingAnnotation createPlaceMarkingAnnotation() {
		PlaceMarkingAnnotationImpl placeMarkingAnnotation = new PlaceMarkingAnnotationImpl();
		return placeMarkingAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PnidsimulatorPackage getPnidsimulatorPackage() {
		return (PnidsimulatorPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PnidsimulatorPackage getPackage() {
		return PnidsimulatorPackage.eINSTANCE;
	}

} //PnidsimulatorFactoryImpl
