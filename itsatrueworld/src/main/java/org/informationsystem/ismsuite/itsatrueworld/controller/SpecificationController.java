package org.informationsystem.ismsuite.itsatrueworld.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.informationsystem.ismsuite.specifier.model.Specification;
import org.informationsystem.ismsuite.specifier.model.Transaction;
import org.informationsystem.ismsuite.specifier.parser.SpecificationReader;

public class SpecificationController {

	
	private Specification spec = new Specification();
	private String fileName = "";
	private boolean modified = false;
	
	public String getFileName() {
		return fileName;
	}
	
	public boolean isModified() {
		return modified;
	}
		
	public boolean export(OutputStream output) {
		// TODO: add code;
		modified = false;
		notifyAllListeners();
		return false;
	}
	
	public boolean open(InputStream input, String filename) throws IOException {
		spec = SpecificationReader.fromStream(input);
		modified = false;
		this.fileName = filename;
		
		notifyAllListeners();
		return !(spec == null);
	}
		
	public Transaction getTransaction(String name) {
		return spec.getTransactionFor(name);
	}
	
	public void addTransaction(String name, Transaction transaction) {
		spec.addTransition(name, transaction);
		modified = true;
		notifyAllListeners();
	}
	
	public Set<String> getTransactionLabels() {
		return spec.transitions();
	}
	
	public boolean removeTransaction(String name) {
		boolean result = spec.removeTransaction(name);
		if (result) {
			modified = true;
			notifyAllListeners();
		}
		
		return result;
	}
	
	
	// Observer pattern
	List<SpecificationListener> listeners = new ArrayList<>();
	
	public void register(SpecificationListener listener) {
		listeners.add(listener);
	}
	
	public void deregister(SpecificationListener listener) {
		listeners.remove(listener);
	}
	
	private void notifyAllListeners() {
		for(int i = 0 ; i < listeners.size() ; i++) {
			if (listeners.get(i) != null) {
				listeners.get(i).onSpecificationChanged();
			}
		}
	}

	public boolean containsLabel(String label) {
		return spec.containsTransition(label);
	}
	
}
