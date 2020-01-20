package org.informationsystem.ismsuite.modeler.process.commands;

import java.io.IOException;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.informationsystem.ismsuite.modeler.simulator.Factory;
import org.informationsystem.ismsuite.prover.io.ClauseReader;
import org.informationsystem.ismsuite.prover.model.World;
import org.informationsystem.ismsuite.specifier.io.SpecificationReader;
import org.pnml.tools.epnk.pnmlcoremodel.PetriNet;

public class ISMSimulatorCommand extends ApplicationStartCommand {

	public ISMSimulatorCommand() {
		super(new Factory());
	}
	
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		PetriNet net = tryToGetNet(window);
		
		if (net == null) {
			return null;
		}
		
		// Show a dialog to open two files: the TFF file and the Specification File
		SelectWorldAndSpecificationDialog dialog = new SelectWorldAndSpecificationDialog(window.getShell());
		dialog.create();
		if (dialog.open() == Window.OK) {
			
			try {
				((Factory) factory).setWorld((World) ClauseReader.buildWorldFromFile(dialog.getWorldFileName()));
			} catch (IOException e) {
				MessageDialog.openError(
						window.getShell(),
						"ISMSuite - " + factory.getName(),
						e.getMessage());
				return null;
			}
			try {
				((Factory) factory).setSpecification(SpecificationReader.fromFileName(dialog.getSpecificationFileName()));
			} catch(Exception e) {
				MessageDialog.openError(
						window.getShell(),
						"ISMSuite - " + factory.getName(),
						e.getMessage());
				return null;
			}
			dialog.close();

			return startApplication(event);
		}
		dialog.close();
		return null;
		
		// 
	}
	
	
	private class SelectWorldAndSpecificationDialog extends TitleAreaDialog {

		public SelectWorldAndSpecificationDialog(Shell parentShell) {
			super(parentShell);
		}
		
		@Override
		public void create() {
			super.create();
			
			setTitle("ISMSuite Simulation");
			setMessage("The Simulator requires an information model and a specification.", IMessageProvider.INFORMATION);
		}
		
		private Label lblWorld;
		private Label lblSpec;
		
		private String worldFileName = "";
		private String specificationFileName = "";
		
		public String getWorldFileName() {
			return worldFileName;
		}
		
		public String getSpecificationFileName() {
			return specificationFileName;
		}
		
		@Override
		protected Control createDialogArea(Composite parent) {
			Composite area = (Composite) super.createDialogArea(parent);
			Composite container = new Composite(area, SWT.NONE);
			container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			container.setLayout(new GridLayout(2, false));
			
			Button btnWorld = new Button(container, SWT.NONE);
	        btnWorld.setText("Select world");
	        btnWorld.addListener(SWT.Selection, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					FileDialog fileDialog = new FileDialog(getShell());
					if (!worldFileName.isBlank()) {
						fileDialog.setFileName(worldFileName);
					}
			        // Set the text
			        fileDialog.setText("Select File");
			        // Set filter on .txt files
			        fileDialog.setFilterExtensions(new String[] { "*.tff" });
			        // Put in a readable name for the filter
			        fileDialog.setFilterNames(new String[] { "Typed First-order Logic File (*.tff)" });
			        // Open Dialog and save result of selection
			        worldFileName = fileDialog.open();
			        lblWorld.setText(worldFileName);
				}
			});
	        			
			lblWorld = new Label(container, SWT.NONE);
			lblWorld.setText("<no world selected>");
			
			Button btnSpec = new Button(container, SWT.NONE);
	        btnSpec.setText("Select specification");
	        btnSpec.addListener(SWT.Selection, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					FileDialog fileDialog = new FileDialog(getShell());
					if (!specificationFileName.isBlank()) {
						fileDialog.setFileName(specificationFileName);
					}
			        // Set the text
			        fileDialog.setText("Select File");
			        // Set filter on .txt files
			        fileDialog.setFilterExtensions(new String[] { "*.spec" });
			        // Put in a readable name for the filter
			        fileDialog.setFilterNames(new String[] { "Specification file (*.spec)" });
			        // Open Dialog and save result of selection
			        specificationFileName = fileDialog.open();
			        lblSpec.setText(specificationFileName);
				}
				});
	        			
			lblSpec = new Label(container, SWT.NONE);
			lblSpec.setText("<no specification selected>");
				        
			return area;
		}
		
	}
}
