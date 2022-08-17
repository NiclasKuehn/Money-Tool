import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Month extends BillContainer implements Serializable {
	private boolean used = false;
	private final ArrayList<BillClass> BillArray = new ArrayList<BillClass>();
	private MonthName Name;
	
	
	public Month(MonthName Monat) {
		this.Name = Monat;
		
	}
	
	public void addBill(BillClass Bill) {
		this.BillArray.add(Bill);
	}
	
	public void remBill(int index) {
		this.BillArray.remove(index);
	}
	
	public String getName() {
		return Name.toString();
	}
	
	public ArrayList<BillClass> getBillArray() {
		return BillArray;
	}
	
	public void getEdited() {
		this.used = true;
		JFrame frame = new JFrame(Name.toString());
		;
		JPanel panel = new JPanel();
		JLabel argenda = new JLabel("ID   Betrag      Grund                       Bemerkung");
		JLabel IDLB = new JLabel("0");
		JTextField valueTF = new JTextField("");
		JComboBox<String> reasonCB = new JComboBox<>(Reason.getStringValue());
		JTextField remarkTF = new JTextField("");
		JButton pushButton = new JButton();
		JTextArea BillListL = new JTextArea();
		JTextArea SumLB = new JTextArea("Summe: ");
		
		
		panel.setLayout(null);
		
		int offx = 5;
		int offy = 10;
		
		argenda.setBounds(5, 25, 300, 25);
		panel.add(argenda);
		
		IDLB.setBounds(2 + offx, 40 + offy, 200, 25);
		panel.add(IDLB);
		
		valueTF.setBounds(20 + offx, 40 + offy, 40, 25);
		panel.add(valueTF);
		
		reasonCB.setBounds(70 + offx, 40 + offy, 90, 25);
		panel.add(reasonCB);
		
		remarkTF.setBounds(170 + offx, 40 + offy, 100, 25);
		panel.add(remarkTF);
		
		pushButton.setText("next");
		pushButton.setBounds(280 + offx, 40 + offy, 70, 25);
		panel.add(pushButton);
		
		BillListL.setBounds(5, 80, 280, 700);
		BillListL.setEditable(false);
		panel.add(BillListL);
		BillListL.setText(getBillistString());
		
		SumLB.setBounds(290, 80, 160, 300);
		panel.add(SumLB);
		SumLB.setText(getInfoString());
		
		
		pushButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Utils.isNumber(valueTF.getText())) {
					BillArray.add(new BillClass(valueTF.getText(), reasonCB.getSelectedItem().toString(), remarkTF.getText()));
					BillListL.setText(getBillistString());
					SumLB.setText(getInfoString());
				}
			}
		});
		
		
		frame.add(panel);
		frame.setSize(500, 600);
		frame.setLocationRelativeTo(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				used = false;
			}
		});
		frame.setVisible(true);
	}
	
	public boolean waitForClosedFrame() {
		//while(used)
		//{wait(1);}
		return true;
	}
	
	public double getBillSum() {
		double L = 0;
		for (int i = 0; i < BillArray.size(); i++) {
			L += BillArray.get(i).value;
		}
		return Math.round(L * 100.0) / 100.0;
	}
	
	public String getInfoString() {
		String s = "Gesamt :  ";
		s += this.getBillSum();
		s += "€\n\n\n";
		for (Reason e :
				Reason.values()) {
			s += e.toString() + ":\t" + getSumOfReason(e) + "\n";
		}
		
		return s;
	}
	
	private String getBillistString() {
		String s = "";
		for (int i = 0; i < BillArray.size(); i++) {
			s += Integer.toString(i) + ".    " + BillArray.get(i).toString() + " \n";
		}
		return s;
	}
	
	public double getSumOfReason(Reason Reason) {
		double p = 0;
		for (int i = 0; i < BillArray.size(); i++) {
			if (BillArray.get(i).reason == Reason) p += BillArray.get(i).value;
		}
		return p;
	}
	
}


