package org.msc.flohmarktauswertung.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.msc.flohmarktauswertung.prog.FlohmarktAuswertung;
import org.msc.flohmarktauswertung.prog.Helper;
import org.msc.flohmarktauswertung.prog.Seller;


public class AuswertungWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private StatementModel sm;
	private JPanel leftPanel;
	private JTabbedPane tab = null;
	private JComboBox menu = null;
	private JTable singleTable = null;
	private SingleStatementModel ssm = null;
	private Toolbar toolbar = null;
	public AuswertungWindow() {
		initComponents();
	}

	private void initComponents() {
		add(getToolbar(), BorderLayout.NORTH);
		add(getJScrollPane0(), BorderLayout.CENTER);
		setSize(1024, 768);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getTab());
		}
		return jScrollPane0;
	}
	public Toolbar getToolbar() {
		if(toolbar == null) {
			toolbar = new Toolbar(this);
		}
		return toolbar;
	}
	private JTabbedPane getTab() {
		if(tab == null) {
			tab = new JTabbedPane();
			tab.add("Gesamt", new JScrollPane(getJTable0()));
			tab.add("Einzelabrechnung", getLeftPanel());
			tab.addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					if(tab.getSelectedIndex() == 0) {
						getToolbar().getPrintAction().setTable(getJTable0());
						getToolbar().getPrintAction().setHeader("Flohmarktabrechnung vom "+Helper.convertMillisToString(System.currentTimeMillis()));
						getToolbar().getPrintAction().setFooter(Helper.convertMillisToTime(System.currentTimeMillis())+" Uhr");
					}
				}
			});
		}
		return tab;
	}
	private JPanel getLeftPanel() {
		if(leftPanel == null) {
			leftPanel = new JPanel(new BorderLayout());
			leftPanel.add(getMenu(), BorderLayout.NORTH);
			leftPanel.add(new JScrollPane(getSingleTable()), BorderLayout.CENTER);
		}
		return leftPanel;
	}
	
	public JComboBox getMenu() {
		if(menu == null) {
			try {
				menu = new JComboBox(FlohmarktAuswertung.getDatabase().getSellers());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menu.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Seller seller = (Seller) getMenu().getSelectedItem();
//					System.out.println(seller);
					getSingleStatementModel().updateTable(seller);
					getToolbar().getPrintAction().setTable(getSingleTable());
					getToolbar().getPrintAction().setHeader(seller.toString());
					getToolbar().getPrintAction().setFooter("Flohmarktabrechnung vom "+Helper.convertMillisToString(System.currentTimeMillis())+", "+Helper.convertMillisToTime(System.currentTimeMillis())+" Uhr.");
				}
			});
		}
		return menu;
	}
	public void updateMenu() {
		this.getMenu().removeAllItems();
		Seller[] sellers= null;
		try {
			sellers = FlohmarktAuswertung.getDatabase().getSellers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < sellers.length; i++) {
			this.getMenu().addItem(sellers[i]);
		}
		
	}
	private JTable getSingleTable() {
		if(singleTable == null) {
			singleTable = new JTable(getSingleStatementModel());
			singleTable.setDefaultRenderer(JFormattedTextField.class, new SingleStatmentRenderer());
			singleTable.setDefaultRenderer(String.class, new SingleStatmentRenderer());
			singleTable.setRowHeight(30);
		}
		return singleTable;
	}
	public JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable(getStatmentModel());
			jTable0.setDefaultRenderer(JFormattedTextField.class, new StatementRenderer());
//			jTable0.setDefaultRenderer(Integer.class, new StatementRenderer());
			jTable0.setRowHeight(50);
		}
		return jTable0;
	}
	public StatementModel getStatmentModel() {
		if(sm == null) {
			sm = new StatementModel();
		}
		return sm;
	}
	public SingleStatementModel getSingleStatementModel() {
		if(ssm == null) {
			ssm = new SingleStatementModel();
		}
		return ssm;
	}
}
