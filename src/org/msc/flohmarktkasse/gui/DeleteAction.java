package org.msc.flohmarktkasse.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.msc.flohmarktkasse.files.LoadFiles;

public class DeleteAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2470842762209662578L;
	private DeleteButton b;
	public DeleteAction(DeleteButton b) {
		this.b=b;
		putValue(NAME, "Löschen");
		putValue(SMALL_ICON, LoadFiles.getImage("delete.png"));
		putValue(SHORT_DESCRIPTION, "Löschen");
	}
	
	public void actionPerformed(ActionEvent e) {
		b.getReceiptModel().deleteRow(b.getId());
	}
	
}
