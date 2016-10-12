package logic;

import java.util.Observable;
import java.util.Observer;

import visual.DigitalFrame;

public class DigitalObserver implements Observer {

	private ClocksControllers CC;
	private DigitalFrame DC;
	
	public DigitalObserver(ClocksControllers cc) {
		CC = cc;
		DC = new DigitalFrame();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o == CC)
			DC.setTime(CC.getHour(), CC.getMin());
	}

}
