package org.msc.flohmarktkasse.gui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.msc.flohmarktkasse.files.LoadFiles;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;

public class AppFrame extends JFrame {
	
	public AppFrame() throws HeadlessException {
		super();
		UIManager.put("Tree.openIcon", LoadFiles.getImage("tree_cashier.png"));
		UIManager.put("Tree.closedIcon", LoadFiles.getImage("tree_cashier.png"));
		UIManager.put("Tree.leafIcon", LoadFiles.getImage("tree_euro.png"));
		UIManager.put("Tree.expandedIcon", LoadFiles.getImage("tree_expanded.png"));
		UIManager.put("Tree.collapsedIcon", LoadFiles.getImage("tree_collapsed.png"));
		this.setIconImage(LoadFiles.getImage("flohmarktkasse.png").getImage());
	}

	public AppFrame(GraphicsConfiguration gc) {
		super(gc);
	}

	public AppFrame(String title) throws HeadlessException {
		super(title);
	}

	public AppFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
			
		}
	}
	public void addName(String s) {
		this.setTitle(FlohmarktKasse.APP_NAME+" - "+s);
	}
}
