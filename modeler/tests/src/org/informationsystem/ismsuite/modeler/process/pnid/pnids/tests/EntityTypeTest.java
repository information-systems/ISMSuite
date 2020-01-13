/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Entity Type</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class EntityTypeTest extends TestCase {

	/**
	 * The fixture for this Entity Type test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityType fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EntityTypeTest.class);
	}

	/**
	 * Constructs a new Entity Type test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityTypeTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Entity Type test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(EntityType fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Entity Type test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityType getFixture() {
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
		setFixture(PnidsFactory.eINSTANCE.createEntityType());
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

} //EntityTypeTest
