import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ZusammenfassungFX {
	private Year MainYear;
	private double[] ReasonBills = new double[Reason.size];
	private int[] PieArgs = new int[Reason.size];
	private String ReasonText;
	private double YearSumm;
	private JFrame frame;
	private JPanel panel;
	
	private JTextField JahrTF;
	
	private JButton JahrAuswahlB;
	private JButton WeiterB;
	private JButton ZurückB;
	private JEditorPane InformationEP;
	private Pie PieChart;
	private Diagram Diagram;
	
	
	public ZusammenfassungFX(Year Year) {
		MainYear = Year;
		Calculate();
		init();
		setBounds();
		setText();
		Listeners();
		addShow();
	}
	
	
	private void init() {
		frame = new JFrame("Übersicht   " + MainYear.year);
		panel = new JPanel(null);
		JahrAuswahlB = new JButton("go");
		WeiterB = new JButton("-->");
		ZurückB = new JButton("<--");
		JahrTF = new JTextField(Integer.toString(MainYear.year));
		InformationEP = new JEditorPane();
		PieChart = new Pie(PieArgs);
		
	}
	
	private void setBounds() {
		frame.setSize(1000, 1000);
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		int SchaltButtonGröße = 80;
		ZurückB.setBounds(panel.getWidth() / 2 - SchaltButtonGröße - 5, panel.getHeight() - ZurückB.getWidth() - 95, SchaltButtonGröße, 25);
		WeiterB.setBounds(panel.getWidth() / 2 + 5, panel.getHeight() - WeiterB.getWidth() - 95, SchaltButtonGröße, 25);
		JahrTF.setBounds(10, 10, 50, 25);
		JahrAuswahlB.setBounds(65, 10, 40, 25);
		InformationEP.setBounds(10, 40, panel.getWidth() - 40, panel.getHeight() - 90);
		PieChart.setBounds(450, 50, 350, 250);
		PieChart.setBackground(Color.WHITE);
	}
	
	private void setText() {
		InformationEP.setText("Gesamtausgaben:\t" + YearSumm + "\t€\n\n" + ReasonText);
		InformationEP.setFont(new Font("Canvas", Font.PLAIN, 18));
	}
	
	private void Calculate() {
		ReasonText = "";
		int j = 0;
		for (Reason r : Reason.values()) {
			ReasonText += r.name();
			ReasonText += ":\t\t";
			double x = 0;
			for (int i = 0; i < 12; i++) {
				x += MainYear.getMonth(i).getSumOfReason(r);
			}
			ReasonBills[j] = x;
			j++;
			ReasonText += x;
			ReasonText += "\t€\n";
		}
		YearSumm = 0.0;
		for (int i = 0; i < 12; i++) {
			YearSumm += MainYear.getMonth(i).getBillSum();
		}
		double p = YearSumm / 360.0;
		for (int i = 0; i < Reason.size; i++) {
			PieArgs[i] = (int) Math.round(ReasonBills[i] / p);
		}
		
	}
	
	private void Listeners() {
	
	}
	
	private void addShow() {
		
		panel.add(PieChart);
		panel.add(InformationEP);
		panel.add(JahrAuswahlB);
		panel.add(JahrTF);
		panel.add(ZurückB);
		panel.add(WeiterB);
		
		frame.add(panel);
		
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.repaint();
	}
	
	public void loadYear(int Year) {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(Integer.toString(Year) + ".txt"));
			MainYear = (Year) stream.readObject();
			stream.close();
		} catch (ClassNotFoundException cnfex) {
			System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
		} catch (IOException ioex) {
			System.err.println("Das Objekt konnte nicht geladen werden.");
			ioex.printStackTrace();
		}
		
	}
}
