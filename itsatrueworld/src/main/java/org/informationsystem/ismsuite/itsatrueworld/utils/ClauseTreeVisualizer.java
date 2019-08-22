package org.informationsystem.ismsuite.itsatrueworld.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

public class ClauseTreeVisualizer {

	public static JPanel constructPanel(Clause c) {
		
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		JTree tree = new JTree(BuildTree.generate(c));		
		
		p.add(tree, BorderLayout.CENTER);
		
		// Expand all its nodes
		for (int i = 0; i < tree.getRowCount(); i++) {
		    tree.expandRow(i);
		}
		
		return p;
	}
	
	private static class BuildTree implements ClauseVisitor<DefaultMutableTreeNode> {

		private Set<Variable> context;
		
		public BuildTree() {
			context = new HashSet<>();
		}
		
		public static DefaultMutableTreeNode generate(Clause c) {
			BuildTree b = new BuildTree();
			
			return c.accept(b);
		}
		
		
		@Override
		public DefaultMutableTreeNode visit(Clause c) {
			return new DefaultMutableTreeNode(c.toString());
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
	
	public static void showTreeDialog(JFrame parent, Clause c) { 
		showTreeDialog(parent, c, "Visualize clause");
	}

	public static void showTreeDialog(JFrame parent, Clause c, String title) {
		JPanel p = ClauseTreeVisualizer.constructPanel(c);
		JScrollPane scroll = new JScrollPane(p);
		p.setPreferredSize(new Dimension(400,400));
		
		scroll.getViewport().add(p);
		
		JOptionPane.showMessageDialog(parent, scroll, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
}