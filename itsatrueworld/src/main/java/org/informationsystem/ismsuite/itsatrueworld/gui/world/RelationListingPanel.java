package org.informationsystem.ismsuite.itsatrueworld.gui.world;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import org.informationsystem.ismsuite.itsatrueworld.control.TrueWorld;
import org.informationsystem.ismsuite.itsatrueworld.control.WorldController;
import org.informationsystem.ismsuite.itsatrueworld.util.ClauseVisualizer;
import org.informationsystem.ismsuite.prover.model.literals.Relation;

public class RelationListingPanel extends AbstractGridPanel<Relation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247588857156850L;
	
	private JFrame owner;

	public RelationListingPanel(WorldController controller, JFrame owner) {
		super(controller);
		this.owner = owner;
	}

	@Override
	public void onNotify(TrueWorld world) {
		ArrayList<String> toBeRemoved = new ArrayList<>();
		
		for (String typ: this.getLabels()) {
			if (!world.getWorld().getRelationLabels().contains(typ)) {
				toBeRemoved.add(typ);
			}
		}
		for( String typ : toBeRemoved) {
			this.removePanel(typ);
		}
		for(String typ: world.getWorld().getRelationLabels()) {
			DefaultListModel<Relation> model = getModel(typ);
			model.clear();
			for(Relation r: world.getWorld().getRelations(typ)) {
				model.addElement(r);
			}
		}
	}

	@Override
	protected void removeAction(Relation elem, String label) {
		getController().removeRelation(elem);
	}

	@Override
	protected void addAction(String label) {
		RelationDialog.showCreateRelationDialog(getController(), owner, label);
	}
	
	@Override
	protected String generateItemString(Relation item) {
		return ClauseVisualizer.clauseToString(item);
	}



}
