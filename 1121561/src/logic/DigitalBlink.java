package logic;

import java.awt.Color;

import visual.DigitalFrame;

public class DigitalBlink extends Thread {
	
	DigitalFrame clock;
	
	public DigitalBlink(DigitalFrame clock) {
		this.clock = clock;
	}
	
	public void run() {
		while(true) {
			if (clock.isHourSelected() == true) {
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

			if (clock.isMinSelected() == true) {
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
