package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.PlainDocument;

import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Literal;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.utils.LowerWordEnforcer;

public class RelationDialog {

	public static void showCreateRelationDialog(JFrame owner, Controller controller, String name) {
		DialogPane pane = new DialogPane(controller, name); 
				
		int result = JOptionPane.showOptionDialog(
	            owner, 
	            pane, 
	            "Create Relation", 
	            JOptionPane.OK_CANCEL_OPTION, 
	            JOptionPane.QUESTION_MESSAGE, null, null, pane);
		if (result != JOptionPane.OK_OPTION) {
			return;
		}
		
		List<Literal> parameters = pane.getElements();
		if (parameters.size() == 0) {
			return;
		}
		
		Relation r = new Relation(pane.getRelationName(), pane.getElements());
		controller.addRelation(r);
	}
	
	public static class DialogPane extends JPanel {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ElementTableModel parameters;
		private JTextField nameField;
		
		public List<Literal> getElements() {
			ArrayList<Literal> elems = new ArrayList<>();
			for(int i = 0 ; i < parameters.getRowCount() ; i++) {
				elems.add(new Element((String)parameters.getValueAt(i, 0), (String) parameters.getValueAt(i, 1)));
			}
			return elems;
		}
		
		public String getRelationName() {
			return nameField.getText();
		}
		
		public DialogPane(Controller controller, String name) {
			
			setLayout(new BorderLayout(5,5));
			
			parameters = new ElementTableModel();
			parameters.addTableModelListener(new TableModelListener() {
				
				@Override
				public void tableChanged(TableModelEvent e) {
					int firstRow = e.getFirstRow();
					int lastRow = e.getLastRow();
					int index = e.getColumn();
					
					// Only interested in the first column
					if (index > 0) return;
					if (index == TableModelEvent.ALL_COLUMNS) return;
					
					switch (e.getType()) {
						case TableModelEvent.UPDATE:
							for(int row = firstRow ; row <= lastRow ; row++) {
								String item = (String) parameters.getValueAt(row, 0);
								String type = controller.getModel().getWorld().findTypeFor(item);
								parameters.setValueAt(type, row, 1);
							}
					}
				}
			});
			
			
			JPanel table = new JPanel(new BorderLayout());
			
			JTable parameterTable = new JTable(parameters);
			parameterTable.setShowHorizontalLines(true);
			parameterTable.setShowGrid(true);
			
			table.add(parameterTable.getTableHeader(), BorderLayout.PAGE_START);
			table.add(parameterTable, BorderLayout.CENTER);
			
			add(table, BorderLayout.CENTER);
			
			// Now set the editor for the elements as a combobox
			DefaultComboBoxModel<String> elementModel = new DefaultComboBoxModel<>();
			Iterator<String> it = controller.getModel().getWorld().getElementLabels();
			while(it.hasNext()) {
				elementModel.addElement(it.next());
			}
			
			JComboBox<String> elements = new JComboBox<>(elementModel);
			
			TableColumn elementColumn = parameterTable.getColumnModel().getColumn(0);
			elementColumn.setCellEditor(new DefaultCellEditor(elements));
			
			JPanel buttons = new JPanel();
			buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
			
			JButton up = new JButton("up");
			up.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int index = parameterTable.getSelectedRow();
					if (index > 0) {
						parameters.moveUp(index);
						parameterTable.setRowSelectionInterval(index-1, index-1);
					}
				}
			});
			JButton down = new JButton("down");
			down.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int index = parameterTable.getSelectedRow();
					if (index >= 0 && index < parameters.getRowCount()-1) {
						parameters.moveDown(index);
						parameterTable.setRowSelectionInterval(index+1, index+1);
					}
				}
			});
			JButton del = new JButton("delete");
			del.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					int index = parameterTable.getSelectedRow();
					if (index >=0 ) {
						parameters.removeRow(index);
					}
					
				}
				
			});
			JButton add = new JButton("add");
			add.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					parameters.addRow("","");
					
				}
			});
			
			buttons.add(up);
			buttons.add(Box.createRigidArea(new Dimension(0,5)));
			buttons.add(down);
			buttons.add(Box.createRigidArea(new Dimension(0,5)));
			buttons.add(add);
			buttons.add(Box.createRigidArea(new Dimension(0,5)));
			buttons.add(del);
						
			add(buttons, BorderLayout.EAST);
			
			JPanel namePanel = new JPanel(new BorderLayout(5,5));
			JLabel nameLabel = new JLabel("Relation: ");
			namePanel.add(nameLabel, BorderLayout.WEST);
			
			nameField = new JTextField(name);
			PlainDocument doc = (PlainDocument) nameField.getDocument();
			doc.setDocumentFilter(new LowerWordEnforcer());
			namePanel.add(nameField, BorderLayout.CENTER);
			
			add(namePanel, BorderLayout.NORTH);
						
			setSize(new Dimension(50,50));
			
		}
		
	}
	
	private static class ElementTableModel extends AbstractTableModel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -2121523840450229168L;

		public class Pair {
			
			public String first;
			public String second;
			
			public Pair(String first, String second) {
				this.first = first;
				this.second = second;
			}
			
			public String get(int column) {
				if (column == 0) return first;
				if (column == 1) return second;
				
				return "";
			}
			
			public void put(int column, String value) {
				if (column == 0) first = value;
				if (column == 1) second = value; 
			}
		}
		
		List<Pair> data = new ArrayList<>();
		
		public void addRow(String name, String type) {
			data.add(new Pair(name, type));
			fireTableRowsInserted(data.size()-1, data.size()-1);
		}
		
		public void removeRow(int row) {
			data.remove(row);
			fireTableRowsDeleted(row, row);
		}
		
		public void moveUp(int row) {
			if (row > 0) swap(row, row-1);
		}
		
		public void moveDown(int row) {
			if (row < getRowCount() - 1) swap(row, row+1);
		}
		
		public void swap(int row1, int row2) {
			Pair temp = data.get(row2);
			data.set(row2, data.get(row1));
			data.set(row1, temp);
			
			fireTableRowsUpdated(row1, row1);
			fireTableRowsUpdated(row2, row2);
		}
		
		@Override
		public int getColumnCount() {
			return 2;
		}
		
		@Override
		public String getColumnName(int column) {
			if (column == 0) {
				return "Element";
			} else {
				return "Type";
			}
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public Object getValueAt(int row, int column) {
			if (row >= getRowCount() || row < 0) return null;
			if (column > 1 || column < 0) return null;
			return data.get(row).get(column);
		}
		
		@Override
		public Class<?> getColumnClass(int c) {
	        return String.class;
	    }
		
		@Override
		public boolean isCellEditable(int row, int col) {
			if (col == 0) return true;
			return false;
		}
		
		@Override
		public void setValueAt(Object value, int row, int col) {
			if (row >= getRowCount() || row < 0) return;
			if (col > 1 || col < 0) return;
			
			if (value instanceof String) {
				data.get(row).put(col, (String) value);
				fireTableCellUpdated(row, col);
			}
	    }
		
	}
	
	
}
