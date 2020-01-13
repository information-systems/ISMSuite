/**
 */
package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>pnidsimulator</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class PnidsimulatorTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new PnidsimulatorTests("pnidsimulator Tests");
		suite.addTestSuite(TransitionActivationAnnotationTest.class);
		suite.addTestSuite(PlaceMarkingAnnotationTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PnidsimulatorTests(String name) {
		super(name);
	}

} //PnidsimulatorTests
