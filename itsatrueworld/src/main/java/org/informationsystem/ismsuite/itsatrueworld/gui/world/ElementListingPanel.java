package org.informationsystem.ismsuite.itsatrueworld.gui.world;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import org.informationsystem.ismsuite.itsatrueworld.control.TrueWorld;
import org.informationsystem.ismsuite.itsatrueworld.control.WorldController;
import org.informationsystem.ismsuite.itsatrueworld.util.ClauseVisualizer;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Relation;

public class ElementListingPanel extends AbstractGridPanel<Element> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247588857156850L;

	private JFrame owner;
		
	public ElementListingPanel(WorldController controller, JFrame owner) {
		super(controller);
		this.owner = owner;
	}
	
	@Override
	protected void removeAction(Element elem, String label) {
		getController().removeElement(elem);
	}
	
	@Override
	protected void addAction(String label) {
		UpdateElementDialog.createElement(getController(), owner, label);
	}
	
	@Override
	protected String generateItemString(Element item) {
		return item.getLabel();
	}
	
	@Override
	public void onNotify(TrueWorld world) {
		ArrayList<String> toBeRemoved = new ArrayList<>();
		for (String typ: this.getLabels()) {
			if (!world.getWorld().getRelationLabels().contains(typ)) {
				toBeRemoved.add(typ);
			}
		}
		for (String typ: toBeRemoved) {
			this.removePanel(typ);
		}
		
		for(String typ: world.getWorld().getElementTypes()) {
			DefaultListModel<Element> model = getModel(typ);
			model.clear();
			Iterator<Element> it = world.getWorld().getElementsIn(typ);
			while(it.hasNext()) {
				model.addElement(it.next());
			}
		}
	}
	
}
