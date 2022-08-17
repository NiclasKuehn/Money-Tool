import javax.swing.*;
import java.awt.*;

public class Infopanel<Type extends BillContainer> extends JPanel {
	private Type Element;
	private JTextField JahrTF;
	private InfoBox InformationBox;
	private PieChart PieEinnahmen;
	private PieChart PieAusgaben;
	private Diagram Diagram;
	
	public Infopanel(Type element) {
		this.Element = element;
		init();
		setBounds();
		addShow();
	}
	
	private void init() {
		
		this.setLayout(null);
		JahrTF = new JTextField(Element.getName());
		InformationBox = new InfoBox(Element);
		PieAusgaben = new PieChart(Element, true);
		PieEinnahmen = new PieChart(Element, false);
		Diagram = new Diagram(Element);
		
	}
	
	private void setBounds() {
		
		InformationBox.setBounds(50, 40, 350, 270);
		PieEinnahmen.setBounds(450, 320, 350, 250);
		PieAusgaben.setBounds(50, 320, 350, 250);
		Diagram.setBounds(50, 580, 860, 320);
		
		PieEinnahmen.setBackground(Color.WHITE);
		PieAusgaben.setBackground(Color.WHITE);
		Diagram.setBackground(Color.WHITE);
	}
	
	private void addShow() {
		this.add(Diagram);
		this.add(PieAusgaben);
		this.add(PieEinnahmen);
		this.add(InformationBox);
		this.add(JahrTF);
		
	}
}
