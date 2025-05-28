package org.msc.flohmarktkasse.gui;

import java.util.Vector;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.msc.flohmarktkasse.prog.Item;
import org.msc.flohmarktkasse.prog.Receipt;

public class OldReceiptsTreeModel implements TreeModel {
	private String treeRoot = "Kasse";
	private Vector<Receipt> receipts =  new Vector<Receipt>();
	private Vector<TreeModelListener> treeModelListeners =
	        new Vector<TreeModelListener>();
	public OldReceiptsTreeModel(Item[] items) {
		long saleTime = -1;
		Receipt r = new Receipt();
		for (int i = 0; i < items.length; i++) {
			if(saleTime<items[i].getTime()) {
				if(saleTime>-1) {
					receipts.add(r);
				}
				r = new Receipt(items[i].getTime());
				saleTime = items[i].getTime();
			} 
			r.addItem(items[i]);
		}
		receipts.add(r);
	}
	@Override
	public Object getRoot() {
		return treeRoot;
	}

	@Override
	public Object getChild(Object parent, int index) {
		if(parent.getClass() == String.class) {
			return receipts.get(index);
		} else if(parent.getClass() == Receipt.class){
			Receipt r = (Receipt) parent;
			return r.getItem(index);
		} 
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if(parent.getClass() == Receipt.class) {
			Receipt r = (Receipt) parent;
			return r.getRows();
		} else if(parent.getClass() == String.class) {
			return receipts.size();
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		if(node.getClass() == Item.class) {
			return true;
		}
		return false;
	}

	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {

	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		return 0;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.addElement(l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.removeElement(l);
	}

}
