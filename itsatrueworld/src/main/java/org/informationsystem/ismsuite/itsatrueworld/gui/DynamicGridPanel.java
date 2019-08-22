package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class DynamicGridPanel {

	private JPanel gridPanel;
	
	private List<JPanel> rows = new ArrayList<>();
	private int currentRow = -1;
	
	private List<JPanel> panels = new ArrayList<>();
	
	private int columns = 3;
	private int panelHeight = 200;
	private Dimension prefDimension;
		
	public DynamicGridPanel() {
		this(3, 200);
	}
	
	/**
	 * 
	 * @param columns The number of columns each row should have
	 * @param panelHeight fixed height of each panel in the grid
	 */
	public DynamicGridPanel(int columns, int panelHeight) {
		this.columns = columns;
		this.panelHeight = panelHeight;
		
		gridPanel = new JPanel();
		gridPanel.setLayout(new BoxLayout(gridPanel, BoxLayout.PAGE_AXIS));
		gridPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		
		gridPanel.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {
				updateMaxWidth();
			}
		});
		
		updateMaxWidth();
	}
	
	public JPanel getPanel() {
		return gridPanel;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public DynamicGridPanel setColumns(int columns) {
		this.columns = columns;
		
		recalculate();
		
		return this;
	}
	
	public void addPanel(JPanel panel) {
		panel.setPreferredSize(prefDimension);
		panel.setSize(prefDimension);
		
		JPanel row = getCurrentRow(); 
		if (row.getComponentCount() == columns) {
			row = createNewRow();
		}
		
		panels.add(panel);
		
		row.add(panel);
	
		updateMaxWidth();
	}
	
	public void removePanel(JPanel panel) {
		if (panels.remove(panel)) {
			recalculate();
		}
	}
	
	private JPanel getCurrentRow() {
		if (currentRow < 0) {
			return createNewRow();
		} else {
			return rows.get(currentRow);
		}
	}
	
	private JPanel createNewRow() {
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.LINE_AXIS));
		row.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		
		rows.add(row);
		currentRow++;
		
		gridPanel.add(row);
		
		return row;
	}
	
	private void updateMaxWidth() {
		int width = gridPanel.getWidth();
		
		prefDimension = new Dimension(width / columns, panelHeight);
			
		for(JPanel p : panels) {
			p.setMaximumSize(prefDimension);
			p.setMinimumSize(prefDimension);
			p.setSize(prefDimension);
			p.revalidate();
		}
		
		gridPanel.revalidate();
		gridPanel.repaint();
	}
	
	private void resetRows() {
		rows.clear();
		currentRow = -1;
	}
	
	private void recalculate() {
		resetRows();
		gridPanel.removeAll();
		
		for(JPanel p: panels) {
			JPanel row = getCurrentRow(); 
			if (row.getComponentCount() == columns) {
				row = createNewRow();
			}
			row.add(p);
		}
		
		updateMaxWidth();
	}	
}
