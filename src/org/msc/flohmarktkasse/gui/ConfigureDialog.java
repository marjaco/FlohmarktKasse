package org.msc.flohmarktkasse.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.msc.flohmarktkasse.prog.Config;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;

public class ConfigureDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3420738692822084737L;
	private JTextField minSellerID;
	private JTextField maxSellerID;
	private JTextField badSellerIDs;
	private JTextField minPrice;
	private JTextField maxPrice;
	private JTextField priceStep;
	private JComboBox fontSizeBox;
	private Properties prp;
	private JToggleButton button = null;
	
	/**
	 * Create the dialog.
	 */
	public ConfigureDialog() {
		prp = FlohmarktKasse.getParameterProperties();
		setBounds(100, 100, 850, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(8, 2, 0, 0));
			{
				JLabel lblNewLabel_1 = new JLabel("Schriftgröße");
				panel.add(lblNewLabel_1);
			}
			{
				int[] sizes =  {4,6,8,10,12,14,16,18,20,22,24,26,28,30};
				fontSizeBox = new JComboBox();
				for (int i = 0; i < sizes.length; i++) {
					fontSizeBox.addItem(sizes[i]);
					if(Integer.parseInt(prp.getProperty("baseFontSize")) == sizes[i]) {
						fontSizeBox.setSelectedIndex(i);
					}
				}
				panel.add(fontSizeBox);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("kleinste Anbieternummer:");
				panel.add(lblNewLabel_2);
			}
			{
				minSellerID = new JTextField();
				panel.add(minSellerID);
				minSellerID.setColumns(10);
				minSellerID.setText(prp.getProperty("minSellerId"));
			}
			{
				JLabel lblNewLabel_3 = new JLabel("größte Anbieternummer:");
				panel.add(lblNewLabel_3);
			}
			{
				maxSellerID = new JTextField();
				panel.add(maxSellerID);
				maxSellerID.setColumns(10);
				maxSellerID.setText(prp.getProperty("maxSellerId"));
			}
			{
				JLabel lblNewLabel = new JLabel("Nicht verwendete Anbieternummern: (Komma, kein Leerz.)");
				panel.add(lblNewLabel);
			}
			{
				badSellerIDs = new JTextField();
				panel.add(badSellerIDs);
				badSellerIDs.setColumns(10);
				badSellerIDs.setText(prp.getProperty("badSellerIds"));
			}
			{
				JLabel lblMindestpreis = new JLabel("Mindestpreis (Punkt als Dezimaltrenner)");
				panel.add(lblMindestpreis);
			}
			{
				minPrice = new JTextField();
				panel.add(minPrice);
				minPrice.setColumns(10);
				minPrice.setText(prp.getProperty("minPrice"));
			}
			{
				JLabel lblHchstpreis = new JLabel("Höchstpreis");
				panel.add(lblHchstpreis);
			}
			{
				maxPrice = new JTextField();
				panel.add(maxPrice);
				maxPrice.setColumns(10);
				maxPrice.setText(prp.getProperty("maxPrice"));
			}
			{
				JLabel lblPreisschritt = new JLabel("Preisschritt (Punkt als Dezimaltrenner)");
				panel.add(lblPreisschritt);
			}
			{
				priceStep = new JTextField();
				panel.add(priceStep);
				priceStep.setColumns(10);
				priceStep.setText(prp.getProperty("priceStep"));
			}
			{
				JLabel lblEingabeleiste = new JLabel("Eingabeleiste");
				panel.add(lblEingabeleiste);
			}
			{
				
				panel.add(getButton());
			}
		}
	}
	private JToggleButton getButton() {
		if (button == null) {
			button = new JToggleButton();
			button.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent e) {
					if(button.isSelected()) {
						button.setText("Nach unten");
						FlohmarktKasse.getParameterProperties().setProperty("inputPanel", "North");
					} else {
						button.setText("Nach oben");
						FlohmarktKasse.getParameterProperties().setProperty("inputPanel", "South");
					}
					try {
						FlohmarktKasse.getParameterProperties().store(new FileOutputStream(FlohmarktKasse.getParamConfFile()), null);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().initComponents();
					FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().updateUI();
				}
			});
			Properties prp = FlohmarktKasse.getParameterProperties();
			if(prp.getProperty("inputPanel").equals("South")) {
				button.setText("Oben");
				button.setSelected(false);
				
			} else {
				button.setText("Unten");
				button.setSelected(true);
			}
			
		}
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String action = arg0.getActionCommand();
		if (action.equals("OK")) {
			prp.setProperty("minSellerId", minSellerID.getText());
			prp.setProperty("maxSellerId", maxSellerID.getText());
			prp.setProperty("minPrice", minPrice.getText());
			prp.setProperty("maxPrice", maxPrice.getText());
			prp.setProperty("priceStep", priceStep.getText());
			prp.setProperty("badSellerIds", badSellerIDs.getText());
			prp.setProperty("baseFontSize", ""+fontSizeBox.getSelectedItem());
			try {
				FlohmarktKasse.getParameterProperties().store(new FileOutputStream(FlohmarktKasse.getParamConfFile()), null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Config.updateConfig();
			this.setVisible(false);
		} else if(action.equals("Cancel")) {
			
			this.setVisible(false);
		}
	}
}
