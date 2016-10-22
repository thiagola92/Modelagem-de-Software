package states;

import logic.ClocksControllers;

public class ChangingHour implements StateClock {

	@Override
	public StateClock active(ClocksControllers CC) {
		System.out.println("Indo para o estado mudando minutos");
		CC.setHourSelected(false);
		CC.setMinSelected(true);
		return (new ChangingMin());
	}

	@Override
	public String getState() {
		return "ChangingHour";
	}

}