package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorld;
import org.informationsystem.ismsuite.prover.model.Element;

public class ElementListingPanel extends AbstractGridPanel<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247588857156850L;

	private JFrame parent;
		
	public ElementListingPanel(Controller controller, JFrame parent) {
		super(controller);
		this.parent = parent;
	}
	
	@Override
	protected JPanel createNewPanel(String label) {
		LabeledButtonPanel p = new LabeledButtonPanel(label);
		
		p.addButtonListener(new ButtonListener() {
			
			@Override
			public void removeAction(String label) {
				String elem = getSelectedItemOf(label);
				if (elem != null) {
					getController().removeElement(elem, label);
				}
			}
			
			@Override
			public void addAction(String label) {
				UpdateElementDialog.createElement(getController(), parent, label);
			}
		});
		
		JLabel l = new JLabel(label);
		p.add(l, BorderLayout.NORTH);
		
		return p;
	}

	@Override
	public void notify(TrueWorld world) {
		for(String typ: world.getWorld().getElementTypes()) {
			DefaultListModel<String> model = getModel(typ);
			model.clear();
			Iterator<Element> it = world.getWorld().getElementsIn(typ);
			while(it.hasNext()) {
				model.addElement(it.next().getLabel());
			}
		}		
	}
	
	public interface ButtonListener {
		void addAction(String label);
		void removeAction(String label);
	}
	
	class LabeledButtonPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 4439854118846343197L;
		
		private String label;
		
		public LabeledButtonPanel(String label) {
			super();
			this.label = label;
			
			setLayout(new BorderLayout(5,5));
		
			JPanel holder = new JPanel(new BorderLayout());
			
			JPanel buttons = new JPanel(new GridLayout(1,2));
			
			JButton addButton = new JButton("+");
			addButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for(ButtonListener l: listeners) {
						l.addAction(getLabel());
					}
				}
				
			});
			JButton removeButton = new JButton("-");
			removeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for(ButtonListener l: listeners) {
						l.removeAction(getLabel());
					}
				}
				
			});
			
			buttons.add(addButton);
			buttons.add(removeButton);
			holder.add(buttons, BorderLayout.EAST);
			
			add(holder, BorderLayout.SOUTH);
			
		}
		
		public String getLabel() {
			return label;
		}
		
		private Set<ButtonListener> listeners = new HashSet<>();
		
		public void addButtonListener(ButtonListener l) {
			listeners.add(l);
		}
		
	}
}
