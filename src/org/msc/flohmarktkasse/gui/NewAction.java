package org.msc.flohmarktkasse.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.msc.flohmarktkasse.files.LoadFiles;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;

public class NewAction extends AbstractAction {
	
	public NewAction() {
		putValue(NAME, "Neue Kasse");
		putValue(SMALL_ICON, LoadFiles.getImage("new.png"));
		putValue(SHORT_DESCRIPTION, "Legen Sie einen neuen Kassenvorgang an.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K));
	}
	
	public void actionPerformed(ActionEvent e) {
		//Insert the choice dialog for automatic or manual configuration.
		int choice = JOptionPane.showConfirmDialog(null, "Möchten sie eine automatische Konfiguration? (empfohlen)");
		if(choice == 0) {
			String name = JOptionPane.showInputDialog("Bitte geben Sie einen Namen für die Kasse ein.");
			FlohmarktKasse.setAutomaticSettings(name, true);
		} else if(choice == 1) {
			new NewFlohmarktDialog(null).setVisible(true);
		}
		
		
		
	}
	
}
