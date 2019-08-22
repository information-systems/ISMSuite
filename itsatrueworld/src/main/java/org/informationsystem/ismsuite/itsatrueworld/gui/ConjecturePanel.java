package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorld;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorldListener;
import org.informationsystem.ismsuite.itsatrueworld.utils.ClauseToText;
import org.informationsystem.ismsuite.itsatrueworld.utils.ClauseTreeVisualizer;
import org.informationsystem.ismsuite.itsatrueworld.utils.ClauseVisualizer;
import org.informationsystem.ismsuite.prover.io.TFFClauseVisitor;
import org.informationsystem.ismsuite.prover.model.Clause;

public class ConjecturePanel extends JPanel implements TrueWorldListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4429693715406696071L;

	
	
	private Controller controller;
	
	private JFrame owner;
	
	private DynamicGridPanel grid;
	
	private Map<String, ChildPanel> conjecturePanels = new HashMap<>();
	
	public ConjecturePanel(JFrame owner, Controller controller) {
		this.controller = controller;
		this.owner = owner;
		
		grid = new DynamicGridPanel(1, 150);
		setLayout(new BorderLayout(5,5));
		JScrollPane scroll = new JScrollPane(grid.getPanel(), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll, BorderLayout.CENTER);
		
		this.controller.register(this);
		
		notify(controller.getModel());
	}
	
	public JFrame getOwner() {
		return owner;
	}
	

	@Override
	public void notify(TrueWorld world) {
		Set<Entry<String, Clause>> conjectures = world.getWorld().getConjectures();
		
		for(Entry<String, Clause> conjecture: conjectures) {
			// If it is not in our list yet, create a panel for it
			if (!conjecturePanels.containsKey(conjecture.getKey())) {
				ChildPanel p = createPanel(conjecture.getKey());
				conjecturePanels.put(conjecture.getKey(), p);
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
		conjecturePanels.remove(childPanel.getId());
		grid.removePanel(childPanel);
	}
	
	
	private class ChildPanel extends JPanel implements TrueWorldListener {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 3369929260307749406L;

		private ConjecturePanel owner;
		
		private Controller controller;
		
		private String id;
		private Clause clause;

		private JTextArea clauseField;

		private Stack<Clause> explanation;

		private JLabel title;

		private JPanel titlePanel;

		private JButton explainButton;
		
		public ChildPanel(Controller controller, ConjecturePanel owner, String conjecture) {
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
		
		public Stack<Clause> getExplanation() {
			// TODO make it look better ;-)
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
			JPanel buttons = new JPanel(new GridLayout(1,3));
			
			explainButton = new JButton("Explain");
			explainButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ClauseVisualizer.showExplanationDialog(owner.getOwner(), getExplanation());					
				}
			});
			JButton deleteButton = new JButton("Delete");
			JButton visualizeButton = new JButton("Visualize");
			visualizeButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ClauseTreeVisualizer.showTreeDialog(owner.getOwner(), getClause(), getId());					
				}
			});
			
			buttons.add(visualizeButton);
			buttons.add(explainButton);
			buttons.add(deleteButton);
			
			holder.add(buttons, BorderLayout.EAST);
			
			add(holder, BorderLayout.SOUTH);
		}

		private void updateTitleText() {
			title.setText("<html><div style='text-align: center;'><b>" 
					+ ClauseVisualizer.generateName(getId())
					+ "</b></div></html>");
		}

		@Override
		public void notify(TrueWorld model) {
			clause = model.getWorld().getConjecture(id);
			if (clause == null) {
				owner.removePanel(this);
			} else {
				
				clauseField.setText(ClauseToText.convertClause(clause));
				
				explanation = clause.findExplanationFor(model.getWorld());
				
				if (explanation.isEmpty()) {
					titlePanel.setBackground(Color.GREEN);
					explainButton.setEnabled(false);
				} else {
					titlePanel.setBackground(Color.RED);
					explainButton.setEnabled(true);
				}
				
			}
		}
		
	}
}
