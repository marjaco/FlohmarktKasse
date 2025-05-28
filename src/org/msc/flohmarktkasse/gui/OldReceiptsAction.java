package org.msc.flohmarktkasse.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import org.msc.flohmarktkasse.files.LoadFiles;

public class OldReceiptsAction extends AbstractAction {
	public OldReceiptsAction() {
		putValue(NAME, "Alte Belege anschauen");
		putValue(SMALL_ICON, LoadFiles.getImage("old-receipts.png"));
		putValue(SHORT_DESCRIPTION, "Schauen Sie alte Belege an.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		OldReceiptsDialog d = new OldReceiptsDialog();
		d.setModal(true);
		d.setVisible(true);
	}

}
