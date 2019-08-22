package org.informationsystem.ismsuite.itsatrueworld.utils;

import java.util.Iterator;

import org.informationsystem.ismsuite.prover.model.All;
import org.informationsystem.ismsuite.prover.model.And;
import org.informationsystem.ismsuite.prover.model.Clause;
import org.informationsystem.ismsuite.prover.model.ClauseVisitor;
import org.informationsystem.ismsuite.prover.model.Element;
import org.informationsystem.ismsuite.prover.model.Equality;
import org.informationsystem.ismsuite.prover.model.Exists;
import org.informationsystem.ismsuite.prover.model.False;
import org.informationsystem.ismsuite.prover.model.Implies;
import org.informationsystem.ismsuite.prover.model.Literal;
import org.informationsystem.ismsuite.prover.model.Not;
import org.informationsystem.ismsuite.prover.model.Or;
import org.informationsystem.ismsuite.prover.model.Relation;
import org.informationsystem.ismsuite.prover.model.True;
import org.informationsystem.ismsuite.prover.model.Variable;

public class ClauseToText {

	public static String convertClause(Clause c) {
		BuildTree b = new BuildTree();
		return c.accept(b);
	}
	
	private static class BuildTree implements ClauseVisitor<String> {

		private boolean noBound = true;
		
		@Override
		public String visit(Element e) {
			return e.getLabel() + (noBound ? " FROM " + e.getType() : "" );
		}

		@Override
		public String visit(Relation r) {
			StringBuilder b = new StringBuilder();
			b.append(r.getLabel());
			b.append("(");
			Iterator<Literal> it = r.iterator();
			noBound = false;
			boolean first = true;
			while(it.hasNext()) {
				if (first) { 
					first = false; 
				} else {
					b.append(", ");
				}
				b.append(it.next().accept(this));
			}
			noBound = true;
			
			b.append(" )");
						
			return b.toString();
		}

		@Override
		public String visit(Variable v) {
			return v.getLabel() + (noBound ? " FROM " + v.getType() : "" );
		}

		@Override
		public String visit(All a) {
			StringBuilder b = new StringBuilder();
			b.append("FOR ALL [ ");
			b.append(a.getVariable().accept(this));
			Clause operand = a.getOperand();
			
			// Compact all bounds
			while(operand instanceof All) {
				b.append(", ");
				b.append(((All) operand).getVariable().accept(this));
				operand = ((All) operand).getOperand(); 
			}
			
			b.append(" ] IT HOLDS THAT ");
			b.append(operand.accept(this));
			
			return b.toString();
		}

		@Override
		public String visit(And a) {
			StringBuilder b = new StringBuilder();
			b.append("( ");
			boolean first = true;
			
			Iterator<Clause> it = a.operands();
			while(it.hasNext()) {
				if (first) {
					first = false;
				} else {
					b.append(" AND ");
					b.append( it.next().accept(this) );
				}
			}
			
			b.append(")");
			return b.toString();
		}

		@Override
		public String visit(Equality e) {
			StringBuilder b = new StringBuilder();
			b.append(" ( " );
			b.append( e.getLeft().accept(this) );
			b.append(" = ");
			b.append( e.getRight().accept(this) );
			b.append(" )");
			return b.toString();
		}

		@Override
		public String visit(Exists e) {
			StringBuilder b = new StringBuilder();
			b.append("THERE EXIST SOME [ ");
			b.append(e.getVariable().accept(this));
			Clause operand = e.getOperand();
			
			// Compact all bounds
			while(operand instanceof Exists) {
				b.append(", ");
				b.append(((Exists) operand).getVariable().accept(this) );
				operand = ((Exists) operand).getOperand(); 
			}
			
			b.append(" ] SUCH THAT ");
			b.append(operand.accept(this));
			
			return b.toString();
		}

		@Override
		public String visit(False f) {
			return "FALSE";
		}

		@Override
		public String visit(Implies i) {
			StringBuilder b = new StringBuilder();
			b.append("( ");
			b.append( i.getPremise().accept(this) );
			b.append( " IMPLIES ");
			b.append( i.getConclusion().accept(this) );
			b.append(" )");
			return b.toString();
		}

		@Override
		public String visit(Not n) {
			StringBuilder b = new StringBuilder();
			b.append("NOT ( ");
			b.append( n.getOperand().accept(this) );
			b.append(" )");
			return b.toString();
		}

		@Override
		public String visit(Or o) {
			StringBuilder b = new StringBuilder();
			b.append("( ");
			boolean first = true;
			
			Iterator<Clause> it = o.operands();
			while(it.hasNext()) {
				if (first) {
					first = false;
				} else {
					b.append(" OR ");
					b.append( it.next().accept(this) );
				}
			}
			
			b.append(")");
			return b.toString();
		}

		@Override
		public String visit(True t) {
			return "TRUE";
		}
		
	}
	
	
}
