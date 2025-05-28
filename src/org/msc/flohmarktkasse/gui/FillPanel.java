package org.msc.flohmarktkasse.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.msc.flohmarktkasse.prog.Config;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;

public class FillPanel extends JPanel {
	private JLabel sum, totalSumLabel, counterLabel;
	private JFormattedTextField ft, totalSum ;
	private JTextField counterField;
	public FillPanel() {
		this.setLayout(new BorderLayout());
		sum = new JLabel();
		sum.setFont(Config.getBoldFont());
		JPanel p = new JPanel(new BorderLayout());
		JLabel l = new JLabel("Summe");
		l.setFont(Config.getBigFont());
		p.add(l, BorderLayout.CENTER);
		p.add(sum, BorderLayout.SOUTH);
		totalSumLabel = new JLabel();
		totalSumLabel.setFont(Config.getNormalFont());
		counterLabel = new JLabel();
		counterLabel.setFont(Config.getNormalFont());
		JPanel topPanel = new JPanel(new GridLayout(4,0));
		topPanel.add(new JLabel("Gesamtsumme"));
		topPanel.add(totalSumLabel);
		topPanel.add(new JLabel("Kunden"));
		topPanel.add(counterLabel);
		this.add(topPanel, BorderLayout.NORTH);
		this.add(p, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(250,300));
	}
	public void setSum(double s) {
		sum.setText(NumberFormat.getCurrencyInstance().format(s));
	}
	public void setCounter(int s) {
		counterLabel.setText(s+"");
	}
	public void setTotalSum(double s) {
		totalSumLabel.setText(NumberFormat.getCurrencyInstance().format(s));
	}
	public JFormattedTextField getTotalSum() {
		if(totalSum == null) {
			totalSum = new JFormattedTextField(NumberFormat.getCurrencyInstance());
			totalSum.setFont(Config.getNormalFont());
			totalSum.setValue(FlohmarktKasse.getTotalSum());
			totalSum.setEditable(false);
		}
		return totalSum;
	}
	public JTextField getCounterField() {
		if(counterField == null) {
			counterField = new JTextField("0");
			counterField.setFont(Config.getNormalFont());
			counterField.setEditable(false);
		}
		return counterField;
	}
}
