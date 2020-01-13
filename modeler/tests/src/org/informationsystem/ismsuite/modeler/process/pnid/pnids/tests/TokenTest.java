/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Token</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class TokenTest extends TestCase {

	/**
	 * The fixture for this Token test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Token fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TokenTest.class);
	}

	/**
	 * Constructs a new Token test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TokenTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Token test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Token fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Token test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Token getFixture() {
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
		setFixture(PnidsFactory.eINSTANCE.createToken());
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

} //TokenTest
