import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import org.cpntools.accesscpn.engine.highlevel.instance.Binding;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;    // Using Swing components and containers

public class UI extends JFrame {
	public String selectedItem;
	public String defSelectedItem;
	String newline = "\n";
	JList list;
	ListSelectionModel listSelectionModel;
	DefaultListModel<String> model;
	
	   // Constructor
	public UI () 
	{
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
	    setTitle("Select a valid binding to fire."); // "super" JFrame sets title
	    setSize(300, 300);         // "super" JFrame sets initial size
	    setVisible(false);          // "super" JFrame shows
	}
	
	public void showUI(List<String> validBindings)
	{
		// Convert the (arrayList) to a ListModel.
		model = new DefaultListModel<>();
		for(String c : validBindings)
			model.addElement(c);
				
		// Create the list element.
		list = new JList(model);
		
		listSelectionModel = list.getSelectionModel();       
		listSelectionModel.setSelectionMode(listSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(
                new ListSelectionHandler());
		
		JScrollPane listPane = new JScrollPane(list);
		
		JPanel controlPane = new JPanel();
		
		Container cp = getContentPane(); 
	    cp.setLayout(new FlowLayout());
	    cp.add(list);
	    
	    JButton btnExecute = new JButton("Execute");
	    cp.add(btnExecute);
	    setVisible(true);
	    
	    btnExecute.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {       	 
	        	 if (selectedItem == null | selectedItem == "null")
	        		 setVisible(true);
	        	 else
	        		 setVisible(false);
	        	 defSelectedItem = selectedItem;
	        	 model.clear();
	         }
	      });	    	
	}
	
	class ListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            
            if (lsm.isSelectionEmpty()) {
            	selectedItem = "None";
            }
            else
            {
            	selectedItem = (String) list.getSelectedValue(); 
            }
      
            
        }
    }
}

