
public class CheckListItem {
		  
		  // The label of the check list item, e.g. Person
		  private String label;
		  
		  // If the item is selected. Note: it will always start as selected.
		  private boolean isSelected = true;

		  // The constructor. Set the check list label to the specified label.
		  public CheckListItem(String label) {
		    this.label = label;
		  }
		  
		  // Returns if the item is selected.
		  public boolean isSelected() {
		    return isSelected;
		  }
		  
		  // Change if the item is selected or not.
		  public void setSelected(boolean isSelected) {
		    this.isSelected = isSelected;
		  }
		  
		  // toString returns the label.
		  @Override
		  public String toString() {
		    return label;
		  }
		}
