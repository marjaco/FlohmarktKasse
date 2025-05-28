package org.msc.flohmarktkasse.prog;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class Config {
	public static int minSellerId = Integer.parseInt(FlohmarktKasse.getParameterProperties().getProperty("minSellerId", "10"));
	public static int maxSellerId = Integer.parseInt(FlohmarktKasse.getParameterProperties().getProperty("maxSellerId", "100"));
	public static double minPrice = Double.parseDouble(FlohmarktKasse.getParameterProperties().getProperty("minPrice", "0.5"));
	public static double maxPrice = Double.parseDouble(FlohmarktKasse.getParameterProperties().getProperty("maxPrice", "50"));
	public static double priceStep = Double.parseDouble(FlohmarktKasse.getParameterProperties().getProperty("priceStep", "0.5"));
	public static int row_height=0;
	public static Font bigFont, boldFont, normalFont, veryBigBoldFont;
	public static int[] badSellerIds = null;
	private static int baseFontSize = Integer.parseInt(FlohmarktKasse.getParameterProperties().getProperty("baseFontSize", "12"));
	public static int[] getBadSellerIds() {
		if(badSellerIds == null) {
			String s = FlohmarktKasse.getParameterProperties().getProperty("badSellerIds");
			String[] sellerIds = s.split(",");
			int[] ids = new int[sellerIds.length];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Integer.parseInt(sellerIds[i]);
			}
			badSellerIds = ids;
		}
		return badSellerIds;
	}
	public static int getRowHeight() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
		if(row_height == 0) {
			if(scrnsize.width>1500) {
				row_height=40;
			}else if(scrnsize.width>1024 && scrnsize.width < 1499) {
				row_height=30;
			} else  {
				row_height=20;
			}
		}
		return row_height;
	}
	public static Font getBigFont() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
//		Font bigFont = null;
		if(bigFont==null) {
			if(scrnsize.width>1500) {
				bigFont=new Font(Font.SANS_SERIF,Font.PLAIN,baseFontSize+28);
			} else if(scrnsize.width>1024 && scrnsize.width < 1499) {
				bigFont=new Font(Font.SANS_SERIF,Font.PLAIN,baseFontSize+18);
			} else  {
				bigFont=new Font(Font.SANS_SERIF,Font.PLAIN,baseFontSize+8);
			}
		}

		return  bigFont;
	}
	
	public static Font getNormalFont() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
//		Font bigFont = null;
		if(normalFont==null) {
			if(scrnsize.width>1500) {
				normalFont=new Font(Font.SANS_SERIF,Font.PLAIN,baseFontSize+10);
			} else if(scrnsize.width>1024 && scrnsize.width < 1499) {
				normalFont=new Font(Font.SANS_SERIF,Font.PLAIN,baseFontSize+6);
			} else  {
				normalFont=new Font(Font.SANS_SERIF,Font.PLAIN,baseFontSize+2);
			}
		}

		return  normalFont;
	}
	public static Font getBoldFont() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
//		Font bigFont = null;
		if(boldFont==null) {
			if(scrnsize.width>1500) {
				boldFont=new Font(Font.SANS_SERIF,Font.BOLD,baseFontSize+28);
			} else if(scrnsize.width>1024 && scrnsize.width < 1499) {
				boldFont=new Font(Font.SANS_SERIF,Font.BOLD,baseFontSize+18);
			} else  {
				boldFont=new Font(Font.SANS_SERIF,Font.BOLD,baseFontSize+8);
			}
		}

		return boldFont;
	}
	public static Font getVeryBigBoldFont() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension scrnsize = toolkit.getScreenSize();
//		Font bigFont = null;
		if(veryBigBoldFont==null) {
			if(scrnsize.width>1500) {
				veryBigBoldFont=new Font(Font.SANS_SERIF,Font.BOLD,baseFontSize+48);
			} else if(scrnsize.width>1024 && scrnsize.width < 1499) {
				veryBigBoldFont=new Font(Font.SANS_SERIF,Font.BOLD,baseFontSize+33);
			} else  {
				veryBigBoldFont=new Font(Font.SANS_SERIF,Font.BOLD,baseFontSize+18);
			}
		}

		return veryBigBoldFont;
	}
	public static double getPriceStep() {
		return 0.5;
	}
	
	
	public static void changeFontSize(int i) {
		baseFontSize+=i;
		FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().setFont(getNormalFont());
		Properties prp = FlohmarktKasse.getParameterProperties();
		prp.setProperty("baseFontSize", ""+baseFontSize);
		try {
			FlohmarktKasse.getParameterProperties().store(new FileOutputStream(FlohmarktKasse.getParamConfFile()), null);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().initComponents();
		FlohmarktKasse.getFlohmarktKasseWindow().getInputPanel().updateUI();
	}
	public static void updateConfig() {
		minSellerId = Integer.parseInt(FlohmarktKasse.getParameterProperties().getProperty("minSellerId", "10"));
		maxSellerId = Integer.parseInt(FlohmarktKasse.getParameterProperties().getProperty("maxSellerId", "100"));
		minPrice = Double.parseDouble(FlohmarktKasse.getParameterProperties().getProperty("minPrice", "0.5"));
		maxPrice = Double.parseDouble(FlohmarktKasse.getParameterProperties().getProperty("maxPrice", "50"));
		priceStep = Double.parseDouble(FlohmarktKasse.getParameterProperties().getProperty("priceStep", "0.5"));
		String s = FlohmarktKasse.getParameterProperties().getProperty("badSellerIds");
		String[] sellerIds = s.split(",");
		int[] ids = new int[sellerIds.length];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = Integer.parseInt(sellerIds[i]);
		}
		badSellerIds = ids;
	}
	
}
