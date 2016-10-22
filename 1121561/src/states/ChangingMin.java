package states;

import logic.ClocksControllers;

public class ChangingMin implements StateClock {

	@Override
	public StateClock active(ClocksControllers CC) {
		System.out.println("Indo para o estado descansando");
		CC.setMinSelected(false);
		return (new Resting());
	}

	@Override
	public String getState() {
		return "ChangingMin";
	}

}
