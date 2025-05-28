package org.msc.flohmarktkasse.gui;

import java.text.NumberFormat;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import org.msc.flohmarktkasse.prog.FlohmarktKasse;
import org.msc.flohmarktkasse.prog.Item;

public class OldReceiptsListener implements TreeSelectionListener {
	JTree tree = null;
	public OldReceiptsListener(JTree tree) {
		this.tree = tree;
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		Object node = tree.getLastSelectedPathComponent();
		if(node.getClass() == Item.class) {
			Item i = (Item) node;
			System.out.println("Juhu");
			FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().getSellerIDField().setText(""+i.getId());
			FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().getPriceField().setText("-"+NumberFormat.getInstance().format(i.getPrice()));
			FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().getPriceField().requestFocusInWindow();
		}
	}

}
