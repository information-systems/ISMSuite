package org.informationsystem.ismsuite.modeler.simulator;

import java.awt.BorderLayout;
import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;
import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.model.Model;
import org.informationsystem.ismsuite.ismsuite.model.StateChangedListener;
import org.informationsystem.ismsuite.ismsuite.ui.ProcessView;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.prover.model.Relation;

public class SimulationView extends ViewPart implements StateChangedListener {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.informationsystem.ismsuite.modeler.simulator.view";
	
	private Controller controller = null;
	
	public void setController(Controller controller) {
		this.controller = controller;
		controller.getModel().addListener(this);
		update(controller.getModel());
	}
	
	public Controller getController() {
		return controller;
	}
	
	private Composite container;
		
	@Override
	public void createPartControl(Composite parent) {
		container = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		update(null);
	}

	@Override
	public void setFocus() {
		if (getController() != null) {
			update(getController().getModel());
		} else {
			update(null);
		}
	}

	@Override
	public void update(Model model) {
		System.out.println("Repopulate!");
		if (container == null) {
			return;
		}
		
		if (model == null) {
			Label l = new Label(container, SWT.NONE);
			l.setText("No IS Model connected");
			return;
		}
		
		for(Control c: container.getChildren()) {
			c.dispose();
		}
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		gridLayout.makeColumnsEqualWidth = true;
		
		
		container.setLayout(gridLayout);
		
		if (model.getWorld().getRelationLabels().isEmpty()) {
			Label l = new Label(container, SWT.NONE);
			l.setText("Model has no relations");
		}
		System.out.println("=== Repopulating! === ");
		
		for(String s: model.getWorld().getRelationLabels()) {
			System.out.println("Adding relation: " + s);
			Group gr = new Group(container, SWT.BORDER_SOLID);
			FillLayout layout = new FillLayout();
			layout.type = SWT.VERTICAL;
			gr.setLayout(layout);
			gr.setText(s);
			List l = new List(gr,  SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
			for(Relation item: model.getWorld().getRelations(s)) {
				l.add(item.toString());
				System.out.println("  element: " + item.toString());
			}
			gr.pack();
		}
		
		System.out.println("=== Finished repopulation ===");
		
		container.pack();
	}

}
