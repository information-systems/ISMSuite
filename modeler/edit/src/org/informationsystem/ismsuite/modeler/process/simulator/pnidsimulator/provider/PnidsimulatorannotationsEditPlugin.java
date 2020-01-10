/**
 */
package org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.provider;

import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.ui.EclipseUIPlugin;

import org.eclipse.emf.common.util.ResourceLocator;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.provider.PnidEditPlugin;

import org.pnml.tools.epnk.annotations.netannotations.provider.NetAnnotationsEditPlugin;

import org.pnml.tools.epnk.pnmlcoremodel.provider.PNMLCoreModelEditPlugin;

import org.pnml.tools.epnk.structuredpntypemodel.provider.PNMLStructuredPNTypeModelEditPlugin;

/**
 * This is the central singleton for the Pnid-simulator-annotations editor plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class PnidsimulatorannotationsEditPlugin extends EMFPlugin {
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final PnidsimulatorannotationsEditPlugin INSTANCE = new PnidsimulatorannotationsEditPlugin();
	
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PnidsimulatorannotationsEditPlugin() {
		super
			(new ResourceLocator [] {
				NetAnnotationsEditPlugin.INSTANCE,
				PnidEditPlugin.INSTANCE,
				PNMLCoreModelEditPlugin.INSTANCE,
				PNMLStructuredPNTypeModelEditPlugin.INSTANCE,
			});
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}
	
	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin() {
		return plugin;
	}
	
	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static class Implementation extends EclipseUIPlugin {
		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Implementation() {
			super();
	
			// Remember the static instance.
			//
			plugin = this;
		}
	}

}
