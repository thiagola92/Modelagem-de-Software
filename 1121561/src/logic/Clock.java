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
	
	private ButtonAThread BaT;
	
	public Clock() {
		this.CC = new ClocksControllers();
		this.BF = new ButtonsFrame();
		this.S = new Resting();
		
		this.BaT = new ButtonAThread(this);
		
		BF.getButtonB().addActionListener(this);
		
		BaT.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (S.getState() == "ChangingHour" && BaT.isHoldingButton())
			CC.plusHour();
		else if (S.getState() == "ChangingMin" && BaT.isHoldingButton())
			CC.plusMin();
		else {
		}
	}
	
	public void nextState() {
		S = S.active(CC);
	}
	
	public JButton getButtonA() {
		return BF.getButtonA();
	}

}
