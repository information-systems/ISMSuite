package org.informationsystem.ismsuite.modeler.process.util;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityType;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.EntityTypeSequence;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.util.entitytypeparser.EntityTypesBaseVisitor;
import org.informationsystem.ismsuite.modeler.process.util.entitytypeparser.EntityTypesParser;
import org.informationsystem.ismsuite.modeler.process.util.entitytypeparser.EntityTypesParser.EntityContext;

public class ConstructEntityTypeVisitor extends EntityTypesBaseVisitor<EntityTypeSequence> {

	/**
	 *  entity | '(' entity_list ')' EOF;
 	 *	entity               : Lower_word;
	 *	entity_list          : entity (',' entity )*;
	 */
	@Override
	public EntityTypeSequence visitEntity_vector(EntityTypesParser.Entity_vectorContext ctx) { 
		EntityTypeSequence result = PnidsFactory.eINSTANCE.createEntityTypeSequence();
		
		if (ctx.entity() != null) {
			addEntityTo(result, ctx.entity());
		} else if (ctx.entity_list() != null) {
			for(EntityContext entity: ctx.entity_list().entity()) {
				addEntityTo(result, entity);
			}
		}
		
		return result;
	}

	private void addEntityTo(EntityTypeSequence result, EntityContext entity) {
		if (entity == null) {
			return;
		}
		EntityType entityType = PnidsFactory.eINSTANCE.createEntityType();
		entityType.setText(entity.getText());
		result.getEntityType().add(entityType);
	}
	
}
