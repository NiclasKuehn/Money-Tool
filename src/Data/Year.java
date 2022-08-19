package Data;

import Enums.MonthName;

import java.io.*;
import java.util.ArrayList;

public class Year extends BillContainer implements Serializable {
	private int year;
	private ArrayList<BillClass> BillListL = new ArrayList<BillClass>();
	
	
	private final ArrayList<Month> MonthList = new ArrayList<>();
	
	public Year() {
		MonthList.add(new Month(MonthName.Januar));
		MonthList.add(new Month(MonthName.Febuar));
		MonthList.add(new Month(MonthName.März));
		MonthList.add(new Month(MonthName.April));
		MonthList.add(new Month(MonthName.Mai));
		MonthList.add(new Month(MonthName.Juni));
		MonthList.add(new Month(MonthName.Juli));
		MonthList.add(new Month(MonthName.August));
		MonthList.add(new Month(MonthName.September));
		MonthList.add(new Month(MonthName.Oktober));
		MonthList.add(new Month(MonthName.November));
		MonthList.add(new Month(MonthName.Dezember));
	}
	
	public Year(int year) {
		this();
		this.year = year;
	}
	
	public Month getMonth(int index) {
		return MonthList.get(index);
	}
	
	public void setMonth(Month month) {
		
		MonthList.set(month.getMonthName().ordinal(), month);
	}
	
	@Override
	public String getName() {
		return Integer.toString(this.year);
	}
	
	public ArrayList<BillClass> getBillList() {
		for (Month M : MonthList
		) {
			BillListL.addAll(M.getBillList());
		}
		return BillListL;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public String toSaveString() {
		String s = "<" + this.getName() + "\n";
		for (Month M : MonthList
		) {
			s += M.toSaveString();
			
		}
		return s + ">\n\n";
	}
	
	public void StringToYear(String input) {
		String[] Monate = input.split("/");
		int i = 1;
		
		for (Month M : MonthList
		) {
			M.StringToMonth(Monate[i]);
			i++;
		}
		System.out.println(Monate[1]);
		
	}
}
