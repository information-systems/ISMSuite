package org.informationsystem.ismsuite.modeler.simulator;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.model.Model;
import org.informationsystem.ismsuite.ismsuite.model.StateChangedListener;
import org.informationsystem.ismsuite.processengine.process.Binding;
import org.informationsystem.ismsuite.prover.model.Clause;
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
		
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		parent.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		final TabFolder tabFolder = new TabFolder (parent, SWT.BORDER);
		// We create three tabs: 
		// 0. World
		TabItem worldItem = new TabItem(tabFolder, SWT.NONE);
		worldItem.setText("Current world");
		worldItem.setControl(createWorldComposite(tabFolder));
		
		// 1. disabled transitions
		TabItem disabledTransitionsItem = new TabItem(tabFolder, SWT.NONE);
		disabledTransitionsItem.setText("Disabled transitions");
		disabledTransitionsItem.setControl(createDisabledTransitionsComposite(tabFolder));
		
		// 2. Conjectures in the model
		TabItem conjecturesItem = new TabItem(tabFolder, SWT.NONE);
		conjecturesItem.setText("Conjectures");
		conjecturesItem.setControl(createConjectureComposite(tabFolder));
	}
	
	private Composite world;
	private List disabledTransitionsList;
	private Map<String, String> disabledExplanation = new HashMap<>();
	private ExpandBar conjectureBar;
	
	private Composite createWorldComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.FILL);
		FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		container.setLayout(layout);
		container.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		world = new Composite(container, SWT.FILL);
		world.setLayout(new FillLayout());
		return container;
	}
	
	private Composite createDisabledTransitionsComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.V_SCROLL);
		GridLayout gridLayout = new GridLayout(2, true);
		gridLayout.horizontalSpacing = SWT.FILL;
		gridLayout.verticalSpacing = SWT.FILL|SWT.TOP;
		container.setLayout(gridLayout);
		
		disabledTransitionsList = new List(parent, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		Label explanation = new Label(parent, SWT.NONE);
		explanation.setText("Select a transition");
		disabledTransitionsList.addListener (SWT.Selection, e -> {
			for(String item: disabledTransitionsList.getSelection()) {
				if(disabledExplanation.containsKey(item)) {
					explanation.setText(disabledExplanation.get(item));
					return;
				}
			}
			explanation.setText("No explanation found");
		});	
		
		return container;
	}
	
	private Composite createConjectureComposite(Composite parent) {
		conjectureBar = new ExpandBar(parent, SWT.V_SCROLL);
		
		return conjectureBar;
	}

	@Override
	public void setFocus() {
	}

	@Override
	public void update(Model model) {
		if (world != null) {
			for(Control c: world.getChildren()) {
				c.dispose();
			}
			GridLayout gridLayout = new GridLayout(3, true);
			gridLayout.horizontalSpacing = SWT.FILL;
			gridLayout.verticalSpacing = SWT.TOP;
			gridLayout.makeColumnsEqualWidth = true;
			
			world.setLayout(gridLayout);
			world.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
			
			for(String rel: model.getWorld().getRelationLabels()) {
				Group group = new Group(world, SWT.BORDER_SOLID | SWT.FILL | SWT.BOLD);
				
				RowLayout layout = new RowLayout(SWT.VERTICAL);
				layout.fill = true;
				layout.justify = true;
				layout.pack = true;
				group.setLayout(layout);
				
				group.setText(rel + " (" + model.getWorld().getRelations(rel).size() + ")");
				List relations = new List(group, SWT.BORDER_SOLID| SWT.V_SCROLL|SWT.FILL);
				RowData rd = new RowData();
				rd.height = 150;
				relations.setLayoutData(rd);
				
				for(Relation relation: model.getWorld().getRelations(rel)) {
					relations.add(relation.toTFF());
				}
				
				group.pack();
				group.requestLayout();
			}
			world.pack();
			world.requestLayout();
		}
		
		if (disabledTransitionsList != null) {
			disabledTransitionsList.removeAll();
			disabledExplanation.clear();
			for(Entry<Binding, String> entry: model.disabledTransitions().entrySet()) {
				String key = entry.getKey().toString();
				disabledTransitionsList.add(key);
				disabledExplanation.put(key, entry.getValue());
			}
		}
		
		if (conjectureBar != null) {
			for(Control c: conjectureBar.getChildren()) {
				c.dispose();
			}
			for(ExpandItem item: conjectureBar.getItems()) {
				item.dispose();
			}
			
			for(Entry<String, Clause> entry: controller.getConjectures()) {
				Composite container = new Composite(conjectureBar, SWT.NONE);
				// container.setLayout(new FillLayout());
				Text label = new Text(container, SWT.NONE);
				label.setEditable(false);
				/*
				GridData gd = new GridData();
				gd.grabExcessHorizontalSpace = true;
				gd.horizontalAlignment = SWT.FILL;
				label.setLayoutData(gd);
				*/
				label.setText(entry.getValue().toTFF());
				// System.out.println(entry.getValue().toTFF());
				ExpandItem item = new ExpandItem(conjectureBar, SWT.NONE);
				item.setText(entry.getKey());
				item.setImage(Display.getCurrent().getSystemImage(SWT.ICON_WORKING));
				item.setHeight(container.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
				item.setControl(container);
			}
			
		}
	}
	

}
