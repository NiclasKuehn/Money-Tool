import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Month {
    private boolean used = false;
    private final ArrayList<BillClass>  BillArray= new ArrayList<BillClass>();
    public MonthName Name;

    public Month(MonthName Monat){
        this.Name=Monat;

    }
    public void addBill(BillClass Bill) {
        this.BillArray.add(Bill);
    }
    public void remBill(int index){
        this.BillArray.remove(index);
    }
    public String getName(){
        return Name.toString();
    }
    public ArrayList<BillClass> getBillArray() {
        return BillArray;
    }

    public void getEdited(){
        this.used = true;
    JFrame frame = new JFrame(Name.toString());;  
    JPanel panel = new JPanel();  
    JLabel argenda = new JLabel("ID   Betrag      Grund                       Bemerkung");
    JLabel IDLB = new JLabel("0"); 
    JTextField valueTF = new JTextField("");
    JComboBox<String> reasonCB = new JComboBox<>(Reason.getStringValue());
    JTextField remarkTF = new JTextField("");
    JButton pushButton = new JButton();
    JTextArea BillListL = new JTextArea();

    
    panel.setLayout(null);
    
    int offx =5;
    int offy =10;

    argenda.setBounds(5,25,300,25);
    panel.add(argenda);

    IDLB.setBounds(2+offx, 40+offy, 200, 25);
    panel.add(IDLB);

    valueTF.setBounds(20+offx,40+offy,40,25);
    panel.add(valueTF);

    reasonCB.setBounds(70+offx,40+offy,90,25);
    panel.add(reasonCB);

    remarkTF.setBounds(170+offx,40+offy,100,25);
    panel.add(remarkTF);

    pushButton.setText("next");  
    pushButton.setBounds(280+offx,40+offy,70,25);
    panel.add(pushButton);

    BillListL.setBounds(5,80,380,700);
    BillListL.setEditable(false);
    panel.add(BillListL);


    pushButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            BillArray.add(new BillClass(valueTF.getText(), reasonCB.getSelectedItem().toString(), remarkTF.getText()));
            String s = "";
            for (int i = 0; i < BillArray.size(); i++) {
                s+= Integer.toString(i)+".    "+ BillArray.get(i).toString() +" \n";
            }
            BillListL.setText(s);
        }
    });
   
    frame.add(panel);  
    frame.setSize(400, 600);  
    frame.setLocationRelativeTo(null);  
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            if (JOptionPane.showConfirmDialog(frame, 
                "Speichern und verlassen? ", "", 
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                used = false;
                System.exit(0);
            }
        }
    });
    frame.setVisible(true);  
} 
public boolean waitForClosedFrame(){
    while(used)
    {wait(1);}
    return true;
}

    
}


