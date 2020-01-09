package org.informationsystem.ismsuite.modeler.commands;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.informationsystem.ismsuite.modeler.process.validator.PNIDSyntaxChecker;
import org.informationsystem.ismsuite.modeler.process.validator.SyntaxError;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class ValidatorCommand extends AbstractCommand {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		PetriNet net = getActivePetriNet(event);
		
		if (net == null || (!(net.getType() instanceof PNID))) {
			MessageDialog.openError(
					window.getShell(),
					"PNID validator",
					"Please select a PNID net");
			return null;
		}
		
		ValidatorCommand.validate(net, window.getShell());
				
		return null;
	}
	
	public static void validate(PetriNet net, Shell shell) {
		List<SyntaxError> errors = PNIDSyntaxChecker.giveErrorsFor(net);
		
		String title;
		if (net.getName() != null && net.getName().getText() != null && !net.getName().getText().isEmpty()) {
			title = net.getName().getText();
		} else if (!net.getId().isEmpty()) {
			title = net.getId();
		} else {
			title = net.toString();
		}
		
		if (errors.isEmpty()) {
			MessageDialog.openInformation(shell, "PNID Validator", "Petri net '" + title + "' contains no syntax errors");
			return;
		}
		
		StringBuilder errorText = new StringBuilder();
		errorText.append("Petri net '");
		errorText.append(title);
		errorText.append("' contains the following errors:\n\n");
		
		for(SyntaxError err: errors) {
			errorText.append("  * ");
			errorText.append(err.getMessage());
			errorText.append("\n");
		}
		
		MessageDialog.openError(shell, "PNID Validator", errorText.toString());
	}
}