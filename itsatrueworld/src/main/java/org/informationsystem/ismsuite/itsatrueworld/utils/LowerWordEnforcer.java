package org.informationsystem.ismsuite.itsatrueworld.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class LowerWordEnforcer extends DocumentFilter {

	private boolean allowSpaces = false;
	
	public LowerWordEnforcer() {
		this(false);
	}
	
	public LowerWordEnforcer(boolean allowSpaces) {
		this.allowSpaces = allowSpaces;
	}
	
	public boolean spacesAreAllowed() {
		return allowSpaces;
	}
	
	public LowerWordEnforcer setSpacesAreAllowed(boolean allowSpaces) {
		this.allowSpaces = allowSpaces;
		return this;
	}
	
	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);

		if (test(sb.toString())) {
			super.insertString(fb, offset, string, attr);
		} else {
			// warn the user and don't allow the insert
		}
	}

	private boolean test(String text) {
		// first character should be lower character.
		// Rest of the characters alphanumeric.
		if (text.isEmpty()) { 
			return true;
		}
		if (spacesAreAllowed()) {
			return text.matches("^[a-z][a-zA-Z0-9\\s]*$");
		}
		
		return text.matches("^[a-z][\\w]*$");
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
			throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);

		if (test(sb.toString())) {
			super.replace(fb, offset, length, text, attrs);
		} else {
			// warn the user and don't allow the insert
		}

	}

	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);

		if (test(sb.toString())) {
			super.remove(fb, offset, length);
		} else {
			// warn the user and don't allow the insert
		}

	}
}