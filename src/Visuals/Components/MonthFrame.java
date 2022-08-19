package Visuals.Components;

import Data.BillClass;
import Data.Month;
import Data.Utils;
import Enums.Reason;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MonthFrame extends JFrame {
	JFrame frame;
	JPanel panel;
	JLabel agenda;
	JLabel IDLY;
	JTextField valueTF;
	JComboBox<String> reasonCB = new JComboBox<>(Reason.getStringValue());
	JTextField remarkTF;
	JButton pushButton;
	ArrayList<JButton> EditButtons = new ArrayList<>();
	ArrayList<JButton> RemoveButtons = new ArrayList<>();
	JTextArea BillListL;
	JTextArea SumLB;
	
	public MonthFrame(Month month) {
		month.used = true;
		init();
		setBounds();
		setText(month);
		Listener(month);
		addShow();
		
	}
	
	private void addButtons() {
		EditButtons.add(new JButton("i"));
		RemoveButtons.add(new JButton("x"));
	}
	
	private void init() {
		
		panel = new JPanel();
		agenda = new JLabel("ID   Betrag      Grund                       Bemerkung");
		IDLY = new JLabel("0");
		valueTF = new JTextField("");
		reasonCB = new JComboBox<>(Reason.getStringValue());
		remarkTF = new JTextField("");
		pushButton = new JButton();
		pushButton.setFocusable(false);
		BillListL = new JTextArea();
		BillListL.setEditable(false);
		BillListL.setFocusable(false);
		SumLB = new JTextArea("Summe: ");
		SumLB.setEditable(false);
	}
	
	private void setBounds() {
		int offx = 5;
		int offy = 10;
		reasonCB.setBounds(70 + offx, 40 + offy, 90, 25);
		agenda.setBounds(5, 25, 300, 25);
		IDLY.setBounds(2 + offx, 40 + offy, 200, 25);
		valueTF.setBounds(20 + offx, 40 + offy, 40, 25);
		remarkTF.setBounds(170 + offx, 40 + offy, 100, 25);
		pushButton.setBounds(280 + offx, 40 + offy, 70, 25);
		BillListL.setBounds(5, 80, 280, 700);
		SumLB.setBounds(320, 80, 160, 300);
		
	}
	
	private void setText(Month MainMonth) {
		frame = new JFrame(MainMonth.getName());
		pushButton.setText("next");
		BillListL.setText(MainMonth.getBillistString());
		SumLB.setText(MainMonth.getInfoString());
	}
	
	private void addShow() {
		
		panel.add(SumLB);
		panel.add(BillListL);
		panel.add(pushButton);
		panel.add(remarkTF);
		panel.add(agenda);
		panel.add(IDLY);
		panel.add(valueTF);
		panel.add(reasonCB);
		panel.setLayout(null);
		
		frame.add(panel);
		frame.setSize(500, 600);
		frame.setLocationRelativeTo(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
	}
	
	private void Listener(Month MainMonth) {
		for (JButton editButton : EditButtons) {
			panel.add(editButton);
		}
		for (JButton removeButton : RemoveButtons) {
			panel.add(removeButton);
		}
		
		reasonCB.setKeySelectionManager(new JComboBox.KeySelectionManager() {
			@Override
			public int selectionForKey(char aKey, ComboBoxModel<?> aModel) {
				for (Reason r :
						Reason.values()) {
					if (reasonCB.getSelectedIndex() != r.ordinal()) {
						if (Character.toLowerCase(aKey) == Character.toLowerCase(r.name().charAt(0)))
							return r.ordinal();
					}
				}
				return -1;
			}
		});
		reasonCB.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyChar() == KeyEvent.VK_ENTER) {
					MainMonth.addBill(new BillClass(valueTF.getText(), reasonCB.getSelectedItem().toString(), remarkTF.getText()));
					BillListL.setText(MainMonth.getBillistString());
					SumLB.setText(MainMonth.getInfoString());
					valueTF.setText("");
					remarkTF.setText("");
				}
			}
		});
		
		valueTF.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utils.isNumber(valueTF.getText())) {
					MainMonth.addBill(new BillClass(valueTF.getText(), reasonCB.getSelectedItem().toString(), remarkTF.getText()));
					BillListL.setText(MainMonth.getBillistString());
					SumLB.setText(MainMonth.getInfoString());
				}
				valueTF.setText("");
				remarkTF.setText("");
				
			}
		});
		remarkTF.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utils.isNumber(valueTF.getText())) {
					MainMonth.addBill(new BillClass(valueTF.getText(), reasonCB.getSelectedItem().toString(), remarkTF.getText()));
					BillListL.setText(MainMonth.getBillistString());
					SumLB.setText(MainMonth.getInfoString());
				}
				valueTF.setText("");
				remarkTF.setText("");
				
				
			}
		});
		
		pushButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utils.isNumber(valueTF.getText())) {
					MainMonth.addBill(new BillClass(valueTF.getText(), reasonCB.getSelectedItem().toString(), remarkTF.getText()));
					BillListL.setText(MainMonth.getBillistString());
					SumLB.setText(MainMonth.getInfoString());
				}
			}
		});
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				MainMonth.used = false;
			}
		});
		
		
	}
	
	private void saveBill(Month MainMonth) {
		if (Utils.isNumber(valueTF.getText())) {
			MainMonth.addBill(new BillClass(valueTF.getText(), reasonCB.getSelectedItem().toString(), remarkTF.getText()));
			BillListL.setText(MainMonth.getBillistString());
			SumLB.setText(MainMonth.getInfoString());
		}
		valueTF.setText("");
		remarkTF.setText("");
	}
}
