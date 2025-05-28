package org.msc.flohmarktauswertung.prog;

import java.awt.HeadlessException;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.msc.flohmarktauswertung.files.LoadFiles;
import org.msc.flohmarktauswertung.gui.AuswertungWindow;

public class FlohmarktAuswertung {
	public static String userHome = System.getProperty("user.dir") + System.getProperty("file.separator", "/");
	private static String appHome = ".flohmarktauswertung/";
	private static String paramConf = "parameters.prp";
	private static String dbDir = "db";
	private static Properties paramProperties = null;
	private static Database db = null;
	public static final String APP_NAME = "FlohmarktAuswertung 0.8";
	public static final String PRINT_FOOTER = "FlohmarktAuswertung © 2012 - Marcus Jacobs";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		check();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				AuswertungWindow frame = new AuswertungWindow();
				frame.setDefaultCloseOperation(AuswertungWindow.EXIT_ON_CLOSE);
				frame.setTitle("Flohmarktauswertung");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
	public static String getAppHome() {
		return userHome + appHome;
	}
	public static String getParamConfFile() {
		return getAppHome() + paramConf;
	}
	public static Properties getParameterProperties() {
	    if(paramProperties==null) {
		paramProperties = new Properties();
		    try {
			paramProperties.load(new FileInputStream(FlohmarktAuswertung.getParamConfFile()));
		    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
	    }
	    
	    return paramProperties;
	}
	public static Database getDatabase() {
		if(db == null) {
			String dbPath = getParameterProperties().getProperty("dbPath");
			String dbName = getParameterProperties().getProperty("dbName");
			try {
				db = new Database(dbPath, dbName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return db;
	}
	/**
     * @return the dbDir
     */
    public static String getDatabaseDir() {
    	return getAppHome() + dbDir;
    }
	private static void check() {
		File appHomeDir = new File(getAppHome());
		if (!appHomeDir.exists()) {
			System.out.println("Erzeuge Programmverzeichnis: " + getAppHome());
			appHomeDir.mkdirs();
			if (System.getProperty("os.name").substring(0, 3).equals("Win")) {
				System.out.println("You are using Windows, what a shame.");
				try {
					Runtime.getRuntime().exec(
							"attrib +h " + getAppHome() + " /d /s");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// Checks, whether the parameters config file exists. If not, it will be
		// created with default values.
		File parametersFile = new File(getParamConfFile());
		if (!parametersFile.exists()) {
			System.out.println("Erzeuge parameters Datei: "
					+ getParamConfFile());
			int b;
			InputStreamReader in = null;
			BufferedOutputStream out = null;
			try {
				in = new InputStreamReader(LoadFiles.getURL("parameters.prp").openStream());
				out = new BufferedOutputStream(new FileOutputStream(FlohmarktAuswertung
						.getParamConfFile()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while ((b = in.read()) != -1) {
					out.write(b);
				}
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Properties prp = getParameterProperties();
		    prp.setProperty("dbName", "flohmarkt");
		    prp.setProperty("dbPath", getDatabaseDir());
		    try {
				prp.store(new FileOutputStream(getParamConfFile()), null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}
		File dbFile = new File(getDatabaseDir());
		if (!dbFile.exists()) {
		    dbFile.mkdir();
//		    getDatabase().makeDatabase();

		    
			try {
				if(getDatabase().makeDatabase()) {
					JOptionPane
				.showMessageDialog(null,
				"Das Programm wurde eingerichtet und wird jetzt gestartet.");
				System.out.println("Datenbank erzeugt und geschlossen");
				} else {
					System.err.println("Datenbank wurde NICHT erzeugt.");
				JOptionPane
				.showMessageDialog(
					null,
				"Die Datenbank konnte nicht erzeugt werden. Das Programm kann nicht gestartet werden.");
				}
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		}
		
	}
	public static int getPercentage() {
		return Integer.parseInt(getParameterProperties().getProperty("percentage", "80"));
	}
}
