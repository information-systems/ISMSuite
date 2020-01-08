/**
 * 
 */
package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.informationsystem.ismsuite.itsatrueworld.controller.WorldController;
import org.informationsystem.ismsuite.itsatrueworld.gui.conjecture.ConjecturePanel;
import org.informationsystem.ismsuite.itsatrueworld.gui.transaction.TransactionDialog;
import org.informationsystem.ismsuite.itsatrueworld.gui.transaction.TransactionPanel;
import org.informationsystem.ismsuite.itsatrueworld.gui.world.ElementListingPanel;
import org.informationsystem.ismsuite.itsatrueworld.gui.world.RelationDialog;
import org.informationsystem.ismsuite.itsatrueworld.gui.world.RelationListingPanel;
import org.informationsystem.ismsuite.itsatrueworld.gui.world.UpdateElementDialog;
import org.informationsystem.ismsuite.specifier.model.Transaction;
import org.informationsystem.ismsuite.itsatrueworld.controller.SpecificationController;
import org.informationsystem.ismsuite.itsatrueworld.controller.TrueWorld;
import org.informationsystem.ismsuite.itsatrueworld.controller.TrueWorldListener;

/**
 * @author jmw
 *
 */
public class MainWindow extends JFrame implements TrueWorldListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9217156852218912678L;

	protected static final String TITLE = "It's a true world";
	
	private static FileNameExtensionFilter tffFileFilter = new FileNameExtensionFilter("Typed First-order logic Formulae", "tff");
	private static FileNameExtensionFilter specFileFilter = new FileNameExtensionFilter("Specification file", "spec");
	
	private WorldController controller;
	
	private SpecificationController specification;

	private MainWindow parent;
		
	public MainWindow() {
		this(new WorldController(), new SpecificationController());
	}
	
	public MainWindow(WorldController controller, SpecificationController specification) {
		if (controller == null) {
			controller = new WorldController();
		}
		
		if (specification == null) {
			specification = new SpecificationController();
		}
	
		this.controller = controller;
		this.specification = specification;
		
		parent = this;
		
		initialize();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter() {
		
			  @Override
			public void windowClosing(WindowEvent we) {
				if (parent.controller.getModel().isModified()) {
					
					int result = JOptionPane.showOptionDialog(
							parent, 
							"Your world contains unsaved elements. Save before exit?", 
							"Unsaved changes",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
					switch(result) {
						case JOptionPane.YES_OPTION:
							exportWorldToFile();
							break;
						case JOptionPane.NO_OPTION:							
							break;
						default:
						case JOptionPane.CANCEL_OPTION:
							System.out.println("cancel");
							return;
					}
				}
				if (parent.specification.isModified()) {
					int result = JOptionPane.showOptionDialog(
							parent, 
							"Your Transaction List contains unsaved elements. Save before exit?", 
							"Unsaved changes",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
					switch(result) {
						case JOptionPane.YES_OPTION:
							exportSpecToFile();
							break;
						case JOptionPane.NO_OPTION:							
							break;
						default:
						case JOptionPane.CANCEL_OPTION:
							System.out.println("cancel");
							return;
					}
			  	}
				// nothing changed, just close
				System.exit(0);
			}
		});
		
		this.controller.register(this);
		onNotify(this.controller.getModel());		
	}

	// Invoke/show the UI.
	public static void invokeUI(WorldController controller, SpecificationController specification) throws InvocationTargetException, InterruptedException {
		EventQueue.invokeAndWait(new Runnable() {
			@Override public void run() {
				try {
					final MainWindow frame = new MainWindow(controller, specification);
					frame.setPreferredSize(new Dimension(500,500));
					frame.setMinimumSize(new Dimension(400, 400));
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	private JPanel quickValidatorPanel;
	
	private JPanel mainPanel;
	private JPanel transactionPanel;
	private JPanel worldPanel;
	private JPanel conjecturePanel;
	
	
	
	/**
	 * Builds the Frame
	 * 
	 * |-----------------------------------------|
	 * |Menu bar                                 |
	 * |-----------------------------------------|
	 * | quickvalidate:                          |
	 * | textfield to enter a conjecture         |
	 * |                       validate | add    |
	 * |-----------------------------------------|
	 * |  Trans- | tabbed panel    | conjectures |
	 * | actions | - elements      |             |
	 * |         | - rel. bucket   |             |
	 * |         | - axiom list    |             |
	 * |---------|-----------------|-------------|
	 *           ^                 ^
	 *        transSplitter      conjectureSplitter
	 * 
	 */
	protected void initialize() {		
		setTitle(TITLE);
		setLayout(new BorderLayout());
		
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setPreferredSize(new Dimension(500, 500));
		this.setLayout(new BorderLayout());
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		buildMenu();
		
		buildQuickValidatorPanel();
		
		buildMainPanel();
		
		
		revalidate();
	}
	
	
	
	protected void buildQuickValidatorPanel() {
		// Build validator panel
		quickValidatorPanel = new QuickValidatorPanel(this, controller);
		this.add(quickValidatorPanel, BorderLayout.NORTH);		
	}
	
	protected void buildMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		transactionPanel = buildTransactionPanel();
		worldPanel = buildWorldPanel();
		conjecturePanel = buildConjecturePanel();
		
		JSplitPane conjectureSplitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, worldPanel, conjecturePanel);
		conjectureSplitter.setOneTouchExpandable(true);
        conjectureSplitter.setContinuousLayout(true);
        // conjectureSplitter.setDividerLocation(0.2);
        conjectureSplitter.setResizeWeight(0.7);
        
		JPanel inBetween = new JPanel();
		inBetween.setLayout(new BorderLayout());
		inBetween.add(conjectureSplitter, BorderLayout.CENTER);
		
		JSplitPane transactionSplitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, transactionPanel, inBetween);
		transactionSplitter.setOneTouchExpandable(true);
        transactionSplitter.setContinuousLayout(true);
		// transactionSplitter.setDividerLocation(0.2);
		transactionSplitter.setResizeWeight(0.2);
		
		mainPanel.add(transactionSplitter);
		
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	protected JPanel buildTransactionPanel() {
		/*
		JPanel panel = new JPanel();
		panel.setBackground(Color.yellow);
		
		JLabel msg = new JLabel("In this panel, all transactions will be visible");
		
		panel.add(msg);
		*/
		TransactionPanel p = new TransactionPanel(controller, specification, parent);
		
		return p;
	}
	
	protected JPanel buildWorldPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JTabbedPane tabs = new JTabbedPane();
		
		tabs.addTab("Elements", new ElementListingPanel(this.controller, this));
		tabs.addTab("Relations", new RelationListingPanel(this.controller, this));
		
		panel.add(tabs, BorderLayout.CENTER);
		return panel;
	}
	
	protected JPanel buildConjecturePanel() {
		JPanel panel = new ConjecturePanel(this, controller);
				
		return panel;
	}
	
	protected JMenu buildFileMenu() {
		// File menu
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("File menu");
		
		
		addNewMenuItem("Exit", KeyEvent.VK_X, menu, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		return menu;
	}
	
	protected JMenu buildWorldMenu() {
		JMenu menu = new JMenu("World");
		menu.setMnemonic(KeyEvent.VK_W);
		
		addNewMenuItem("Open...", KeyEvent.VK_X, menu, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				loadWorldFromFile();
			}
		});
		
		addNewMenuItem("Export...", KeyEvent.VK_X, menu, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exportWorldToFile();
			}
		});
		
		menu.addSeparator();
		
		addNewMenuItem("Add element", KeyEvent.VK_E, menu, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateElementDialog.createElement(controller, parent, "");	
			}
		});
		
		addNewMenuItem("Add relation", KeyEvent.VK_R, menu, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RelationDialog.showCreateRelationDialog(controller, parent, "");
			}
		});
		
		return menu;
	}
	
	protected JMenu buildTransactionMenu() {
		JMenu menu = new JMenu("Transactions");
		menu.setMnemonic(KeyEvent.VK_T);
		
		addNewMenuItem("Open...", KeyEvent.VK_O, menu, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadSpecificationFromFile();
			}
		});
		addNewMenuItem("Export...", KeyEvent.VK_X, menu, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exportSpecToFile();				
			}
		});
		
		menu.addSeparator();
		
		addNewMenuItem("Add transaction", KeyEvent.VK_A, menu, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Transaction t = TransactionDialog.showDialog(parent);
				if (t != null) {
					specification.addTransaction(t);
				}
			}
		});
		
		
		return menu;
	}
	
	protected JMenu buildHelpMenu() {
		JMenu menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription("File menu");
		
		addNewMenuItem("About", KeyEvent.VK_A, menu, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(parent, "This tool is built by Jan Martijn van der Werf for the project on Information System Modeling");	
			}
		});
		
		return menu;
	}
	
	protected void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.add(buildFileMenu());
		menuBar.add(buildWorldMenu());
		menuBar.add(buildTransactionMenu());
		menuBar.add(buildHelpMenu());
				
		setJMenuBar(menuBar);
	}
	
	private JMenuItem addNewMenuItem(String label, int keyEvent, JMenu menu, ActionListener listener) {
		JMenuItem item = new JMenuItem(label);
		item.setMnemonic(keyEvent);
		
		if (listener != null) item.addActionListener(listener);
		
		menu.add(item);
		
		return item;
	}


	@Override
	public void onNotify(TrueWorld world) {
		setTitle(TITLE + " (" + world.getFileName() + ")" + (world.isModified() ? " *" : ""));		
	}
	
	@Override
	public void onReset() {
		// Do nothing
	}
	
	private void exportSpecToFile() {
		@SuppressWarnings("serial")
		JFileChooser saveDialog = new JFileChooser() {

			@Override
			public void approveSelection(){
		        File f = getSelectedFile();
		        if(f.exists() && getDialogType() == SAVE_DIALOG){
		            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
		            switch(result){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    return;
		                case JOptionPane.NO_OPTION:
		                    return;
		                case JOptionPane.CLOSED_OPTION:
		                    return;
		                case JOptionPane.CANCEL_OPTION:
		                    cancelSelection();
		                    return;
		            }
		        }
		        super.approveSelection();
			}
		};
		
		saveDialog.addChoosableFileFilter(specFileFilter);
		saveDialog.setFileFilter(specFileFilter);
		
		if (!specification.getFileName().isEmpty()) {
			File f = new File(specification.getFileName());
			
			// System.out.println(f);
			if (f.exists()) saveDialog.setSelectedFile(f);
		}
		
		
		int result = saveDialog.showSaveDialog(parent);
		
		if (result == JOptionPane.OK_OPTION) {
	
		    try {
				specification.export(new FileOutputStream(saveDialog.getSelectedFile()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
	}
	
	private void exportWorldToFile() {
		@SuppressWarnings("serial")
		JFileChooser saveDialog = new JFileChooser() {

			@Override
			public void approveSelection(){
		        File f = getSelectedFile();
		        if(f.exists() && getDialogType() == SAVE_DIALOG){
		            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
		            switch(result){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    return;
		                case JOptionPane.NO_OPTION:
		                    return;
		                case JOptionPane.CLOSED_OPTION:
		                    return;
		                case JOptionPane.CANCEL_OPTION:
		                    cancelSelection();
		                    return;
		            }
		        }
		        super.approveSelection();
			}
		};
		
		saveDialog.addChoosableFileFilter(tffFileFilter);
		saveDialog.setFileFilter(tffFileFilter);
		
		if (!controller.getModel().getFileName().isEmpty()) {
			File f = new File(controller.getModel().getFileName());
			
			System.out.println(f);
			if (f.exists()) saveDialog.setSelectedFile(f);
		}
		
		
		int result = saveDialog.showSaveDialog(parent);
		
		if (result == JOptionPane.OK_OPTION) {
	
		    try {
				controller.export(new FileOutputStream(saveDialog.getSelectedFile()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
	}
	
	private void loadSpecificationFromFile() {
		JFileChooser fileChooser = new JFileChooser();
		// fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		if (specification.getFileName() != null || !specification.getFileName().isEmpty()) {
			fileChooser.setCurrentDirectory(new File(specification.getFileName()));
		}
		
		fileChooser.addChoosableFileFilter(specFileFilter);
		fileChooser.setFileFilter(specFileFilter);
		
		int result = fileChooser.showOpenDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    try {
				specification.open(new FileInputStream(selectedFile), selectedFile.getAbsolutePath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File: " + selectedFile + " not found!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private void loadWorldFromFile() {
		JFileChooser fileChooser = new JFileChooser();
		// fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		if (controller.getFileName() != null || !controller.getFileName().isEmpty()) {
			fileChooser.setCurrentDirectory(new File(controller.getFileName()));
		}
		fileChooser.addChoosableFileFilter(tffFileFilter);
		fileChooser.setFileFilter(tffFileFilter);
		
		int result = fileChooser.showOpenDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    try {
				controller.open(new FileInputStream(selectedFile), selectedFile.getAbsolutePath());
			} catch (FileNotFoundException e) {
				System.out.println("File: " + selectedFile + " not found!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
