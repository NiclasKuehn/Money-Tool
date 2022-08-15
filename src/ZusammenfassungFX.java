import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ZusammenfassungFX {
    private Year MainYear;
    private JFrame frame;
    private JPanel panel;

    private JTextField JahrTF;

    private JButton JahrAuswahlB;
    private JButton WeiterB;
    private JButton ZurückB;

    public ZusammenfassungFX(int Year){
        loadYear(Year);
        setNames();
        setBounds();
        setText();
        Listeners();
        addShow();
    }


    private void setNames(){
        JahrAuswahlB = new JButton("go");
        WeiterB = new JButton("-->");
        ZurückB = new JButton("<--");
        frame = new JFrame("Übersicht   "+MainYear.year);
        panel = new JPanel(null);
        JahrTF = new JTextField(Integer.toString(MainYear.year));
    }
    private void setBounds(){
        frame.setSize(1000,1000);
        panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        int SchaltButtonGröße =80;
        ZurückB.setBounds(panel.getWidth()/2-SchaltButtonGröße-5,panel.getHeight()-ZurückB.getWidth()-95,SchaltButtonGröße,25);
        WeiterB.setBounds(panel.getWidth()/2+5,panel.getHeight()-WeiterB.getWidth()-95,SchaltButtonGröße,25);
    }
    private void setText(){

    }
    private void Listeners(){

    }
    private void addShow(){

        panel.add(ZurückB);
        panel.add(WeiterB);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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

}}
