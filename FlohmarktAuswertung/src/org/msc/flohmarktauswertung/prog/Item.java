package org.msc.flohmarktauswertung.prog;

import java.text.NumberFormat;

public class Item {
	private int id;
	private double price;
	private long time=0;
	private String cashier="";
	public Item(int id, double price) {
		this.id=id;
		this.price=price;
	}
	public Item(int id, double price, long time, String name) {
		this.id=id;
		this.price=price;
		this.time=time;
		this.cashier=name;
	}
	public int getId() {
		return id;
	}
	public double getPrice() {
		return price;
	}
	public long getTime() {
		return time;
	}
	public String getCashier() {
		return cashier;
	}
	public String getFormattedPrice() {
		return NumberFormat.getInstance().format(price)+" €";
		
	}
	public String toString() {
		return "Anbieter: "+this.id+", Preis: "+getFormattedPrice();
	}
	public boolean setId(String id) {
		try {
			this.id = Integer.parseInt(id);
			return true;
		} catch (NumberFormatException nf) {
			return false;
		}
		
	}
	public boolean setPrice(String price) {
		try {
			this.price = Double.parseDouble(price);
			return true;
		} catch (NumberFormatException nf) {
			return false;
		}
		
	}
}
