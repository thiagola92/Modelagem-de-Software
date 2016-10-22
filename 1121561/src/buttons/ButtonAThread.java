package buttons;

import logic.Clock;

public class ButtonAThread extends Thread {

	private Clock clock;
	
	private boolean readyToChangeState = true;
	
	public ButtonAThread(Clock clock) {
		this.clock = clock;
	}
	
	public void run() {
		while(true) {
			boolean pressed = clock.getButtonA().getModel().isPressed();
			
			//System.out.println("ready = " + readyToChangeState + "\tPressed = " + pressed);
			
			synchronized (this) {
				if (readyToChangeState == true && pressed == true) {
					readyToChangeState = false;
					clock.nextState();
				} else if (readyToChangeState == false && pressed == false) {
					readyToChangeState = true;
				}
			}
		}
	}

}
