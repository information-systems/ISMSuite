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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
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
	
	private Controller controller;

	private MainWindow parent;
		
	public MainWindow() {
		this(new Controller());
	}
	
	public MainWindow(Controller controller) {
		if (controller == null) {
			controller = new Controller();
		}
	
		this.controller = controller;
		
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
							System.exit(0);
							break;
						case JOptionPane.NO_OPTION:
							
							System.exit(0);
							break;
						default:
						case JOptionPane.CANCEL_OPTION:
							System.out.println("cancel");
							break;
					}
				} else {
					// nothing changed, just close
					System.exit(0);
				}
			}
		});
		
		this.controller.register(this);
		notify(this.controller.getModel());
	}

	// Invoke/show the UI.
	public static void invokeUI(Controller controller) throws InvocationTargetException, InterruptedException {
		EventQueue.invokeAndWait(new Runnable() {
			@Override public void run() {
				try {
					final MainWindow frame = new MainWindow(controller);
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
        conjectureSplitter.setDividerLocation(0.33);
        conjectureSplitter.setResizeWeight(0.7);
        
		JPanel inBetween = new JPanel();
		inBetween.setLayout(new BorderLayout());
		inBetween.add(conjectureSplitter, BorderLayout.CENTER);
		
		JSplitPane transactionSplitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, transactionPanel, inBetween);
		transactionSplitter.setOneTouchExpandable(true);
        transactionSplitter.setContinuousLayout(true);
		transactionSplitter.setDividerLocation(0.33);
		transactionSplitter.setResizeWeight(0.3);
		
		mainPanel.add(transactionSplitter);
		
		this.add(mainPanel, BorderLayout.CENTER);
	}
	
	protected JPanel buildTransactionPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		
		return panel;
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
	
	protected void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		// File menu
		JMenu mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		mnFile.getAccessibleContext().setAccessibleDescription("File menu");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic(KeyEvent.VK_X);
		mntmExit.getAccessibleContext().setAccessibleDescription("Exit It's a True World");
		
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		mnFile.add(mntmExit);
		
		
		JMenu mnWorld = new JMenu("World");
		mnWorld.setMnemonic(KeyEvent.VK_W);
		
		JMenuItem mntmOpenWorld = new JMenuItem("Open ...");
		mntmOpenWorld.setMnemonic(KeyEvent.VK_O);
		mntmOpenWorld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				loadWorldFromFile();
			}
		});
		mntmOpenWorld.getAccessibleContext().setAccessibleDescription("Open a TFF-based world file");
		mnWorld.add(mntmOpenWorld);
		
		JMenuItem mntmExportWorld = new JMenuItem("Export...");
		mntmExportWorld.setMnemonic(KeyEvent.VK_E);
		mntmExportWorld.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exportWorldToFile();
			}
		});
		mnWorld.add(mntmExportWorld);
		
		mnWorld.addSeparator();
		
		JMenuItem mntmAddElement = new JMenuItem("Add element");
		mntmAddElement.setMnemonic(KeyEvent.VK_E);
		mntmAddElement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateElementDialog.createElement(controller, parent, "");	
			}
		});
		
		JMenuItem mntmAddRelation = new JMenuItem("Add relation");
		mntmAddRelation.setMnemonic(KeyEvent.VK_R);
		mntmAddRelation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RelationDialog.showCreateRelationDialog(controller, parent, "");
			}
		});
		
		mnWorld.add(mntmAddElement);
		mnWorld.add(mntmAddRelation);
		
		menuBar.add(mnWorld);
		
		setJMenuBar(menuBar);
	}


	@Override
	public void notify(TrueWorld world) {
		setTitle(TITLE + " (" + world.getFileName() + ")" + (world.isModified() ? " *" : ""));		
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
	
	
	private void loadWorldFromFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		
		fileChooser.addChoosableFileFilter(tffFileFilter);
		fileChooser.setFileFilter(tffFileFilter);
		
		int result = fileChooser.showOpenDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    try {
				controller.open(new FileInputStream(selectedFile), selectedFile.getAbsolutePath());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File: " + selectedFile + " not found!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
