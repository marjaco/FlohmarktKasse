package org.msc.flohmarktkasse.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.msc.flohmarktkasse.files.LoadFiles;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;
/**
 * @deprecated
 * @author marcus
 *
 */
public class ExitAction extends AbstractAction {
	
	public ExitAction() {
		putValue(NAME, "Kasse beenden");
		putValue(SMALL_ICON, LoadFiles.getImage("exit.png"));
		putValue(SHORT_DESCRIPTION, "Beenden Sie das Kassenprogramm.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_B));
	}
	
	public void actionPerformed(ActionEvent e) {
		FlohmarktKasse.getClient().closeApp();
		
	}
	
}
