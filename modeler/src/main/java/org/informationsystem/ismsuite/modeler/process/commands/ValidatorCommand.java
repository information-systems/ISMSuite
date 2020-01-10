package org.informationsystem.ismsuite.modeler.process.commands;

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.informationsystem.ismsuite.modeler.commands.AbstractCommand;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PNID;
import org.informationsystem.ismsuite.modeler.process.validator.PNIDSyntaxChecker;
import org.informationsystem.ismsuite.modeler.process.validator.SyntaxError;
import org.pnml.tools.epnk.actions.commands.AddMissingIDsCommand;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNetDoc;

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
	
	public static boolean validate(PetriNet net, Shell shell) {
		return validate(net, shell, true);
	}
	
	public static boolean validate(PetriNet net, Shell shell, boolean showMessageOnSuccess) {
		List<SyntaxError> errors = PNIDSyntaxChecker.giveErrorsFor(net);
		
		// Add the IDs if not present
		if (net.eContainer() instanceof PetriNetDoc) {
			try {
				PetriNetDoc doc = (PetriNetDoc) net.eContainer();
				EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(doc);	
				domain.getCommandStack().execute(new AddMissingIDsCommand(doc));
			} catch(Exception ex) {}
		}
		
		String title;
		if (net.getName() != null && net.getName().getText() != null && !net.getName().getText().isEmpty()) {
			title = net.getName().getText();
		} else if (!net.getId().isEmpty()) {
			title = net.getId();
		} else {
			title = net.toString();
		}
		
		if (errors.isEmpty()) {
			if (showMessageOnSuccess) {
				MessageDialog.openInformation(shell, "PNID Validator", "Petri net '" + title + "' contains no syntax errors");
			}
			return true;
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
		
		return false;
	}
}