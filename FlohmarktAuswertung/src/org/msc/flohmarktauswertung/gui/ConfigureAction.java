/**
 * 
 */
package org.msc.flohmarktauswertung.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.msc.flohmarktauswertung.files.LoadFiles;

/**
 * @author marcus
 *
 */
public class ConfigureAction extends AbstractAction {
	public ConfigureAction() {
		putValue(NAME, "Einstellungen");
		putValue(SMALL_ICON, LoadFiles.getImage("configure.png"));
		putValue(SHORT_DESCRIPTION, "Konfigurieren Sie das Kassenprogramm.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_E));
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ConfigureDialog cd = new ConfigureDialog();
		cd.setModal(true);
		cd.setVisible(true);
	}

}
