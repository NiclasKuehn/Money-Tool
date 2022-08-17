import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Diagram<Type extends BillContainer> extends JPanel {
	private int value[] = new int[12];
	
	Color[] colors = {Color.red, Color.green, Color.blue, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.yellow};
	
	
	public Diagram(Year year) {
		for (int i = 0; i < 12; i++) {
			value[i] = (int) Math.round(year.getMonth(i).getBillSum());
		}
		
		
	}
	
	public Diagram(Type element) {
		if (element.getClass() == Year.class) {
			Year year = (Year) element;
			for (int i = 0; i < 12; i++) {
				value[i] = (int) Math.round(year.getMonth(i).getBillSum());
			}
		} else {
		
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		QualitySettings.set(g);
		g.setFont(new Font("Canvas", Font.BOLD, 20));
		g.drawString("Jahresüberblick", 5, 20);
		g.setFont(new Font("Canvas", Font.PLAIN, 12));
		if (value == null) {
			return;
		}
		
		int[] paintvalue = new int[value.length];
		int max = 0;
		for (int i = 0; i < value.length; i++) {
			if (Math.abs(value[i]) > Math.abs(value[max])) max = i;
		}
		int puffer = ((this.getHeight() / 100) * 65);
		int y = this.getHeight() - puffer;//height=max*per
		double per = (double) y / (double) Math.abs(value[max]);
		for (int i = 0; i < value.length; i++) {
			paintvalue[i] = (int) Math.round(value[i] * per);
		}
		int min = paintvalue[0];
		for (double d :
				paintvalue) {
			min = (int) Math.round(Math.min(min, d));
			
		}
		int offsetY;
		if (min < 0) offsetY = -min + 30;
		else offsetY = 30;
		
		
		g.drawLine(0, this.getHeight() - offsetY, this.getWidth(), this.getHeight() - offsetY);
		int j = 0;
		int yy = this.getHeight() - offsetY;
		for (MonthName reason : MonthName.values()
		
		) {
			g.setColor(Color.BLACK);
			g.drawString(reason.toString(), 70 * j + 10, this.getHeight() - 17);
			g.drawString(Integer.toString(value[j]) + "€", 70 * j + 13, this.getHeight() - 3);
			g.setColor(colors[j % colors.length]);
			if (j < paintvalue.length)
				if (paintvalue[j] < 0) g.fillRect(70 * j + 20, yy, 20, Math.abs(paintvalue[j]));
				else g.fillRect(70 * j + 20, yy - paintvalue[j], 20, Math.abs(paintvalue[j]));
			
			j++;
		}
		
		
	}
}