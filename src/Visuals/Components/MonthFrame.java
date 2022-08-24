package Visuals.Components;

import Data.BillClass;
import Data.Month;
import Data.Utils;
import Enums.Reason;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MonthFrame extends JFrame {
	JFrame frame;
	JPanel panel;
	JLabel agenda;
	JLabel IDLY;
	JLabel IDText;
	JTextField valueTF;
	JComboBox<String> reasonCB = new JComboBox<>(Reason.getStringValue());
	JTextField remarkTF;
	JButton pushButton;
	JButton EditButtons;
	JTextField EditNumberTF;
	JButton RemoveButtons;
	JTextArea BillListL;
	JTextArea SumLB;
	Month OnlyReadMonth;
	
	public MonthFrame(Month month) {
		OnlyReadMonth = month;
		month.used = true;
		init();
		setBounds();
		setText();
		Listener(month);
		addShow();
		
	}
	
	
	private void init() {
		
		IDText = new JLabel("ID:");
		EditButtons = new JButton(new ImageIcon("Recources/pen.png"));
		RemoveButtons = new JButton(new ImageIcon("Recources/cross.png"));
		EditNumberTF = new JTextField("");
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
		
		IDText.setBounds(320, 380, 25, 25);
		EditButtons.setBounds(360, 380, 25, 24);
		RemoveButtons.setBounds(385, 380, 25, 24);
		EditNumberTF.setBounds(335, 380, 25, 25);
		reasonCB.setBounds(70 + offx, 40 + offy, 90, 25);
		agenda.setBounds(5, 25, 300, 25);
		IDLY.setBounds(2 + offx, 40 + offy, 200, 25);
		valueTF.setBounds(20 + offx, 40 + offy, 40, 25);
		remarkTF.setBounds(170 + offx, 40 + offy, 100, 25);
		pushButton.setBounds(280 + offx, 40 + offy, 70, 25);
		BillListL.setBounds(5, 80, 280, 700);
		SumLB.setBounds(320, 80, 160, 300);
		
	}
	
	private void setText() {
		frame = new JFrame(OnlyReadMonth.getName());
		pushButton.setText("next");
		BillListL.setText(OnlyReadMonth.getBillistString());
		BillListL.setFont(new Font("Canvas", Font.PLAIN, 13));
		SumLB.setText(OnlyReadMonth.getInfoString());
	}
	
	private void addShow() {
		panel.add(IDText);
		panel.add(EditButtons);
		panel.add(RemoveButtons);
		panel.add(EditNumberTF);
		panel.add(SumLB);
		panel.add(BillListL);
		panel.add(pushButton);
		panel.add(remarkTF);
		panel.add(agenda);
		panel.add(IDLY);
		panel.add(valueTF);
		panel.add(reasonCB);
		panel.setLayout(null);
		
		frame.setSize(500, 600);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setVisible(true);
		
	}
	
	
	private void addBill(Month MainMonth) {
		MainMonth.addBill(new BillClass(valueTF.getText(), reasonCB.getSelectedItem().toString(), remarkTF.getText()));
		valueTF.setText("");
		remarkTF.setText("");
		BillListL.setText(MainMonth.getBillistString());
		SumLB.setText(MainMonth.getInfoString());
	}
	
	private void Listener(Month MainMonth) {
		EditButtons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Utils.isNumber(EditNumberTF.getText())) return;
				BillClass B = MainMonth.getBillList().get(Integer.parseInt(EditNumberTF.getText()));
				MainMonth.remBill(Integer.parseInt(EditNumberTF.getText()));
				valueTF.setText(Double.toString(B.value));
				reasonCB.setSelectedIndex(B.reason.ordinal());
				remarkTF.setText(B.remark);
				BillListL.setText(MainMonth.getBillistString());
				SumLB.setText(MainMonth.getInfoString());
				
			}
		});
		RemoveButtons.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!Utils.isNumber(EditNumberTF.getText())) return;
				if (MainMonth.getBillList().size() <= Integer.parseInt(EditNumberTF.getText())) return;
				MainMonth.remBill(Integer.parseInt(EditNumberTF.getText()));
				BillListL.setText(MainMonth.getBillistString());
				SumLB.setText(MainMonth.getInfoString());
			}
		});
		//ComboBox autoSelect
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
		//Enter Grund
		reasonCB.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent event) {
				if (event.getKeyChar() == KeyEvent.VK_ENTER) {
					addBill(MainMonth);
				}
			}
		});
		//Enter Betrag
		valueTF.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utils.isNumber(valueTF.getText())) {
					addBill(MainMonth);
				}
				
				
			}
		});
		//Enter Bemerkung
		remarkTF.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utils.isNumber(valueTF.getText())) {
					addBill(MainMonth);
				}
			}
		});
		// next Button
		pushButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utils.isNumber(valueTF.getText())) {
					addBill(MainMonth);
				}
			}
		});
		// Fenster schließen
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				MainMonth.used = false;
			}
		});
		
		
	}
}
