/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>pnids</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class PnidsTests extends TestSuite {

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
		TestSuite suite = new PnidsTests("pnids Tests");
		suite.addTestSuite(PNIDTest.class);
		suite.addTestSuite(EntityTypeLabelTest.class);
		suite.addTestSuite(VariableInscriptionLabelTest.class);
		suite.addTestSuite(PNIDMarkingTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PnidsTests(String name) {
		super(name);
	}

} //PnidsTests
