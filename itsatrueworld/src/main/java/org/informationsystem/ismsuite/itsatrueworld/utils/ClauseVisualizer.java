package org.informationsystem.ismsuite.itsatrueworld.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

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

public class ClauseVisualizer {

	public static void showExplanationDialog(JFrame parent, Stack<Clause> explanation) {
		
		StringBuilder expl = new StringBuilder();
		Clause last = null;
		Clause lastSimple = null;
		
		boolean first = true;
		expl.append("Because:\n");
		for(Clause c: explanation) {
			Clause simple = c.simplify();
						
			if (simple.equals(lastSimple)) { continue; }
			last = c;
			lastSimple = simple;
			if(first) {
				first  = false;
			}  else {
				expl.append("hence\n\t");
			}
			last = c;
			expl.append(clauseToString(simple));
			expl.append("\n");
		}
		
		expl.append("\nAnd therefore\n\n");
		expl.append(clauseToString(last));
		
		// Create Message Dialog
		JOptionPane.showMessageDialog(parent, expl.toString(), "Formula is not valid", JOptionPane.WARNING_MESSAGE);
	}

	public static String generateName(String id) {
		StringTokenizer tokenizer = new StringTokenizer(id, "_");
		StringBuilder b = new StringBuilder();
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			b.append(" ");
			b.append(token);
		}
		b.delete(0, 1);
		
		// Ensure that the first letter is lower.
		b.replace(0,1, b.toString().substring(0,1).toLowerCase());
		
		return b.toString();
	}
	
	public static String generateId(String name) {
		StringBuilder b = new StringBuilder(name.replaceAll("[^A-Za-z0-9 ]", "").replace(' ', '_'));
		
		// Ensure that the first letter is lower case
		b.replace(0, 1, b.toString().substring(0,1).toLowerCase());
		
		return b.toString();
	}
	
	public static void showTreeDialog(JFrame parent, Clause c) { 
		showTreeDialog(parent, c, "Visualize clause");
	}

	public static void showTreeDialog(JFrame parent, Clause c, String title) {
		JPanel p = constructClauseTreePanel(c);
		JScrollPane scroll = new JScrollPane(p);
		p.setPreferredSize(new Dimension(400,400));
		
		scroll.getViewport().add(p);
		
		JOptionPane.showMessageDialog(parent, scroll, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	public static JPanel constructClauseTreePanel(Clause c) {
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		JTree tree = new JTree(BuildTreeViewVisitor.generate(c));		
		
		p.add(tree, BorderLayout.CENTER);
		
		// Expand all its nodes
		for (int i = 0; i < tree.getRowCount(); i++) {
		    tree.expandRow(i);
		}
		
		return p;
	}
	
	public static String clauseToString(Clause c) {
		ClauseToStringVisitor b = new ClauseToStringVisitor();
		return c.accept(b);
	}
	
	
	
	private static class BuildTreeViewVisitor implements ClauseVisitor<DefaultMutableTreeNode> {

		private Set<Variable> context;
		
		public BuildTreeViewVisitor() {
			context = new HashSet<>();
		}
		
		public static DefaultMutableTreeNode generate(Clause c) {
			BuildTreeViewVisitor b = new BuildTreeViewVisitor();
			
			return c.accept(b);
		}
		
		@Override
		public DefaultMutableTreeNode visit(Element e) {
			StringBuilder text = new StringBuilder();
			text.append("<html>");
			text.append("<b>");
			text.append(e.getLabel());
			text.append("</b>");
			text.append(" from ");
			text.append("<i>");
			text.append(e.getType());
			text.append("</i>");
			text.append("</html>");
			
			return new DefaultMutableTreeNode(text.toString());
		}

		@Override
		public DefaultMutableTreeNode visit(Relation r) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(r.getLabel());
			
			Iterator<Literal> it = r.iterator();
			
			while(it.hasNext()) {
				DefaultMutableTreeNode child = it.next().accept(this);
				node.add(child);
			}
			
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(Variable v) {
			
			StringBuilder text = new StringBuilder();
			
			text.append("<html>");
			if (!context.contains(v)) {
				text.append("<font color=\"red\">");
			}
			text.append("<b>");
			text.append(v.getLabel());
			text.append("</b>");
			text.append(" from ");
			text.append("<i>");
			text.append(v.getType());
			text.append("</i>");
			if (!context.contains(v)) {
				text.append("</font>");
			}
			text.append("</html>");
			
			return new DefaultMutableTreeNode(text.toString());
		}

		@Override
		public DefaultMutableTreeNode visit(All a) {
			
			context.add(a.getVariable());

			DefaultMutableTreeNode node = new DefaultMutableTreeNode("FOR ALL");
			node.add(a.getVariable().accept(this));
			node.add(a.getOperand().accept(this));

			context.remove(a.getVariable());
			
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(And a) {
	
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("AND");

			Iterator<Clause> it = a.operands();
			while(it.hasNext()) {
				node.add(it.next().accept(this));
			}
			
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(Equality e) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("EQUALS");
			
			node.add(e.getLeft().accept(this));
			node.add(e.getRight().accept(this));
			
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(Exists e) {
			context.add(e.getVariable());
		
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("EXISTS");
			
			node.add(e.getVariable().accept(this));
			node.add(e.getOperand().accept(this));
			
			context.remove(e.getVariable());
			
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(False f) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("FALSE");
			
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(Implies i) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("IMPLIES");
			
			node.add(i.getPremise().accept(this));
			node.add(i.getConclusion().accept(this));
			
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(Not n) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("NOT");
			node.add(n.getOperand().accept(this));
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(Or o) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("OR");
			
			Iterator<Clause> it = o.operands();
			while(it.hasNext()) {
				node.add(it.next().accept(this));
			}
			
			return node;
		}

		@Override
		public DefaultMutableTreeNode visit(True t) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("TRUE");
			
			return node;
		}
		
	}
	
	private static class ClauseToStringVisitor implements ClauseVisitor<String> {

		private boolean noBound = true;
		
		@Override
		public String visit(Element e) {
			return e.getLabel() + (noBound ? " FROM " + e.getType() : "" );
		}

		@Override
		public String visit(Relation r) {
			StringBuilder b = new StringBuilder();
			b.append(r.getLabel());
			b.append(" ( ");
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
			return v.getLabel() + (noBound ? " IN " + v.getType() : "" );
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
				}
				b.append( it.next().accept(this) );
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
				}
				b.append( it.next().accept(this) );
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
