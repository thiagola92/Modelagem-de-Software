package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import buttons.ButtonAThread;
import buttons.ButtonsFrame;
import states.Resting;
import states.StateClock;

public class Clock implements ActionListener {
	
	private ClocksControllers CC;
	private ButtonsFrame BF;
	private StateClock S;
	
	public Clock() {
		this.CC = new ClocksControllers();
		this.BF = new ButtonsFrame();
		this.S = new Resting();
		
		BF.getButtonB().addActionListener(this);
		
		(new ButtonAThread(this)).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(S.getState());
		if (S.getState() == "ChangingHour")
			CC.plusHour();
		else if (S.getState() == "ChangingMin")
			CC.plusMin();
		else {
			System.out.println("Nao fazer nada");
		}
	}
	
	public void nextState() {
		S = S.active(CC);
	}
	
	public JButton getButtonA() {
		return BF.getButtonA();
	}

}
