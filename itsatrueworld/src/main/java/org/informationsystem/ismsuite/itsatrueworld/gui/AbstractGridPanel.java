package org.informationsystem.ismsuite.itsatrueworld.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.informationsystem.ismsuite.itsatrueworld.controller.Controller;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorld;
import org.informationsystem.ismsuite.itsatrueworld.model.TrueWorldListener;


@SuppressWarnings("serial")
public abstract class AbstractGridPanel<E> extends JPanel implements TrueWorldListener, ListCellRenderer<E> {

	private Map<String, DefaultListModel<E>> listmodels = new HashMap<>();
	private Map<String, JPanel> panels = new HashMap<>();
	private Map<JList<E>, String> lists = new HashMap<>();
	private Map<String, E> selectedItem = new HashMap<>();
	
	private Controller controller;
	
	public Controller getController() {
		return controller;
	}
	
	protected DynamicGridPanel grid;
	
	public AbstractGridPanel(Controller controller) {
		this(controller, 3);
	}
	
	public AbstractGridPanel(Controller controller, int columns) {
		this(controller, columns, 200);
	}
	
	public AbstractGridPanel(Controller controller, int columns, int panelHeight) {
		this.controller = controller;
		
		this.setLayout(new BorderLayout());
		
		grid = new DynamicGridPanel(columns, panelHeight);
		
		JScrollPane scrollPanel = new JScrollPane(grid.getPanel());
		
		this.add(scrollPanel, BorderLayout.CENTER);
		
		this.controller.register(this);
		this.notify(controller.getModel());
	}
	
	public DefaultListModel<E> getModel(String label) {
		if (!panels.containsKey(label)) {
			getPanel(label);
		}
		return listmodels.get(label);
	}
	
	public JPanel getPanel(String label) {
		if (!panels.containsKey(label)) {
			JPanel p = createNewPanel(label);
			grid.addPanel(p);
			panels.put(label, p);
		} 
		
		return panels.get(label);
		
	}
	
	protected abstract void removeAction(E elem, String label);
	
	protected abstract void addAction(String label);
	
	
	protected JPanel createNewPanel(String label) {
		LabeledButtonPanel p = new LabeledButtonPanel(label);
		
		JLabel l = new JLabel(label);
		p.add(l, BorderLayout.NORTH);
		
		p.addButtonListener(new ButtonListener() {
			
			@Override
			public void onRemove(String label) {
				E elem = getSelectedItemOf(label);
				if (elem != null) {
					removeAction(elem, label);
				}
			}
			
			@Override
			public void onAdd(String label) {
				addAction(label);
			}
		});
		
		listmodels.put(label, new DefaultListModel<E>());
		JList<E> list = new JList<E>(listmodels.get(label));
		lists.put(list, label);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					@SuppressWarnings("rawtypes")
					JList l = (JList) arg0.getSource();
					String type = lists.get(l);
					E item = listmodels.get(type).get(l.getSelectedIndex());
					
					selectedItem.put(type, item);
					
				} catch(Exception e) {}
			}
		});
		
		list.setCellRenderer(this);
		
		JScrollPane scroller = new JScrollPane(list);
		
		p.add(scroller, BorderLayout.CENTER);
		
		p.setBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createEmptyBorder(5, 5, 5, 5),
						BorderFactory.createCompoundBorder(
								BorderFactory.createLineBorder(Color.black, 1, true),
								BorderFactory.createEmptyBorder(5, 5, 5, 5)
						)
				)
		);
		
		return p;
	}
	
	public E getSelectedItemOf(String label) {
		if (selectedItem.containsKey(label)) {
			return selectedItem.get(label);
		} else {
			return null;
		}
	}
	
	public interface ButtonListener {
		void onAdd(String label);
		void onRemove(String label);
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
						l.onAdd(getLabel());
					}
				}
				
			});
			JButton removeButton = new JButton("-");
			removeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					for(ButtonListener l: listeners) {
						l.onRemove(getLabel());
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

	@Override
	public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel label = new JLabel(generateItemString(value));
		label.setOpaque(true);
		label.setHorizontalAlignment(JLabel.LEFT);
		
		if (isSelected) {
			label.setBackground(list.getSelectionBackground());
			label.setForeground(list.getSelectionForeground());
		} else {
			label.setBackground(list.getBackground());
			label.setForeground(list.getForeground());
		}
		label.setEnabled(list.isEnabled());
		label.setFont(list.getFont());
		
		if (cellHasFocus) {
			label.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
		} else {
			label.setBorder(noFocusBorder);
		}
		
		return label;
	}
	
	protected Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
	
	protected abstract String generateItemString(E item);
	
}
