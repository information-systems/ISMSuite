/**
 * 
 */
package org.informationsystem.ismsuite.itsatrueworld.gui.world;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.PlainDocument;

import org.informationsystem.ismsuite.itsatrueworld.control.WorldController;
import org.informationsystem.ismsuite.itsatrueworld.util.LowerWordEnforcer;

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
		PlainDocument doc = (PlainDocument) identifier.getDocument();
		doc.setDocumentFilter(new LowerWordEnforcer());
		controls.add(identifier);
		
		type = new JTextField(initialType);
		PlainDocument doctype = (PlainDocument) type.getDocument();
		doctype.setDocumentFilter(new LowerWordEnforcer());
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
	
		
	public static void createElement(WorldController c, Frame frame, String type) {
		UpdateElementDialog d = new UpdateElementDialog();
		
		int result = JOptionPane.showOptionDialog(
	            frame, 
	            d.buildPane(type), 
	            "Create Element", 
	            JOptionPane.OK_CANCEL_OPTION, 
	            JOptionPane.QUESTION_MESSAGE, null, null, d);
		
		
		if (result != JOptionPane.OK_OPTION) {
			return;
		}
		
		if (d.getIdentifier().isEmpty() || d.getType().isEmpty()) {
			return;
		}
		
		c.addElement(d.getIdentifier(), d.getType());
	}

}
