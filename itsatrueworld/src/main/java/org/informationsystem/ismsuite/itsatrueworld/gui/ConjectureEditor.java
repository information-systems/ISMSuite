package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.utils.ClauseVisualizer;
import org.informationsystem.ismsuite.prover.io.TFFClauseVisitor;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;

public class ConjectureEditor extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1357363718093455386L;

	private JTextField nameField;
	
	private JTextArea editor;
	
	private TFFClauseVisitor tffClauseVisitor;
	
	public String getId() {
		return ClauseVisualizer.generateId(getLabel());
	}
	
	public String getLabel() {
		return nameField.getText();
	}
	
	public Clause getClause() {
		return tffClauseVisitor.visit(editor.getText());
	}

	public ConjectureEditor(String label, Clause clause, FirstOrderLogicWorld world) {
		setLayout(new BorderLayout(5,5));
		
		JPanel namePanel = new JPanel(new BorderLayout(5,5));
		JLabel nameLabel = new JLabel("Label: ");
		namePanel.add(nameLabel, BorderLayout.WEST);
		
		nameField = new JTextField(ClauseVisualizer.generateName(label));
		namePanel.add(nameField, BorderLayout.CENTER);
		
		add(namePanel, BorderLayout.NORTH);
		
		tffClauseVisitor = new TFFClauseVisitor(world);
		
		editor = new JTextArea();
		editor.setLineWrap(true);

		if (clause != null) {
			editor.setText(ClauseVisualizer.clauseToString(clause));
		}
		
		add(editor, BorderLayout.CENTER);
					
		setPreferredSize(new Dimension(400,400));
		
	}
	
	
	public static ConjectureEditor showDialog(JFrame owner, String label, Clause c, FirstOrderLogicWorld world) {
		ConjectureEditor pane = new ConjectureEditor(label, c, world);
		
		int result = JOptionPane.showOptionDialog(
				owner, 
				pane, 
				"Edit conjecture", 
				JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, null, 
				pane);
		
		if (result == JOptionPane.OK_OPTION) {
			return pane;
		} else {
			return null;
		}
		
	}
	
	public static String addNewConjecture(Controller controller, JFrame owner, String label) {
		return addNewConjecture(controller, owner, label, null);
	}
	
	public static String addNewConjecture(Controller controller, JFrame owner, String label, Clause c) {
		ConjectureEditor pane = ConjectureEditor.showDialog(owner, label, c, controller.getModel().getWorld());
		
		if (pane != null) {
			// OK was pressed. Try to save!
			Clause newClause = pane.getClause();
			
			if (newClause != null && !pane.getId().isEmpty()) {
				// As we do not have an edit for the label
				// we first remove the conjecture, and then
				// save it under the possibly new label.
				
				controller.addConjecture(pane.getId(), newClause);
				
				return pane.getId();
			}
		}
		
		return label;
	}
}
