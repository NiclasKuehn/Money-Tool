import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Diagram extends JPanel {
	private int value[] = new int[12];
	private int start = 0;
	
	Color[] colors = {Color.red, Color.green, Color.blue, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.yellow};
	
	public Diagram(String[] args) {
		value = new int[args.length];
		
		for (int i = 0; i < args.length; i++) //Error at this line
		{
			value[i] = Integer.parseInt(args[i]);
		}
		
	}
	
	public Diagram(Year year) {
		for (int i = 0; i < 12; i++) {
			value[i] = (int) Math.round(year.getMonth(i).getBillSum());
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
	
	
	public static void main(String args[]) {
		JFrame f = new JFrame("Piechart");
		Diagram p = new Diagram(new String[]{"90", "90", "90", "70", "20", "80", "10", "20"});
		
		f.add(p);
		p.setBounds(0, 0, 400, 400);
		p.setPreferredSize(new Dimension(600, 600));
		f.setSize(500, 500);
		//f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.repaint();
	}
}