package org.informationsystem.ismsuite.modeler.process.util;

import java.util.ArrayList;
import java.util.List;

import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Entity;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.PnidsFactory;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.Token;
import org.informationsystem.ismsuite.modeler.process.pnid.pnids.TokenBag;
import org.informationsystem.ismsuite.modeler.process.util.tokenparser.TokenMultiSetBaseVisitor;
import org.informationsystem.ismsuite.modeler.process.util.tokenparser.TokenMultiSetParser;
import org.informationsystem.ismsuite.modeler.process.util.tokenparser.TokenMultiSetParser.EntityContext;
import org.informationsystem.ismsuite.modeler.process.util.tokenparser.TokenMultiSetParser.PnidtokenContext;

public class ConstructTokenBagVisitor extends TokenMultiSetBaseVisitor<TokenBag> {

	@Override 
	public TokenBag visitMultiset(TokenMultiSetParser.MultisetContext ctx) {
		TokenBag bag = PnidsFactory.eINSTANCE.createTokenBag();
		
		if (ctx.pnidtoken() != null) {
			for(PnidtokenContext tokenCtx: ctx.pnidtoken()) {
				int amount = 1;
				if (tokenCtx.amount() != null) {
					amount = Integer.parseInt(tokenCtx.amount().getText());
				}
				// Build the token
				List<String> entityList = new ArrayList<>();
				if (tokenCtx.entities() != null) {
					if (tokenCtx.entities().entity() != null) {
						entityList.add(tokenCtx.entities().entity().getText());
					} else if (tokenCtx.entities().entity_list() != null) {
						for(EntityContext entity: tokenCtx.entities().entity_list().entity()) {
							entityList.add(entity.getText());
						}
					}
				}
				for(int i = 0 ; i < amount ; i++) {
					Token token = PnidsFactory.eINSTANCE.createToken();
					for(String value : entityList) {
						Entity entity = PnidsFactory.eINSTANCE.createEntity();
						entity.setText(value);
						token.getEntity().add(entity);
					}
					bag.getToken().add(token);
				}
			}
		}
		
		return bag;
	}
}
