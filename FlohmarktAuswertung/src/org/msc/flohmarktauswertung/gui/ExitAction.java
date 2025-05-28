package org.msc.flohmarktauswertung.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.msc.flohmarktauswertung.files.LoadFiles;
import org.msc.flohmarktauswertung.prog.FlohmarktAuswertung;

public class ExitAction extends AbstractAction {
	
	public ExitAction() {
		putValue(NAME, "Klausur beenden");
		putValue(SMALL_ICON, LoadFiles.getImage("exit.png"));
		putValue(SHORT_DESCRIPTION, "Beenden Sie das Kassenprogramm.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
	}
	
	public void actionPerformed(ActionEvent e) {
//		FlohmarktKasse.getClient().closeApp();
		FlohmarktAuswertung.getDatabase().shutdown();
		System.exit(0);
	}
	
}
