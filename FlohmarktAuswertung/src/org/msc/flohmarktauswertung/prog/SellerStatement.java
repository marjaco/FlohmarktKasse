package org.msc.flohmarktauswertung.prog;

import java.text.NumberFormat;

public class SellerStatement {
	private int id;
	//private double[] figures;
	public SellerStatement(int id) {
		this.id = id;
		//figures = FlohmarktAuswertung.getDatabase().getFigures(id);
	}
	public double getSum() {
		double sum = 0;
		double[] figures = FlohmarktAuswertung.getDatabase().getFigures(id);
		for (int i = 0; i < figures.length; i++) {
			sum += figures[i];
		}
		return sum; 
	}
	public double getPercentage() {
		return getSum()/100*FlohmarktAuswertung.getPercentage();
	}
	public double getPercentageOrganizer() {
		return getSum()/100*(100 - FlohmarktAuswertung.getPercentage());
	}
	public int getNumberItems() {
		return FlohmarktAuswertung.getDatabase().getFigures(id).length;
	}
	public int getSellerId() {
		return this.id;
	}
	public String getFormattedSum() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(getSum());
		
	}
	public String getFormattedPercentage() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(getPercentage());
	}
	public String getCashiers () {
		String[] cashiers = FlohmarktAuswertung.getDatabase().getCashiers(this.id);
		String cash="";
		for (int i = 0; i < cashiers.length; i++) {
			cash+=cashiers[i]+", ";
		}
		return cash;
	}
}
