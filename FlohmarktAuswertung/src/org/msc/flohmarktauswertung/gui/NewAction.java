package org.msc.flohmarktauswertung.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.msc.flohmarktauswertung.files.LoadFiles;
import org.msc.flohmarktauswertung.prog.FlohmarktAuswertung;

public class NewAction extends AbstractAction {
	private AuswertungWindow w = null;
	public NewAction(AuswertungWindow w) {
		this.w = w;
		putValue(NAME, "Neue Abrechnung");
		putValue(SMALL_ICON, LoadFiles.getImage("new.png"));
		putValue(SHORT_DESCRIPTION, "Legen Sie eine neue Abrechnung an.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K));
	}
	
	public void actionPerformed(ActionEvent e) {
		int choice = JOptionPane.showConfirmDialog(null, "Wollen Sie wirklich?", "Neue Abrechnung", JOptionPane.YES_NO_OPTION);
		if(choice == 0) {
			try {
				FlohmarktAuswertung.getDatabase().emptyDatabase();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.w.getStatmentModel().updateTable();
			this.w.getMenu().removeAll();
		}
	}
	
}
