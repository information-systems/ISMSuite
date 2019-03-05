package org.informationsystem.ismodeler.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import org.informationsystem.ismodeler.model.Controller;
import org.informationsystem.ismodeler.model.Model;

public class MainFrame extends JFrame {

	private ProcessView processView;
	private InformationView informationView;
	private JSplitPane splitter;
	
	private Controller controller;
	
	public MainFrame(Controller controller) {
		this.controller = controller;
		
		setTitle("ISModeler");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1328, 700); // The bounds of the frame/
		
		setLayout(new BorderLayout());
		
		processView = new ProcessView(controller);
		informationView = new InformationView(controller.getModel());
		
		splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, processView, informationView);
		splitter.setOneTouchExpandable(true);
        splitter.setContinuousLayout(true);
				
		this.add(splitter, BorderLayout.CENTER);
	}
	
}
