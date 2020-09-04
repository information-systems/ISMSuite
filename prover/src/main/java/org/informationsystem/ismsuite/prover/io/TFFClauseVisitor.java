package org.informationsystem.ismsuite.prover.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.FirstOrderLogicWorld;
import org.informationsystem.ismsuite.prover.model.literals.Element;
import org.informationsystem.ismsuite.prover.model.literals.Literal;
import org.informationsystem.ismsuite.prover.model.literals.Relation;
import org.informationsystem.ismsuite.prover.model.literals.Variable;
import org.informationsystem.ismsuite.prover.model.operators.All;
import org.informationsystem.ismsuite.prover.model.operators.And;
import org.informationsystem.ismsuite.prover.model.operators.Equality;
import org.informationsystem.ismsuite.prover.model.operators.Exists;
import org.informationsystem.ismsuite.prover.model.operators.Implies;
import org.informationsystem.ismsuite.prover.model.operators.Not;
import org.informationsystem.ismsuite.prover.model.operators.Or;
import org.informationsystem.ismsuite.prover.parser.tff.TFFBaseVisitor;
import org.informationsystem.ismsuite.prover.parser.tff.TFFLexer;
import org.informationsystem.ismsuite.prover.parser.tff.TFFParser;
import org.informationsystem.ismsuite.prover.parser.tff.TFFParser.Tff_formulaContext;

public class TFFClauseVisitor extends TFFBaseVisitor<Clause> {

	protected FirstOrderLogicWorld lookup;
	
	private Set<ANTLRErrorListener> listeners;
	
	public TFFClauseVisitor(FirstOrderLogicWorld world) {
		super();
		this.lookup = world;
		listeners = new HashSet<>();
	}
	
	public TFFClauseVisitor setWorld(FirstOrderLogicWorld world) {
		this.lookup = world;
		return this;
	}
	
	public FirstOrderLogicWorld getWorld() {
		return lookup;
	}
	
	public void addErrorListener(ANTLRErrorListener listener) {
		listeners.add(listener);
	}
	
	public void removeErrorListener(ANTLRErrorListener listener) {
		listeners.remove(listener);
	}
	
	protected void setErrorListeners(TFFLexer lexer) {
		for(ANTLRErrorListener listener: listeners) {
			lexer.addErrorListener(listener);
		}
	}
	
	protected void setErrorListeners(TFFParser parser) {
		for(ANTLRErrorListener listener: listeners) {
			parser.addErrorListener(listener);
		}
	}
	
	public Clause visit(String tff) {
		TFFLexer lexer = new TFFLexer(CharStreams.fromString(tff));
		lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
		setErrorListeners(lexer);
		
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        TFFParser parser = new TFFParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        setErrorListeners(parser);
        Tff_formulaContext tree = parser.tff_formula();
        
        return this.visit(tree);
	}
	
	/**
	 * First argument is the Variable Name, Second its type. If a variable is not in
	 * this list, it is a Free variable, and hence, we do not know its type.
	 */
	private Map<String, String> currentlyBoundVariables = new HashMap<>();


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

	public Clause visitTff_quantified_formula(TFFParser.Tff_quantified_formulaContext txc) {
		List<Variable> variables = new ArrayList<>();
		if (txc.fof_quantifier() == null) {
			return null;
		}
		
		if (txc.variable_list() != null) {
			for (int i = 0; i < txc.variable_list().variable().size(); i++) {
				String type = "";
				if (txc.variable_list().variable(i).atomic_word() != null) {
					type = txc.variable_list().variable(i).atomic_word().getText();
				}
				String name = txc.variable_list().variable(i).Upper_word().getText();
				variables.add(new Variable(name, type));
				currentlyBoundVariables.put(name, type);
			}
			// Next, parse the Clause
			Clause clause = visitTff_unitary_formula(txc.tff_unitary_formula());
			
			if (txc.fof_quantifier().Forall() != null) {
				// Walk back
				for (int i = variables.size() - 1; i >= 0; i--) {
					All all = new All(variables.get(i), clause);
					clause = all;
					currentlyBoundVariables.remove(variables.get(i).getLabel());
				}
			} else if (txc.fof_quantifier().Exists()!=null) {
				// Walk back
				for (int i = variables.size() - 1; i >= 0; i--) {
					Exists exists = new Exists(variables.get(i), clause);
					clause = exists;
					currentlyBoundVariables.remove(variables.get(i).getLabel());
				}
			}

			return clause;
		}

		return null;
	}

