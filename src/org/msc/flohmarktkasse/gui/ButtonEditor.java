package org.msc.flohmarktkasse.gui;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

class ButtonEditor extends DefaultCellEditor {
	  protected DeleteButton button;

	  private String label;

	  private boolean isPushed;
	  private ReceiptModel rm = null;
	  public ButtonEditor(JCheckBox checkBox, ReceiptModel rm) {
	    super(checkBox);
	    this.rm = rm;
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
		  if(value instanceof DeleteButton) {
			  System.out.println("is delete button");
			  this.button = (DeleteButton) value;
		  }
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
//	    button.setId(row);
	    
//	    label = (value == null) ? "" : value.toString();
//	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
	    if (isPushed) {
	      return this.button;
	    }
	    isPushed = false;
	    return this.button;
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	  private void deleteItem() {
		  System.err.println("Starting deleting");
		  this.rm.deleteRow(this.button.getId());
	  }
	}