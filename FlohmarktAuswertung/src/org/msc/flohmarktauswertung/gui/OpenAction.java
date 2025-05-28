package org.msc.flohmarktauswertung.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.msc.flohmarktauswertung.files.LoadFiles;
import org.msc.flohmarktauswertung.prog.FlohmarktAuswertung;
import org.msc.flohmarktauswertung.prog.Helper;
import org.msc.flohmarktauswertung.prog.Item;

public class OpenAction extends AbstractAction {
	private AuswertungWindow w ;
	public OpenAction(AuswertungWindow w) {
		this.w = w;
		putValue(NAME, "Kassendatei öffnen");
		putValue(SMALL_ICON, LoadFiles.getImage("fileopen.png"));
		putValue(SHORT_DESCRIPTION, "Kassendatei öffnen.");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_O));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(true);
		int option = chooser.showOpenDialog(null);
		if (option==JFileChooser.APPROVE_OPTION) {
//			File selectedFile= chooser.getSelectedFile().getAbsoluteFile();
			File[] selectedFiles = chooser.getSelectedFiles();
			for (int i = 0; i < selectedFiles.length; i++) {
				if(!isRegistered(selectedFiles[i].getAbsoluteFile())) {
					try {
						FlohmarktAuswertung.getDatabase().saveFileHash(getMD5Checksum(selectedFiles[i].getAbsoluteFile()));
						loadFile(selectedFiles[i].getAbsoluteFile());
						JOptionPane.showMessageDialog(null, "Die Datei "+selectedFiles[i].getName()+" wurde eingelesen!");
						this.w.getStatmentModel().updateTable();
						this.w.updateMenu();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Die Datei "+selectedFiles[i].getName()+" konnte nicht eingelesen werden!");
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Die Datei "+selectedFiles[i].getName()+" wurde schon einmal eingelesen!");
				}
			}
			/*if(!isRegistered(selectedFile)) {
				try {
					FlohmarktAuswertung.getDatabase().saveFileHash(getMD5Checksum(selectedFile));
					loadFile(selectedFile);
					JOptionPane.showMessageDialog(null, "Die Datei wurde eingelesen!");
					this.w.getStatmentModel().updateTable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Die Datei konnte nicht eingelesen werden!");
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Die Datei wurde schon einmal eingelesen!");
			}*/

		}
	}
	public static byte[] createChecksum(File file) throws Exception {
		InputStream fis =  new FileInputStream(file);

		byte[] buffer = new byte[1024];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;

		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	// see this How-to for a faster way to convert
	// a byte array to a HEX string
	public static String getMD5Checksum(File file) throws Exception {
		byte[] b = createChecksum(file);
		String result = "";

		for (int i=0; i < b.length; i++) {
			result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		//System.out.println("Filehash: "+result);
		return result;
	}
	public boolean isRegistered(File file) {
		String [] registeredhashes = FlohmarktAuswertung.getDatabase().getFileHashes();
		String newHash="";
		try {
			newHash = getMD5Checksum(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean found = false;
		for (int i = 0; i < registeredhashes.length; i++) {
			if(newHash.equals(registeredhashes[i])) {
				found = true;
				break;
			}
		}
		return found;
	}
	private static void loadFile(File f) {
		String fileContent = Helper.fileToString(f);
		String[] fileLines = fileContent.split("\n");
		for (int i = 0; i < fileLines.length; i++) {
			String[] s = fileLines[i].split(";");
			FlohmarktAuswertung.getDatabase().saveItem(new Item(Integer.parseInt(s[1]), Double.parseDouble(s[2]), Long.parseLong(s[3]), s[4]));
		}
	}
}
