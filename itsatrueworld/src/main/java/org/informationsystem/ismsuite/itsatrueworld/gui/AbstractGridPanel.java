package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorldListener;


@SuppressWarnings("serial")
public abstract class AbstractGridPanel<E> extends JPanel implements TrueWorldListener {

	private Map<String, DefaultListModel<E>> listmodels = new HashMap<>();
	private Map<String, JPanel> panels = new HashMap<>();
	private Map<JList<E>, String> lists = new HashMap<>();
	private Map<String, E> selectedItem = new HashMap<>();
	
	private Controller controller;
	
	public Controller getController() {
		return controller;
	}
	
	protected DynamicGridPanel grid;
	
	public AbstractGridPanel(Controller controller) {
		this(controller, 3);
	}
	
	public AbstractGridPanel(Controller controller, int columns) {
		this(controller, columns, 200);
	}
	
	public AbstractGridPanel(Controller controller, int columns, int panelHeight) {
		this.controller = controller;
		
		this.setLayout(new BorderLayout());
		
		grid = new DynamicGridPanel(columns, panelHeight);
		
		JScrollPane scrollPanel = new JScrollPane(grid.getPanel());
		
		this.add(scrollPanel, BorderLayout.CENTER);
		
		this.controller.register(this);
		this.notify(controller.getModel());
	}
	
	public DefaultListModel<E> getModel(String label) {
		if (!panels.containsKey(label)) {
			getPanel(label);
		}
		return listmodels.get(label);
	}
	
	public JPanel getPanel(String label) {
		if (!panels.containsKey(label)) {
			JPanel p = createNewPanel(label);
			decoratePanel(p,label);
			grid.addPanel(p);
			panels.put(label, p);
		} 
		
		return panels.get(label);
		
	}
	
	protected JPanel createNewPanel(String label) {
		return new JPanel(new BorderLayout(5,5));
	}
	
	protected void decoratePanel(JPanel p, String label) {
		listmodels.put(label, new DefaultListModel<E>());
		JList<E> list = new JList<E>(listmodels.get(label));
		lists.put(list, label);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					@SuppressWarnings("rawtypes")
					JList l = (JList) arg0.getSource();
					String type = lists.get(l);
					E item = listmodels.get(type).get(l.getSelectedIndex());
					
					selectedItem.put(type, item);
					
				} catch(Exception e) {}
			}
		});
		
		JScrollPane scroller = new JScrollPane(list);
		
		p.add(scroller, BorderLayout.CENTER);
		
		p.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(5, 5, 5, 5),
						BorderFactory.createCompoundBorder(
								BorderFactory.createLineBorder(Color.black, 1, true),
								BorderFactory.createEmptyBorder(5, 5, 5, 5)
						)
				)
		);
	}
	
	public E getSelectedItemOf(String label) {
		if (selectedItem.containsKey(label)) {
			return selectedItem.get(label);
		} else {
			return null;
		}
	}
	
}
