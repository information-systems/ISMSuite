package org.informationsystem.ismsuite.modeler.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class ValidatorCommand extends AbstractCommand {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		PetriNet net = getActivePetriNet(event);
		
		if (net == null) {
			MessageDialog.openInformation(
					window.getShell(),
					"ISM Suite validator",
					"Please select a Petri net");
			return null;
		}
		
		String title = "";
		if (net.getName() != null && net.getName().getText() != null && !net.getName().getText().isEmpty()) {
			title = net.getName().getText();
		} else if (!net.getId().isEmpty()) {
			title = net.getId();
		} else {
			title = net.toString();
		}
		
		MessageDialog.openInformation(window.getShell(), "Validator", "I will be run on Net: " + title);
		
		
		return null;
	}
}