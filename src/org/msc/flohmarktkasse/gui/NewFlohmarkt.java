package org.msc.flohmarktkasse.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


public class NewFlohmarkt extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField cashiername, dateField;
	private JLabel date;
	private JButton saveFieldButton;
	private JLabel jLabel0;
	private JLabel jLabel2;

	public NewFlohmarkt() {
		initComponents();
	}

	public NewFlohmarkt(Frame parent) {
		super(parent);
		initComponents();
	}

	

	private void initComponents() {
		setTitle("Neue Kasse einrichten");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(new Color(223, 223, 223));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJTextField0(), new Constraints(new Leading(229, 184, 10, 10), new Leading(12, 12, 12)));
		add(getJLabel1(), new Constraints(new Leading(67, 12, 12), new Leading(68, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(229, 114, 12, 12), new Leading(62, 25, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(343, 10, 10), new Leading(62, 12, 12)));
		add(getJButton0(), new Constraints(new Leading(344, 12, 12), new Leading(112, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(229, 114, 12, 12), new Leading(112, 25, 12, 12)));
		add(getDate(), new Constraints(new Leading(109, 12, 12), new Leading(43, 12, 12)));
		add(getJLabel0(), new Constraints(new Leading(12, 12, 12), new Leading(12, 12, 12)));
		add(getJLabel2(), new Constraints(new Leading(67, 12, 12), new Leading(112, 12, 12)));
		add(getJTextField0(), new Constraints(new Leading(229, 184, 12, 12), new Leading(37, 12, 12)));
		setSize(470, 240);
	}

	private JTextField getJTextField0() {
		if (cashiername == null) {
			cashiername = new JTextField();
			cashiername.setText("jTextField0");
		}
		return cashiername;
	}
	
	private JTextField getDate() {
		if(dateField == null) {
			dateField = new JTextField();
		}
		return dateField;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Sicherung in: ");
		}
		return jLabel2;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Kassenbezeichnung:");
		}
		return jLabel0;
	}

	private JLabel getJLabel1() {
		if (date == null) {
			date = new JLabel();
			date.setText("Datum: ");
		}
		return date;
	}

	private JButton getJButton0() {
		if (saveFieldButton == null) {
			saveFieldButton = new JButton();
			saveFieldButton.setText("...");
		}
		return saveFieldButton;
	}

}
