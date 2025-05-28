package org.msc.flohmarktkasse.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.msc.flohmarktkasse.files.LoadFiles;
import org.msc.flohmarktkasse.prog.FlohmarktKasse;

public class HelpDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	
	/**
	 * Create the dialog.
	 */
	public HelpDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				/*JTextArea textArea = new JTextArea("Testtext");
				scrollPane.setViewportView(textArea);*/
				JEditorPane editor = new JEditorPane();
				editor.setContentType("txt/html");
				try {
					editor.setPage(""+LoadFiles.getURL("documentation.html"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				editor.setEditable(false);
				scrollPane.setViewportView(editor);
			}
		}
		{
			JPanel filenamePanel = new JPanel();
			contentPanel.add(filenamePanel, BorderLayout.NORTH);
			filenamePanel.setLayout(new GridLayout(2, 0, 0, 0));
			{
				JLabel filenameLabel = new JLabel("Kassendaten: "+FlohmarktKasse.getFilename());
				filenamePanel.add(filenameLabel);
			}
			{
				JLabel backupFileLabel = new JLabel("Sicherung: "+FlohmarktKasse.getBackupFilename());
				filenamePanel.add(backupFileLabel);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

}
