import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Diagram extends JPanel {
	private int value[];
	private int start = 0;
	
	Color[] colors = {Color.red, Color.green, Color.blue, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.yellow};
	
	public Diagram(String[] args) {
		value = new int[args.length];
		
		for (int i = 0; i < args.length; i++) //Error at this line
		{
			value[i] = Integer.parseInt(args[i]);
		}
		
	}
	
	public Diagram(int[] args) {
		value = args;
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (value == null) {
			return;
		}
		int size;
		if (this.getWidth() < this.getHeight()) {
			size = this.getWidth();
		} else {
			size = this.getHeight();
		}
		int j = 0;
		int[] truevalue = new int[value.length];
		int max = 1;
		for (int i = 0; i < value.length; i++) {
			if (value[i] > max) max = value[i];
		}
		int y = this.getHeight() - 40;
		double per = (double) max / (double) y;
		for (int i = 0; i < value.length; i++) {
			truevalue[i] = (int) Math.round(value[i] / per);
		}
		for (MonthName reason : MonthName.values()
		) {
			g.setColor(Color.BLACK);
			g.drawString(reason.toString(), 60 * j + 5, this.getHeight() - 10);
			g.setColor(colors[j % colors.length]);
			if (j < truevalue.length) g.fillRect(60 * j + 20, this.getHeight() - 30 - truevalue[j], 20, truevalue[j]);
			
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