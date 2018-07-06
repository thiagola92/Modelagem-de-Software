package buttons;

public class ButtonAThread extends Thread {

	private Buttons buttons;
	
	private boolean readyToChangeState = true;
	private boolean holdingButton = false;
	
	public ButtonAThread(Buttons buttons) {
		this.buttons = buttons;
	}
	
	public void run() {
		while(true) {
			boolean pressed = buttons.getButtonA().getModel().isPressed();
			boolean rollover = buttons.getButtonA().getModel().isRollover();
			
			synchronized (this) {
				if (readyToChangeState == true && pressed == true) {
					readyToChangeState = false;
					holdingButton = true;
					System.out.println("Botão pressionado");
					buttons.nextState();
				} else if (readyToChangeState == false && pressed == false) {
					readyToChangeState = true;
				} else if (pressed == false && rollover == true) {
					System.out.println("Botão solto");
					holdingButton = false;
				}
			}
		}
	}

	public boolean isHoldingButton() {
		return holdingButton;
	}

}
