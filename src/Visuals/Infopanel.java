package Visuals;

import Data.BillContainer;

import javax.swing.*;
import java.awt.*;

public class Infopanel<Type extends BillContainer> extends JPanel {
	private Type Element;
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
		InformationBox = new InfoBox(Element);
		PieAusgaben = new PieChart(Element, true);
		PieEinnahmen = new PieChart(Element, false);
		Diagram = new Diagram(Element);
		
	}
	
	private void setBounds() {
		
		InformationBox.setBounds(20, 50, 670, 510);
		PieEinnahmen.setBounds(700, 50, 460, 250);
		PieAusgaben.setBounds(700, 310, 460, 250);
		Diagram.setBounds(20, 570, 1140, 320);
		
		PieEinnahmen.setBackground(Color.WHITE);
		PieAusgaben.setBackground(Color.WHITE);
		Diagram.setBackground(Color.WHITE);
	}
	
	private void addShow() {
		this.add(Diagram);
		this.add(PieAusgaben);
		this.add(PieEinnahmen);
		this.add(InformationBox);
	}
}
