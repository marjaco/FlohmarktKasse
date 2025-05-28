package org.msc.flohmarktkasse.gui;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

class ReimburseButtonRenderer extends DeleteButton implements TableCellRenderer {

	public ReimburseButtonRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		this.setId(row);
		return this;
	}
}