package org.informationsystem.ismsuite.itsatrueworld.gui;

import javax.swing.DefaultListModel;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorld;
import org.informationsystem.ismsuite.prover.model.Relation;

public class RelationListingPanel extends AbstractGridPanel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247588857156850L;

	public RelationListingPanel(Controller controller) {
		super(controller);
	}

	@Override
	public void notify(TrueWorld world) {
		for(String typ: world.getWorld().getRelationLabels()) {
			DefaultListModel<String> model = getModel(typ);
			model.clear();
			for(Relation r: world.getWorld().getRelations(typ)) {
				model.addElement(r.toTFF());
			}
		}
	}

}
