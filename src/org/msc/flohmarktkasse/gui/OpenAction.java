package org.msc.flohmarktkasse.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import org.msc.flohmarktkasse.files.LoadFiles;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;
import org.msc.flohmarktkasse.prog.Helper;

public class OpenAction extends AbstractAction {
	public OpenAction() {
		putValue(NAME, "Alten Kassenvorgang öffnen");
		putValue(SMALL_ICON, LoadFiles.getImage("fileopen.png"));
		putValue(SHORT_DESCRIPTION, "Alten Kassenvorgang öffnen.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_O));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int option = chooser.showOpenDialog(null);
		if (option==JFileChooser.APPROVE_OPTION) {
			File selectedFile= chooser.getSelectedFile().getAbsoluteFile();
			FlohmarktKasse.processFileContent(Helper.fileToString(selectedFile));
			FlohmarktKasse.setFile(selectedFile);
			FlohmarktKasse.startCashier();
		}
	}

}
