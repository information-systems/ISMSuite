package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.informationsystem.ismsuite.itsatrueworld.controller.SpecificationController;
import org.informationsystem.ismsuite.itsatrueworld.controller.SpecificationListener;
import org.informationsystem.ismsuite.itsatrueworld.controller.WorldController;
import org.informationsystem.ismsuite.itsatrueworld.utils.ClauseVisualizer;
import org.informationsystem.ismsuite.specifier.model.Transaction;

public class TransactionPanel extends JPanel implements SpecificationListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -339203423600586178L;

	private WorldController worldController;
	private SpecificationController specificationController; 
	
	private JFrame owner;
	
	public JFrame getOwner() {
		return owner;
	}
	
	
	public WorldController getWorldController() {
		return worldController;
	}
	
	public SpecificationController getSpecificationController() {
		return specificationController;
	}
	
	
	
	public TransactionPanel(WorldController worldController, SpecificationController speccontroller, JFrame owner) {
		this.worldController = worldController;
		this.specificationController = speccontroller;
		this.owner = owner;
		
		initialize();
		
		onSpecificationChanged();
	}
	
	private DynamicGridPanel grid;
	
	private void initialize() {
		this.grid = new DynamicGridPanel(1, 150);
		
		setLayout(new BorderLayout(5,5));
		add(grid.getPanel(), BorderLayout.CENTER);
		
	}
	
	private Set<String> transactions = new HashSet<>();

	@Override
	public void onSpecificationChanged() {
		transactions.retainAll(getSpecificationController().getTransactionLabels());
		
		for(String s: getSpecificationController().getTransactionLabels()) {
			if (!transactions.contains(s)) {
				createNewPanel(s);
				transactions.add(s);
			}
		}
		
	}
	
	private void createNewPanel(String label) {
		ChildPanel p = new ChildPanel(label);
		grid.addPanel(p);
	}
	
	private void removePanel(ChildPanel p) {
		transactions.remove(p.getLabel());
		grid.removePanel(p);
	}
	
	
	private class ChildPanel extends JPanel implements SpecificationListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = -5836170438824420331L;

		private String label;
		
		private JLabel title = new JLabel();
		private JTextArea transactionField = new JTextArea();
		
		private ChildPanel myself;
		
		public ChildPanel(String label) {
			myself = this;
			
			setLabel(label);
			transactionField.setLineWrap(true);
			transactionField.setEditable(false);
			
			setLayout(new BorderLayout(5,5));
			
			setBorder(
					BorderFactory.createCompoundBorder(
							BorderFactory.createEmptyBorder(5, 5, 5, 5),
							BorderFactory.createCompoundBorder(
									BorderFactory.createLineBorder(Color.black, 1, true),
									BorderFactory.createEmptyBorder(5, 5, 5, 5)
							)
					)
			);
			
			add(title, BorderLayout.NORTH);
			add(transactionField, BorderLayout.CENTER);
			
			JPanel holder = new JPanel(new BorderLayout());
			JPanel buttons = new JPanel(new GridLayout(1,3));
			
			JButton delete = new JButton("Delete");
			delete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int result = JOptionPane.showConfirmDialog(owner, "Are you sure you want to delete transaction '"+getLabel()+"'?", "Delete transaction", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						removePanel(myself);
					}
				}
			});
			
			JButton edit = new JButton("Edit");
			JButton execute = new JButton("Execute");
			
			buttons.add(delete);
			buttons.add(edit);
			buttons.add(execute);
			
			holder.add(buttons, BorderLayout.CENTER);
			
			this.add(holder, BorderLayout.SOUTH);
			
			onSpecificationChanged();
		}
		
		private void setLabel(String label) {
			this.label = label;
			title.setText("<html><b>" + ClauseVisualizer.generateName(label) + "</b></html>");
		}
		
		public String getLabel() {
			return label;
		}
		
		@Override
		public void onSpecificationChanged() {
			Transaction t = getSpecificationController().getTransaction(label);
			transactionField.setText(label + t.toString().replace("\t", "  "));
		}
		
	}
	
}
