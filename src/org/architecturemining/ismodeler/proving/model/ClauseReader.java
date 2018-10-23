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
	
	/**
	 * First argument is the Variable Name, Second its type.
	 * If a variable is not in this list, it is a Free variable,
	 * and hence, we do not know its type.
	 */
	private Map<String, String> currentlyBoundVariables = new HashMap<>();
	
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
	
	public Clause visitTff_quantified_formula(TFFParser.Tff_quantified_formulaContext tcx) {
		// TODO Keep track of the variables defined here, add them to 
		// the context, and remove them once this function is finished.
		
		return null;
	}
	
	/**
	 * unary_connective tff_unitary_formula | fof_infix_unary | '(' tff_logic_formula ')';
	 */
	@Override
	public Clause visitTff_unary_formula(TFFParser.Tff_unary_formulaContext txc) {
		if (txc.tff_logic_formula() != null) {
			return visitTff_logic_formula(txc.tff_logic_formula());
		}
		if (txc.tff_unitary_formula() != null) {
			return visitTff_unitary_formula(txc.tff_unitary_formula());
		}
		if (txc.fof_infix_unary() != null) {
			return visitFof_infix_unary(txc.fof_infix_unary());
		}
		
		return null;
	}
	
	/**
	 * fof_term Infix_inequality fof_term | fof_term Infix_equality fof_term 
	 */
	@Override
	public Clause visitFof_infix_unary(TFFParser.Fof_infix_unaryContext txc) {
		if (txc.Infix_equality() != null) {
			Clause left = visitFof_term(txc.fof_term(0));
			Clause right = visitFof_term(txc.fof_term(1));
			return new Equality(left, right);
		}
		if (txc.Infix_inequality() != null) {
			Clause left = visitFof_term(txc.fof_term(0));
			Clause right = visitFof_term(txc.fof_term(1));
			return new Not(new Equality(left, right));
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
				if (txc.argument_list().argument(i).atomic_word() != null) {
					String s = txc.argument_list().argument(i).atomic_word().getText();
					parameters.add(new Element(s, world.findTypeFor(s)));
				}
				if (txc.argument_list().argument(i).variable() != null) {
					String s = txc.argument_list().argument(i).variable().getText();
					String type = "";
					if (currentlyBoundVariables.containsKey(s)) {
						type = currentlyBoundVariables.get(s);
					}
					parameters.add(new Variable(s, type));
				}
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
	 * tff_unitary_formula binary_connective tff_unitary_formula
	 */
	@Override
	public Clause visitTff_binary_nonassoc(TFFParser.Tff_binary_nonassocContext txc) {
		Clause premise;
		Clause conclusion;
		
		// A <= B === B => A
		if (txc.binary_connective().If() != null) {
			premise = visitTff_unitary_formula(txc.tff_unitary_formula(1));
			conclusion =  visitTff_unitary_formula(txc.tff_unitary_formula(0));
			
			return new Implies(premise, conclusion);
		}
		if (txc.binary_connective().Impl() != null) {
			premise = visitTff_unitary_formula(txc.tff_unitary_formula(0));
			conclusion =  visitTff_unitary_formula(txc.tff_unitary_formula(1));
			
			return new Implies(premise, conclusion);
		}
		if (txc.binary_connective().Iff() != null) {
			premise = visitTff_unitary_formula(txc.tff_unitary_formula(0));
			conclusion =  visitTff_unitary_formula(txc.tff_unitary_formula(1));
			
			return new And(
					new Implies(premise, conclusion),
					new Implies(conclusion, premise)
			);
		}
		
		return new True();
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
	 * tff_unitary_formula And tff_unitary_formula | tff_and_formula And tff_unitary_formula
	 */
	@Override
	public Clause visitTff_or_formula(TFFParser.Tff_or_formulaContext txc) {
		
		Clause right;
		Clause left;
		if (txc.tff_or_formula() != null) {
			left = visitTff_or_formula(txc.tff_or_formula());
			right = visitTff_unitary_formula(txc.tff_unitary_formula().get(0));
		} else {
			left = visitTff_unitary_formula(txc.tff_unitary_formula().get(0));
			right = visitTff_unitary_formula(txc.tff_unitary_formula().get(1));
		}
		
		return new Or(left, right);
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
