package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.controller.TrueWorld;
import org.informationsystem.ismsuite.itsatrueworld.controller.TrueWorldListener;
import org.informationsystem.ismsuite.itsatrueworld.utils.ClauseVisualizer;
import org.informationsystem.ismsuite.prover.io.TFFClauseVisitor;
import org.informationsystem.ismsuite.prover.model.Clause;

public class QuickValidatorPanel extends JPanel implements TrueWorldListener, ANTLRErrorListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -880575158867305519L;
	private JLabel quickLabel;
	private JTextArea quickText;
	private TFFClauseVisitor tffClauseVisitor;
	
	private Controller controller;
	
	private JFrame owner;
	
	public QuickValidatorPanel(JFrame parent, Controller controller) {
		this.controller = controller;
		this.controller.register(this);
		tffClauseVisitor = new TFFClauseVisitor(this.controller.getModel().getWorld());
		tffClauseVisitor.addErrorListener(this);
		initialize();
	}

	
	private void initialize() {
		setLayout(new BorderLayout());
		
		quickText = new JTextArea(4, 80);
		// quickText.setText("! [X: universe, Y: universe] : (likes(X, Y) => likes(X, X))");
		// quickText.setText("FOR ALL [ X IN universe] IT HOLDS THAT THERE EXISTS [Y IN universe ] SUCH THAT likes( X, Y)");
		quickText.addKeyListener(new KeyListener() {
	
			@Override public void keyTyped(KeyEvent e) {}
			@Override public void keyPressed(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// do stuff
				try {
					Clause c = tffClauseVisitor.visit(quickText.getText());
					quickLabel.setText(c.toString());
				} catch(Exception ex) {
					// System.out.println(ex.getMessage());
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(quickText);
		
		this.add(scrollPane, BorderLayout.CENTER);
		this.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(5, 5, 5, 5),
						BorderFactory.createCompoundBorder(
								BorderFactory.createLineBorder(Color.black, 1, true),
								BorderFactory.createEmptyBorder(5, 5, 5, 5)
						)
				)
		);
		
						
		JPanel quickButtonPanel = new JPanel();
		quickButtonPanel.setLayout(new BorderLayout());
		
		JPanel holder = new JPanel();
		holder.setLayout(new GridLayout(1, 2));
		
		JButton visualizeButton = new JButton("Visualize");
		visualizeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Clause c = tffClauseVisitor.visit(quickText.getText());
				if (c != null) {
					ClauseVisualizer.showTreeDialog(owner, c);
				}
			}
		});
		holder.add(visualizeButton);
		
		
		JButton validateButton = new JButton("Validate");
		validateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Clause c = tffClauseVisitor.visit(quickText.getText());
				if (c != null) {
					Stack<Clause> expl = c.findExplanationFor(controller.getModel().getWorld());
					if (expl.isEmpty()) {
						JOptionPane.showMessageDialog(owner, "Formula is valid!", "Quick validator", JOptionPane.INFORMATION_MESSAGE);
					} else {
						expl.push(c);
						ClauseVisualizer.showExplanationDialog(owner, expl);
					}
				}
				
			}
		});
		holder.add(validateButton);
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Clause c = tffClauseVisitor.visit(quickText.getText());
				if (c != null) {
					ConjectureEditor.addNewConjecture(controller, owner, "new conjecture", c);
				}
			}
			
		});
		holder.add(addButton);
		
		quickLabel = new JLabel("");
		quickButtonPanel.add(quickLabel, BorderLayout.CENTER);
		
		quickButtonPanel.add(holder, BorderLayout.EAST);
		
		this.add(quickButtonPanel, BorderLayout.SOUTH);
	
	}


	@Override
	public void notify(TrueWorld world) {
		tffClauseVisitor.setWorld(world.getWorld());
	}


	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {

		quickLabel.setText(
				"Error: " + msg + " (l: " + line + " pos: " + charPositionInLine + ")"
				);
		
		revalidate();
	}


	@Override
	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
			BitSet ambigAlts, ATNConfigSet configs) {
		
	}


	@Override
	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
			BitSet conflictingAlts, ATNConfigSet configs) {
	}


	@Override
	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
			ATNConfigSet configs) {
	}
	
}
