package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.util.Iterator;

import javax.swing.DefaultListModel;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorld;
import org.informationsystem.ismsuite.prover.model.Element;

public class ElementListingPanel extends AbstractGridPanel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247588857156850L;

	public ElementListingPanel(Controller controller) {
		super(controller);
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
