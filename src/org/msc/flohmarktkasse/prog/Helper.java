package org.msc.flohmarktkasse.prog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.GregorianCalendar;



public abstract class Helper {
	protected static final int BLKSIZ = 8192;
	public static String fileToString(File f) {
		Reader fileInput=null;
		String fileContent=null;
		try {
			fileInput = new FileReader(f.getAbsoluteFile());
			fileContent = Helper.readerToString(fileInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return fileContent;
	}
	public static String readerToString(Reader is) throws IOException {
		StringBuffer sb = new StringBuffer();
		char[] b = new char[BLKSIZ];
		int n;
		while ((n = is.read(b)) > 0) {
			sb.append(b, 0, n);
		}
		is.close();
		return sb.toString();
	}
	public static String readerToString(BufferedReader is) throws IOException {
		StringBuffer sb = new StringBuffer();
		char[] b = new char[BLKSIZ];
		int n;
		while ((n = is.read(b)) > 0) {
			sb.append(b, 0, n);
		}
		is.close();
		return sb.toString();
	}
	public static boolean isInArray(int[] array, int var) {
		boolean found = false;
		for (int i = 0; i < array.length; i++) {
			if(array[i]==var) {
				found=true;
			}
//			System.out.println(array[i]+", "+var);
		}
		return found;
	}
	public static String convertMillisToString(long time) {
		String date = "";
		String[] months= {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(time);
		date+=cal.get(GregorianCalendar.DAY_OF_MONTH)+".";
		date+=months[cal.get(GregorianCalendar.MONTH)]+".";
		date+=cal.get(GregorianCalendar.YEAR)+"-";
		date+=cal.get(GregorianCalendar.HOUR)+":";
		date+=cal.get(GregorianCalendar.MINUTE);
		return date;
	}
	public static String convertMillisToTime(long time) {
		String date = "";
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTimeInMillis(time); 
		if(cal.get(GregorianCalendar.HOUR_OF_DAY) < 10) {
			date+="0"+cal.get(GregorianCalendar.HOUR_OF_DAY)+":";
		} else {
			date+=cal.get(GregorianCalendar.HOUR_OF_DAY)+":";
		}
		if(cal.get(GregorianCalendar.MINUTE) > 0 && cal.get(GregorianCalendar.MINUTE) < 10) {
			date+="0"+cal.get(GregorianCalendar.MINUTE);
		} else {
			date+=cal.get(GregorianCalendar.MINUTE);
		}
		return date;
	}
}
