package org.msc.flohmarktkasse.gui;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.msc.flohmarktkasse.prog.FlohmarktKasse;
import org.msc.flohmarktkasse.prog.Item;
import org.msc.flohmarktkasse.prog.Receipt;

public class ReceiptModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 589628088605392138L;
	Receipt r;
	public ReceiptModel(Receipt r) {
		this.r=r;
	}
	@Override
	public int getRowCount() {
		return this.r.getRows()+1;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex>=this.r.getRows()) {
			if(columnIndex==0) {
				return "Summe";
			} else if(columnIndex==1){
				return this.r.getSum();
			} else {
				return new DeleteButton();
			}
		} else {
			if(columnIndex==0) {
				return r.getItem(rowIndex).getId();
			}else if(columnIndex == 1){
				return r.getItem(rowIndex).getPrice();
			} else {
				DeleteButton b = new DeleteButton(rowIndex, this);
				b.setId(rowIndex);
				return b;
			}
				
		}
	}

	@Override
	public String getColumnName(int column) {
		if(column==0) {
			return "AnbieterNr.";
		} else if(column==1){
			return "Preis";
		} else {
			return "L";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==0) {
			return String.class;
		} else if(columnIndex == 1){
			return Double.class;
		} else {
			return DeleteButton.class;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			this.r.getItem(rowIndex).setId((String) aValue);
		}else if(columnIndex == 1) {
			this.r.getItem(rowIndex).setPrice(Double.toString((Double) aValue));
		} 
	}

	@Override
	public void fireTableRowsInserted(int firstRow, int lastRow) {
		super.fireTableRowsInserted(firstRow, lastRow);
	}

	@Override
	public void fireTableRowsDeleted(int firstRow, int lastRow) {
		super.fireTableRowsDeleted(firstRow, lastRow);
	}
	public void addRow(Item item) {
		Object[] o = new Object[2];
		o[0]=item.getId();
		o[1]=item.getPrice();
		fireTableDataChanged();
		JTable t = FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().getJTable0();
		t.scrollRectToVisible(t.getCellRect(t.getRowCount()-1, 0, true));
	}
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
//		
	}
	public void deleteRow(int id) {
		this.r.deleteItem(id);
		this.fireTableDataChanged();
		FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().getSellerIDField().requestFocusInWindow();
	}
	
}
