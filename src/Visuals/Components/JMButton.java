package Visuals.Components;

import javax.swing.*;

public class JMButton extends JButton {
	public JMButton() {
		super();
	}
	
	private int M;
	
	public void setMonthID(int MID) {
		this.M = MID;
	}
	
	public int getMonthID() {
		return M;
	}
}
