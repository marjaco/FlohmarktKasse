package org.msc.flohmarktauswertung.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.msc.flohmarktauswertung.files.LoadFiles;

public class HelpAction extends AbstractAction {
	public HelpAction() {
		putValue(NAME, "Hilfe");
		putValue(SMALL_ICON, LoadFiles.getImage("help.png"));
		putValue(SHORT_DESCRIPTION, "Hilfe zum Kassenprogramm.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_H));
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		HelpDialog hd = new HelpDialog();
		hd.setModal(true);
		hd.setVisible(true);
	}

}
