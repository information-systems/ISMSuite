package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.informationsystem.ismsuite.itsatrueworld.controller.WorldController;
import org.informationsystem.ismsuite.itsatrueworld.controller.TrueWorld;
import org.informationsystem.ismsuite.itsatrueworld.controller.TrueWorldListener;
import org.informationsystem.ismsuite.itsatrueworld.utils.ClauseVisualizer;
import org.informationsystem.ismsuite.prover.model.Clause;

public class ConjecturePanel extends JPanel implements TrueWorldListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4429693715406696071L;

	
	
	private WorldController controller;
	
	private JFrame owner;
	
	private DynamicGridPanel grid;
	
	private Set<String> conjectureSet = new HashSet<>();


	private JLabel errorLabel;
	private JPanel errorPane;
	
	private Set<String> errors = new HashSet<>();
	
	public ConjecturePanel(JFrame owner, WorldController controller) {
		this.controller = controller;
		this.owner = owner;
		
		grid = new DynamicGridPanel(1, 150);
		setLayout(new BorderLayout(5,5));
		JScrollPane scroll = new JScrollPane(grid.getPanel(), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll, BorderLayout.CENTER);
		
		errorLabel = new JLabel("No errors");
		errorPane = new JPanel(new BorderLayout(5,5));
		errorPane.setBackground(Color.GREEN);
		errorPane.add(errorLabel, BorderLayout.CENTER);
		add(errorPane, BorderLayout.NORTH);
		
		this.controller.register(this);
		
		notify(controller.getModel());
	}
	
	public JFrame getOwner() {
		return owner;
	}
	
	public void addError(String label) {
		errors.add(label);
		updateErrorPane();
	}
	
	public void removeError(String label) {
		errors.remove(label);
		updateErrorPane();
	}
	
	private void updateErrorPane() {
		if (errors.isEmpty()) {
			errorLabel.setText("No errors");
			errorPane.setBackground(Color.GREEN);
			errorPane.setToolTipText("");
		} else {
			if (errors.size() > 1) {
				errorLabel.setText("<html>There are <b>" + errors.size() + "</b> errors!</html>" );
			} else {
				errorLabel.setText("<html>There is <b>" + errors.size() + "</b> error!</html>" );
			}
			errorPane.setBackground(Color.RED);
			StringBuilder b = new StringBuilder();
			b.append("<html>The following conjectures are false:<ul>");
			for(String e: errors) {
				b.append("<li>");
				b.append(ClauseVisualizer.generateName(e));
				b.append("</li>");
			}
			b.append("</ul></html>");
			errorPane.setToolTipText(b.toString());
		}
	}

	@Override
	public void notify(TrueWorld world) {
		Set<Entry<String, Clause>> conjectures = world.getWorld().getConjectures();
		
		for(Entry<String, Clause> conjecture: conjectures) {
			// If it is not in our list yet, create a panel for it
			if (!conjectureSet.contains(conjecture.getKey())) {
				conjectureSet.add(conjecture.getKey());
				ChildPanel p = createPanel(conjecture.getKey());
				grid.addPanel(p);
			}
		}
		
		// Notice that the Notify of ChildPanel automatically 
		// removes itself if it is removed from the world :-)
	}
	
	private ChildPanel createPanel(String name) {
		return new ChildPanel(controller, this, name);
	}
	
	public void removePanel(ChildPanel childPanel) {
		grid.removePanel(childPanel);
	}
	
	public void renameConjecture(String oldName, String newName) {
		conjectureSet.remove(oldName);
		conjectureSet.add(newName);
		
		// check if it needs to be renamed in errors
		if (errors.contains(oldName)) {
			errors.remove(oldName);
			errors.add(newName);
			updateErrorPane();
		}
	}
	
	
	private class ChildPanel extends JPanel implements TrueWorldListener {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 3369929260307749406L;

		private ConjecturePanel owner;
		
		private WorldController controller;
		
		private String id;
		private Clause clause;

		private JTextArea clauseField;

		private Stack<Clause> explanation;

		private JLabel title;

		private JPanel titlePanel;

		private JButton explainButton;
		
		public ChildPanel(WorldController controller, ConjecturePanel owner, String conjecture) {
			this.controller = controller;
			
			this.owner = owner;
			
			this.id = conjecture;
			
			initialize();
			
			this.controller.register(this);
			notify(controller.getModel());
		}
		
		public String getId() {
			return this.id;
		}
		
		private void setId(String id) {
			owner.renameConjecture(getId(), id);
			this.id = id;
			updateTitleText();
		}
		
		public Stack<Clause> getExplanation() {
			return explanation;
		}
				
		public Clause getClause() {
			return clause;
		}
		
		private void initialize() {
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
			
			title = new JLabel();
			updateTitleText();
			
			titlePanel = new JPanel(new BorderLayout());
			titlePanel.add(title, BorderLayout.CENTER);
			
			add(titlePanel, BorderLayout.NORTH);
			
			clauseField = new JTextArea();
			clauseField.setEditable(false);
			clauseField.setLineWrap(true);
			
			add(clauseField, BorderLayout.CENTER);
			
			// Button panel
			JPanel holder = new JPanel(new BorderLayout());
			JPanel buttons = new JPanel(new GridLayout(1,4));
			
			explainButton = new JButton("Explain");
			explainButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ClauseVisualizer.showExplanationDialog(owner.getOwner(), getExplanation());					
				}
			});
			JButton deleteButton = new JButton("Delete");
			deleteButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int result = JOptionPane.showConfirmDialog(owner.getOwner(), "Are you sure you want to delete conjecture '"+ClauseVisualizer.generateName(getId())+"'?", "Delete conjecture", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						controller.removeConjecture(id);
					}
				}
			});
			JButton visualizeButton = new JButton("Visualize");
			visualizeButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ClauseVisualizer.showTreeDialog(owner.getOwner(), getClause(), getId());					
				}
			});
			
			JButton editButton = new JButton("Edit");
			editButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ConjectureEditor editor = ConjectureEditor.showDialog(owner.getOwner(), getId(), getClause(), controller.getModel().getWorld());
					if (editor != null) {
						if (editor.getClause() == null || editor.getLabel().isEmpty()) {
							return;
						}
						
						String oldId = getId();
						setId(editor.getId());
						
						controller.updateConjecture(oldId, editor.getId(), editor.getClause());
					}
					
					
				}
			});
			
			buttons.add(visualizeButton);
			buttons.add(explainButton);
			buttons.add(editButton);
			buttons.add(deleteButton);
			
			holder.add(buttons, BorderLayout.EAST);
			
			add(holder, BorderLayout.SOUTH);
		}

		private void updateTitleText() {
			String name = ClauseVisualizer.generateName(getId());
			title.setText("<html><div style='text-align: center;'><b>" 
					+ name
					+ "</b></div></html>");
		}

		@Override
		public void notify(TrueWorld model) {
			clause = model.getWorld().getConjecture(getId());
			if (clause == null) {
				owner.removePanel(this);
			} else {
				
				clauseField.setText(ClauseVisualizer.clauseToString(clause));
				
				explanation = clause.findExplanationFor(model.getWorld());
				
				if (explanation.isEmpty()) {
					titlePanel.setBackground(Color.GREEN);
					explainButton.setEnabled(false);
					owner.removeError(getId());
				} else {
					titlePanel.setBackground(Color.RED);
					explainButton.setEnabled(true);
					owner.addError(getId());
				}
				
			}
		}
		
	}
}
