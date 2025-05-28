package org.msc.flohmarktkasse.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultStyledDocument;

import org.msc.flohmarktkasse.prog.Config;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;

public class FlohmarktKasseWindow {
	private JPanel jContentPane = null;
	private AppFrame jFrame = null;
	private JMenuBar menu = null;
	private JMenu fileMenu = null;
	private JMenuItem exit = null;
	private JPanel editor= null;
	private InputPanel inputPanel = null;
	private Toolbar toolbar = null;
	private DefaultStyledDocument doc = null;
	
	private static Font normalFont;
	private static Font bigFont;
	private FillPanel fillPanel=new FillPanel();
	private static int baseFontSize=12;
	public FlohmarktKasseWindow() {
		Properties prp = FlohmarktKasse.getParameterProperties();
		baseFontSize = Integer.parseInt(prp.getProperty("baseFontSize", "12"));
	}
	private JPanel getContentPane() {
		if (jContentPane == null) {
		    jContentPane = new JPanel();
		    jContentPane.setLayout(new BorderLayout());
		    jContentPane.add(getCashierPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	    
	}
	public AppFrame getJFrame() {
		if (jFrame == null) {
		    jFrame = new AppFrame();
		    jFrame
		    .setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		    jFrame.setSize(new Dimension(600,350));
		    jFrame.setContentPane(getContentPane());
		    jFrame.setTitle("Flohmarktkasse");
		}
		return jFrame;
	}
	private JMenuBar getJMenuBar() {
		if(menu==null) {
			menu=new JMenuBar();
			menu.add(getFileMenu());
		}
		return menu;
	}
	private JMenu getFileMenu() {
		if(fileMenu == null) {
			fileMenu=new JMenu("Datei");
			fileMenu.add(getExit());
		}
		return fileMenu;
	}
	private JMenuItem getExit() {
		if(exit == null) {
			exit = new JMenuItem("Exit");
			exit.addActionListener(new ActionListener(){
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					FlohmarktKasse.getClient().closeApp();
				}
			});
		}
		return exit;
	}
	private JPanel getCashierPanel() {
		if(editor == null) {
			editor = new JPanel(new BorderLayout());
			
			editor.add(getToolbar(), BorderLayout.NORTH);
			editor.add(new JScrollPane(getInputPanel()), BorderLayout.CENTER);
			editor.add(fillPanel, BorderLayout.EAST);
			JLabel l = new JLabel("Flohmarktkasse");
			l.setUI(new VerticalLabelUI());
			l.setFont(Config.getVeryBigBoldFont());
			l.setForeground(Color.white);
			JLabel l2 = new JLabel("               ");
			l2.setFont(Config.getVeryBigBoldFont());
			JPanel p = new JPanel(new BorderLayout());
			p.add(l, BorderLayout.CENTER);
			p.add(l2, BorderLayout.SOUTH);
			p.setBackground(Color.blue);
			editor.add(p, BorderLayout.WEST);
		}
		return editor;
	}
	
	public InputPanel getInputPanel() {
		if(inputPanel == null) {
			inputPanel = new InputPanel();
			inputPanel.setFont(Config.getBigFont());
		}
		return inputPanel;
	}
	
	public Toolbar getToolbar() {
		if(toolbar == null) {
			toolbar = new Toolbar();
		}
		return toolbar;
	}
	
	public static Font getBigFont() {
		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, baseFontSize+14);
		return f; 
	}
	public static Font getNormalFont() {
		Font f = new Font(Font.SANS_SERIF, Font.PLAIN, baseFontSize);
		return f; 
	}
	
	public FillPanel getFillPanel() {
		return fillPanel;
	}
}
