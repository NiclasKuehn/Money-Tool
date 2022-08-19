package Data;

import Enums.MonthName;
import Enums.Reason;

import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

public class Month extends BillContainer implements Serializable {
	public boolean used = false;
	private final ArrayList<BillClass> BillArray = new ArrayList<BillClass>();
	private MonthName Name;
	
	public Month(MonthName Monat) {
		this.Name = Monat;
		
	}
	
	public void addBill(BillClass Bill) {
		this.BillArray.add(Bill);
	}
	
	public void remBill(int index) {
		this.BillArray.remove(index);
	}
	
	public String getName() {
		return Name.toString();
	}
	
	public ArrayList<BillClass> getBillList() {
		return BillArray;
	}
	
	public double getBillSum() {
		double L = 0;
		for (int i = 0; i < BillArray.size(); i++) {
			L += BillArray.get(i).value;
		}
		return Math.round(L * 100.0) / 100.0;
	}
	
	public MonthName getMonthName() {
		return this.Name;
	}
	
	public String getInfoString() {
		String s = "Gesamt :  ";
		s += this.getBillSum();
		s += "€\n\n\n";
		for (Reason e :
				Reason.values()) {
			s += e.toString() + ":\t" + getSumOfReason(e) + "\n";
		}
		
		return s;
	}
	
	public String getBillistString() {
		String s = "";
		for (int i = 0; i < BillArray.size(); i++) {
			s += Integer.toString(i) + ".    " + BillArray.get(i).toString() + " \n";
		}
		return s;
	}
	
	public double getSumOfReason(Reason Reason) {
		double p = 0;
		for (int i = 0; i < BillArray.size(); i++) {
			if (BillArray.get(i).reason == Reason) p += BillArray.get(i).value;
		}
		return p;
	}
	
	
	public String toSaveString() {
		String s = "";
		for (BillClass billClass : BillArray) {
			s += billClass.toSaveString();
			
		}
		return "/" + this.getName() + "\n" + s + "\n";
	}
	
	public void StringToMonth(String input) {
		String[] Bills = input.split("#");
		if (Bills.length < 2) return;
		System.out.println(Bills[0]);
		int i = 1;
		for (String s : Bills
		) {
			if (i > 1) {
				BillClass b = new BillClass();
				b.StringToBill(s);
				BillArray.add(b);
			}
			i++;
		}
	}
}


