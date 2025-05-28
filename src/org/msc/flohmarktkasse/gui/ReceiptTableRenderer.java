package org.msc.flohmarktkasse.gui;

import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.msc.flohmarktkasse.prog.Config;

public class ReceiptTableRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6258767450374018386L;
	private ReceiptModel rm;
	public ReceiptTableRenderer(ReceiptModel rm) {
		this.rm = rm;
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if(column==1) {
			JFormattedTextField ft = new JFormattedTextField(NumberFormat.getCurrencyInstance());
			ft.setValue(Float.parseFloat(value+""));
			if(Float.parseFloat(value+"")<0) {
				ft.setForeground(Color.RED);
			}
			if(rm.getRowCount()-1==row) {
				ft.setFont(Config.getBoldFont());
			} else {
				ft.setFont(Config.getBigFont());
			}
			return ft;
		} else if(column==0){
			JLabel l = new JLabel(""+value);
			if(rm.getRowCount()-1==row) {
				l.setFont(Config.getBoldFont());
			} else {
				l.setFont(Config.getBigFont());
			}
			
			l.setForeground(Color.gray);
			return l;
		} else {
			return null;
		}
	}
	
}
