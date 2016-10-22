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
		while(true) {
			if (CC.isHourSelected() == true) {
				clock.hourColor(Color.RED);

				try {
					clock.setHourVisible(false);
					sleep(1000);
					clock.setHourVisible(true);
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else
				clock.hourColor(Color.BLACK);

			if (CC.isMinSelected() == true) {
				clock.minColor(Color.RED);

				try {
					clock.setMinVisible(false);
					sleep(1000);
					clock.setMinVisible(true);
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else
				clock.minColor(Color.BLACK);
		
		}
	}

}
