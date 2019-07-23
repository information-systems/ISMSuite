package org.informationsystem.ismsuite.ismsuite.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GridContainerPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6282450744271769354L;
	private int columns = 6;
	private int panelHeight = 200;
	private int panelMinWidth = 200;
	
	private List<JPanel> panels = new ArrayList<>();
	
	private JPanel currentRow = null;
	
	private Dimension prefDimension = new Dimension(panelMinWidth, panelHeight);
	
	private JPanel setupRow() {
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.LINE_AXIS));
		row.setAlignmentX(LEFT_ALIGNMENT);
		return row;
	}
	
	public JPanel getNewPanel() {
		if (currentRow == null) {
			currentRow = setupRow();
			add(currentRow);
		}
		if (currentRow.getComponentCount() == columns) {
			currentRow = setupRow();
			add(currentRow);
		}
		
		JPanel panel = new JPanel();
		
		panel.setMaximumSize(prefDimension);
		panel.setPreferredSize(prefDimension);
		panel.setMinimumSize(prefDimension);		
		
		currentRow.add(panel);
		revalidate();
		
		panels.add(panel);
				
		return panel;
	}
	
	private void updateMaxWidth(int width) {
		prefDimension = new Dimension(Math.max(panelMinWidth, width / columns), panelHeight);
		
		for(JPanel p : panels) {
			p.setMaximumSize(prefDimension);
			p.setMinimumSize(prefDimension);
			p.revalidate();
		}
		
		revalidate();
	}
	
	public GridContainerPanel(int columns, int panelMinWidth, int panelHeight) {
		
		this.columns = columns;
		this.panelMinWidth = panelMinWidth;
		this.panelHeight = panelHeight;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setAlignmentX(LEFT_ALIGNMENT);
		
		addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
				updateMaxWidth(c.getWidth());
			}
		});
	}

}
