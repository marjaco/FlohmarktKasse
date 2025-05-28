package org.msc.flohmarktauswertung.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.msc.flohmarktauswertung.prog.FlohmarktAuswertung;

public class ConfigureDialog extends JDialog implements ActionListener {
	private JFormattedTextField textField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ConfigureDialog dialog = new ConfigureDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public ConfigureDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			
			{
				JLabel lblProzentualerAnteilDes = new JLabel("Prozentualer Anteil des Verkäufers");
				panel.add(lblProzentualerAnteilDes);
			}
			{
				textField = new JFormattedTextField(NumberFormat.getNumberInstance());
				textField.setColumns(2);
				textField.setText(FlohmarktAuswertung.getParameterProperties().getProperty("percentage", "80"));
				panel.add(textField);
				
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getActionCommand().equalsIgnoreCase("OK")) {
			int percent;
			try {
				percent = Integer.parseInt(textField.getText());
			} catch (NumberFormatException nf) {
				percent = 80;
			}

			Properties prp = FlohmarktAuswertung.getParameterProperties();
			prp.setProperty("percentage", percent+"");
			try {
				prp.store(new FileOutputStream(FlohmarktAuswertung.getParamConfFile()), null);
				JOptionPane.showMessageDialog(null, "Bitte starten Sie das Programm neu");
				this.setVisible(false);
			} catch (FileNotFoundException ef) {
				// TODO Auto-generated catch block
				ef.printStackTrace();
			} catch (IOException eff) {
				// TODO Auto-generated catch block
				eff.printStackTrace();
			}
		} else {
			this.setVisible(false);
		}

	}

}
