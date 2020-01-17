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
import org.informationsystem.ismsuite.modeler.process.simulator.PNIDBinding;
import org.informationsystem.ismsuite.modeler.process.simulator.BasicPNIDSimulator;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.TransitionActivationAnnotation;
import org.pnml.tools.epnk.annotations.netannotations.ObjectAnnotation;
import org.pnml.tools.epnk.applications.ui.IActionHandler;
import org.pnml.tools.epnk.pnmlcoremodel.TransitionNode;

public class FireTransitionHandler implements IActionHandler, SelectionListener {

	public static final String BINDING = "org.pnml.tools.epnk.pnids.engine.Binding";
	
	private BasicPNIDSimulator simulator;
	
	public FireTransitionHandler(BasicPNIDSimulator simulator) {
		super();
		this.simulator = simulator;
	}
	
	@Override
	public boolean mousePressed(MouseEvent arg0, ObjectAnnotation annotation) {
		if (annotation instanceof TransitionActivationAnnotation) {
						
			
			TransitionActivationAnnotation taa = (TransitionActivationAnnotation) annotation;
			if (taa.getObject() instanceof TransitionNode) {
				
				
				final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow(); 
				final Shell shell = window.getShell();
				
				Menu root = new Menu(shell, SWT.POP_UP);
				root.setVisible(false);
				
				// Create menu here
				for(PNIDBinding b: simulator.getBindings((TransitionNode) taa.getObject())) {
					MenuItem subItem = new MenuItem(root, SWT.CASCADE);
					subItem.setText(b.toString(false));
					subItem.setData(BINDING, b);
					subItem.addSelectionListener(this);
				}
						
				root.setVisible(true);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		if(e.getSource() instanceof MenuItem) {
			MenuItem item = (MenuItem) e.getSource();
			
			Object o = item.getData(BINDING);
			if (o instanceof PNIDBinding) {
				simulator.fire((PNIDBinding) o);
			}
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean mouseDoubleClicked(MouseEvent arg0, ObjectAnnotation annotation) {
		return false;
	}

	@Override
	public boolean mouseReleased(MouseEvent arg0, ObjectAnnotation annotation) {
		// TODO Auto-generated method stub
		return false;
	}

}
