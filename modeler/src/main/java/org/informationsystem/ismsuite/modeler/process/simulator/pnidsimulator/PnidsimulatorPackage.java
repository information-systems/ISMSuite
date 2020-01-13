/**
 */
package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.pnml.tools.epnk.annotations.netannotations.NetannotationsPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PnidsimulatorFactory
 * @model kind="package"
 * @generated
 */
public interface PnidsimulatorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "pnidsimulator";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.pnml.tools/epnk/applications/pnids/simulator";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "pnidsa";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PnidsimulatorPackage eINSTANCE = org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PnidsimulatorPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.TransitionActivationAnnotationImpl <em>Transition Activation Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.TransitionActivationAnnotationImpl
	 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PnidsimulatorPackageImpl#getTransitionActivationAnnotation()
	 * @generated
	 */
	int TRANSITION_ACTIVATION_ANNOTATION = 0;

	/**
	 * The feature id for the '<em><b>Net Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTIVATION_ANNOTATION__NET_ANNOTATIONS = NetannotationsPackage.OBJECT_ANNOTATION__NET_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTIVATION_ANNOTATION__OBJECT = NetannotationsPackage.OBJECT_ANNOTATION__OBJECT;

	/**
	 * The number of structural features of the '<em>Transition Activation Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ACTIVATION_ANNOTATION_FEATURE_COUNT = NetannotationsPackage.OBJECT_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PlaceMarkingAnnotationImpl <em>Place Marking Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PlaceMarkingAnnotationImpl
	 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PnidsimulatorPackageImpl#getPlaceMarkingAnnotation()
	 * @generated
	 */
	int PLACE_MARKING_ANNOTATION = 1;

	/**
	 * The feature id for the '<em><b>Net Annotations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_MARKING_ANNOTATION__NET_ANNOTATIONS = NetannotationsPackage.OBJECT_ANNOTATION__NET_ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_MARKING_ANNOTATION__OBJECT = NetannotationsPackage.OBJECT_ANNOTATION__OBJECT;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_MARKING_ANNOTATION__TEXT = NetannotationsPackage.OBJECT_ANNOTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Place Marking Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLACE_MARKING_ANNOTATION_FEATURE_COUNT = NetannotationsPackage.OBJECT_ANNOTATION_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.TransitionActivationAnnotation <em>Transition Activation Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Activation Annotation</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.TransitionActivationAnnotation
	 * @generated
	 */
	EClass getTransitionActivationAnnotation();

	/**
	 * Returns the meta object for class '{@link org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation <em>Place Marking Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Place Marking Annotation</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation
	 * @generated
	 */
	EClass getPlaceMarkingAnnotation();

	/**
	 * Returns the meta object for the attribute '{@link org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation#getText()
	 * @see #getPlaceMarkingAnnotation()
	 * @generated
	 */
	EAttribute getPlaceMarkingAnnotation_Text();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PnidsimulatorFactory getPnidsimulatorFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.TransitionActivationAnnotationImpl <em>Transition Activation Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.TransitionActivationAnnotationImpl
		 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PnidsimulatorPackageImpl#getTransitionActivationAnnotation()
		 * @generated
		 */
		EClass TRANSITION_ACTIVATION_ANNOTATION = eINSTANCE.getTransitionActivationAnnotation();

		/**
		 * The meta object literal for the '{@link org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PlaceMarkingAnnotationImpl <em>Place Marking Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PlaceMarkingAnnotationImpl
		 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl.PnidsimulatorPackageImpl#getPlaceMarkingAnnotation()
		 * @generated
		 */
		EClass PLACE_MARKING_ANNOTATION = eINSTANCE.getPlaceMarkingAnnotation();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PLACE_MARKING_ANNOTATION__TEXT = eINSTANCE.getPlaceMarkingAnnotation_Text();

	}

} //PnidsimulatorPackage
