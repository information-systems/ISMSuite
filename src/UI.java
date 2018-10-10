import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.common.collect.Multimap;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class UI extends JFrame {
	public static String selectedBinding; // The selected binding
	public static String bindingToExecute; // The binding to execute, i.e. the binding which has been selected when the 'execute' button has been pressed.
	private static DefaultListModel<String> model; // The model which contains 
	private static JPanel contentPane; // The contentPane of the whole UI.
	private static JTextField bucketText; 
	private static JList bindingList; // The list with all the bindings.
	private JScrollPane scrollPaneBindings; // The scroll pane that contains all the bindings to fire. Contains the bindingList
	static CheckListItem[] checkListItemArray; // The array with all the check list items.
	private static List<JScrollPane> scrollPaneList;
	private JButton btnSelectRandom;
	private JButton executeButton;
	private static List<String> checkedcheckboxes = new ArrayList<String>(); // List with all the checkedcheckboxes.
	private static JScrollPane Scrollpane;
	private static JButton updateBucketsButton; // The button which if pressed updates the shown buckets.
	private static List<String> currentPopulation; // The current population.
	private static JList bucketList; // The list with checkboxitems.
	
	//public static boolean bindingExecuted;
	
	// Invoke/show the UI.
	public static void invokeUI(List<String> validBindings, List<String> population, String specificationFileLocation) throws Exception, InterruptedException {
		EventQueue.invokeAndWait(new Runnable() {
			@Override public void run() {
				try {
					final UI frame = new UI(validBindings, population, specificationFileLocation);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame and show it.
	 */
	public UI(List<String> validBindings, List<String> population, String specificationFileLocation) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1328, 700); // The bounds of the frame/
		
		// Create the content pane for the frame.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Create the execute bottn and add it to the contentpane.
		executeButton = new JButton("Execute");
		executeButton.setBounds(762, 9, 129, 23);
		contentPane.add(executeButton);
		
		scrollPaneBindings = new JScrollPane();
		scrollPaneBindings.setBounds(10, 11, 742, 229);
		contentPane.add(scrollPaneBindings);
		
		// Create the model, i.e. which valid bindings the user can fire.
		model = castListToDefaultListModel(validBindings);
		
		// Create the list that contains all the validbindings that the user can execute.
		bindingList = new JList();
		
		ListSelectionModel listSelectionModel = bindingList.getSelectionModel();
		listSelectionModel.setSelectionMode(listSelectionModel.SINGLE_SELECTION); // Make it so you can only select 1 item always.
		
		// Add the model to the bindingList.
		bindingList.setModel(model);
		
		// Set that the bindings list is in the middle of the scrollpane.
		scrollPaneBindings.setViewportView(bindingList);
		
		// Create the select random button.
		btnSelectRandom = new JButton("Select Random");
		btnSelectRandom.setBounds(762, 43, 129, 23);
		contentPane.add(btnSelectRandom);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(916, 11, 172, 229);
		contentPane.add(scrollPane);
		
		// Create the list with checkboxes, which contains all the buckets.
		bucketList = new JList();
		
		// Fill the bucket list with all the possible buckets, i.e. all the possible labels.
		List<populationLabel> labels = SpecificationFileAccessor.getLabels(specificationFileLocation);
		checkListItemArray = new CheckListItem[labels.size()];
		
		for (int i = 0; i < labels.size(); i++)
		{
			checkListItemArray[i] = new CheckListItem(labels.get(i).getName());
		}
		
		bucketList.setListData(checkListItemArray);
		bucketList.setCellRenderer(new CheckListRenderer());	
		bucketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // make it so you can only select one item at once.
		scrollPane.setViewportView(bucketList);
		
		// If a checkbox is clicked, select or deselect the specific clicked checkbox.
		bucketList.addMouseListener(new MouseAdapter() {
		      @Override
		      public void mouseClicked(MouseEvent event) {
		        JList list = (JList) event.getSource();
		        int index = list.locationToIndex(event.getPoint());// Get index of item clicked
		                                                           
		        CheckListItem item = (CheckListItem) list.getModel().getElementAt(index); // Get the checklist that is clicked.
		            
		        item.setSelected(!item.isSelected()); // Toggle selected state
		        list.repaint(list.getCellBounds(index, index));// Repaint cell
		      }
		    });
		
		// Create the 'update buckets' button.
		updateBucketsButton = new JButton("Update Buckets");
		updateBucketsButton.setBounds(1114, 9, 129, 23);
		contentPane.add(updateBucketsButton);
		
		// Set the binding executed to false.
		//bindingExecuted = false;
		
		// What happens if you click on the button.
		// Select a random item within the binding list.
		btnSelectRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListModel<String> bindingModel = bindingList.getModel();
	    		int rnd = new Random().nextInt(bindingModel.getSize());
	    		bindingList.setSelectedIndex(rnd);
			}
		});
		
		// What happens if the user select a value in the binding list.
				bindingList.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = bindingList.getSelectionModel();
				// If no binding has been selected. Set the selectedBinding to null;
				if (lsm.isSelectionEmpty() | bindingList.getSelectedValue() == null)
				{
					selectedBinding = "None";
				}
					
				if (!lsm.isSelectionEmpty() | bindingList.getSelectedValue() != null)
				{
					selectedBinding = (String) bindingList.getSelectedValue();
				}
								
				}});
		
		// What happens if you click the execute button.
		// It sets the bindingToExecute to the value of the selectedBinding and clears the list that contains all the bindings.
		executeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedBinding != null | bindingToExecute != null )
				{
					bindingToExecute = selectedBinding;
					
					//bindingExecuted = true;
					
					// Clear the bindings within the list.
					model.clear();
				}
					
			}
		});
	}
	
	
	private static void createBuckets(List<String> population, List<String> labels) throws Exception {
		
	int x = 10; 		// The x-value (horizontal) from which the buckets are added to the east.
	int y = 250; 		// The starting y-value (vertical) from which the buckets are added.
	int width = 201; 	// The width of one bucket.
	int height = 80; 	// The height of one bucket.
	int currentAmountOfHorizontalBuckets = 0; // Gets updated. Contains the amount of buckets on one line in the horizontal direction
	int numberHorizontalBuckets = 6; // The amount of buckets allowed on one horizontal line.
	scrollPaneList = new ArrayList<JScrollPane>(); // List which keeps track of the current scroll panes on the UI.
	
	for (String label : labels)
	{	
		// If the maximum amount of horizontal buckets on one line has been reached. Update the x and y coordinates for the next line.
		// Also reset the current amount of horizontal buckets to 0.
		if (currentAmountOfHorizontalBuckets == numberHorizontalBuckets)
		{
			x = 10;
			y += 105;		
			currentAmountOfHorizontalBuckets = 0;
		}
		
		// Add the scrollpane for the buckets. This allows the user to scroll through the content in the bucket.
		Scrollpane = new JScrollPane();
		scrollPaneList.add(Scrollpane);
		
		// Set the coordinates for the scrollpane.
		Scrollpane.setBounds(x, y, width, height);
		contentPane.add(Scrollpane);
		
		// If the maximum of horizontal buckets on one line has not been reached.
		// Update the current amount of horizontal buckets and the x-coordinate.
		if (currentAmountOfHorizontalBuckets != numberHorizontalBuckets)
		{
			currentAmountOfHorizontalBuckets += 1;
			x += 220;
		}
		
		// Add the list. This will contain all the content for the bucket.
		JList list = new JList(getPartOfPopulation(population, label));
		
		// Make it so that the list is in the middle of the scrollpane.
		Scrollpane.setViewportView(list);
		
		// Add an textfield that contains the name of the label in the bucket.
		bucketText = new JTextField();
		bucketText.setText(label);
		Scrollpane.setColumnHeaderView(bucketText);
		bucketText.setColumns(10);
		bucketText.setEditable(false); // Make it so that the user can not edit the text at the bucket.
		
	}
}
	
	// Casts a list a DefaultListModel.
	private static DefaultListModel castListToDefaultListModel(List<String> list)
	{
		DefaultListModel model = new DefaultListModel<>();
		for (String s : list)
		{
			model.addElement(s);
		}
		return model;
	}
	
	// Returns the statements (part of the population) that belongs to the specific label.
	private static String[] getPartOfPopulation(List<String> population, String label) throws Exception
	{
		List<String> partOfPopulation = new ArrayList<String>();
		
		for (String value : population)
		{
			String valueTemp = value.substring(0, value.indexOf("(")); // Get the label from the value from the population.
				
			if (!label.toLowerCase().equals(valueTemp.toLowerCase())) // If the label from the population is not equal to the specified label, go to the next item in the loop.
				continue;
			
			else // If they are equal, add it to list.
			{
				partOfPopulation.add(value);
			}
		}
		return partOfPopulation.stream().toArray(String[]:: new); // Convert to array.
	}
	
	public static void updateUIList(List<String> validBindings, List<String> population, String specificationFileLocation) throws Exception, InterruptedException
	{
		EventQueue.invokeAndWait(new Runnable() {
			@Override public void run() {
				try {
					// Check if the list with checked checkboxes is not null and not empty.
					if (checkedcheckboxes != null && checkListItemArray != null)
					{
						
						checkedcheckboxes.clear(); // Clear the check box list.
						
						// For each check list item, check if it is selected. if so, add to the checked checkboxes list.
						for (CheckListItem checkList : checkListItemArray)
						{
							if (checkList.isSelected())
								checkedcheckboxes.add(checkList.toString());
						}
						
						// Revalidate and repaint.
						bucketList.revalidate();
						bucketList.repaint();
						contentPane.revalidate();
					}
					
					// Remove all the scrollPanes.
					if (scrollPaneList != null && !scrollPaneList.isEmpty())	
					{		
						for (JScrollPane scrollp : scrollPaneList)
						{
							scrollp.removeAll();
							scrollp.getParent().remove(scrollp); // Get the contentPane and remove the item itself from the contentPane.
							scrollp.revalidate();
							scrollp.repaint();
							scrollp.getLayout();
						}
					}
					
					model = castListToDefaultListModel(validBindings); // reset the model to the new validBindings.
					bindingList.setModel(model); // Set the model
					
					// Revalidate and repaint the bindingList.
					bindingList.revalidate();
					bindingList.repaint();
					
					// Set the current population to the new population.
					currentPopulation = population;
					
					// Create the buckets with the new population and for the specified labels.
					// The specified labels are the checkedcheckboxes.
					createBuckets(population, checkedcheckboxes);
					
					// Revalidate and repaint the scrollpanes. This fills the buckets with the new values.
					for (JScrollPane sp : scrollPaneList)
					{
						sp.revalidate();
						sp.repaint();
						sp.doLayout();
					}
					
					
					// If the update Buckets button has been pressed, do the following.
					updateBucketsButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							// Clear the checkedcheckboxes list.
							checkedcheckboxes.clear();
							
							// Check for each checklist if it is selected, if it is. Add the item to the checkedcheckboxes list.
							for (CheckListItem checkList : checkListItemArray)
							{
								if (checkList.isSelected())
									checkedcheckboxes.add(checkList.toString());
							}
							
							// If there are scroll panes (in the UI). Remove them and thereafter repaint.
							if (scrollPaneList != null && !scrollPaneList.isEmpty())	
								{		
									for (JScrollPane scrollp : scrollPaneList)
									{
										scrollp.removeAll();
										scrollp.getParent().remove(scrollp);
										scrollp.revalidate();
										scrollp.repaint();
										scrollp.getLayout();
									}
								}
								
								try {
									// Create buckets, with the current population and the checkedCheckboxes.
									createBuckets(currentPopulation, checkedcheckboxes);
									contentPane.revalidate();
									contentPane.repaint();
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							
						}
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
