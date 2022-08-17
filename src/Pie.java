import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pie extends JPanel {
	private int value[];
	private int start = 0;
	
	Color[] colors = {Color.red, Color.green, Color.blue, Color.orange, Color.cyan, Color.magenta, Color.pink, Color.yellow};
	
	public Pie(String[] args) {
		value = new int[args.length];
		
		for (int i = 0; i < args.length; i++) //Error at this line
		{
			value[i] = Integer.parseInt(args[i]);
		}
		
	}
	
	public Pie(int[] args) {
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
		for (Reason reason : Reason.values()
		) {
			
			g.drawString(reason.toString(), 20, 25 * j + 50);
			j++;
			
		}
		
		for (int i = 0; i < value.length; i++) {
			g.setColor(colors[i % colors.length]);
			g.fillRect(0, 25 * i + 40, 15, 15);
			
			g.fillArc(this.getWidth() - size, this.getHeight() - size, size - 20, size - 20, start, value[i]);
			start = start + value[i];
			
		}
		
	}
	
	
	public static void main(String args[]) {
		JFrame f = new JFrame("Piechart");
		Pie p = new Pie(new String[]{"90", "90", "90", "70"});
		
		f.add(p);
		p.setPreferredSize(new Dimension(400, 400));
		f.setSize(500, 500);
		//f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.repaint();
	}
}