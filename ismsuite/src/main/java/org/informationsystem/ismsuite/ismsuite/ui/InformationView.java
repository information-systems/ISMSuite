package org.informationsystem.ismsuite.ismsuite.ui;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.informationsystem.ismsuite.ismsuite.model.Model;
import org.informationsystem.ismsuite.ismsuite.model.StateChangedListener;
import org.informationsystem.ismsuite.prover.model.Relation;

public class InformationView extends JPanel implements StateChangedListener {

	// private DefaultListModel relationModel;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1127483435738278658L;

	private GridContainerPanel grid;
	
	private Map<String, JPanel> relationPanels = new HashMap<>();
	@SuppressWarnings("rawtypes")
	private Map<String, DefaultListModel> relationModels = new HashMap<>();

	public InformationView(Model m) {
		m.addListener(this);
		
		setLayout(new BorderLayout());
		
		grid = new GridContainerPanel(5,150,200);
		JScrollPane scroll = new JScrollPane(grid);
		
		add(scroll, BorderLayout.CENTER);
		
		update(m);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Model model) {
		for(String label: model.getWorld().getRelationLabels()) {
			if(!relationPanels.containsKey(label)) {
				JPanel p = grid.getNewPanel();
				p.setLayout(new BorderLayout());
				p.add(new JLabel(label), BorderLayout.NORTH);
				
				relationModels.put(label, new DefaultListModel<>());
				
				@SuppressWarnings("rawtypes")
				JList relationList = new JList(relationModels.get(label));
				
				relationList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				relationList.setLayoutOrientation(JList.VERTICAL);
				relationList.setVisibleRowCount(-1);
				JScrollPane listScroller = new JScrollPane(relationList);
				
				p.add(listScroller, BorderLayout.CENTER);
				
				relationPanels.put(label, p);
			}
			
			// Now we know it exists, so we can update the list
			@SuppressWarnings("rawtypes")
			DefaultListModel relModel = relationModels.get(label);
			relModel.clear();
			for(Relation r: model.getWorld().getRelations(label)) {
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
