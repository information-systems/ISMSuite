package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.text.PlainDocument;

import org.informationsystem.ismsuite.itsatrueworld.controller.WorldController;
import org.informationsystem.ismsuite.itsatrueworld.utils.LowerWordEnforcer;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.specifier.model.Transaction;

public class ExecuteTransactionDialog {

	ParameterTableModel parameters = new ParameterTableModel();
	
	private JPanel buildPane(Transaction t, WorldController world) {
		JPanel p = new JPanel(new BorderLayout(5,5));
		
		JLabel label = new JLabel("Transaction: " + t.getLabel());
		
		JTable table = new JTable(parameters);
		TableColumn elementColumn = table.getColumnModel().getColumn(ParameterTableModel.ELEMENT_NAME);
		elementColumn.setCellEditor(new ElementTypeBasedCellEditor(world));
		
		
		Iterator<Variable> it = t.variables();
		while(it.hasNext()) {
			Variable v = it.next();
			parameters.addRow(v);
		}
		
		p.add(label, BorderLayout.NORTH);
		p.add(table, BorderLayout.CENTER);
		
		return p;
	}
	
	public Map<Variable, Element> getBinding() {
		return parameters.getBinding();
	}
	
	
	public static void executeTransaction(WorldController world, Frame frame, Transaction t) {
		ExecuteTransactionDialog d = new ExecuteTransactionDialog();
		
		int result = JOptionPane.showOptionDialog(
	            frame, 
	            d.buildPane(t, world), 
	            "Execute transaction", 
	            JOptionPane.OK_CANCEL_OPTION, 
	            JOptionPane.QUESTION_MESSAGE, null, null, d);
		
		
		if (result != JOptionPane.OK_OPTION) {
			return;
		}
		
		world.execute(t, d.getBinding());
		
	}
	
	
	
	private class ParameterTableModel extends AbstractTableModel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3910032699741433959L;

		public class Row {
			public Variable variable;
			public Element element;
			
			public Row(Variable v, Element e) {
				this.variable = v;
				this.element = e;
			}
		}

		private static final int VARIABLE_NAME = 0;
		private static final String VARIABLE = "Variable";

		private static final int ELEMENT_NAME = 1;
		private static final String ELEMENT = "Element";
		
		private static final int ELEMENT_TYPE = 2;
		private static final String TYPE = "Type";
		
		private List<Row> rows = new ArrayList<>();
		
		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public int getRowCount() {
			return rows.size();
		}
		
		public Map<Variable, Element> getBinding() {
			Map<Variable, Element> binding = new HashMap<>();
			for(Row r: rows) {
				binding.put(r.variable, r.element);
			}
			return binding;
		}
		
		public void addRow(Variable v) {
			addRow(v, new Element("n", v.getType()));
		}
		
		public void addRow(Variable v, Element e) {
			rows.add(new Row(v,e));
			fireTableRowsInserted(rows.size()-1, rows.size()-1);
		}
		

		@Override
		public Object getValueAt(int row, int col) {
			if (row < 0 || row >= getRowCount() ) {
				return null;
			}
			if (col < 0 || col >= 3) {
				return null;
			}
			Row r = rows.get(row);
			
			switch(col) {
				case VARIABLE_NAME:
					return r.variable.getLabel();
				case ELEMENT_NAME:
					return r.element.getLabel();
				case ELEMENT_TYPE:
					return r.variable.getType();
			}
			
			return null;
		}
		
		@Override
		public String getColumnName(int column) {
			switch(column) {
				case VARIABLE_NAME:
					return VARIABLE;
				case ELEMENT_NAME:
					return ELEMENT;
				case ELEMENT_TYPE:
					return TYPE;
				default:
					return "Unkwown";
			}
		}
		
		@Override
		public Class<?> getColumnClass(int c) {
	        return String.class;
	    }
		
		@Override
		public boolean isCellEditable(int row, int col) {
			if (col == 1) return true;
			return false;
		}
		
		@Override
		public void setValueAt(Object value, int row, int col) {
			if (row >= getRowCount() || row < 0) return;
			if (col != 1) return;
			
			if (value instanceof String) {
				Row r = rows.get(row);			
				r.element = new Element((String) value, r.variable.getType());
				
				fireTableCellUpdated(row, col);
			}
	    }
		
	}
	
	class ElementTypeBasedCellEditor extends AbstractCellEditor implements TableCellEditor {

		/**
		 * 
		 */
		private static final long serialVersionUID = -6584073541621874189L;
		
		JComboBox<String> component;
		DefaultComboBoxModel<String> elementModel;
		WorldController world;
		
		public ElementTypeBasedCellEditor(WorldController world) {
			elementModel = new DefaultComboBoxModel<>();
			component = new JComboBox<String>(elementModel);
			component.setEditable(true);
			((PlainDocument) ((JTextField)component.getEditor().getEditorComponent()).getDocument()).setDocumentFilter(new LowerWordEnforcer());; 
			this.world = world;
		}
		

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int col) {

			elementModel.removeAllElements();
			
			String type = (String) table.getValueAt(row, ParameterTableModel.ELEMENT_TYPE);
			Iterator<Element> it = world.getModel().getWorld().getElementsIn(type);
			while(it.hasNext()) {
				Element e = it.next();
				elementModel.addElement(e.getLabel());
			}
				
			return component;
		}

		public Object getCellEditorValue() {
			return component.getEditor().getItem();
		}
	}
	
}
