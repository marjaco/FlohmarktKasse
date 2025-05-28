package org.msc.flohmarktkasse.gui;

import java.awt.Dimension;

import javax.swing.JButton;

import org.msc.flohmarktkasse.files.LoadFiles;

public class DeleteButton extends JButton {
	private int id;
	private ReceiptModel rm;
	public DeleteButton() {
		this.id=0;
		this.setText("");
		this.setIcon(LoadFiles.getImage("delete.png"));
	}
	public DeleteButton(int id, ReceiptModel rm) {
		this.id=id;
		this.setText("");
		this.rm=rm;
		this.addActionListener(new DeleteAction(this));
		this.setIcon(LoadFiles.getImage("delete.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setSize(new Dimension(20, 20));
	}
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return this.id;
	}
	public ReceiptModel getReceiptModel() {
		return this.rm;
	}
}
