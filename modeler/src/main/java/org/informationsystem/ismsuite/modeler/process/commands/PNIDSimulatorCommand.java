package org.informationsystem.ismsuite.modeler.process.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.informationsystem.ismsuite.modeler.commands.AbstractCommand;

public class PNIDSimulatorCommand extends AbstractCommand {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		MessageDialog.openError(
				window.getShell(),
				"PNID Simulator",
				"Please select a PNID net");
		return null;
	}

	
}
