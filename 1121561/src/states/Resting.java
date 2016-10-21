package states;

import logic.ClocksControllers;

public class Resting implements State {
	
	private ClocksControllers CC;
	
	public Resting(ClocksControllers CC) {
		this.CC = CC;
	}

	@Override
	public State active() {
		
		CC.plusSec();
		
		return this;
	}

	@Override
	public String getState() {
		return "Resting";
	}

}
