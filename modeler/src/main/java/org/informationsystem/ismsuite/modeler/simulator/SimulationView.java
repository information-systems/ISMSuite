package org.informationsystem.ismsuite.modeler.simulator;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.part.ViewPart;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Variable;
import org.informationsystem.ismsuite.modeler.process.simulator.PNIDBinding;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.World;

public class SimulationView extends ViewPart implements FiringListener {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.informationsystem.ismsuite.modeler.simulator.view";
			
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		parent.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		tabFolder = new TabFolder (parent, SWT.BORDER);
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
	
	private TabFolder tabFolder;
	private Composite worldComposite;
	private ExpandBar conjectureBar;
	private Composite disabledTransitionsComposite;
	private Simulator simulator;
	private ScrolledComposite worldContainer;
	
	private Composite createWorldComposite(Composite parent) {
		worldContainer = new ScrolledComposite(parent, SWT.NONE | SWT.V_SCROLL );
		
		worldContainer.setLayout(new GridLayout(1, false));
		
		// container.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		worldComposite = new Composite(worldContainer, SWT.NONE);
		worldComposite.setLayout(new GridLayout(3, true));
		worldComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		worldContainer.setContent(worldComposite);
		worldContainer.setExpandHorizontal(false);
		worldContainer.setExpandVertical(true);
		worldContainer.setMinSize(worldComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		return worldContainer;
	}
	
	private Composite createDisabledTransitionsComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		container.setLayout(layout);
		container.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		disabledTransitionsComposite = new Composite(container, SWT.NONE);
		disabledTransitionsComposite.setLayout(new FillLayout());
		
		return container;
	}
	
	private Composite createConjectureComposite(Composite parent) {
		conjectureBar = new ExpandBar(parent, SWT.V_SCROLL);
		
		return conjectureBar;
	}

	@Override
	public void setFocus() {
	}

	public void setSimulator(Simulator simulator) {
		this.simulator = simulator;
		
		if (conjectureBar != null) {
			updateConjectureList();
		}
	}

	@Override
	public void onBindingFired(PNIDBinding fired, FirstOrderLogicWorld world, Map<PNIDBinding, World> enabledBindings,
			Map<PNIDBinding, String> disabledBindings) {
		
		updateWorld(world);
		updateDisabledTransitions(disabledBindings);
		updateConjectureList();
	}
	
	private void updateWorld(FirstOrderLogicWorld world) {
		for(Control c: worldComposite.getChildren()) {
			c.dispose();
		}
		
		worldComposite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		for(String rel: world.getRelationLabels()) {
			Group group = new Group(worldComposite, SWT.BORDER_SOLID | SWT.FILL);
			RowLayout layout = new RowLayout(SWT.VERTICAL);
			layout.fill = true;
			layout.justify = true;
			/// layout.pack = true;
			group.setLayout(layout);
			group.setText(rel + " (" + world.getRelations(rel).size() + ")");
			
			List relations = new List(group, SWT.BORDER_SOLID| SWT.V_SCROLL|SWT.H_SCROLL);
			RowData rd = new RowData();
			rd.height = 150;
			relations.setLayoutData(rd);
			
			for(Relation relation: world.getRelations(rel)) {
				relations.add(relation.toTFF());
			}
			group.pack();
			group.requestLayout();
		}
		
		worldComposite.pack();
		worldComposite.requestLayout();
		worldContainer.setMinSize(worldComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		tabFolder.requestLayout();
	}
	
	
	private void updateDisabledTransitions(Map<PNIDBinding, String> disabledBindings) {
		if (disabledTransitionsComposite != null) {
			for(Control c: disabledTransitionsComposite.getChildren()) {
				c.dispose();
			}
			
			ExpandBar bar = new ExpandBar(disabledTransitionsComposite, SWT.V_SCROLL);
			for(Entry<PNIDBinding, String> entry : disabledBindings.entrySet()) {
				StringBuilder title = new StringBuilder();
				title.append(entry.getKey().getTransition().getId());
				title.append("(");
				for(Entry<Variable, Entity> e: entry.getKey().getValuation().entrySet()) {
					title.append(" [ ");
					title.append(e.getKey().getText());
					title.append(" -> ");
					title.append(e.getValue().getText());
					title.append("]");
				}
				title.append(" )");
				createStringExpandItem(bar, title.toString(), entry.getValue(), Display.getCurrent().getSystemImage(SWT.ICON_ERROR));
			}
		}
		
	}

	private void updateConjectureList() {
		if (simulator != null && conjectureBar != null && conjectureBar.getItemCount() == 0) {
			
			for(Control c: conjectureBar.getChildren()) {
				c.dispose();
			}
			
			for(Entry<String, Clause> entry: simulator.getConjectures()) {
				// createStringExpandItem(conjectureBar, entry.getKey(), entry.getValue().toTFF(), Display.getCurrent().getSystemImage(SWT.ICON_SEARCH));
				Composite c = new Composite(conjectureBar, SWT.FILL| SWT.BORDER_SOLID);
				c.setLayout(new FillLayout());
				Label l = new Label(c, SWT.WRAP);
				l.setText(entry.getValue().toTFF());
				
				ExpandItem item = new ExpandItem(conjectureBar, SWT.NONE);
				item.setText(entry.getKey());
				item.setControl(c);
				conjectureBar.addListener(SWT.Resize, new Listener() {
					public void handleEvent(Event event) {
						Point size = l.computeSize(conjectureBar.getClientArea().width,SWT.DEFAULT);
						item.setHeight(size.y);
					}
				});
				// item.setImage(Display.getCurrent().getSystemImage(SWT.ICON_QUESTION));
			}
			
		}
	}
	
	private ExpandItem createStringExpandItem(ExpandBar bar, String title, String value, Image icon) {
		Composite c = new Composite(bar, SWT.NONE);
		c.setLayout(new FillLayout());
		Label l = new Label(c, SWT.WRAP);
		l.setText(value);
		
		ExpandItem item = new ExpandItem(bar, SWT.NONE);
		item.setText(title);
		item.setControl(c);
		bar.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				Point size = l.computeSize(bar.getClientArea().width,SWT.DEFAULT);
				item.setHeight(size.y);
			}
		});
		if (icon != null) {
			item.setImage(icon);
		}
		
		return item;
	}
	

}
