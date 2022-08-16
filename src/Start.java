import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Start {
	
	private Year MainYear = new Year(2022);
	private JFrame frame;
	private JPanel panel;
	private JLabel Caption;
	private ArrayList<JMButton> MButtons = new ArrayList<>();
	private JButton JahrAuswahlB = new JButton("go");
	
	private JButton Uebersicht = new JButton("Gesamtübersicht");
	private JTextField JahrTF;
	
	private int ThisYear = new GregorianCalendar().get(Calendar.YEAR);
	
	
	private Start() {
		
		
		this.loadYear(ThisYear);
		this.buildFrame();
		this.buildButtons();
		
	}
	
	private void setCaption() {
		this.Caption.setText("Jahresplan   " + MainYear.year);
	}
	
	private void buildButtons() {
		for (int i = 0; i < 12; i++) {
			MButtons.add(new JMButton());
		}
		//Buttons erstellen
		{
			int x = 0;
			int y = 0;
			int j = 0;
			for (JMButton e :
					MButtons) {
				e.setText((MainYear.getMonth(j).getName()));
				e.setMonthID(j);
				e.setBounds(30 + x, 150 + y, 160, 60);
				panel.add(e);
				if (x <= frame.getWidth() - (e.getWidth() + 10) * 2) {
					x += e.getWidth() + 10;
				} else {
					x = 0;
					y += e.getHeight() + 10;
				}
				e.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent x) {
						
						MainYear.getMonth(e.getMonthID()).getEdited();
						
						
					}
				});
				j++;
			}
			
		}
	}
	
	private void buildFrame() {
		frame = new JFrame("Money-Tool");
		frame.setSize(750, 450);
		panel = new JPanel();
		
		
		Caption = new JLabel();
		Caption.setBounds(273, 15, 300, 25);
		panel.add(Caption);
		Caption.setFont(new Font("Canvas", Font.PLAIN, 28));
		this.setCaption();
		
		
		JahrAuswahlB.setBounds(80, 50, 48, 25);
		JahrTF = new JTextField(Integer.toString(MainYear.year));
		JahrTF.setBounds(30, 50, 40, 25);
		
		JahrAuswahlB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainYear.save();
				MainYear = new Year(Integer.parseInt(JahrTF.getText()));
				loadYear(Integer.parseInt(JahrTF.getText()));
				setCaption();
			}
		});
		panel.add(JahrAuswahlB);
		panel.add(JahrTF);
		
		
		Uebersicht.setBounds(300, 50, 180, 25);
		panel.add(Uebersicht);
		Uebersicht.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Uebersichtsanzeige();
			}
		});
		panel.setLayout(null);
		
		
		frame.add(panel);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(frame,
						"Speichern und verlassen? ", "",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					MainYear.save();
					System.exit(0);
				}
			}
		});
		frame.setVisible(true);
	}
	
	public static void go() {
		Start s = new Start();
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
	
	private void Uebersichtsanzeige() {
		ZusammenfassungFX x = new ZusammenfassungFX(MainYear);
	}
}