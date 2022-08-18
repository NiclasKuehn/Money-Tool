import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PieChart<Type extends BillContainer> extends JPanel {
	private ArrayList<Double> value = new ArrayList<Double>();
	private ArrayList<Integer> truevalue = new ArrayList<Integer>();
	private ArrayList<String> Names = new ArrayList<String>();
	private String Caption;
	private int start = 0;
	
	Color[] colors = {Color.blue, Color.orange, Color.cyan, Color.pink, Color.magenta, Color.yellow, Color.red, Color.green};
	int rand = (int) (Math.random() * 10);
	
	
	public PieChart(Year year, Boolean negativ) {
		double[] args = new double[Reason.values().length];
		int j = 0;
		for (Reason r : Reason.values()) {
			for (int i = 0; i < 12; i++) {
				args[j] += year.getMonth(i).getSumOfReason(r);
			}
			j++;
		}
		
		
		double YearSumm = 0.0;
		if (negativ) Caption = "Ausgaben";
		else Caption = "Einnahmen";
		for (int i = 0; i < args.length; i++) {
			if (negativ) {
				if (args[i] < 0) {
					YearSumm += Math.abs(args[i]);
					value.add(Math.abs(args[i]));
					Names.add(Reason.getStringValue()[i]);
				}
			} else if (args[i] > 0) {
				YearSumm += Math.abs(args[i]);
				value.add(Math.abs(args[i]));
				Names.add(Reason.getStringValue()[i]);
			}
		}
		
		
		double gradProProzent = 360.0 / Math.abs(YearSumm);//1 % = p Grad
		
		for (int i = 0; i < Names.size(); i++) {
			truevalue.add((int) (Math.abs(value.get(i) * gradProProzent)));
		}
		
		
	}
	
	public PieChart(Type element, Boolean negativ) {
		if (element.getClass() == Year.class) {
			Year year = (Year) element;
			double[] args = new double[Reason.values().length];
			int j = 0;
			for (Reason r : Reason.values()) {
				for (int i = 0; i < 12; i++) {
					args[j] += year.getMonth(i).getSumOfReason(r);
				}
				j++;
			}
			
			
			double YearSumm = 0.0;
			if (negativ) Caption = "Ausgaben";
			else Caption = "Einnahmen";
			for (int i = 0; i < args.length; i++) {
				if (negativ) {
					if (args[i] < 0) {
						YearSumm += Math.abs(args[i]);
						value.add(Math.abs(args[i]));
						Names.add(Reason.getStringValue()[i]);
					}
				} else if (args[i] > 0) {
					YearSumm += Math.abs(args[i]);
					value.add(Math.abs(args[i]));
					Names.add(Reason.getStringValue()[i]);
				}
			}
			
			
			double gradProProzent = 360.0 / Math.abs(YearSumm);//1 % = p Grad
			
			for (int i = 0; i < Names.size(); i++) {
				truevalue.add((int) (Math.abs(value.get(i) * gradProProzent)));
			}
		} else {
			Month month = (Month) element;
			double[] args = new double[Reason.values().length];
			int j = 0;
			for (Reason r : Reason.values()) {
				
				args[j] += month.getSumOfReason(r);
				
				j++;
			}
			
			
			double YearSumm = 0.0;
			if (negativ) Caption = "Ausgaben";
			else Caption = "Einnahmen";
			for (int i = 0; i < args.length; i++) {
				if (negativ) {
					if (args[i] < 0) {
						YearSumm += Math.abs(args[i]);
						value.add(Math.abs(args[i]));
						Names.add(Reason.getStringValue()[i]);
					}
				} else if (args[i] > 0) {
					YearSumm += Math.abs(args[i]);
					value.add(Math.abs(args[i]));
					Names.add(Reason.getStringValue()[i]);
				}
			}
			
			
			double gradProProzent = 360.0 / Math.abs(YearSumm);//1 % = p Grad
			
			for (int i = 0; i < Names.size(); i++) {
				truevalue.add((int) (Math.abs(value.get(i) * gradProProzent)));
				
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		QualitySettings.set(g);
		if (truevalue == null) {
			return;
		}
		g.setFont(new Font("Canvas", Font.BOLD, 20));
		g.drawString(Caption, 12, 20);
		g.setFont(new Font("Canvas", Font.PLAIN, 15));
		int size = Math.min(this.getWidth(), this.getHeight()) + -5;
		int j = 0;
		for (String name : Names) {
			g.drawString(name, 25, 25 * j + 53);
			j++;
		}
		
		int p = 0;
		for (int i = 0; i < truevalue.size(); i++) {
			p += truevalue.get(i);
			g.setColor(colors[(i + rand) % colors.length]);
			g.fillRect(5, 25 * i + 40, 15, 15);
			
			g.fillArc(this.getWidth() - size, this.getHeight() - size, size - 20, size - 20, start, truevalue.get(i));
			start = start + truevalue.get(i);
			
			
		}
		if (p < 360) {
			g.fillArc(this.getWidth() - size, this.getHeight() - size, size - 20, size - 20, start, 360 - p);
			start = start + 360 - p;
		}
		
	}
	
}