package Visuals;

import Data.Month;
import Data.Storage;
import Data.Year;
import Visuals.Containers.Infopanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ÜbersichtFrame {
	private Year MainYear;
	private JFrame frame;
	private Infopanel panel;
	
	
	private JButton MonthYearToggleB;
	private JButton WeiterB;
	private JButton ZurückB;
	private boolean YearActive = true;
	private int Monthcount = 0;
	
	
	public ÜbersichtFrame(Year Year) {
		MainYear = Year;
		Calculate();
		init();
		setBounds();
		Listeners();
		addShow();
	}
	
	private void Calculate() {
	
		
	}
	
	private void init() {
		frame = new JFrame("Übersicht   " + MainYear.getName());
		panel = new Infopanel<Year>(MainYear);
		MonthYearToggleB = new JButton("Umschalten Monat/Jahr");
		WeiterB = new JButton("-->");
		ZurückB = new JButton("<--");
		
	}
	
	
	private void setBounds() {
		frame.setSize(1200, 1000);
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		int SchaltButtonGröße = 80;
		ZurückB.setBounds(panel.getWidth() / 2 - SchaltButtonGröße - 5, panel.getHeight() - ZurückB.getWidth() - 95, SchaltButtonGröße, 25);
		WeiterB.setBounds(panel.getWidth() / 2 + 5, panel.getHeight() - WeiterB.getWidth() - 95, SchaltButtonGröße, 25);
		
		
		MonthYearToggleB.setBounds(500, 10, 180, 25);
		
	}
	
	private void changeBounds() {
		int SchaltButtonGröße = frame.getWidth() / 20;
		int SchaltButtonheight = frame.getHeight() / 40;
		ZurückB.setBounds(frame.getWidth() / 2 - SchaltButtonGröße - 5, frame.getHeight() - SchaltButtonheight - 45, SchaltButtonGröße, SchaltButtonheight);
		WeiterB.setBounds(frame.getWidth() / 2 + 5, frame.getHeight() - SchaltButtonheight - 45, SchaltButtonGröße, SchaltButtonheight);
		MonthYearToggleB.setBounds(frame.getWidth() / 2 - 90, frame.getHeight() / 100, 180, 25);
		panel.setBounds(0, 0, frame.getWidth() - 15, frame.getHeight() - 40);
		panel.changeBounds();
	}
	
	
	private void Listeners() {
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				changeBounds();
			}
		});
		MonthYearToggleB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel);
				if (YearActive) {
					YearActive = false;
					panel = new Infopanel<Month>(MainYear.getMonth(Monthcount));
					
				} else {
					YearActive = true;
					panel = new Infopanel<Year>(MainYear);
					
				}
				changeBounds();
				addShow();
			}
		});
		ZurückB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel);
				if (!YearActive) {
					Monthcount = ((Monthcount - 1) % 12);
					if (Monthcount < 0) Monthcount = Monthcount + 12;
					panel = new Infopanel<Month>(MainYear.getMonth(Monthcount));
				} else {
					if (Storage.isYear(MainYear.getYear() - 1)) {
						MainYear = Storage.loadYear(MainYear.getYear() - 1);
						panel = new Infopanel<Year>(MainYear);
					}
				}
				changeBounds();
				addShow();
				
			}
		});
		WeiterB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(panel);
				if (!YearActive) {
					
					Monthcount = (Monthcount + 1) % 12;
					panel = new Infopanel<Month>(MainYear.getMonth(Monthcount));
					
				} else {
					
					if (Storage.isYear(MainYear.getYear() + 1)) {
						MainYear = Storage.loadYear(MainYear.getYear() + 1);
						panel = new Infopanel<Year>(MainYear);
					}
				}
				changeBounds();
				addShow();
				
			}
		});
		
		
	}
	
	private void addShow() {
		
		panel.add(MonthYearToggleB);
		panel.add(ZurückB);
		panel.add(WeiterB);
		
		frame.add(panel);
		
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.repaint();
	}
	
	public boolean loadYear(int Year) {
		try {
			ObjectInputStream stream = new ObjectInputStream(new FileInputStream(Integer.toString(Year) + ".txt"));
			MainYear = (Year) stream.readObject();
			stream.close();
			return true;
		} catch (ClassNotFoundException cnfex) {
			
			System.err.println("Die Klasse des geladenen Objekts konnte nicht gefunden werden.");
			return false;
		} catch (IOException ioex) {
			
			System.err.println("Das Objekt konnte nicht geladen werden.");
			ioex.printStackTrace();
			return false;
		}
		
		
	}
}
