import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class InfoBox<Type extends BillContainer> extends JTextArea {
	private String ReasonText;
	private String AusgabenText;
	private String EinnahmenText;
	private String GesamtProfit;
	private String Caption;
	private Type Element;
	private BillClass MaxSpend;
	private BillClass MaxGot;
	private String NiceInfoText = "";
	
	
	public InfoBox(Type element) {
		this.Element = element;
		double a = 0;
		double e = 0;
		double g = 0;
		Caption = element.getName();
		ReasonText = "";
		if (element.getClass() == Year.class) {
			Year year = (Year) element;
			
			
			for (Reason r : Reason.values()) {
				ReasonText += "  -" + r.name();
				ReasonText += ":\t\t";
				double x = 0;
				for (int i = 0; i < 12; i++) {
					x += year.getMonth(i).getSumOfReason(r);
					
					if (year.getMonth(i).getSumOfReason(r) < 0) a += year.getMonth(i).getSumOfReason(r);
					else e += year.getMonth(i).getSumOfReason(r);
					g += year.getMonth(i).getSumOfReason(r);
				}
				
				ReasonText += x;
				ReasonText += "\t€\n";
			}
		} else {
			Month Month = (Month) element;
			
			for (Reason r : Reason.values()) {
				ReasonText += r.name();
				ReasonText += ":\t\t";
				ReasonText += Month.getSumOfReason(r);
				ReasonText += "\t€\n";
				
				if (Month.getSumOfReason(r) < 0) a += Month.getSumOfReason(r);
				else e += Month.getSumOfReason(r);
				g += Month.getSumOfReason(r);
			}
			
		}
		
		
		AusgabenText = " Ausgaben:\t\t" + Long.toString(Math.round(a)) + "\t€\n";
		EinnahmenText = " Einnahmen:\t\t" + Long.toString(Math.round(e)) + "\t€\n";
		GesamtProfit = " Gesamtprofit:\t\t" + Long.toString(Math.round(g)) + "\t€\n";
		calcMinMax();
	}
	
	private void calcMinMax() {
		if (Element.getBillList() == null) return;
		if (Element.getBillList().size() == 0) return;
		ArrayList<BillClass> List = Element.getBillList();
		MaxGot = List.get(0);
		MaxSpend = List.get(0);
		for (BillClass bill : List) {
			if (bill.value < MaxSpend.value && bill.value < 0) MaxSpend = bill;
			if (bill.value > MaxGot.value && bill.value > 0) MaxGot = bill;
		}
		NiceInfoText = "\n Größte Investition:\n    " + MaxSpend.toString() + "\n Größte Einnahme:\n    " + MaxGot.toString();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		QualitySettings.set(g);
		
		g.setFont(new Font("Canvas", Font.BOLD, 20));
		g.drawString(Caption, 5, 20);
		this.setFont(new Font("Canvas", Font.PLAIN, 15));
		String s = "\n\n" + AusgabenText + EinnahmenText + GesamtProfit + "\n" + ReasonText + NiceInfoText;
		this.setText(s);
	}
	
}