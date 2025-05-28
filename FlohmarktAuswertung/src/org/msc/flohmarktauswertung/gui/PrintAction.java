package org.msc.flohmarktauswertung.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.AbstractAction;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

import org.msc.flohmarktauswertung.files.LoadFiles;

public class PrintAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2865959228580540269L;
	private JTable t; 
	private String header = "";
	private String footer = "";
	public PrintAction(JTable t) {
		this.t = t;
		putValue(NAME, "Drucken");
		putValue(SMALL_ICON, LoadFiles.getImage("printer.png"));
		putValue(SHORT_DESCRIPTION, "Drucken Sie die Tabelle");
		putValue(MNEMONIC_KEY, new Integer(KeyEvent.VK_K));
	}
	
	public void actionPerformed(ActionEvent e) {
		MessageFormat head = new MessageFormat(header);
		MessageFormat foot = new MessageFormat(footer);
		try {
			t.print(PrintMode.FIT_WIDTH, head, foot);
		} catch (PrinterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void setTable(JTable t) {
		this.t = t;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}
}
