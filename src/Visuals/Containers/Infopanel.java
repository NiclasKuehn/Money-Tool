package Visuals.Containers;

import Data.BillContainer;
import Visuals.Components.Diagram;
import Visuals.Components.InfoBox;
import Visuals.Components.PieChart;

import javax.swing.*;
import java.awt.*;

public class Infopanel<Type extends BillContainer> extends JPanel {
	private Type Element;
	private InfoBox InformationBox;
	private PieChart PieEinnahmen;
	private PieChart PieAusgaben;
	private Visuals.Components.Diagram Diagram;
	
	public Infopanel(Type element) {
		this.Element = element;
		init();
		setBounds();
		changeBounds();
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
	
	public void changeBounds() {
		int p1x = this.getWidth() / 100;
		int p20x = this.getWidth() / 5;
		int p50x = this.getWidth() / 2;
		int p100x = this.getWidth();
		
		int p1y = this.getHeight() / 100;
		int p5y = this.getHeight() / 20;
		int p20y = this.getHeight() / 5;
		int p50y = this.getHeight() / 2;
		int p100y = this.getHeight();
		
		InformationBox.setBounds(p1x, p5y, p50x, p50y);
		PieEinnahmen.setBounds(p1x * 2 + InformationBox.getWidth(), p5y, p100x - (p1x * 3 + InformationBox.getWidth()), InformationBox.getHeight() / 2);
		PieAusgaben.setBounds(p1x * 2 + InformationBox.getWidth(), PieEinnahmen.getHeight() + p5y + p1y, p100x - (p1x * 3 + InformationBox.getWidth()), InformationBox.getHeight() / 2 - p1y);
		Diagram.setBounds(InformationBox.getX(), InformationBox.getHeight() + InformationBox.getY() + p1y, p100x - 2 * p1x, p100y - InformationBox.getY() - InformationBox.getHeight() - p5y);
		
		PieEinnahmen.setBackground(Color.WHITE);
		PieAusgaben.setBackground(Color.WHITE);
		Diagram.setBackground(Color.WHITE);
	}
}
