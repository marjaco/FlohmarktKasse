package org.msc.flohmarktkasse.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;

import org.msc.flohmarktkasse.prog.FlohmarktKasse;


public class OldReceiptsDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable jTable0;
	private JTree tree;
	private JScrollPane jScrollPane0;
	private JButton close = null;
	public OldReceiptsDialog() {
		initComponents();
	}

	public OldReceiptsDialog(Frame parent) {
		super(parent);
		initComponents();
	}


	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(new Color(223, 223, 223));
		setForeground(Color.black);
		
		add(getJScrollPane0(), BorderLayout.CENTER);
		add(getClose(), BorderLayout.SOUTH);
		setSize(300, 800);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getTree());
		}
		return jScrollPane0;
	}

	
	private JTree getTree() {
		if(tree == null) {
			
			tree = new JTree(new OldReceiptsTreeModel(FlohmarktKasse.getItems()));
			tree.addTreeSelectionListener(new OldReceiptsListener(tree));
		}
		return tree;
	}
	private JButton getClose() {
		if(close == null) {
			close = new JButton("Schließen");
			close.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					close();
					
				}
			});
		}
		return close;
	}
	private void close() {
		this.setVisible(false);
		FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().getPriceField().requestFocusInWindow();
	}
}
