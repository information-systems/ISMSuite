/**
 */
package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PnidsimulatorPackage
 * @generated
 */
public interface PnidsimulatorFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PnidsimulatorFactory eINSTANCE = org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PnidsimulatorFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Transition Activation Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition Activation Annotation</em>'.
	 * @generated
	 */
	TransitionActivationAnnotation createTransitionActivationAnnotation();

	/**
	 * Returns a new object of class '<em>Place Marking Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Place Marking Annotation</em>'.
	 * @generated
	 */
	PlaceMarkingAnnotation createPlaceMarkingAnnotation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PnidsimulatorPackage getPnidsimulatorPackage();

} //PnidsimulatorFactory
