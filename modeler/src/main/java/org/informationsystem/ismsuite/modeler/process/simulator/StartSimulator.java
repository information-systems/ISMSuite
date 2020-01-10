package org.informationsystem.ismsuite.modeler.process.simulator;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.applications.registry.ApplicationRegistry;

import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class StartSimulator implements IObjectActionDelegate {

	private PetriNet petrinet;
	
	public boolean isEnabled() {
		return petrinet != null;
	}


		
	@Override
	public void run(IAction action) {
		Simulator application = new Simulator(petrinet);
		Activator activator = Activator.getInstance();
		ApplicationRegistry registry = activator.getApplicationRegistry();
		application.initialize(null);
		registry.addApplication(application);
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		petrinet = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1) {
				java.lang.Object selected = structuredSelection.getFirstElement();
				if (selected instanceof PetriNet) {
					petrinet = (PetriNet) selected;
					// it should be of the right type...
					if ((petrinet.getType() == null) || !(petrinet.getType() instanceof PNID)) {
						petrinet = null;
					}
				}
			}
		}
		action.setEnabled(isEnabled());
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
