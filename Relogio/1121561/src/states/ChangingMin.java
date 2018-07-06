package states;

import logic.ClocksControllers;

public class ChangingMin implements StateClock {

	@Override
	public StateClock active(ClocksControllers CC) {
		CC.setMinSelected(false);
		return (new Resting());
	}

	@Override
	public String getState() {
		return "ChangingMin";
	}

}
