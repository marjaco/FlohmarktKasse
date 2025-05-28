package org.msc.flohmarktauswertung.gui;

import javax.swing.JFormattedTextField;
import javax.swing.table.AbstractTableModel;

import org.msc.flohmarktauswertung.prog.FlohmarktAuswertung;
import org.msc.flohmarktauswertung.prog.Helper;
import org.msc.flohmarktauswertung.prog.Item;
import org.msc.flohmarktauswertung.prog.Seller;

public class SingleStatementModel extends AbstractTableModel {
//	private double[] figures;
	private Item[] items;
	public SingleStatementModel() {
		
	}
	@Override
	public int getRowCount() {
		if(items == null) {
			return 0;
		}
		return items.length+1;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(items == null) {
			return "";
		}
		//Letzte Zeile
		if(rowIndex>=items.length) {
			if(columnIndex == 2) {
				return getPercentage();
			}
			if(columnIndex == 3) {
				return getSum();
			}
			return "";
		//Alle anderen Zeilen
		} else {
			switch(columnIndex) {
			case 0: return items[rowIndex].getCashier();
			case 1: return Helper.convertMillisToTime(items[rowIndex].getTime());
			case 2: return getPercentage(rowIndex);
			case 3: return items[rowIndex].getPrice();
			}
		}
		return "";
	}
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Kasse";
		case 1:
			return "Uhrzeit";
		case 2:
			return "Anteil ("+FlohmarktAuswertung.getPercentage()+"%)";
		case 3:
			return "Umsatz";
		default:
			return "";
		}
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0 || columnIndex == 1) {
			return String.class;
		}
		return JFormattedTextField.class;
		
	}
	private double getSum() {
		double sum = 0;
		for (int i = 0; i < items.length; i++) {
			sum += items[i].getPrice();
		}
		return sum;
	}
	private double getPercentage(int id) {
		return this.items[id].getPrice()/100*FlohmarktAuswertung.getPercentage();
	}
	private double getPercentage() {
		double sum = 0;
		for (int i = 0; i < items.length; i++) {
			sum += items[i].getPrice()/100*FlohmarktAuswertung.getPercentage();
		}
		return sum;
	}
	/*private double getPercentageOrganizer() {
		double sum = 0;
		for (int i = 0; i < statements.length; i++) {
			sum += statements[i].getPercentageOrganizer();
		}
		return sum;
	}*/
	public void updateTable(Seller seller) {
//		System.out.println("Updating");
		this.items = FlohmarktAuswertung.getDatabase().getItems(seller.getSellerId());
		
//		System.out.println(seller + " "+this.figures.length);
		fireTableDataChanged();
	}
}
