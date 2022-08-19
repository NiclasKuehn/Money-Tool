package Visuals;

import Data.Year;
import Data.Storage;
import Visuals.Components.JMButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StartFrame {
	
	private Year MainYear = new Year(2022);
	private JFrame frame;
	private JPanel panel;
	private JLabel Caption;
	private ArrayList<JMButton> MButtons = new ArrayList<>();
	private JButton JahrAuswahlB = new JButton("go");
	
	private JButton Uebersicht = new JButton("Gesamtübersicht");
	private JTextField JahrTF;
	
	private int ThisYear = new GregorianCalendar().get(Calendar.YEAR);
	private int ThisMonth = new GregorianCalendar().get(Calendar.MONTH);
	
	private StartFrame() {
		
		MainYear = Storage.loadYear(ThisYear);
		//this.loadYear(ThisYear);
		this.buildFrame();
		this.buildButtons();
		this.ActionListeners();
		
	}
	
	private void buildFrame() {
		
		init();
		setBounds();
		setText();
		addShow();
	}
	
	private void init() {
		frame = new JFrame("Money-Tool");
		panel = new JPanel();
		Caption = new JLabel();
		JahrTF = new JTextField(MainYear.getName());
		
	}
	
	private void setBounds() {
		frame.setSize(750, 450);
		Caption.setBounds(273, 15, 300, 25);
		JahrAuswahlB.setBounds(80, 50, 48, 25);
		JahrTF.setBounds(30, 50, 40, 25);
		Uebersicht.setBounds(300, 50, 180, 25);
	}
	
	private void setText() {
		Caption.setFont(new Font("Canvas", Font.PLAIN, 28));
		this.setCaption();
	}
	
	private void addShow() {
		panel.add(Caption);
		panel.add(JahrAuswahlB);
		panel.add(JahrTF);
		panel.add(Uebersicht);
		panel.setLayout(null);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
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
				e.setBackground(new Color(153, 204, 255));
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
				j++;
			}
			
		}
		MButtons.get(this.ThisMonth).setBackground(new Color(100, 255, 100));
		
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
					MainYear.getMonth(e.getMonthID()).getEdited();
					
					
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