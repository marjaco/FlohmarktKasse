package org.msc.flohmarktauswertung.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class StatementRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6258767450374018386L;
	private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	private Font smallFont = new Font(Font.SANS_SERIF, Font.PLAIN,8);
	private Color EVEN_LIGHTER_GRAY = new Color(235,235,235);
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if(value.equals("")) {
			value="";
		}
		JFormattedTextField ft=null;
		if(column==2 || column==3 || column==4) {
			ft = new JFormattedTextField(NumberFormat.getCurrencyInstance());
			ft.setValue(Float.parseFloat(value+""));
			ft.setFont(font);
			ft.setHorizontalAlignment(JTextField.RIGHT);
			
		} else if(column==5){
			ft= new JFormattedTextField(toString());
			ft.setValue(value+"");
			ft.setFont(smallFont);
			ft.setHorizontalAlignment(JTextField.LEFT);
			
		} else {
			ft = new JFormattedTextField(NumberFormat.getInstance());
			ft.setValue(Float.parseFloat(value+""));
			ft.setFont(font);
			ft.setHorizontalAlignment(JTextField.RIGHT);
		}
		if(row%2 == 0) {
			ft.setBackground(EVEN_LIGHTER_GRAY);
		} else {
			ft.setBackground(Color.WHITE);
		}
		return ft;
	}
	
}
