package states;

import logic.ClocksControllers;

public class Resting implements StateClock {

	@Override
	public StateClock active(ClocksControllers CC) {
		CC.setHourSelected(true);
		return (new ChangingHour());
	}

	@Override
	public String getState() {
		return "Resting";
	}

}
