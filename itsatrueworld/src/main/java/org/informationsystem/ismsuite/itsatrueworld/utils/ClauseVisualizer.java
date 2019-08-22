package org.informationsystem.ismsuite.itsatrueworld.utils;

import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.informationsystem.ismsuite.prover.model.Clause;

public class ClauseVisualizer {

	public static void showExplanationDialog(JFrame parent, Stack<Clause> explanation) {
		
		StringBuilder expl = new StringBuilder();
		boolean first = true;
		expl.append("Because:\n");
		for(Clause c: explanation) {
			if(first) {
				first  = false;
			}  else {
				expl.append("hence\n\t");
			}
			expl.append(c.toTFF());
			expl.append("\n");
		}
		
		// Create Message Dialog
		JOptionPane.showMessageDialog(parent, expl.toString(), "Formula is not valid", JOptionPane.WARNING_MESSAGE);
	}

	public static String generateName(String id) {
		return id;
	}
	
}
