package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorldListener;


@SuppressWarnings("serial")
public abstract class AbstractGridPanel<E> extends JPanel implements TrueWorldListener, GridContainerPanel.GridPanelListener {

	private GridContainerPanel grid;

	private Map<String, DefaultListModel<E>> listmodels = new HashMap<>();
	
	private Controller controller;
	
	public Controller getController() {
		return controller;
	}
	
	public AbstractGridPanel(Controller controller) {
		this(controller, false, false);
	}

	public AbstractGridPanel(Controller controller, boolean withAddButton, boolean withRemoveButton) {
		this.controller = controller;
		
		grid = new GridContainerPanel(4, 100, 200, withAddButton, withRemoveButton);
		JScrollPane scroller = new JScrollPane(grid);
		
		this.setLayout(new BorderLayout());
		this.add(scroller, BorderLayout.CENTER);		
		
		grid.addGridListener(this);
		
		this.controller.register(this);
	}
	
	protected JPanel getPanelFor(String name) {
		if (grid.containsPanel(name)) {
			return grid.getPanel(name);
		}
		
		JPanel p = grid.getPanel(name);
		p.add(new JLabel(name), BorderLayout.NORTH);
		
		listmodels.put(name, new DefaultListModel<E>());
		
		JList<E> list = new JList<E>(listmodels.get(name));
		
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
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
		
		return p;
	}
	
	protected DefaultListModel<E> getModel(String name) {
		// If the panel does not yet exist, we first need to create
		// a panel for it.
		if (!grid.containsPanel(name)) {
			getPanelFor(name);
		}
		return listmodels.get(name);
	}
	
	@Override
	public void addAction(String label) {
		JOptionPane.showMessageDialog(this, label);
	}
	
	@Override
	public void removeAction(String label) {
		JOptionPane.showMessageDialog(this, label);
	}
}
