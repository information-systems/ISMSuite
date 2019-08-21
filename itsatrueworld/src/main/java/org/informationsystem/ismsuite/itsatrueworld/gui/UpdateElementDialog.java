/**
 * 
 */
package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;

/**
 * @author jmw
 *
 */
public class UpdateElementDialog {
	
	private JTextField identifier;
	private JTextField type;

	

	private JPanel buildPane(String initialType) {
		JPanel p = new JPanel(new BorderLayout(5,5));
		
		JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
		
		labels.add(new JLabel("Identifier", SwingConstants.RIGHT));
		labels.add(new JLabel("Type", SwingConstants.RIGHT));
		
		p.add(labels, BorderLayout.WEST);
		
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		
		identifier = new JTextField();
		controls.add(identifier);
		
		type = new JTextField(initialType);
		controls.add(type);
		
		p.add(controls, BorderLayout.CENTER);
		
		return p;
	}
	
	public String getType() {
		if (this.type != null) {
			return this.type.getText();
		}
		
		return "";
	}
	
	public String getIdentifier() {
		if (this.identifier != null) {
			return this.identifier.getText();
		}
		
		return "";
	}
	
		
	public static void createElement(Controller c, Frame frame, String type) {
		UpdateElementDialog d = new UpdateElementDialog();
		
		JOptionPane.showMessageDialog(
	            frame, d.buildPane(type), "Create Element", JOptionPane.QUESTION_MESSAGE);
		
		if (d.getIdentifier().isEmpty() || d.getType().isEmpty()) {
			return;
		}
		
		c.addElement(d.getIdentifier(), d.getType());
	}

}
