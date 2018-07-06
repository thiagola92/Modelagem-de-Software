package buttons;

import javax.swing.JButton;

import logic.Clock;

public class Buttons {
	
	private Clock clock;
	
	private ButtonsFrame BF;
	private ButtonAThread BaT;
	
	public Buttons(Clock clock) {
		this.clock = clock;
		
		this.BF = new ButtonsFrame();
		this.BaT = new ButtonAThread(this);

		BF.getButtonB().addActionListener(clock);
		
		BaT.start();
	}
	
	public JButton getButtonA() {
		return BF.getButtonA();
	}

	public boolean isHoldingButton() {
		return BaT.isHoldingButton();
	}
	
	public void nextState() {
		clock.nextState();
	}
}
