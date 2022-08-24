package Visuals;

import Data.Year;
import Data.Storage;
import Visuals.Components.JMButton;
import Visuals.Components.MonthFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StartFrame {
	
	private Year MainYear;
	private JFrame frame;
	private JPanel panel;
	private JLabel Caption;
	private final ArrayList<JMButton> MButtons = new ArrayList<>();
	private final JButton JahrAuswahlB = new JButton("go");
	
	private final JButton Uebersicht = new JButton("Gesamtübersicht");
	private JTextField JahrTF;
	
	private final int ThisMonth = new GregorianCalendar().get(Calendar.MONTH);
	
	private StartFrame() {
		
		MainYear = Storage.loadYear(new GregorianCalendar().get(Calendar.YEAR));
		init();
		setBounds();
		setText();
		addShow();
		ActionListeners();
		
	}
	
	
	private void init() {
		frame = new JFrame("Money-Tool");
		panel = new JPanel();
		Caption = new JLabel();
		JahrTF = new JTextField(MainYear.getName());
		for (int i = 0; i < 12; i++) {
			MButtons.add(new JMButton());
		}
		
	}
	
	private void setBounds() {
		frame.setSize(750, 450);
		frame.setResizable(false);
		int captionWidth = 350;
		Caption.setBounds(frame.getWidth() / 2 - (captionWidth / 2), 35, captionWidth, 70);
		JahrAuswahlB.setBounds(80, 150, 48, 25);
		JahrTF.setBounds(32, 150, 40, 25);
		Uebersicht.setBounds(280, 150, 180, 25);
		int x = 0;
		int y = 0;
		for (JMButton e : MButtons) {
			e.setBounds(32 + x, 180 + y, 160, 60);
			if (x <= frame.getWidth() - (e.getWidth() + 10) * 2) {
				x += e.getWidth() + 10;
			} else {
				x = 0;
				y += e.getHeight() + 10;
			}
		}
	}
	
	private void setText() {
		Caption.setFont(new Font("Canvas", Font.PLAIN, 38));
		this.setCaption();
		int j = 0;
		for (JMButton e :
				MButtons) {
			e.setBackground(new Color(153, 204, 255));
			e.setText((MainYear.getMonth(j).getName()));
			e.setMonthID(j);
			j++;
		}
		MButtons.get(this.ThisMonth).setBackground(new Color(100, 255, 100));
	}
	
	private void addShow() {
		panel.add(Caption);
		panel.add(JahrAuswahlB);
		panel.add(JahrTF);
		panel.add(Uebersicht);
		panel.setLayout(null);
		
		for (JMButton e : MButtons) {
			panel.add(e);
		}
		
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void setCaption() {
		this.Caption.setText("Jahresplan   " + MainYear.getName());
	}
	
	private void ActionListeners() {
		Uebersicht.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Storage.saveYear(MainYear);
				Uebersichtsanzeige();
			}
		});
		JahrAuswahlB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Storage.saveYear(MainYear);
				MainYear = new Year(Integer.parseInt(JahrTF.getText()));
				MainYear = Storage.loadYear(MainYear.getYear());
				setCaption();
				Storage.saveYear(MainYear);
			}
		});
		for (JMButton e :
				MButtons) {
			e.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent x) {
					Storage.saveYear(MainYear);
					MonthFrame m = new MonthFrame(MainYear.getMonth(e.getMonthID()));
				}
			});
			
		}
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(frame,
						"Speichern und verlassen? ", "",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					Storage.saveYear(MainYear);
					System.exit(0);
				}
			}
		});
	}
	
	public static void go() {
		StartFrame s = new StartFrame();
	}
	
	private void Uebersichtsanzeige() {
		ÜbersichtFrame x = new ÜbersichtFrame(MainYear);
	}
}