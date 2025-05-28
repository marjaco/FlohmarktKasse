package org.msc.flohmarktkasse.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.msc.flohmarktkasse.prog.FlohmarktKasse;

public class NewFlohmarktDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel savePanel = null;

	private JPanel mainPanel = null;

	private JButton saveButton = null;

	private JTextField saveField = null;
	private JTextField backupField = null;
	private JLabel backupLabel = null;
	private JLabel courseName = null;

	private JTextField nameField = null;

	private JLabel jLabel2 = null;

	private JTextField courseField = null;

	private JLabel jLabel3 = null;

	private JLabel jLabel4 = null;

	private JButton fileButton = null;
	private JButton backupButton = null;
	private JLabel jLabel5 = null;


	/**
	 * @param owner
	 */
	public NewFlohmarktDialog(Frame owner) {
		super(owner);
		initialize();
	}
	
	private void initialize() {
		Dimension size = new Dimension(407,262);
		this.setSize(size);
		this.setLocation(FlohmarktKasse.getPosition(size));
		this.setModal(true);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getSavePanel(), BorderLayout.SOUTH);
			jContentPane.add(getMainPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes savePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSavePanel() {
		if (savePanel == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 3;
			gridBagConstraints11.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 1;
			gridBagConstraints.ipady = 1;
			gridBagConstraints.weightx = 0.5;
			gridBagConstraints.gridwidth = 2;
			gridBagConstraints.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints.gridy = 1;
			savePanel = new JPanel();
			savePanel.setLayout(new GridBagLayout());
			savePanel.add(getSaveButton(), gridBagConstraints);
//			savePanel.add(getDeleteButton(), gridBagConstraints11);
		}
		return savePanel;
	}

	/**
	 * This method initializes mainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMainPanel() {
		if (mainPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridy = 5;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.gridx = 1;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.anchor = GridBagConstraints.EAST;
			gridBagConstraints31.gridy = 5;
			jLabel5 = new JLabel();
			jLabel5.setText("Ende:");
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints21.gridy = 4;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.gridx = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.gridy = 4;
			jLabel4 = new JLabel();
			jLabel4.setText("Beginn: ");
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridwidth = 2;
			gridBagConstraints10.gridheight = 1;
			gridBagConstraints10.ipadx = 1;
			gridBagConstraints10.ipady = 7;
			gridBagConstraints10.gridy = 0;
			jLabel3 = new JLabel();
			jLabel3.setText("Geben Sie hier bitte die Daten für den Flohmarkt ein. ");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.gridy = 3;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.weighty = 0.0;
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.gridy = 3;
			jLabel2 = new JLabel();
			jLabel2.setText("Kassenbezeichnung: ");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.weighty = 0.0;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.gridy = 2;
			courseName = new JLabel();
			courseName.setText("Datum: ");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.anchor = GridBagConstraints.EAST;
			gridBagConstraints3.gridy = 4;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 3;
			
			GridBagConstraints conPercent = new GridBagConstraints();
			conPercent.fill = GridBagConstraints.VERTICAL;
			conPercent.gridy = 4;
			conPercent.gridx = 0;
			conPercent.weightx = 1.0;
			JLabel percentLabel = new JLabel("Prozentualer Anteil des Verkäufers: ");
			
			mainPanel = new JPanel();
			mainPanel.setLayout(new GridBagLayout());
			mainPanel.add(courseName, gridBagConstraints6);
			mainPanel.add(getNameField(), gridBagConstraints7);
			mainPanel.add(jLabel2, gridBagConstraints8);
			mainPanel.add(getCourseField(), gridBagConstraints9);
			mainPanel.add(jLabel3, gridBagConstraints10);
			mainPanel.add(new JLabel("Speichern in Verzeichnis: "), gridBagConstraints1);
			mainPanel.add(getSaveField(), gridBagConstraints21);
			mainPanel.add(getFileButton(), gridBagConstraints3);
			GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
			gridBagConstraints30.gridx = 0;
			gridBagConstraints30.anchor = GridBagConstraints.EAST;
			gridBagConstraints30.gridy = 5;
			GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
			gridBagConstraints32.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints32.gridy = 5;
			gridBagConstraints32.weightx = 1.0;
			gridBagConstraints32.gridx = 1;
			GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
			gridBagConstraints33.gridx = 3;
			gridBagConstraints33.anchor = GridBagConstraints.EAST;
			gridBagConstraints33.gridy = 5;
			mainPanel.add(getBackupLabel(), gridBagConstraints30);
			mainPanel.add(getbackupField(), gridBagConstraints32);
			mainPanel.add(getBackupButton(), gridBagConstraints33);
		}
		return mainPanel;
	}
	private JTextField getSaveField() {
		if(saveField == null) {
			saveField = new JTextField();
		}
		return saveField;
	}
	private JTextField getbackupField() {
		if(backupField == null) {
			backupField = new JTextField();
		}
		return backupField;
	}
	private JLabel getBackupLabel() {
		if(backupLabel == null) {
			backupLabel = new JLabel("Sichern in:");
		}
		return backupLabel;
	}
	private JButton getFileButton() {
		if(fileButton == null) {
			fileButton = new JButton("...");
			fileButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = chooser.showSaveDialog(null);
					if (option==JFileChooser.APPROVE_OPTION) {
						File selectedFile= chooser.getSelectedFile().getAbsoluteFile();
						getSaveField().setText(selectedFile.toString());
					}
				}
				
			});
		}
		return fileButton;
	}
	private JButton getBackupButton() {
		if(backupButton == null) {
			backupButton = new JButton("...");
			backupButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int option = chooser.showSaveDialog(null);
					if (option==JFileChooser.APPROVE_OPTION) {
						File selectedFile= chooser.getSelectedFile().getAbsoluteFile();
						getbackupField().setText(selectedFile.toString());
					}
				}
				
			});
		}
		return backupButton;
	}
	/**
	 * This method initializes saveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setText("Speichern");
			saveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					save();
				}
			});
		}
		return saveButton;
	}

	
	/**
	 * This method initializes courseField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNameField() {
		if (nameField == null) {
			nameField = new JTextField();
			
		}
		return nameField;
	}

	/**
	 * This method initializes subjectField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCourseField() {
		if (courseField == null) {
			courseField = new JTextField();
		}
		return courseField;
	}
	private void save() {
		if(!getNameField().getText().equals("")) {
			FlohmarktKasse.setDate(getNameField().getText());
			FlohmarktKasse.setName(getNameField().getText());
		} else {
			return;
		}
		if(!getCourseField().getText().equals("")) {
			FlohmarktKasse.setName(getCourseField().getText());
		} else {
			return;
		}
		if(!getSaveField().getText().equals("")){
			String file = getSaveField().getText()+System.getProperty("file.separator", "/")+getCourseField().getText()+"_"+getNameField().getText()+".flohmarkt";
			System.out.println("File: "+file);
			FlohmarktKasse.setFile(new File(file));
		} else {
			return;
		}
		if(!getbackupField().getText().equals("")){
			String file = getbackupField().getText()+System.getProperty("file.separator", "/")+getCourseField().getText()+"_"+getNameField().getText()+".flohmarkt";
			System.out.println("Backupfile: "+file);
			FlohmarktKasse.setBackupFile(new File(file));
		} else {
			return;
		}
		FlohmarktKasse.setTotalSum(0.0);
		FlohmarktKasse.setCounter(1);
		FlohmarktKasse.setName(getCourseField().getText());
		FlohmarktKasse.startCashier();
		this.setVisible(false);
		this.dispose();
	}
	
	
	
}  
