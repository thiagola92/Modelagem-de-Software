package logic;

import java.util.Observable;
import java.util.Observer;

import visual.AnalogFrame;

public class AnalogObserver implements Observer {

	private ClocksControllers CC;
	private AnalogFrame AC;
	
	public AnalogObserver(ClocksControllers cc) {
		CC = cc;
		AC = new AnalogFrame();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o == CC)
			AC.setTime(CC.getHour(), CC.getMin(), CC.getSec());
	}

}
