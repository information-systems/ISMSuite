/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Arc;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Arc</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ArcTest extends TestCase {

	/**
	 * The fixture for this Arc test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Arc fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ArcTest.class);
	}

	/**
	 * Constructs a new Arc test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArcTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Arc test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Arc fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Arc test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Arc getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(PnidsFactory.eINSTANCE.createArc());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //ArcTest
