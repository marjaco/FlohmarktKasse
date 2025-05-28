package org.msc.flohmarktauswertung.gui;

import java.sql.SQLException;

import javax.swing.JFormattedTextField;
import javax.swing.table.AbstractTableModel;

import org.msc.flohmarktauswertung.prog.FlohmarktAuswertung;
import org.msc.flohmarktauswertung.prog.SellerStatement;

public class StatementModel extends AbstractTableModel {
	private SellerStatement[] statements;
	public StatementModel() {
		updateTable();
	}
	@Override
	public int getRowCount() {
		return statements.length+1;
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(rowIndex<statements.length) {
		switch (columnIndex) {
		case 0:
			return statements[rowIndex].getSellerId();
		case 1:
			return statements[rowIndex].getNumberItems();
		case 2:
			return statements[rowIndex].getSum();
		case 3:
			return statements[rowIndex].getPercentage();
		case 4: 
			return statements[rowIndex].getPercentageOrganizer();
		case 5: 
			return statements[rowIndex].getCashiers();
		default:
			return "";
		}
		} else {
			switch (columnIndex) {
			case 0:
				return "0";
			case 1:
				return "0";
			case 2:
				return getSum();
			case 3:
				return getPercentage();
			case 4: 
				return getPercentageOrganizer();
			default:
				return "";
			}
		}
		
	}
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Verkäufernr";
		case 1:
			return "Anzahl Artikel";
		case 2:
			return "Umsatz";
		case 3:
			return "Anteil Verkäufer ("+FlohmarktAuswertung.getPercentage()+"%)";
		case 4: 
			int p = 100-FlohmarktAuswertung.getPercentage();
			return "Anteil Flohmarkt ("+p+"%)";
		case 5: 
			return "Kassen";
		default:
			return "";
		}
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==2 || columnIndex==3 || columnIndex==4) {
			return JFormattedTextField.class;
		} else {
//			return super.getColumnClass(columnIndex);
			return JFormattedTextField.class;
		}
		
	}
	private double getSum() {
		double sum = 0;
		for (int i = 0; i < statements.length; i++) {
			sum += statements[i].getSum();
		}
		return sum;
	}
	private double getPercentage() {
		double sum = 0;
		for (int i = 0; i < statements.length; i++) {
			sum += statements[i].getPercentage();
		}
		return sum;
	}
	private double getPercentageOrganizer() {
		double sum = 0;
		for (int i = 0; i < statements.length; i++) {
			sum += statements[i].getPercentageOrganizer();
		}
		return sum;
	}
	public void updateTable() {
		int[] sellerIds=null;
		try {
			sellerIds = FlohmarktAuswertung.getDatabase().getSellerIds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		statements = new SellerStatement[sellerIds.length];
		for (int i = 0; i < sellerIds.length; i++) {
			statements[i] = new SellerStatement(sellerIds[i]);
		}
		fireTableDataChanged();
	}
}
