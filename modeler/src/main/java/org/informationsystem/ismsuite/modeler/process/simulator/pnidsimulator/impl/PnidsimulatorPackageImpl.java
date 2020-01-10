/**
 */
package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage;

import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PnidsimulatorFactory;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PnidsimulatorPackage;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.TransitionActivationAnnotation;

import org.pnml.tools.epnk.annotations.netannotations.NetannotationsPackage;

import org.pnml.tools.epnk.pnmlcoremodel.PnmlcoremodelPackage;

import org.pnml.tools.epnk.structuredpntypemodel.StructuredpntypemodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PnidsimulatorPackageImpl extends EPackageImpl implements PnidsimulatorPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transitionActivationAnnotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass placeMarkingAnnotationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PnidsimulatorPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PnidsimulatorPackageImpl() {
		super(eNS_URI, PnidsimulatorFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link PnidsimulatorPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PnidsimulatorPackage init() {
		if (isInited) return (PnidsimulatorPackage)EPackage.Registry.INSTANCE.getEPackage(PnidsimulatorPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredPnidsimulatorPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		PnidsimulatorPackageImpl thePnidsimulatorPackage = registeredPnidsimulatorPackage instanceof PnidsimulatorPackageImpl ? (PnidsimulatorPackageImpl)registeredPnidsimulatorPackage : new PnidsimulatorPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		NetannotationsPackage.eINSTANCE.eClass();
		PnidsPackage.eINSTANCE.eClass();
		PnmlcoremodelPackage.eINSTANCE.eClass();
		StructuredpntypemodelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePnidsimulatorPackage.createPackageContents();

		// Initialize created meta-data
		thePnidsimulatorPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePnidsimulatorPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PnidsimulatorPackage.eNS_URI, thePnidsimulatorPackage);
		return thePnidsimulatorPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTransitionActivationAnnotation() {
		return transitionActivationAnnotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPlaceMarkingAnnotation() {
		return placeMarkingAnnotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPlaceMarkingAnnotation_Text() {
		return (EAttribute)placeMarkingAnnotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PnidsimulatorFactory getPnidsimulatorFactory() {
		return (PnidsimulatorFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		transitionActivationAnnotationEClass = createEClass(TRANSITION_ACTIVATION_ANNOTATION);

		placeMarkingAnnotationEClass = createEClass(PLACE_MARKING_ANNOTATION);
		createEAttribute(placeMarkingAnnotationEClass, PLACE_MARKING_ANNOTATION__TEXT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		NetannotationsPackage theNetannotationsPackage = (NetannotationsPackage)EPackage.Registry.INSTANCE.getEPackage(NetannotationsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		transitionActivationAnnotationEClass.getESuperTypes().add(theNetannotationsPackage.getObjectAnnotation());
		placeMarkingAnnotationEClass.getESuperTypes().add(theNetannotationsPackage.getObjectAnnotation());
		placeMarkingAnnotationEClass.getESuperTypes().add(theNetannotationsPackage.getTextualAnnotation());

		// Initialize classes and features; add operations and parameters
		initEClass(transitionActivationAnnotationEClass, TransitionActivationAnnotation.class, "TransitionActivationAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(placeMarkingAnnotationEClass, PlaceMarkingAnnotation.class, "PlaceMarkingAnnotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPlaceMarkingAnnotation_Text(), ecorePackage.getEInt(), "text", null, 0, 1, PlaceMarkingAnnotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //PnidsimulatorPackageImpl
