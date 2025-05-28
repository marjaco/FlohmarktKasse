package org.msc.flohmarktauswertung.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JTable;

import org.msc.flohmarktauswertung.files.LoadFiles;
import org.msc.flohmarktauswertung.prog.FlohmarktAuswertung;
import org.msc.flohmarktauswertung.prog.SellerStatement;

public class ExportStatement extends AbstractAction {
	private JTable t; 
	private String header = "";
	private String footer = "";
	public ExportStatement() {
		putValue(NAME, "Daten exportieren");
		putValue(SMALL_ICON, LoadFiles.getImage("export.png"));
		putValue(SHORT_DESCRIPTION, "Exportieren Sie die Daten");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K));
	}
	
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int option = chooser.showSaveDialog(null);
		if (option==JFileChooser.APPROVE_OPTION) {
			File selectedFile= chooser.getSelectedFile().getAbsoluteFile();
			FileWriter fileWritter;
			SellerStatement[] statements = getSellerStatements();
	        String fileContent="Anbieternummer;Anzahl Artikel;Umsatz;Anteil des Verkäufers;Kassen\n";
	        for (int i = 0; i < statements.length; i++) {
				fileContent += statements[i].getSellerId()+";"+statements[i].getNumberItems()+";"+statements[i].getFormattedSum()+";"+statements[i].getFormattedPercentage()+";"+statements[i].getCashiers()+"\n";
			}
			try {
				fileWritter = new FileWriter(selectedFile.getAbsolutePath(),true);
				BufferedWriter bufferWriter = new BufferedWriter(fileWritter);
				bufferWriter.write(fileContent);
		        bufferWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	        
	        
		}
	}
	private SellerStatement[] getSellerStatements() {
		int[] sellerIds=null;
		try {
			sellerIds = FlohmarktAuswertung.getDatabase().getSellerIds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SellerStatement[] statements = new SellerStatement[sellerIds.length];
		for (int i = 0; i < sellerIds.length; i++) {
			statements[i] = new SellerStatement(sellerIds[i]);
		}
		return statements;
	}
}
