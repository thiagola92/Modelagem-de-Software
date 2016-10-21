package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import buttons.ButtonsFrame;
import states.Resting;
import states.State;

public class Clock implements ActionListener {
	
	private ClocksControllers CC;
	
	private ButtonsFrame BF;
	private JButton A;
	private JButton B;
	
	private State S;
	
	public Clock() {
		this.CC = new ClocksControllers();
		
		this.BF = new ButtonsFrame();
		this.A = BF.getButtonA();
		this.B = BF.getButtonB();
		
		this.S = new Resting(CC);
		
		B.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (S.getState() == "ChangingHour") {
			CC.plusHour();
		} else if (S.getState() == "ChangingMin") {
			CC.plusMin();
		} else {
			System.out.println("Nao fazer nada");
		}
	}

}
