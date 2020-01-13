package org.informationsystem.ismsuite.itsatrueworld.gui.transaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.informationsystem.ismsuite.specifier.io.TransactionReader;
import org.informationsystem.ismsuite.specifier.model.Transaction;

public class TransactionTextEditor extends JPanel implements ANTLRErrorListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7238584002092745993L;
	
	private TransactionReader reader;

	private JTextArea text;
	private JLabel error;

	public TransactionTextEditor() {
		this(4, 80);
	}
	
	public TransactionTextEditor(int rows, int cols) {
		reader = new TransactionReader();
		reader.addErrorListener(this);
		
		this.setLayout(new BorderLayout(5,5));
		
		text = new JTextArea(rows, cols);
		text.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				updateText();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(text);
		this.add(scrollPane, BorderLayout.CENTER);
		
		error = new JLabel("");
		this.add(error, BorderLayout.SOUTH);
	}
	
	public void setTransaction(Transaction transaction) {
		text.setText(transaction.toString(true));
		updateText();
	}
	
	public Transaction getTransaction() {
		try {
			Transaction o = reader.parse(text.getText());
			if (o instanceof Transaction) {
				return (Transaction) o;
			}
		} catch(Exception ex) {}
		
		return null;
	}
	
	private void updateText() {
		try {
			Transaction t = reader.parse(text.getText());
			if (t != null) {
				error.setText("Transaction: " + t.getLabel());
				error.setForeground(Color.green);
			}
		} catch(Exception ex) {
			error.setForeground(Color.red);
		}
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		error.setText(
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
