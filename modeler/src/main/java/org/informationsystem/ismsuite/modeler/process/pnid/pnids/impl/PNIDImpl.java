/**
 */
package org.informationsystem.ismsuite.modeler.process.pnid.pnids.impl;

import org.eclipse.emf.ecore.EClass;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsPackage;
import org.informationsystem.ismsuite.modeler.process.util.PNIDLinker;
import org.pnml.tools.epnk.structuredpntypemodel.Linker;
import org.pnml.tools.epnk.structuredpntypemodel.impl.StructuredPetriNetTypeImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PNID</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class PNIDImpl extends StructuredPetriNetTypeImpl implements PNID {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PNIDImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PnidsPackage.Literals.PNID;
	}
	
	private PNIDLinker linker;
	
	/**
	 * @generated NOT
	 * Returns a linker, required for parsing labels.
	 */
	public Linker getLinker() {
		if (linker == null) {
			linker = new PNIDLinker();
		}
		return linker;
	}

} //PNIDImpl