	/**
	 * unary_connective tff_unitary_formula | fof_infix_unary | '('
	 * tff_logic_formula ')';
	 */
	@Override
	public Clause visitTff_unary_formula(TFFParser.Tff_unary_formulaContext txc) {
		if (txc.tff_logic_formula() != null) {
			return visitTff_logic_formula(txc.tff_logic_formula());
		}
		if (txc.tff_unitary_formula() != null) {
			Clause clause = visitTff_unitary_formula(txc.tff_unitary_formula());
			return new Not(clause);
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
		Clause left = visitFof_term(txc.fof_term(0));
		Clause right = visitFof_term(txc.fof_term(1));

		if ((left instanceof Literal) && (right instanceof Literal) ) {
			Equality eq = new Equality((Literal) left, (Literal) right);
			if (txc.Infix_equality() != null) {
				return eq;
			}
			if (txc.Infix_inequality() != null) {
				return new Not(eq);
			}
		}
		return null;
	}
	
	/**
	 * tff_atomic_formula | variable
	 */
	@Override
	public Clause visitFof_term(TFFParser.Fof_termContext txc) {
		if (txc.tff_atomic_formula() != null) {
			return visitTff_atomic_formula(txc.tff_atomic_formula());
		}
		if (txc.variable() != null) {
			return visitVariable(txc.variable());
		}
		return null;
	}
	
	@Override
	public Clause visitVariable(TFFParser.VariableContext txc) {
		String name = txc.Upper_word().getText();
		String type = "";
		if (currentlyBoundVariables.containsKey(name)) {
			type = currentlyBoundVariables.get(name);
		}
		if (txc.atomic_word() != null) {
			type = txc.atomic_word().getText();
		}
		
		return new Variable(name, type);
	}
	
	/**
	 * atomic_word ( '(' argument_list ')' )?
	 */
	@Override
	public Clause visitTff_atomic_formula(TFFParser.Tff_atomic_formulaContext txc) {
		List<Literal> parameters = new ArrayList<>();
		String name = txc.atomic_word().getText();
		if (txc.argument_list() != null) {
			for (int i = 0; i < txc.argument_list().argument().size(); i++) {
				if (txc.argument_list().argument(i).atomic_word() != null) {
					String s = txc.argument_list().argument(i).atomic_word().getText();
					parameters.add(new Element(s, getWorld().findTypeFor(s)));
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
			return new Relation(name, parameters);
		} else {
			return new Element(name, getWorld().findTypeFor(name));
		}

		
	}

	/**
	 * tff_binary_formula : tff_binary_nonassoc | tff_binary_assoc
	 */
	@Override
	public Clause visitTff_binary_formula(TFFParser.Tff_binary_formulaContext txc) {
		if (txc.tff_binary_assoc() != null) {
			return visitTff_binary_assoc(txc.tff_binary_assoc());
		}
		if (txc.tff_binary_nonassoc() != null) {
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
			conclusion = visitTff_unitary_formula(txc.tff_unitary_formula(0));

			return new Implies(premise, conclusion);
		}
		if (txc.binary_connective().Impl() != null) {
			premise = visitTff_unitary_formula(txc.tff_unitary_formula(0));
			conclusion = visitTff_unitary_formula(txc.tff_unitary_formula(1));

			return new Implies(premise, conclusion);
		}
		if (txc.binary_connective().Iff() != null) {
			premise = visitTff_unitary_formula(txc.tff_unitary_formula(0));
			conclusion = visitTff_unitary_formula(txc.tff_unitary_formula(1));

			return new And(new Implies(premise, conclusion), new Implies(conclusion, premise));
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
	 * tff_unitary_formula And tff_unitary_formula | tff_and_formula And
	 * tff_unitary_formula
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
	 * tff_unitary_formula And tff_unitary_formula | tff_and_formula And
	 * tff_unitary_formula
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
		return new Element(txc.atomic_word().get(0).getText(), txc.atomic_word().get(1).getText());
	}
	
}
