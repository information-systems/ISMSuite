package org.informationsystem.ismsuite.modeler.simulator;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.JSplitPane;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.ui.InformationView;
import org.informationsystem.ismsuite.ismsuite.ui.MainFrame;
import org.informationsystem.ismsuite.ismsuite.ui.ProcessView;
import org.informationsystem.ismsuite.modeler.process.simulator.BasicPNIDSimulator;
import org.informationsystem.ismsuite.pnidprocessor.PNIDModel;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.specifier.model.Specification;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class Simulator extends BasicPNIDSimulator {

	private Specification specification;
	private FirstOrderLogicWorld world;
	
	public Simulator(PetriNet petrinet, Specification specification, FirstOrderLogicWorld world) {
		super(petrinet);
		this.specification = specification;
		this.world = world;
	}

	@Override
	public void initializeContents() {
		initializeSimulator();
		
		ISMProcessModel model = new ISMProcessModel(this);
		
		Controller controller = new Controller(
				model,
				specification,
				world
				);
		
		try {
			SimulationView viewer = (SimulationView) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(SimulationView.ID);
			viewer.setController(controller);
			
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class ISMProcessModel extends PNIDModel {
		
		Simulator simulator;

		public ISMProcessModel(Simulator simulator) {
			super(simulator.getEngine().getMarkedPetriNet());
			this.simulator = simulator;
		}
		
		@Override
		public boolean fire(Binding binding) {
			boolean result = this.getPetriNet().fire(binding);
			simulator.generateCurrentAnnotations();
			
			return result;	
		}

		
	}
}
