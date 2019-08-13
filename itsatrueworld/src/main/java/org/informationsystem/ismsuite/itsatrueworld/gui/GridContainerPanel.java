package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GridContainerPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6282450744271769354L;
	private int columns = 6;
	private int panelHeight = 200;
	private int panelMinWidth = 200;
	
	private Map<String, InternalPanel> panels = new HashMap<>();
	// private Map<String, JPanel> panels = new HashMap<>();
	
	private JPanel currentRow = null;
	
	private Dimension prefDimension = new Dimension(panelMinWidth, panelHeight);

	private boolean withAddButton;
	private boolean withRemoveButton;
	
	public boolean containsPanel(String label) {
		return panels.containsKey(label);
	}
	
	private JPanel createRow() {
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.LINE_AXIS));
		row.setAlignmentX(LEFT_ALIGNMENT);
		return row;
	}
	
	public JPanel getPanel(String label) {
		if (panels.containsKey(label)) {
			return panels.get(label);
		}
		
		if (currentRow == null) {
			currentRow = createRow();
			add(currentRow);
		}
		if (currentRow.getComponentCount() == columns) {
			currentRow = createRow();
			add(currentRow);
		}
		
		InternalPanel panel = new InternalPanel(this, label, withAddButton, withRemoveButton);
		
		// JPanel panel = new JPanel();
		
		panel.setMaximumSize(prefDimension);
		panel.setPreferredSize(prefDimension);
		panel.setMinimumSize(prefDimension);		
		
		currentRow.add(panel);
		revalidate();
		
		panels.put(label, panel);
				
		return panel;
	}
	
	private void updateMaxWidth(int width) {
		prefDimension = new Dimension(Math.max(panelMinWidth, width / columns), panelHeight);
		
		for(JPanel p : panels.values()) {
			p.setMaximumSize(prefDimension);
			p.setMinimumSize(prefDimension);
			p.revalidate();
		}
		
		revalidate();
	}
	
	public GridContainerPanel(int columns, int panelMinWidth, int panelHeight) {
		this(columns, panelMinWidth, panelHeight, false, false);
	}
	
	public GridContainerPanel(int columns, int panelMinWidth, int panelHeight, boolean withAddButton, boolean withRemoveButton) {
		
		this.columns = columns;
		this.panelMinWidth = panelMinWidth;
		this.panelHeight = panelHeight;
		
		this.withAddButton = withAddButton;
		this.withRemoveButton = withRemoveButton;
		
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
	
	protected void addAction(String label) {
		for(GridPanelListener l : listeners) {
			l.addAction(label);
		}
	}
	
	protected void removeAction(String label) {
		for(GridPanelListener l : listeners) {
			l.removeAction(label);
		}
	}
	
	public void addGridListener(GridPanelListener l) {
		listeners.add(l);
	}
	
	public void removeGridListener(GridPanelListener l) {
		listeners.remove(l);
	}
	
	private Set<GridPanelListener> listeners = new HashSet<>();
	
	public interface GridPanelListener {
		void addAction(String label);
		void removeAction(String label);
	}
	
	class InternalPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = -42341578401225353L;

		private String label;
		private GridContainerPanel grid;
		
		public String getLabel() {
			return label;
		}
		
		public GridContainerPanel getGrid() {
			return grid;
		}
		
		public InternalPanel(GridContainerPanel parent, String label, boolean withAddButton, boolean withRemoveButton) {
			this.label = label;
			this.grid = parent;
			
			this.setLayout(new BorderLayout());
			
			if (withAddButton | withRemoveButton) {
				JPanel holder = new JPanel();
				holder.setLayout(new BorderLayout());
				
				JPanel buttons = new JPanel();
				buttons.setLayout(new GridLayout(1,2));
				
				if (withAddButton) {
					JButton add = new JButton("Add");
					add.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							getGrid().addAction(getLabel());
						}
					});
					buttons.add(add);
				}
				
				if (withRemoveButton) {
					JButton rem = new JButton("Remove");
					rem.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							getGrid().removeAction(getLabel());
						}
					});
					buttons.add(rem);
				}
				
				holder.add(buttons, BorderLayout.EAST);
				this.add(holder, BorderLayout.SOUTH);
			}
		}
		
	}

}
