package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorldListener;


@SuppressWarnings("serial")
public abstract class AbstractGridPanel<E> extends JPanel implements TrueWorldListener {

	private GridContainerPanel grid;
	private Map<String, JPanel> panels = new HashMap<>();

	private Map<String, DefaultListModel<E>> listmodels = new HashMap<>();
	
	private String prefix = "";
	
	public void setLabelPrefix(String prefix) {
		this.prefix = prefix;
		if (!prefix.endsWith(" ")) {
			prefix += " ";
		}
	}
	
	public String getLabelPrefix() {
		return prefix;
	}

	public AbstractGridPanel(Controller controller) {
		
		grid = new GridContainerPanel(4, 100, 200);
		JScrollPane scroller = new JScrollPane(grid);
		
		this.setLayout(new BorderLayout());
		this.add(scroller, BorderLayout.CENTER);
		
		controller.register(this);
	}
	
	protected JPanel getPanelFor(String name) {
		if (panels.containsKey(name)) {
			return panels.get(name);
		}
		
		JPanel p = grid.getNewPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel(getLabelPrefix() + name), BorderLayout.NORTH);
		
		listmodels.put(name, new DefaultListModel<E>());
		@SuppressWarnings("rawtypes")
		JList list = new JList<E>(listmodels.get(name));
		
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		JScrollPane scroller = new JScrollPane(list);
		
		p.add(scroller, BorderLayout.CENTER);
		
		panels.put(name, p);
		
		return p;
	}
	
	protected DefaultListModel<E> getModel(String name) {
		if (!panels.containsKey(name)) {
			getPanelFor(name);
		}
		
		return listmodels.get(name);
	}

}
