package org.informationsystem.ismsuite.modeler.process.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.pnml.tools.epnk.gmf.integration.helpers.PNMLPageEditorInput;
import org.pnml.tools.epnk.pnmlcoremodel.Page;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNetDoc;
import org.pnml.tools.epnk.pnmlcoremodel.presentation.PnmlcoremodelEditor;

public abstract class AbstractPetriNetCommand extends AbstractHandler {
	
	
	protected PetriNet getActivePetriNet(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		return getActivePetriNet(window);
	}
	
	protected PetriNet getActivePetriNet(IWorkbenchWindow window) throws ExecutionException {
		if (window.getActivePage() == null) {
			return null;
		}
		
		IEditorPart edit = window.getActivePage().getActiveEditor();
		if (edit == null) {
			return null;
		}
		
		if (edit.getEditorInput() instanceof PNMLPageEditorInput) {
			 EObject container = ((PNMLPageEditorInput) edit.getEditorInput()).getInputPage().eContainer();
			 
			 while ((container instanceof Page)) {
				 container = container.eContainer();
			 }
			 if (container instanceof PetriNet) {
				 return (PetriNet) container;
			 }
		}
		
		if (edit instanceof PnmlcoremodelEditor) {
			ISelection selection = ((PnmlcoremodelEditor) edit).getSelection();
			if (selection instanceof TreeSelection) {
				if (((TreeSelection) selection).size() > 1 ) {
					return null;
				}
				Object selected = ((TreeSelection) selection).getFirstElement();
				if (selected instanceof PetriNet) {
					return (PetriNet) selected;
				} else if (selected instanceof PetriNetDoc) {
					return null;
				} else if (selected instanceof org.pnml.tools.epnk.pnmlcoremodel.Object) {
					// Traverse up, until we get at the document level!
					EObject container = ((org.pnml.tools.epnk.pnmlcoremodel.Object) selected).eContainer();
					while (container != null && !(container instanceof PetriNet)) {
						container = container.eContainer();
					}
					if (container instanceof PetriNet) {
						return (PetriNet) container;
					}
				}
			}
		}
		return null;
	}
	
	
}
