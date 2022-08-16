import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ZusammenfassungFX {
	private Year MainYear;
	private JFrame frame;
	private JPanel panel;
	
	private JTextField JahrTF;
	
	private JButton JahrAuswahlB;
	private JButton WeiterB;
	private JButton ZurückB;
	private JEditorPane InformationEP;
	
	public ZusammenfassungFX(Year Year) {
		//loadYear(Year);
		MainYear = Year;
		setNames();
		setBounds();
		setText();
		Listeners();
		addShow();
	}
	
	
	private void setNames() {
		frame = new JFrame("Übersicht   " + MainYear.year);
		panel = new JPanel(null);
		JahrAuswahlB = new JButton("go");
		WeiterB = new JButton("-->");
		ZurückB = new JButton("<--");
		JahrTF = new JTextField(Integer.toString(MainYear.year));
		InformationEP = new JEditorPane();
		
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
		
	}
	
	private void setText() {
		String ReasonText = "";
		for (Reason r : Reason.values()) {
			ReasonText += r.name();
			ReasonText += ":\t\t";
			double x = 0;
			for (int i = 0; i < 12; i++) {
				x += MainYear.getMonth(i).getSumOfReason(r);
			}
			ReasonText += x;
			ReasonText += "\t€\n";
		}
		Double YearSumm = 0.0;
		for (int i = 0; i < 12; i++) {
			YearSumm += MainYear.getMonth(i).getBillSum();
		}
		InformationEP.setText("Gesamtausgaben:\t" + YearSumm + "\t€\n\n" + ReasonText);
		InformationEP.setFont(new Font("Canvas", Font.PLAIN, 18));
	}
	
	private void Listeners() {
	
	}
	
	private void addShow() {
		panel.add(InformationEP);
		panel.add(JahrAuswahlB);
		panel.add(JahrTF);
		panel.add(ZurückB);
		panel.add(WeiterB);
		frame.add(panel);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
