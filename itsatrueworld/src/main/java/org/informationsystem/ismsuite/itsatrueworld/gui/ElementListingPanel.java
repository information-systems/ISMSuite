package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorld;
import org.informationsystem.ismsuite.prover.model.Element;

public class ElementListingPanel extends AbstractGridPanel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247588857156850L;

	private JFrame parent;
		
	public ElementListingPanel(Controller controller, JFrame parent) {
		super(controller);
		this.parent = parent;
	}
	
	@Override
	protected void removeAction(String elem, String label) {
		getController().removeElement(elem, label);
	}
	
	@Override
	protected void addAction(String label) {
		UpdateElementDialog.createElement(getController(), parent, label);
	}
	
	@Override
	public void notify(TrueWorld world) {
		for(String typ: world.getWorld().getElementTypes()) {
			DefaultListModel<String> model = getModel(typ);
			model.clear();
			Iterator<Element> it = world.getWorld().getElementsIn(typ);
			while(it.hasNext()) {
				model.addElement(it.next().getLabel());
			}
		}		
	}
	
	
}
