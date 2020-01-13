/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>PNID</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PNIDTest extends TestCase {

	/**
	 * The fixture for this PNID test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PNID fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PNIDTest.class);
	}

	/**
	 * Constructs a new PNID test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PNIDTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this PNID test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(PNID fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this PNID test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PNID getFixture() {
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
		setFixture(PnidsFactory.eINSTANCE.createPNID());
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

} //PNIDTest
