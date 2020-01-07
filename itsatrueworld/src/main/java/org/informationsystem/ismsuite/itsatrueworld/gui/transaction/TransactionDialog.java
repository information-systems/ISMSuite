package org.informationsystem.ismsuite.itsatrueworld.gui.transaction;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.informationsystem.ismsuite.specifier.model.Transaction;

public class TransactionDialog {
	
	
	public static Transaction showDialog(JFrame owner) {
		return showDialog(owner, null);
	}
	
	public static Transaction showDialog(JFrame owner, Transaction t) {
		TransactionTextEditor pane = new TransactionTextEditor(10, 80);
		
		if (t != null) {
			pane.setTransaction(t);
		}	
				
		int result = JOptionPane.showOptionDialog(
	            owner, 
	            pane, 
	            "Transaction", 
	            JOptionPane.OK_CANCEL_OPTION, 
	            JOptionPane.QUESTION_MESSAGE, null, null, pane);
		
		if (result != JOptionPane.OK_OPTION) {
			return null;
		}
		
		Transaction transaction = pane.getTransaction();
		
		if (transaction != null) {
			return transaction;
		} else {
			return null;
		}
	}
	
	
	
	

}
