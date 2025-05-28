package org.msc.flohmarktkasse.prog;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.msc.flohmarktkasse.files.LoadFiles;
import org.msc.flohmarktkasse.gui.FlohmarktKasseWindow;

public class FlohmarktKasse {
	private static FlohmarktKasse flohmarktKasse = null;
	private static File saveFile = null;
	private static File backupFile = null;
	private static FlohmarktKasseWindow flohmarktKasseWindow = null;
	private static String name, date;
	private static double totalSum=0;
	private static int counter=1;
	public static String userHome = System.getProperty("user.home") + System.getProperty("file.separator", "/");
	public static String userDir = System.getProperty("user.dir") + System.getProperty("file.separator", "/");
	private static String appHome = ".flohmarktkasse/";
	private static String paramConf = "parameters.prp";
	private static Properties paramProperties = null;
//	private static boolean automatic = true;
	public static final String APP_NAME = "Flohmarktkasse 1.1.1";
	public static final String PRINT_FOOTER = "Flohmarktkasse © 2012-2016 - Marcus Jacobs";
	public static FlohmarktKasse getClient() {
		if(flohmarktKasse == null) {
			flohmarktKasse = new FlohmarktKasse();
		}
		return flohmarktKasse;
	}
	public void closeApp() {
		System.exit(0);
	}
	public static Point getPosition(Dimension d) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// Get the current screen size
		Dimension scrnsize = toolkit.getScreenSize();
		return new Point(Math.round(scrnsize.width/2-d.width/2),Math.round(scrnsize.height/2-d.height/2));
	}
	public static void setFile(File sf) {
//		System.out.println(sf);
    	saveFile=sf;
    	if(!saveFile.exists()) {
    		try {
				saveFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	Properties prp = getParameterProperties();
    	prp.setProperty("file", sf.getAbsolutePath());
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
	public static void setBackupFile(File sf) {
    	backupFile=sf;
    	if(!backupFile.exists()) {
    		try {
    			backupFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	Properties prp = getParameterProperties();
    	prp.setProperty("backupFile", sf.getAbsolutePath());
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
	public static void setModeAutomatic(boolean automatic) {
    	String mode="";
		if(automatic) {
    		mode="automatic";
    	} else {
    		mode="manual";
    	}
    	Properties prp = getParameterProperties();
    	prp.setProperty("configmode", mode);
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
	public static boolean isAutomatic() {
		Properties prp = getParameterProperties();
		if(prp.getProperty("configmode")== null) {
			return false;
		}
		if(prp.getProperty("configmode").equals("automatic")) {
			return true;
		}
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		check();
		getFlohmarktKasseWindow().getJFrame().setSize(new Dimension(800,500));
		getFlohmarktKasseWindow().getJFrame().setLocation(FlohmarktKasse.getPosition(getFlohmarktKasseWindow().getJFrame().getSize()));
		getFlohmarktKasseWindow().getJFrame().setUndecorated(false);
		getFlohmarktKasseWindow().getJFrame().setResizable(true);
		getFlohmarktKasseWindow().getJFrame().setVisible(true);
		getFlohmarktKasseWindow().getInputPanel().getSellerIDField().requestFocusInWindow();
		start();
	}
	public static FlohmarktKasseWindow getFlohmarktKasseWindow() {
		if(flohmarktKasseWindow == null) {
			flohmarktKasseWindow = new FlohmarktKasseWindow();
		}
		return flohmarktKasseWindow;
	}
	public static void startCashier() {
		//Vollbildmodus funktioniert unter Windows nicht, da das Hauptfenster bei einem Popup verschwindet. 
		/*GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		if(gd.isFullScreenSupported()) {
			gd.setFullScreenWindow(getFlohmarktKasseWindow().getJFrame());
		}*/
		getFlohmarktKasseWindow().getInputPanel().getSellerIDField().setEnabled(true);
		getFlohmarktKasseWindow().getInputPanel().getPriceField().setEnabled(true);
		getFlohmarktKasseWindow().getInputPanel().getSellerIDField().requestFocusInWindow();
		getFlohmarktKasseWindow().getToolbar().getMessage().setText("");
	}
	public static String getName() {
		return name;
	}
	public static void setName(String name) {
		FlohmarktKasse.name = name;
		Properties prp = getParameterProperties();
    	prp.setProperty("name", name);
    	try {
			prp.store(new FileOutputStream(getParamConfFile()), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	getFlohmarktKasseWindow().getJFrame().addName(name);
	}
	public static String getDate() {
		return date;
	}
	public static void setDate(String date) {
		FlohmarktKasse.date = date;
	}
	
	public static void saveFile (String text) throws IOException {
		FileWriter fileWritter = new FileWriter(getSaveFile().getAbsolutePath(),true);
        BufferedWriter bufferWriter = new BufferedWriter(fileWritter);
        bufferWriter.write(text);
        bufferWriter.close();
	}
	public static void saveBackupFile (String text) throws IOException {
		FileWriter fileWritter = new FileWriter(getBackupFile().getAbsolutePath(),true);
        BufferedWriter bufferWriter = new BufferedWriter(fileWritter);
        bufferWriter.write(text);
        bufferWriter.close();
	}
	public static File getSaveFile() {
		return saveFile;
	}
	public static File getBackupFile() {
		return backupFile;
	}
	public static double getTotalSum() {
		return totalSum;
	}
	public static void addToTotalSum(double sum) {
		totalSum = totalSum+sum;
	}
	public static void setTotalSum(double sum) {
		totalSum = sum;
	}
	public static String getAppHome() {
		return userDir + appHome;
	}
	public static String getParamConfFile() {
		return getAppHome() + paramConf;
	}
	public static String getFileContent() {
		return Helper.fileToString(getSaveFile());
	}
	public static Item[] getItems() {
		String s = getFileContent();
		String[] fileLines = s.split("\n");
		Item[] items = new Item[fileLines.length];
		for (int i = 0; i < fileLines.length; i++) {
			String[] fileLineContent = fileLines[i].split(";");
			items[i] = new Item(Integer.parseInt(fileLineContent[1]), Double.parseDouble(fileLineContent[2]), Long.parseLong(fileLineContent[3]));
		}
		return items;
	}
	public static void processFileContent(String fileContent) {
		String[] fileLines = fileContent.split("\n");
		double sum = 0;
		int counter = 0;
		if(fileLines.length>1) {
			for (int i = 0; i < fileLines.length; i++) {
				String[] s = fileLines[i].split(";");
				sum = sum+Double.parseDouble(s[2]);
			}
	
			counter = Integer.parseInt(fileLines[fileLines.length-1].split(";")[0]);
		}
		FlohmarktKasse.setTotalSum(sum);
		FlohmarktKasse.setCounter(counter);
		FlohmarktKasse.getFlohmarktKasseWindow().getFillPanel().setTotalSum(FlohmarktKasse.getTotalSum());
		FlohmarktKasse.getFlohmarktKasseWindow().getFillPanel().setCounter(FlohmarktKasse.getCounter());
	}
	public static Properties getParameterProperties() {
	    if(paramProperties==null) {
	    paramProperties = new Properties();
		    try {
			paramProperties.load(new FileInputStream(FlohmarktKasse.getParamConfFile()));
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
	private static void check() {
		File appHomeDir = new File(getAppHome());
		if (!appHomeDir.exists()) {
			System.out.println("Erzeuge Programmverzeichnis: " + getAppHome());
			appHomeDir.mkdirs();
			if (System.getProperty("os.name").substring(0, 3).equals("Win")) {
				System.out.println("You are using Windows, what a shame. Java Home: "+System.getProperty("user.home"));
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
		if (!parametersFile.exists() || parametersFile.length() == 0) {
			System.out.println("Erzeuge parameters Datei: "
					+ getParamConfFile());
			int b;
			InputStreamReader in = null;
			BufferedOutputStream out = null;
			try {
				in = new InputStreamReader(LoadFiles.getURL("parameters.prp").openStream());
				out = new BufferedOutputStream(new FileOutputStream(FlohmarktKasse
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

		}
		if(isAutomatic()) {
			Properties prp = getParameterProperties();
			if(!prp.getProperty("name").equals("")){
				FlohmarktKasse.setAutomaticSettings(prp.getProperty("name"),false);
			}
		}
	}
	private static void start() {
		Properties prp = getParameterProperties();
		String filename = prp.getProperty("file");
		String backupFilename = prp.getProperty("backupFile");
		FlohmarktKasse.setName(prp.getProperty("name", "Kasse"));
		FlohmarktKasse.getFlohmarktKasseWindow().getJFrame().addName(FlohmarktKasse.getName());
		if(filename != null) {
			File f = new File(filename);
			FlohmarktKasse.processFileContent(Helper.fileToString(f));
			FlohmarktKasse.setFile(f);
		}
		if(backupFilename != null) {
			File f = new File(backupFilename);
			FlohmarktKasse.setBackupFile(f);
			FlohmarktKasse.startCashier();
		}
		
	}
	/**
	 * This sets the parameters automatically. Only the name for the cashier is given.
	 * Date and filenames are determined. 
	 * @param name
	 */
	public static void setAutomaticSettings(String name, boolean newFile) {
		FlohmarktKasse.setName(name);
		Properties prp = getParameterProperties();
		String filename = prp.getProperty("file");
		String backupFilename = prp.getProperty("backupFile");
		File f = null;
		File bf = null;
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy-HH.mm.ss");
		Date date = new Date();
		FlohmarktKasse.setDate(dateFormat.format(date));
		String file = name+"-"+dateFormat.format(date)+".flohmarkt";
		String path= System.getProperty("user.dir")+System.getProperty("file.separator", "/")+file;
		String backupPath = userHome + System.getProperty("file.separator", "/")+file;
//		System.out.println(path);
//		System.out.println(backupPath);
		if(filename != null) {
			f = new File(filename);
			if(!f.exists() || newFile ||isOld(f)) {
				f= new File(path);
			}
		} else {
			f= new File(path);
		}
		if(backupFilename != null) {
			bf = new File(backupFilename);
			if(!bf.exists() || newFile || isOld(bf)) {
				bf= new File(backupPath);
			}
		} else {
			bf = new File(backupPath);
		}
		FlohmarktKasse.setFile(f);
		FlohmarktKasse.setBackupFile(bf);
		FlohmarktKasse.setModeAutomatic(true);
		FlohmarktKasse.setTotalSum(0.0);
		FlohmarktKasse.setCounter(1);
		FlohmarktKasse.setName(name);
		FlohmarktKasse.startCashier();
	}
	public static boolean isOld(File f) {
		return f.lastModified() < (System.currentTimeMillis()-86400000);
	}
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		FlohmarktKasse.counter = counter;
	}
	public static void addToCounter() {
		counter = counter+1;
	}
	public static String getFilename() {
		Properties prp = getParameterProperties();
		return prp.getProperty("file", "");
	}
	public static String getBackupFilename() {
		Properties prp = getParameterProperties();
		return prp.getProperty("backupFile", "");
	}
}
