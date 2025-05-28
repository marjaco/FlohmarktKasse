package org.msc.flohmarktkasse.gui;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.msc.flohmarktkasse.prog.FlohmarktKasse;

public class Toolbar extends JToolBar {
	private NewAction newAction = null;
	private OldReceiptsAction oldReceiptsAction = null;
	private JLabel words = new JLabel();
	private JLabel name = new JLabel();
	private JLabel alarm = null;
	private JToggleButton button = null;
	private JLabel inputlabel = null;
	private JLabel message = null;
	public Toolbar() {
		this.setFloatable(false);
		newAction = new NewAction();
		this.add(newAction);
		this.add(new ConfigureAction());
//		this.add(new OpenAction());
//		this.add(new ExitAction());
		this.addSeparator();
		this.add(getOldReceiptsAction());
		this.addSeparator();
		this.add(new HelpAction());
		this.addSeparator();
		this.add(getMessage());
		//this.add(getInputLabel());
		//this.add(getButton());
//		this.add(new FileSizeAction(true));
//		this.add(new FileSizeAction(false));
	}
	public OldReceiptsAction getOldReceiptsAction() {
		if(oldReceiptsAction == null) {
			oldReceiptsAction = new OldReceiptsAction();
		}
		return oldReceiptsAction;
	}
	public JLabel getMessage() {
		if(message == null) {
			message = new JLabel("Die Kasse muss noch eingerichtet werden.");
			message.setForeground(Color.RED);
		}
		return message;
	}
	public NewAction getNewAction() {
		return newAction;
	}
	public JLabel getWords() {
		return words;
	}
	public void setPupilName(String name) {
		this.name.setText(name);
	}
	private JLabel getAlarmLabel() {
		if(alarm==null) {
			alarm = new JLabel();
		}
		return alarm;
	}
	public void setAlarm(String text) {
		this.getAlarmLabel().setText(text);
		this.setBackground(Color.RED);
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
	private JLabel getInputLabel() {
		if (inputlabel == null) {
			inputlabel = new JLabel();
			inputlabel.setText("Eingabefeld: ");
		}
		return inputlabel;
	}
}
