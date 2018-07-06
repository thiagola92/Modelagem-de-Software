package digital;

import java.awt.Color;

import logic.ClocksControllers;

public class DigitalBlink extends Thread {
	
	private DigitalFrame clock;
	private ClocksControllers CC;
	
	public DigitalBlink(DigitalFrame clock, ClocksControllers CC) {
		this.clock = clock;
		this.CC = CC;
	}
	
	public void run() {
		
		int sleepTime = 250;
		
		while(true) {
			if (CC.isHourSelected() == true) {
				clock.hourColor(Color.RED);
				clock.minColor(Color.BLACK);

				try {
					clock.setHourVisible(false);
					sleep(sleepTime);
					clock.setHourVisible(true);
					sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else
				clock.hourColor(Color.BLACK);

			if (CC.isMinSelected() == true) {
				clock.minColor(Color.RED);
				clock.hourColor(Color.BLACK);

				try {
					clock.setMinVisible(false);
					sleep(sleepTime);
					clock.setMinVisible(true);
					sleep(sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else
				clock.minColor(Color.BLACK);
		
		}
	}

}
