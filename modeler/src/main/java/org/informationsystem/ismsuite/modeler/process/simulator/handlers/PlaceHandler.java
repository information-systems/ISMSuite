package org.informationsystem.ismsuite.modeler.process.simulator.handlers;

import org.eclipse.draw2d.MouseEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag;
import org.informationsystem.ismsuite.modeler.process.simulator.BasicPNIDSimulator;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.PlaceMarkingAnnotation;
import org.informationsystem.ismsuite.processengine.process.MultiSet;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.ui.IActionHandler;
import org.pnml.tools.epnk.pnmlcoremodel.PlaceNode;

public class PlaceHandler implements IActionHandler, SelectionListener {

	private BasicPNIDSimulator simulator;
	
	public PlaceHandler(BasicPNIDSimulator simulator) {
		super();
		this.simulator = simulator;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	@Override
	public boolean mouseDoubleClicked(MouseEvent arg0, ObjectAnnotation annotation) {
		return false;
	}

	@Override
	public boolean mousePressed(MouseEvent arg0, ObjectAnnotation annotation) {
		if (annotation instanceof PlaceMarkingAnnotation) {
			PlaceMarkingAnnotation ann = (PlaceMarkingAnnotation) annotation;
			if (ann.getObject() instanceof PlaceNode) {
				
				final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow(); 
				final Shell shell = window.getShell();
				
				TokenBag bag = simulator.getTokensFor((PlaceNode) ann.getObject());
				if (bag.getToken().isEmpty()) {
					return false;
				}
				
				// Create token list here
				MultiSet<String> set = new MultiSet<>();
				for(Token token: bag.getToken()) {
					StringBuilder sb = new StringBuilder();
					for(Entity e: token.getEntity()) {
						sb.append(", ");
						sb.append(e.getText());
					}
					sb.append(" ]");
					sb.replace(0, 1, "[");
					set.add(sb.toString());
				}
				
				Menu root = new Menu(shell, SWT.POP_UP);
				root.setVisible(false);

				for(String s: set.getUnique()) {
					MenuItem subItem = new MenuItem(root, SWT.CASCADE);
					subItem.setText(set.size(s) + ": " + s);
					subItem.addSelectionListener(this);
				}
				
				root.setVisible(true);
				
			}
			
		}
		return false;
	}

	@Override
	public boolean mouseReleased(MouseEvent arg0, ObjectAnnotation annotation) {
		// TODO Auto-generated method stub
		return false;
	}

}
