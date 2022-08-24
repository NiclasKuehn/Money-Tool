package Visuals;

import Data.Month;
import Data.Storage;
import Data.Year;
import Visuals.Containers.Infopanel;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ÜbersichtFrame {
	private Year MainYear;
	private JFrame frame;
	private Infopanel panel;
	
	
	private JButton MonthYearToggleB;
	private JButton WeiterB;
	private JButton ZurückB;
	private boolean YearActive = true;
	private int ThisMonth =  new GregorianCalendar().get(Calendar.MONTH);
	
	
	public ÜbersichtFrame(Year Year) {
		MainYear = Year;
		init();
		setBounds();
		Listeners();
		addShow();
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
		frame.requestFocus();
		
	}
	
	
	private void Listeners() {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT -> {
						frame.remove(panel);
						if (!YearActive) {
							ThisMonth = ((ThisMonth - 1) % 12);
							if (ThisMonth < 0) ThisMonth = ThisMonth + 12;
							panel = new Infopanel<Month>(MainYear.getMonth(ThisMonth));
						} else {
							if (Storage.isYear(MainYear.getYear() - 1)) {
								MainYear = Storage.loadYear(MainYear.getYear() - 1);
								panel = new Infopanel<Year>(MainYear);
							}
						}
						changeBounds();
						addShow();
						
						
					}
					case KeyEvent.VK_RIGHT -> {
						frame.remove(panel);
						if (!YearActive) {
							
							ThisMonth = (ThisMonth + 1) % 12;
							panel = new Infopanel<Month>(MainYear.getMonth(ThisMonth));
							
						} else {
							
							if (Storage.isYear(MainYear.getYear() + 1)) {
								MainYear = Storage.loadYear(MainYear.getYear() + 1);
								panel = new Infopanel<Year>(MainYear);
							}
						}
						changeBounds();
						addShow();
						
					}
					case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> {
						frame.remove(panel);
						if (YearActive) {
							YearActive = false;
							panel = new Infopanel<Month>(MainYear.getMonth(ThisMonth));
							
						} else {
							YearActive = true;
							panel = new Infopanel<Year>(MainYear);
							
						}
						changeBounds();
						addShow();
					}
				}
			}
		});
		
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
					panel = new Infopanel<Month>(MainYear.getMonth(ThisMonth));
					
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
					ThisMonth = ((ThisMonth - 1) % 12);
					if (ThisMonth < 0) ThisMonth = ThisMonth + 12;
					panel = new Infopanel<Month>(MainYear.getMonth(ThisMonth));
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
					
					ThisMonth = (ThisMonth + 1) % 12;
					panel = new Infopanel<Month>(MainYear.getMonth(ThisMonth));
					
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
