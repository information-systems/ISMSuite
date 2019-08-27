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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	
	Controller controller;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.setMnemonic(KeyEvent.VK_O);
		mntmOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Typed First-order logic Formulae", "tff"));
				
				int result = fileChooser.showOpenDialog(parent);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    try {
						controller.open(new FileInputStream(selectedFile));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("File: " + selectedFile + " not found!");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		mntmOpen.getAccessibleContext().setAccessibleDescription("Open a TFF-based world file");
		mnFile.add(mntmOpen);
			
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setMnemonic(KeyEvent.VK_X);
		mntmExit.getAccessibleContext().setAccessibleDescription("Exit It's a True World");
		
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();				
			}
		});
		
		mnFile.addSeparator();
		mnFile.add(mntmExit);
		
		
		JMenu mnWorld = new JMenu("World");
		mnWorld.setMnemonic(KeyEvent.VK_W);
		
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
		setTitle(TITLE + " (" + world.getName() + ")" + (world.isModified() ? " *" : ""));		
	}

	

}
