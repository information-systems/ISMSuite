package org.informationsystem.ismsuite.modeler.process.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.ApplicationFactory;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class ApplicationStartCommand  extends AbstractPetriNetCommand {

	private ApplicationFactory factory;
	
	public ApplicationStartCommand(ApplicationFactory factory) {
		super();
		
		this.factory = factory;
	}
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		PetriNet net = getActivePetriNet(window);
		
		if (net == null) {
			MessageDialog.openError(
					window.getShell(),
					"ISMSuite - " + factory.getName(),
					"No Petri net is selected!");
			
			return null;
		}

		if (!factory.isApplicable(net)) {
			String name = "";
			if (net.getName() != null && !net.getName().getText().isBlank()) {
				name = net.getName().getText();
			} else if (net.getId() != null && !net.getId().isBlank()) {
				name = net.getId();
			} else {
				name = net.toString();
			}
			
			MessageDialog.openError(
					window.getShell(),
					"ISMSuite - " + factory.getName(),
					"Application '" + factory.getName() +"' is not applicable on the net: " + name);
			
			return null;
		}
		
		Activator applicationFactoryRegistry = Activator.getInstance();
		
		Application application = factory.startApplication(net);
		if (application != null) {
			application.initialize(null);
			application.setName(factory.getName());
			application.getNetAnnotations().setAppId(factory.getID());
			application.getNetAnnotations().setAppVersion(factory.getVersion());
			applicationFactoryRegistry.getApplicationRegistry().addApplication(application);
		}
		
		return null;
	}
	

}
