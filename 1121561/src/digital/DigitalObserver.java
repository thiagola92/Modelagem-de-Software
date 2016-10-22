package digital;

import java.util.Observable;
import java.util.Observer;

import logic.ClocksControllers;

public class DigitalObserver implements Observer {

	private ClocksControllers CC;
	private DigitalFrame DC;
	
	public DigitalObserver(ClocksControllers cc) {
		CC = cc;
		DC = new DigitalFrame(cc);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o == CC)
			DC.setTime(CC.getHour(), CC.getMin());
	}

}
