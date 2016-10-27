package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import buttons.Buttons;
import states.Resting;
import states.StateClock;

public class Clock implements ActionListener {
	
	private ClocksControllers CC;
	private Buttons B;
	private StateClock S;
	
	public Clock() {
		this.CC = new ClocksControllers();
		this.B = new Buttons(this);
		this.S = new Resting();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (S.getState() == "ChangingHour" && B.isHoldingButton())
			CC.plusHour();
		else if (S.getState() == "ChangingMin" && B.isHoldingButton())
			CC.plusMin();
		else {
		}
	}
	
	public void nextState() {
		S = S.active(CC);
	}

}
