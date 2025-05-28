package org.msc.flohmarktauswertung.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import org.msc.flohmarktauswertung.files.LoadFiles;
import org.msc.flohmarktauswertung.prog.Helper;

public class Toolbar extends JToolBar {
	private PrintAction printAction = null;
	private ExportStatement exportStatement=null;
	private JLabel words = new JLabel();
	private JLabel name = new JLabel();
	private JLabel alarm = null;
	private JButton refresh = new JButton(LoadFiles.getImage("update.png"));
	private AuswertungWindow w;
	public Toolbar(AuswertungWindow w) {
		this.w = w;
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		this.setFloatable(false);
		this.add(new NewAction(this.w));
		this.add(new OpenAction(this.w));
		this.add(getPrintAction());
		this.add(getExportStatementAction());
		this.add(new HelpAction());
		this.add(new ConfigureAction());
		this.add(new ExitAction());
		this.addSeparator();
		
//		this.add(new FileSizeAction(true));
//		this.add(new FileSizeAction(false));
//		this.addSeparator();
//		this.add(refresh);
//		this.add(words);
		this.addSeparator();
		this.add(name);
//		this.add(getAlarmLabel());
		
	}
	public PrintAction getPrintAction() {
		if(printAction == null) {
			printAction = new PrintAction(this.w.getJTable0());
			getPrintAction().setHeader("Flohmarktabrechnung vom "+Helper.convertMillisToString(System.currentTimeMillis()));
			getPrintAction().setFooter(Helper.convertMillisToTime(System.currentTimeMillis())+" Uhr");
		}
		return printAction;
	}
	public ExportStatement getExportStatementAction() {
		if(exportStatement == null) {
			exportStatement = new ExportStatement();
		}
		return exportStatement;
	}
	public PrintAction getNewAction() {
		return printAction;
	}
	public JLabel getWords() {
		return words;
	}
	public JButton getRefresh() {
		return refresh;
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
}
