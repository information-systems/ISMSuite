/**
 * 
 */
package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;

/**
 * @author jmw
 *
 */
public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9217156852218912678L;
	
	Controller controller;
	
	public MainWindow() {
		this(new Controller());
	}
	
	public MainWindow(Controller controller) {
		if (controller == null) {
			controller = new Controller();
		}
	
		this.controller = controller;
		
		initialize();
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
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JMenuItem mntmExit;
	
	private JPanel quickValidatorPanel;
	
	private JPanel transactionPanel;
	private JPanel worldPanel;
	private JPanel conjecturePanel;
	private JTextArea quickText;
	
	
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
		setTitle("It's a true world");
		setLayout(new BorderLayout());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(500, 500));
		this.setLayout(new BorderLayout());
		
		// Build menu
		buildMenu();
		
		buildQuickValidatorPanel();
		
		this.setExtendedState( this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
		revalidate();
	}
	
	protected void buildQuickValidatorPanel() {
		// Build validator panel
		quickValidatorPanel = new JPanel();
		quickValidatorPanel.setLayout(new BorderLayout());
		
		quickText = new JTextArea(4, 80);
		JScrollPane scrollPane = new JScrollPane(quickText);
		
		quickValidatorPanel.add(scrollPane, BorderLayout.CENTER);
		quickValidatorPanel.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(5, 5, 5, 5),
						BorderFactory.createCompoundBorder(
								BorderFactory.createLineBorder(Color.black, 1, true),
								BorderFactory.createEmptyBorder(5, 5, 5, 5)
						)
				)
		);
						
		JPanel quickButtonPanel = new JPanel();
		quickButtonPanel.setLayout(new BorderLayout());
		
		JPanel holder = new JPanel();
		holder.setLayout(new GridLayout(1, 2));
		
		JButton validateButton = new JButton("Validate");
		holder.add(validateButton);
		
		JButton addButton = new JButton("Add");
		holder.add(addButton);
		
		quickButtonPanel.add(holder, BorderLayout.EAST);
		
		quickValidatorPanel.add(quickButtonPanel, BorderLayout.SOUTH);
		
		this.add(quickValidatorPanel, BorderLayout.NORTH);		
	}
	
	protected void buildMenu() {
		menuBar = new JMenuBar();
		
		// File menu
		mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		mnFile.getAccessibleContext().setAccessibleDescription("File menu");
		menuBar.add(mnFile);
		
		mntmOpen = new JMenuItem("Open...");
		mntmOpen.setMnemonic(KeyEvent.VK_O);
		mntmOpen.getAccessibleContext().setAccessibleDescription("Open a TFF-based world file");
		mnFile.add(mntmOpen);
			
		mntmExit = new JMenuItem("Exit");
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
		
		setJMenuBar(menuBar);
	}

}
