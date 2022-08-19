package Data;

import Enums.Reason;

import java.io.Serializable;

public class BillClass implements Serializable {
	public Reason reason;
	public double value = 0.00;
	public String remark;
	
	public BillClass(int value, Reason reason, String remark) {
		this.reason = reason;
		this.value = value;
		this.remark = remark;
	}
	
	public BillClass() {
		this.reason = null;
		this.value = 0;
		this.remark = "";
	}
	
	public BillClass(String value, String reason, String remark) {
		this.value = Double.parseDouble(value);
		
		this.reason = Reason.valueOf(reason);
		
		this.remark = remark;
	}
	
	public String toString() {
		
		String s = value + "€\t" + reason.toString() + "\t" + remark;
		return s;
	}
	
	public String toSaveString() {
		
		String s = "#" + +value + ";" + reason.toString() + ";" + remark + ";" + "\n";
		return s;
	}
	
	public void StringToBill(String input) {
		
		String[] values = input.split(";");
		this.value = Double.parseDouble(values[0]);
		this.reason = Reason.StringToReason(values[1]);
		this.remark = values[2];
		
	}
}
