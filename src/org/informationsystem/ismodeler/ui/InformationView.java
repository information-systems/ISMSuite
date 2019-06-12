package org.informationsystem.ismodeler.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.informationsystem.ismodeler.model.Model;
import org.informationsystem.ismodeler.model.StateChangedListener;
import org.informationsystem.proving.model.Clause;
import org.informationsystem.proving.model.Relation;

public class InformationView extends JPanel implements StateChangedListener {

	private DefaultListModel relationModel;

	public InformationView(Model m) {
		m.addListener(this);
		
		setBackground(Color.blue);
				
		relationModel = new DefaultListModel<>();
		JList relationList = new JList(relationModel);
		
		relationList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		relationList.setLayoutOrientation(JList.VERTICAL);
		relationList.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(relationList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		setLayout(new BorderLayout());
		add(listScroller, BorderLayout.CENTER);
		
		update(m);
		
	}

	@Override
	public void update(Model model) {
		relationModel.clear();
		
		Iterator<String> itEl = model.getWorld().elementLabels();
		while(itEl.hasNext()) {
			relationModel.addElement("Element: " + itEl.next());
		}
		
		
		Iterator<Relation> it = model.getWorld().relations();
		
		while(it.hasNext()) {
			Relation r = it.next();
			relationModel.addElement(r.toTFF());
		}
	}
}
