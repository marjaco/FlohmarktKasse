package org.msc.flohmarktkasse.prog;

import java.text.NumberFormat;

public class Item {
	private int id;
	private double price;
	private long time = 0;
	public Item(int id, double price) {
		this.id=id;
		this.price=price;
	}
	public Item(int id, double price, long time) {
		this.id = id;
		this.price = price;
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public double getPrice() {
		return price;
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
	public long getTime() {
		return time;
	}
}
