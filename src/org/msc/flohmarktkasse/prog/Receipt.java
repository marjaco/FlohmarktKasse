package org.msc.flohmarktkasse.prog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Vector;


public class Receipt {
	private Vector<Item> items = new Vector<Item>();
	private long time = 0;
	public Receipt() {
//		items.add(new Item(0,0));
	}
	public Receipt(long time) {
		this.time = time;
	}
	public void addItem(Item i) {
		this.items.add(i);
	}
	public int getRows() {
		return this.items.size();
	}
	public Item getItem(int row) {
		return items.get(row);
	}
	public double getSum() {
		double s = 0;
		for (int i = 0; i < this.items.size(); i++) {
			s=s+items.get(i).getPrice();
		}
		FlohmarktKasse.getFlohmarktKasseWindow().getFillPanel().setSum(s);
		return s;
	}
	public String getFormattedSum() {
		double s = 0;
		for (int i = 0; i < this.items.size(); i++) {
			s=s+items.get(i).getPrice();
		}
		return NumberFormat.getInstance().format(s)+" €";
		
	}
	public void deleteItem(int id) {
		this.items.remove(id);
	}
	public void close()  {
		FlohmarktKasse.addToCounter();
		FlohmarktKasse.addToTotalSum(getSum());
		FlohmarktKasse.getFlohmarktKasseWindow().getFillPanel().setTotalSum(FlohmarktKasse.getTotalSum());
		FlohmarktKasse.getFlohmarktKasseWindow().getFillPanel().setCounter(FlohmarktKasse.getCounter());
		String s = getReceipt();
		
//		System.out.println(s);
		try {
			FlohmarktKasse.saveFile(s);
			FlohmarktKasse.saveBackupFile(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.items.removeAllElements();
	}
	public String getReceipt() {
		long time = System.currentTimeMillis();
		String s="";
		for (int i = 0; i < items.size(); i++) {
			s+=FlohmarktKasse.getCounter()+";"+items.get(i).getId()+";"+items.get(i).getPrice()+";"+time+";"+FlohmarktKasse.getName()+"\n";
		}
		return s;
	}
	private long getTime() {
		return time;
	}
	public String toString() {
		return Helper.convertMillisToTime(getTime())+"Uhr. Summe: "+getFormattedSum();
	}
}
