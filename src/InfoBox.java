import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class InfoBox<Type extends BillContainer> extends JTextArea {
	private String ReasonText;
	private String AusgabenText;
	private String EinnahmenText;
	private String GesamtProfit;
	private String Caption;
	
	
	public InfoBox(Month Month) {
		Caption = Month.getName();
		ReasonText = "";
		double a = 0;
		double e = 0;
		double g = 0;
		for (Reason r : Reason.values()) {
			ReasonText += r.name();
			ReasonText += ":\t\t";
			ReasonText += Month.getSumOfReason(r);
			ReasonText += "\t€\n";
			
			if (Month.getSumOfReason(r) < 0) a += Month.getSumOfReason(r);
			else e += Month.getSumOfReason(r);
			g += Month.getSumOfReason(r);
		}
		AusgabenText = "Ausgaben: " + Long.toString(Math.round(a)) + "€\n";
		EinnahmenText = "Einnahmen: " + Long.toString(Math.round(e)) + "€\n";
		GesamtProfit = "Gesamtprofit: " + Long.toString(Math.round(g)) + "€\n";
		
	}
	
	public InfoBox(Year year) {
		Caption = year.getYear();
		ReasonText = "";
		
		double a = 0;
		double e = 0;
		double g = 0;
		
		for (Reason r : Reason.values()) {
			ReasonText += "    -" + r.name();
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
		
		AusgabenText = " Ausgaben: " + Long.toString(Math.round(a)) + "€\n";
		EinnahmenText = " Einnahmen: " + Long.toString(Math.round(e)) + "€\n";
		GesamtProfit = " Gesamtprofit: " + Long.toString(Math.round(g)) + "€\n";
		
	}
	
	public InfoBox(Type element) {
		double a = 0;
		double e = 0;
		double g = 0;
		Caption = element.getName();
		ReasonText = "";
		if (element.getClass() == Year.class) {
			Year year = (Year) element;
			
			
			for (Reason r : Reason.values()) {
				ReasonText += "    -" + r.name();
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
		
		
		AusgabenText = " Ausgaben: " + Long.toString(Math.round(a)) + "€\n";
		EinnahmenText = " Einnahmen: " + Long.toString(Math.round(e)) + "€\n";
		GesamtProfit = " Gesamtprofit: " + Long.toString(Math.round(g)) + "€\n";
		
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		QualitySettings.set(g);
		
		g.setFont(new Font("Canvas", Font.BOLD, 20));
		g.drawString(Caption, 5, 20);
		this.setFont(new Font("Canvas", Font.PLAIN, 15));
		String s = "\n" + AusgabenText + EinnahmenText + GesamtProfit + ReasonText;
		this.setText(s);
	}
	
}