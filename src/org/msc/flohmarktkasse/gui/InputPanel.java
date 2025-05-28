package org.msc.flohmarktkasse.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.msc.flohmarktkasse.prog.Config;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;
import org.msc.flohmarktkasse.prog.Helper;
import org.msc.flohmarktkasse.prog.Item;
import org.msc.flohmarktkasse.prog.Receipt;

public class InputPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane scrollReceipt;
	private JTextField sellerID;
	private JTextField priceField;
	private JPanel inputPanel;
	private Receipt r;
	private ReceiptModel rm;
	public InputPanel() {
		this.r=new Receipt();
		this.rm = new ReceiptModel(this.r);
		initComponents();
	}

	public void initComponents() {
		setLayout(new BorderLayout());
		add(getScrollReceipt(), BorderLayout.CENTER);
		Properties prp = FlohmarktKasse.getParameterProperties();
		if(prp.getProperty("inputPanel").equals("South")) {
			add(getInputPanel(), BorderLayout.SOUTH);
		} else {
			add(getInputPanel(), BorderLayout.NORTH);
		}
		
		getSellerIDField().requestFocusInWindow();
	}
	private JPanel getInputPanel() {
		if(inputPanel == null) {
			inputPanel = new JPanel();
			inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
			inputPanel.add(Box.createHorizontalGlue());
			inputPanel.add(new JLabel("AnbieterNr.:"));
			inputPanel.add(getSellerIDField());
			inputPanel.add(Box.createHorizontalGlue());
			inputPanel.add(new JLabel("Preis:"));
			inputPanel.add(getPriceField());
			inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLUE, Color.LIGHT_GRAY), "Eingabe"));
		}
		return inputPanel;
	}
	public JTextField getPriceField() {
		if (priceField == null) {
			priceField = new JTextField();
			priceField.setText("");
			priceField.setFont(Config.getBigFont());
			priceField.setEnabled(false);
			priceField.addKeyListener(new KeyListener() {
				
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
					if(e.getKeyCode() == 10) {
						if(checkPrice()){
							enter();
						} else {
							Toolkit.getDefaultToolkit().beep();
							if(JOptionPane.showConfirmDialog(null, "Soll wirklich "+getPriceField().getText()+" eingegeben werden?", "Möglicher Fehler bei der Eingabe", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) ==0) {
								enter();
							} else {
								getPriceField().setText("");
							}
							
							
						}
					}
				}
			});
		}
		return priceField;
	}

	public JTextField getSellerIDField() {
		if (sellerID == null) {
			sellerID = new JTextField();
			sellerID.setText("");
			sellerID.setFont(Config.getBigFont());
			sellerID.setEnabled(false);
			sellerID.addKeyListener(new KeyListener() {
				
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
					if(e.getKeyCode() == 107 || e.getKeyCode() == 521 || e.getKeyChar() == '+') {
						
						dialog();
						
					}
					
					if(e.getKeyCode() == 10) {
						if(checkSellerId()) {
							getPriceField().requestFocusInWindow();
						} else {
							Toolkit.getDefaultToolkit().beep();
							if(JOptionPane.showConfirmDialog(null, "Soll wirklich die Anbieternummer "+getSellerIDField().getText()+" eingegeben werden?", "Möglicher Fehler bei der Eingabe", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) ==0) {
								getPriceField().requestFocusInWindow();
							} else {
								getSellerIDField().setText("");
							}
							
						}
					}
				}
			});
			
		}
		return sellerID;
	}
	private void dialog() {
		new ChangeDialog2(r.getSum(), this).setVisible(true);
		FlohmarktKasse.getFlohmarktKasseWindow().getToolbar().getOldReceiptsAction().setEnabled(true);
	}
	private JScrollPane getScrollReceipt() {
		if (scrollReceipt == null) {
			scrollReceipt = new JScrollPane();
			scrollReceipt.setPreferredSize(new Dimension(300,200));
			scrollReceipt.setViewportView(getJTable0());
		}
		return scrollReceipt;
	}

	public JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			jTable0.setModel(rm);
			jTable0.setDefaultRenderer(Double.class, new ReceiptTableRenderer(rm));
			jTable0.setDefaultRenderer(String.class, new ReceiptTableRenderer(rm));
			jTable0.setDefaultRenderer(DeleteButton.class, new ButtonRenderer());
			jTable0.setDefaultEditor(DeleteButton.class, new ButtonEditor(new JCheckBox(), rm));
			jTable0.setRowHeight(Config.getRowHeight());
			TableTools.packTable(jTable0);
		}
		return jTable0;
	}
	private boolean checkSellerId() {
		int id = 0;
		try {
			id = Integer.parseInt(getSellerIDField().getText());
		} catch (NumberFormatException nf) {
			return false;
		}
		
		return (Config.minSellerId <= id && Config.maxSellerId >= id) && (!Helper.isInArray(Config.getBadSellerIds(), id));
	}
	private boolean checkPrice() {
		String p = getPriceField().getText();
		if(p.equals("")) {
			return false;
		}
		double price=0;
		try {
			price = NumberFormat.getInstance().parse(p).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(price);
		return (Config.minPrice <= price && Config.maxPrice >= price) && (price%Config.getPriceStep()==0);
	}
	private void enter() {
		int id = Integer.parseInt(getSellerIDField().getText());
		double price=0;
		try {
			price = NumberFormat.getInstance().parse(getPriceField().getText()).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Item item = new Item(id, price);
		this.r.addItem(item);
		this.rm.addRow(item);
		FlohmarktKasse.getFlohmarktKasseWindow().getToolbar().getOldReceiptsAction().setEnabled(false);
		this.getSellerIDField().setText("");
		this.getPriceField().setText("");
		this.getSellerIDField().requestFocusInWindow();
	}
	public void makeSum() {
		this.r.close();
		this.rm.fireTableDataChanged();
		
	}

}
