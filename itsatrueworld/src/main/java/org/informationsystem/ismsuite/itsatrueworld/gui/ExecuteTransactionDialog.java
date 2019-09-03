package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

import org.informationsystem.ismsuite.itsatrueworld.controller.WorldController;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Variable;
import org.informationsystem.ismsuite.specifier.model.Transaction;

public class ExecuteTransactionDialog {

	ParameterTableModel parameters = new ParameterTableModel();
	
	private JPanel buildPane(Transaction t) {
		JPanel p = new JPanel(new BorderLayout(5,5));
		
		JLabel label = new JLabel("Transaction: " + t.getLabel());
		
		p.add(label, BorderLayout.NORTH);
		
		p.setSize(new Dimension(400, 400));
		return p;
	}
	
	public Map<Variable, Element> getBinding() {
		return parameters.getBinding();
	}
	
	
	public static void executeTransaction(WorldController world, Frame frame, Transaction t) {
		ExecuteTransactionDialog d = new ExecuteTransactionDialog();
		
		int result = JOptionPane.showOptionDialog(
	            frame, 
	            d.buildPane(t), 
	            "Execute transaction", 
	            JOptionPane.OK_CANCEL_OPTION, 
	            JOptionPane.QUESTION_MESSAGE, null, null, d);
		
		
		if (result != JOptionPane.OK_OPTION) {
			return;
		}
		
		world.execute(t, d.getBinding());
		
	}
	
	
	
	private class ParameterTableModel extends AbstractTableModel {

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
					return r.element.getType();
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
	
}
