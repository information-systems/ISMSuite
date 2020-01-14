package ismsuite.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.pnml.tools.epnk.applications.Application;
import org.pnml.tools.epnk.applications.ApplicationFactory;
import org.pnml.tools.epnk.applications.activator.Activator;
import org.pnml.tools.epnk.diagram.part.PNMLCoreModelDiagramEditor;
import org.pnml.tools.epnk.helpers.NetFunctions;
import org.pnml.tools.epnk.pnmlcoremodel.Page;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class ApplicationStartCommand  extends AbstractHandler {

	private ApplicationFactory factory;
	
	public ApplicationStartCommand(ApplicationFactory factory) {
		super();
		
		this.factory = factory;
	}
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		PetriNet net = getActiveNet(window);

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
	
	private PetriNet getActiveNet(IWorkbenchWindow window) {
		if (window == null) {
			return null;
		}
		IWorkbenchPage page = window.getActivePage();
		if (page == null) {
			return null;
		}
		
		IEditorPart editor = page.getActiveEditor();
		if (editor instanceof PNMLCoreModelDiagramEditor) {
			PNMLCoreModelDiagramEditor diagramEditor = (PNMLCoreModelDiagramEditor) editor;
			Object content = diagramEditor.getDiagramDocument().getContent();
			if (content instanceof Diagram) {
				Diagram diagram = (Diagram) content;
				EObject element = diagram.getElement();
				if (element instanceof Page) {
					PetriNet net = NetFunctions.getPetriNet((Page) element);
					return net;
				}	
			}
		}
		return null;
	}
	

}
