package org.informationsystem.ismsuite.modeler.process.validator;

import org.pnml.tools.epnk.pnmlcoremodel.Object;

public class SyntaxError {
	
	private Object pnObject;
	
	private String template;
	
	public SyntaxError(Object pnObject, String template) {
		this.pnObject = pnObject;
		this.template = template;
	}
	
	public Object getObject() {
		return pnObject;
	}
	
	public String getMessage() {
		if (template.isEmpty()) {
			template = "#ID";
		}
		
		if (pnObject == null) {
			return template.replace("#ID", "No object").replace("#NAME", "No object");
		}
		
		String message = template;
		String id;
		
		if (pnObject.getId() != null && (!pnObject.getId().equals(""))) {
			id = pnObject.getId();
		} else {
			id = pnObject.toString();
		}
		message = message.replace("#ID", id);
		
		if (pnObject.getName() != null && pnObject.getName().getText() != null) {
			message = message.replace("#NAME", pnObject.getName().getText());
		} else {
			message = message.replace("#NAME", id);
		}

		return message;
	}

}
