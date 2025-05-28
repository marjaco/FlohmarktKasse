package org.msc.flohmarktkasse.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.msc.flohmarktkasse.prog.Config;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;


public class ChangeDialog2 extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel sumLabel;
	private JLabel jLabel1;
	private JLabel givenLabel;
	private JFormattedTextField givenField, sumField;
	private JLabel changeLabel;
	private JFormattedTextField changeField;
	private JButton okButton;
	private JButton cancelButton;
	private double sum;
	private String formattedSum;
	private InputPanel ip;
	public ChangeDialog2(double sum, InputPanel ip) {
		this.sum = sum;
		this.ip = ip;
		this.formattedSum = NumberFormat.getCurrencyInstance().format(sum);
		initComponents();
		getGivenField().requestFocusInWindow();
	}

	private void initComponents() {
		setFont(new Font("Kassieren", Font.PLAIN, 12));
		setBackground(new Color(223, 223, 223));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		setSize(500, 350);
		add(getJLabel1(), new Constraints(new Leading(164, 10, 10), new Leading(12, 12, 12)));
		add(getGivenLabel(), new Constraints(new Leading(12, 12, 12), new Leading(72, 10, 10)));
		add(getGivenField(), new Constraints(new Leading(161, 10, 10), new Leading(70, 12, 12)));
		add(getChangeField(), new Constraints(new Leading(161, 12, 12), new Leading(138, 12, 12)));
		add(getOKButton(), new Constraints(new Leading(12, 12, 12), new Leading(195, 10, 10)));
		add(getCancelButton(), new Constraints(new Leading(158, 10, 10), new Leading(193, 10, 10)));
		add(getSumlabel(), new Constraints(new Leading(19, 12, 12), new Leading(12, 12, 12)));
		add(getChangeLabel(), new Constraints(new Leading(27, 10, 10), new Leading(140, 12, 12)));
		
		setLocation(FlohmarktKasse.getPosition(this.getSize()));
		getGivenField().requestFocusInWindow();
	}

	private JLabel getSumlabel() {
		if (sumLabel == null) {
			sumLabel = new JLabel();
			sumLabel.setText("Summe");
			sumLabel.setFont(Config.getNormalFont());
		}
		return sumLabel;
	}

	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Abbrechen");
		}
		return cancelButton;
	}

	private JButton getOKButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("OK");
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					exit();
				}
			});
			okButton.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					exit();
				}
			});
		}
		return okButton;
	}

	private JFormattedTextField getChangeField() {
		if (changeField == null) {
			changeField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
			changeField.setValue(0);
			changeField.setColumns(7);
			changeField.setMinimumSize(new Dimension(100, Config.getRowHeight()));
			changeField.setFont(Config.getBigFont());
			changeField.setEditable(false);
		}
		return changeField;
	}
	private JFormattedTextField getSumField() {
		if (sumField == null) {
			sumField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
			sumField.setValue(this.sum);
			sumField.setColumns(7);
			sumField.setMinimumSize(new Dimension(100, Config.getRowHeight()));
			sumField.setFont(Config.getNormalFont());
			sumField.setEditable(false);
		}
		return sumField;
	}
	private JLabel getChangeLabel() {
		if (changeLabel == null) {
			changeLabel = new JLabel();
			changeLabel.setText("zurück");
			changeLabel.setFont(Config.getNormalFont());
		}
		return changeLabel;
	}

	private JFormattedTextField getGivenField() {
		if (givenField == null) {
			givenField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
			givenField.setColumns(7);
			givenField.setFont(Config.getBigFont());
			givenField.setMinimumSize(new Dimension(100, Config.getRowHeight()));
			givenField.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyCode() == 10) {
						calculate();
					}
				}
			});
		}
		return givenField;
	}

	private JLabel getGivenLabel() {
		if (givenLabel == null) {
			givenLabel = new JLabel();
			givenLabel.setText("Gegeben");
			givenLabel.setFont(Config.getNormalFont());
		}
		return givenLabel;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText(this.formattedSum);
			jLabel1.setFont(Config.getNormalFont());
		}
		return jLabel1;
	}
	private void calculate() {
		if(!getGivenField().getText().equals("")) {
			Double change=0.0;
			Double given=0.0;
			try {
				change = this.sum - NumberFormat.getInstance().parse(getGivenField().getText()).doubleValue();
				given = NumberFormat.getInstance().parse(getGivenField().getText()).doubleValue();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(change <= 0) {
				if(change < 0) {
					this.getChangeField().setForeground(Color.red);
				}
				this.getChangeField().setValue(change);
				this.getGivenField().setValue(given);
				getOKButton().requestFocusInWindow();
			}
			
		}
		
		
	}
	private void exit() {
		this.ip.makeSum();
		this.ip.getSellerIDField().setText("");
		this.ip.getSellerIDField().requestFocusInWindow();
		this.setVisible(false);
		this.dispose();
	}
}
