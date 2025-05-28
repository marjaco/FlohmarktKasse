package org.msc.flohmarktauswertung.prog;

public class Seller {
	private int id = 0;
	public Seller(int id) {
		this.id = id;
	}
	public int getSellerId() {
		return id;
	}
	public String toString() {
		return "Anbieter "+id;
	}
}
