package org.informationsystem.ismsuite.modeler.simulator;

import java.awt.BorderLayout;
import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.ui.ProcessView;

public class SimulationView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.informationsystem.ismsuite.modeler.simulator.view";
	
	private Controller controller = null;
	private ProcessView processView = null;
	
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public Controller getController() {
		return controller;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		System.out.println("here");
		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		Frame frame = SWT_AWT.new_Frame(composite);
		
		frame.setLayout(new BorderLayout());
		processView = new ProcessView(controller);
		frame.add(processView, BorderLayout.CENTER);
	}

	@Override
	public void setFocus() {

	}

}
