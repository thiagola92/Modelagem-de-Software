package buttons;

import logic.Clock;

public class ButtonAThread extends Thread {

	private Clock clock;
	
	private boolean readyToChangeState = true;
	private boolean holdingButton = false;
	
	public ButtonAThread(Clock clock) {
		this.clock = clock;
	}
	
	public void run() {
		while(true) {
			boolean pressed = clock.getButtonA().getModel().isPressed();
			boolean rollover = clock.getButtonA().getModel().isRollover();
			
			synchronized (this) {
				if (readyToChangeState == true && pressed == true) {
					readyToChangeState = false;
					holdingButton = true;
					clock.nextState();
				} else if (readyToChangeState == false && pressed == false) {
					readyToChangeState = true;
				} else if (pressed == false && rollover == true) {
					holdingButton = false;
				}
			}
		}
	}

	public boolean isHoldingButton() {
		return holdingButton;
	}

}
