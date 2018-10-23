package org.architecturemining.ismodeler.proving.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.architecturemining.ismodeler.proving.model.parsing.TFFBaseVisitor;
import org.architecturemining.ismodeler.proving.model.parsing.TFFParser;
import org.architecturemining.ismodeler.proving.model.parsing.TFFParser.Atomic_wordContext;

public class ClauseReader extends TFFBaseVisitor<Clause> {
	
	private World world;
	private Map<String, Clause> conjectures;
	
	public World getWorld() {
		return world;
	}
	
	public Map<String, Clause> getConjectures() {
		return conjectures;
	}
	
	@Override
	public Clause visit(ParseTree tree) {
		world = new World();
		conjectures = new HashMap<>();
		
		return super.visit(tree);
	}
	
	@Override
	public Clause visitTff_line(TFFParser.Tff_lineContext txc) {
		Clause clause = visitTff_formula(txc.tff_formula());
		
		if (clause == null) {
			return null;
		}
		
		String role = txc.formula_role().getText().toLowerCase();
		
		if (role.equals("axiom")) {
			if (clause instanceof Relation) {
				world.addRelation((Relation) clause);
			}
		} else if (role.equals("type")){
			if (clause instanceof Element) {
				world.addElement((Element) clause);
			}
		} else if (role.equals("conjecture")) {
			conjectures.put(txc.name().getText(), clause);
		}
		
		return clause;
	}
	
	@Override
	public Clause visitTff_formula(TFFParser.Tff_formulaContext txc) {
		if (txc.tff_logic_formula() != null) {
			return visitTff_logic_formula(txc.tff_logic_formula());
		}
		if (txc.tff_typed_atom() != null) {
			return visitTff_typed_atom(txc.tff_typed_atom());
		}
		return null;
	}
	
	/**
	 * tff_logic_formula : tff_binary_formula | tff_unitary_formula;
	 */
	@Override
	public Clause visitTff_logic_formula(TFFParser.Tff_logic_formulaContext txc) {
		if (txc.tff_binary_formula() != null) {
			return visitTff_binary_formula(txc.tff_binary_formula());
		}
		if (txc.tff_unitary_formula() != null) {
			return visitTff_unitary_formula(txc.tff_unitary_formula());
		}
		return null;
	}
	
	/**
	 * : tff_quantified_formula | tff_unary_formula | tff_atomic_formula;
	 */
	@Override
	public Clause visitTff_unitary_formula(TFFParser.Tff_unitary_formulaContext txc) {
		if (txc.tff_atomic_formula() != null) {
			return visitTff_atomic_formula(txc.tff_atomic_formula());
		}
		if (txc.tff_quantified_formula() != null) {
			return visitTff_quantified_formula(txc.tff_quantified_formula());
		}
		if (txc.tff_unary_formula() != null) {
			return visitTff_unary_formula(txc.tff_unary_formula());
		}
		
		return null;
	}
	
	/**
	 * atomic_word ( '(' argument_list ')' )?
	 */
	@Override
	public Clause visitTff_atomic_formula(TFFParser.Tff_atomic_formulaContext txc) {
		List<Literal> parameters = new ArrayList<>();
		if (txc.argument_list() != null) {
			for(int i = 0 ; i < txc.argument_list().argument().size() ; i++ ) {
				String s = txc.argument_list().argument(i).getText();
				parameters.add(new Element(s, world.findTypeFor(s)));
			}
		}
		
		return new Relation(txc.atomic_word().getText(), parameters);
	}
	
	
	/**
	 * tff_binary_formula : tff_binary_nonassoc | tff_binary_assoc
	 */
	@Override
	public Clause visitTff_binary_formula(TFFParser.Tff_binary_formulaContext txc) {
		if (txc.tff_binary_assoc() != null) {
			return visitTff_binary_assoc(txc.tff_binary_assoc());
		}
		if (txc.tff_binary_nonassoc()!= null) {
			return visitTff_binary_nonassoc(txc.tff_binary_nonassoc());
		}
		return null;
	}
	
	/**
	 * tff_or_formula | tff_and_formula;
	 */
	@Override
	public Clause visitTff_binary_assoc(TFFParser.Tff_binary_assocContext txc) {
		if (txc.tff_or_formula() != null) {
			return visitTff_or_formula(txc.tff_or_formula());
		}
		if (txc.tff_and_formula() != null) {
			return visitTff_and_formula(txc.tff_and_formula());
		}
		return null;
	}
	
	/**
	 * tff_unitary_formula And tff_unitary_formula | tff_and_formula And tff_unitary_formula
	 */
	@Override
	public Clause visitTff_and_formula(TFFParser.Tff_and_formulaContext txc) {
		
		Clause right;
		Clause left;
		if (txc.tff_and_formula() != null) {
			left = visitTff_and_formula(txc.tff_and_formula());
			right = visitTff_unitary_formula(txc.tff_unitary_formula().get(0));
		} else {
			left = visitTff_unitary_formula(txc.tff_unitary_formula().get(0));
			right = visitTff_unitary_formula(txc.tff_unitary_formula().get(1));
		}
		
		return new And(left, right);
		// */
	}
	
	/**
	 * Returns a type
	 */
	@Override
	public Clause visitTff_typed_atom(TFFParser.Tff_typed_atomContext txc) {
		if (txc.atomic_word().size() < 2) {
			return null;
		}
		return new Element(
				txc.atomic_word().get(0).getText(),
				txc.atomic_word().get(1).getText()
		);
				
		
	}
	
}
