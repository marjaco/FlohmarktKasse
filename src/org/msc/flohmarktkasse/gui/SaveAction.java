package org.msc.flohmarktkasse.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import org.msc.flohmarktkasse.files.LoadFiles;

public class SaveAction extends AbstractAction {
	
	public SaveAction() {
		putValue(NAME, "Datei speichern");
		putValue(SMALL_ICON, LoadFiles.getImage("fileexport.png"));
		putValue(SHORT_DESCRIPTION, "Datei speichern");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K));
	}
	
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int option = chooser.showSaveDialog(null);
		if (option==JFileChooser.APPROVE_OPTION) {
			File selectedFile= chooser.getSelectedFile().getAbsoluteFile();
		}
	}
	
}
