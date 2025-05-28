package org.msc.flohmarktkasse.gui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

public class TableTools {
	public static void packTable(JTable table) {
		int margin=10;
		for (int c=0; c<table.getColumnCount(); c++) {
	    TableModel model = table.getModel();
		DefaultTableColumnModel colModel = (DefaultTableColumnModel) table
				.getColumnModel();
		TableColumn col = colModel.getColumn(c);
		int width = 0;

		// Get width of column header
		TableCellRenderer renderer=null;
		renderer = col.getHeaderRenderer();
		if (renderer == null) {
			renderer = table.getTableHeader().getDefaultRenderer();
		}
		Component comp=null;
		comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false, false, 0, 0);
		width = comp.getPreferredSize().width;

		// Get maximum width of column data
		for (int r = 0; r < table.getRowCount(); r++) {
			renderer = table.getCellRenderer(r, c);
			comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, c), false, false, r, c);
			width = Math.max(width, comp.getPreferredSize().width);
		}

		// Add margin
		width += 2 * margin;

		// Set the width
		col.setPreferredWidth(width);
		}

	}
}
