package org.informationsystem.ismodeler.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.informationsystem.ismodeler.model.Model;
import org.informationsystem.ismodeler.model.StateChangedListener;
import org.informationsystem.proving.model.Clause;
import org.informationsystem.proving.model.Relation;

public class InformationView extends JPanel implements StateChangedListener {

	// private DefaultListModel relationModel;
	
	private GridContainerPanel grid;
	
	private Map<String, JPanel> relationPanels = new HashMap<>();
	private Map<String, DefaultListModel> relationModels = new HashMap<>();

	public InformationView(Model m) {
		m.addListener(this);
		
		setLayout(new BorderLayout());
		
		grid = new GridContainerPanel(5,150,200);
		JScrollPane scroll = new JScrollPane(grid);
		
		add(scroll, BorderLayout.CENTER);
		
		update(m);
		
	}

	@Override
	public void update(Model model) {
		for(String label: model.getWorld().relationLabels()) {
			if(!relationPanels.containsKey(label)) {
				JPanel p = grid.getNewPanel();
				p.setLayout(new BorderLayout());
				p.add(new JLabel(label), BorderLayout.NORTH);
				
				relationModels.put(label, new DefaultListModel<>());
				
				JList relationList = new JList(relationModels.get(label));
				
				relationList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				relationList.setLayoutOrientation(JList.VERTICAL);
				relationList.setVisibleRowCount(-1);
				JScrollPane listScroller = new JScrollPane(relationList);
				
				p.add(listScroller, BorderLayout.CENTER);
				
				relationPanels.put(label, p);
			}
			
			// Now we know it exists, so we can update the list
			DefaultListModel relModel = relationModels.get(label);
			relModel.clear();
			for(Relation r: model.getWorld().relations(label)) {
				relModel.addElement(r.toTFF());
			}
		}
	}
	
	public void updateOld(Model model) {
		/*
		relationModel.clear();
		
		Iterator<String> itEl = model.getWorld().elementLabels();
		while(itEl.hasNext()) {
			relationModel.addElement("Element: " + itEl.next());
		}
		
		
		Iterator<Relation> it = model.getWorld().relations();
		
		while(it.hasNext()) {
			Relation r = it.next();
			relationModel.addElement(r.toTFF());
		}*/
	}
}
