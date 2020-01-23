package org.informationsystem.ismsuite.modeler.process.pnid.wizards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.provider.PnidEditPlugin;
import org.informationsystem.ismsuite.modeler.process.simulator.pnidsimulator.provider.PnidsimulatorannotationsEditPlugin;
import org.pnml.tools.epnk.pnmlcoremodel.Page;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNetDoc;
import org.pnml.tools.epnk.pnmlcoremodel.PnmlcoremodelFactory;
import org.pnml.tools.epnk.pnmlcoremodel.presentation.PNMLCoreModelEditorPlugin;

public class NewPNIDFileWizard extends Wizard implements INewWizard {

	public static final List<String> FILE_EXTENSIONS =
			Collections.unmodifiableList(Arrays.asList(new String[]{"pnml"}));
	
	
	private IStructuredSelection selection;
	private IWorkbench workbench;
	
	protected NewPNIDFilePage newFilePage;
	
	protected ObjectCreationPage creationPage;
	
	
	public NewPNIDFileWizard() {
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		
		setWindowTitle("New Information System Process model");
		setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(PnidEditPlugin.INSTANCE.getImage("full/wizban/NewPnidsimulator.gif")));
	}
	
	protected EObject createInitialModel(String netName) {
		PetriNetDoc doc = PnmlcoremodelFactory.eINSTANCE.createPetriNetDoc();
		PetriNet net = PnmlcoremodelFactory.eINSTANCE.createPetriNet();
		net.setType(PnidsFactory.eINSTANCE.createPNID());
		net.setId("n1");
		if (netName != null && !netName.isBlank()) {
			net.setName(PnmlcoremodelFactory.eINSTANCE.createName());
			net.getName().setText(netName);
		}
		
		Page page = PnmlcoremodelFactory.eINSTANCE.createPage();
		page.setId("pg1");
		net.getPage().add(page);
		
		doc.getNet().add(net);
		
		return doc;
	}
	
	public IFile getModelFile() {
		return newFilePage.getModelFile();
	}
	
	@Override
	public void addPages() {
		newFilePage = new NewPNIDFilePage("New Information System Process Model", selection);
		newFilePage.setTitle("New Information System Process Model");
		newFilePage.setDescription("Create a new Information System Process Model");
		
		newFilePage.setFileName(PNMLCoreModelEditorPlugin.INSTANCE.getString("_UI_PnmlcoremodelEditorFilenameDefaultBase") + "." + FILE_EXTENSIONS.get(0)); //$NON-NLS-1$ //$NON-NLS-2$
		addPage(newFilePage);
		
		// Try and get the resource selection to determine a current directory for the file dialog.
		if (selection != null && !selection.isEmpty()) {
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				IResource selectedResource = (IResource) selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
					selectedResource = selectedResource.getParent();
				}
				
				// This gives us a directory...
				//
				if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
					newFilePage.setContainerFullPath(selectedResource.getFullPath());
					
					String defaultModelBaseFilename = PNMLCoreModelEditorPlugin.INSTANCE.getString("_UI_PnmlcoremodelEditorFilenameDefaultBase"); //$NON-NLS-1$
					String defaultModelFilenameExtension = FILE_EXTENSIONS.get(0);
					String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension; //$NON-NLS-1$
					for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i) {
						modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension; //$NON-NLS-1$
					}
					newFilePage.setFileName(modelFilename);
				}
			}
		}
		
		creationPage = new ObjectCreationPage("ISM Process Wizard");
		creationPage.setTitle("New Information System Process Model");
		addPage(creationPage);
	}

	@Override
	public boolean performFinish() {
		try {
			final IFile modelFile = getModelFile();
			
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				
				@Override
				protected void execute(IProgressMonitor monitor) {
					try {
						ResourceSet resourceSet = new ResourceSetImpl();
						URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);
						Resource resource = resourceSet.createResource(fileURI);
						
						EObject rootObject = createInitialModel(creationPage.getName());
						if (rootObject != null) {
							resource.getContents().add(rootObject);
						}
						
						
						Map<Object, Object> options = new HashMap<Object, Object>();
						options.put(XMLResource.OPTION_ENCODING, creationPage.getEncoding());
						resource.save(options);
						
						
					} catch(Exception exception) {
						PNMLCoreModelEditorPlugin.INSTANCE.log(exception);
					} finally {
						monitor.done();
					}
				}
			};
			
			getContainer().run(false, false, operation);
			
			// Select the new file resource in the current view.
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(modelFile);
				getShell().getDisplay().asyncExec
					(new Runnable() {
						 public void run() {
							 ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
						 }
					 });
			}
			

			try {
				// Open the editor directly on the page
				page.openEditor(new FileEditorInput(modelFile), workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
				
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(), PNMLCoreModelEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), exception.getMessage()); //$NON-NLS-1$
				return false;
			}
			
			return true;
		} catch (Exception exception) {
			PNMLCoreModelEditorPlugin.INSTANCE.log(exception);
			return false;
		}
	}
	
	public class NewPNIDFilePage extends WizardNewFileCreationPage {

		public NewPNIDFilePage(String pageName, IStructuredSelection selection) {
			super(pageName, selection);
		}

		@Override
		protected boolean validatePage() {
			if (super.validatePage()) {
				String extension = new Path(getFileName()).getFileExtension();
				if (extension == null || !FILE_EXTENSIONS.contains(extension)) {
					String key = FILE_EXTENSIONS.size() > 1 ? "_WARN_FilenameExtensions" : "_WARN_FilenameExtension"; //$NON-NLS-1$ //$NON-NLS-2$
					setErrorMessage(PNMLCoreModelEditorPlugin.INSTANCE.getString(key, new Object [] { FILE_EXTENSIONS }));
					return false;
				}
				return true;
			}
			return false;
		}
		
		public IFile getModelFile() {
			return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
		}

	}
	
	public class ObjectCreationPage extends WizardPage {

		private List<String> encodings;

		protected Combo encodingField;
		
		protected Text nameField;


		protected ObjectCreationPage(String pageName) {
			super(pageName);
		}

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE); 
			{
				GridLayout layout = new GridLayout();
				layout.numColumns = 2;
				layout.verticalSpacing = 12;
				composite.setLayout(layout);

				GridData data = new GridData();
				data.verticalAlignment = GridData.FILL;
				data.grabExcessVerticalSpace = true;
				data.horizontalAlignment = GridData.FILL;
				composite.setLayoutData(data);
			}
			
			Label nameLabel = new Label(composite, SWT.LEFT);
			{
				nameLabel.setText("Process Name");
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				nameLabel.setLayoutData(data);
			}
			nameField = new Text(composite, SWT.BORDER);
			{
				nameField.setText("New Net");
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				nameField.setLayoutData(data);
			}
			
			Label encodingLabel = new Label(composite, SWT.LEFT);
			{
				encodingLabel.setText(PNMLCoreModelEditorPlugin.INSTANCE.getString("_UI_XMLEncoding")); //$NON-NLS-1$

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				encodingLabel.setLayoutData(data);
			}
			encodingField = new Combo(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				encodingField.setLayoutData(data);
			}

			for (String encoding : getEncodings()) {
				encodingField.add(encoding);
			}

			encodingField.select(0);
			encodingField.addModifyListener(validator);

			setPageComplete(validatePage());
			setControl(composite);
			
		}
		
		protected ModifyListener validator = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		};
		
				
		protected boolean validatePage() {
			return getName() != null && getEncodings().contains(encodingField.getText());
		}
		
		@Override
		public void setVisible(boolean visible) {
			super.setVisible(visible);
			if (visible) {
				nameField.setFocus();
			}
		}
				
		public String getName() {
			return nameField.getText();
		}
		
		public String getEncoding() {
			return encodingField.getText();
		}		
		
		protected Collection<String> getEncodings() {
			if (encodings == null) {
				encodings = new ArrayList<String>();
				for (StringTokenizer stringTokenizer = new StringTokenizer(PNMLCoreModelEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices")); stringTokenizer.hasMoreTokens(); ) //$NON-NLS-1$
				{
					encodings.add(stringTokenizer.nextToken());
				}
			}
			return encodings;
		}
		
	}

}
