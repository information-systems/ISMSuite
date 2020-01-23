package org.informationsystem.ismsuite.ismsuite.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.informationsystem.ismsuite.ismsuite.model.Controller;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4029574147623942189L;
	private ProcessView processView;
	private InformationView informationView;
	private JSplitPane splitter;
	
	
	public MainFrame(Controller controller) {
		this(controller, true);
	}
	
	public MainFrame(Controller controller, boolean exitOnClose) {
		
		if (exitOnClose) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		setTitle("ISMSuite");
		
		setBounds(100, 100, 800, 700); // The bounds of the frame/
		
		
		setLayout(new BorderLayout());
		
		processView = new ProcessView(controller);
		informationView = new InformationView(controller.getModel());
		
		splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, processView, informationView);
		splitter.setOneTouchExpandable(true);
        splitter.setContinuousLayout(true);
				
		this.add(splitter, BorderLayout.CENTER);
	}
	
	
	public static void invokeUI(Controller controller) throws InvocationTargetException, InterruptedException {
		invokeUI(controller, true);
	}
	
	// Invoke/show the UI.
	public static void invokeUI(Controller controller, boolean exitOnClose) throws InvocationTargetException, InterruptedException {
		EventQueue.invokeAndWait(new Runnable() {
			@Override public void run() {
				try {
					final MainFrame frame = new MainFrame(controller, exitOnClose);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
