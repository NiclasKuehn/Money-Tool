
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;  
import javax.swing.JPanel;
import javax.swing.JTextField;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MonthBillFX {
    JFrame frame;  
    JPanel panel = new JPanel();  
    JLabel argenda = new JLabel("ID   Betrag      Grund                       Bemerkung");
    JLabel IDLB = new JLabel("0"); 
    JTextField valueTF = new JTextField("");
    JComboBox<String> reasonCB = new JComboBox<>(Reason.getStringValue());
    JTextField remarkTF = new JTextField("");
    JButton pushButton = new JButton();  

    public MonthBillFX(Month Monat){
        frame = new JFrame(Monat.getName());
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

        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushButton();
            }
        });
       
        frame.add(panel);  
        frame.setSize(400, 600);  
        frame.setLocationRelativeTo(null);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  
    } 
    private void pushButton(){

    }
     
}
