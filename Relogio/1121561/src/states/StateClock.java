package states;

import logic.ClocksControllers;

public interface StateClock {
	
	public StateClock active(ClocksControllers CC);
	
	public String getState();

}
