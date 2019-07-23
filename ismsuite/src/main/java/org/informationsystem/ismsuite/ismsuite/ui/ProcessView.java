package org.informationsystem.ismsuite.ismsuite.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.informationsystem.ismsuite.processengine.process.BoundTransition;
import org.informationsystem.ismsuite.ismsuite.model.Controller;
import org.informationsystem.ismsuite.ismsuite.model.Model;
import org.informationsystem.ismsuite.ismsuite.model.StateChangedListener;

public class ProcessView extends JPanel implements StateChangedListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -138817495785697164L;
		
	private JPanel buttonPanel;
	private DefaultListModel<BoundTransition> transitionListModel;
	
	private JButton fireButton;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ProcessView(Controller controller) {		
		controller.getModel().addListener(this);
		
		// set a nice border
		setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Process view"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		setLayout(new BorderLayout());
		
		transitionListModel = new DefaultListModel<>();
		JList transitionList = new JList(transitionListModel);
		
		transitionList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		transitionList.setLayoutOrientation(JList.VERTICAL);
		transitionList.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(transitionList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		add(listScroller, BorderLayout.CENTER);
		
		buttonPanel = new JPanel();
		
		fireButton = new JButton("Fire");
		fireButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// get Selected item
				if (transitionList.getSelectedIndex() >= 0) {
					BoundTransition item = transitionListModel.getElementAt(transitionList.getSelectedIndex());
					controller.fire(item);
				}
			}
		});
		buttonPanel.add(fireButton);
		
		add(buttonPanel, BorderLayout.EAST);
		
		update(controller.getModel());
	}

	@Override
	public void update(Model model) {
		transitionListModel.clear();
		List<BoundTransition> items = new ArrayList<>(model.enabledTransitions());
		
		Collections.sort(items);
		
		for(BoundTransition t: items) {
			transitionListModel.addElement(t);
		}
	}

}
