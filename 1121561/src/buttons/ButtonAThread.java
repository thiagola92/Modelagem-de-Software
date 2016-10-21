package buttons;

import javax.swing.JButton;

public class ButtonAThread extends Thread {
	
	private JButton A;
	
	public ButtonAThread(JButton A) {
		this.A = A;
	}
	
	public void run() {
		while(true) {
			boolean armed = A.getModel().isArmed();
			boolean pressed = A.getModel().isPressed();
			boolean rollover = A.getModel().isRollover();
			System.out.println("Armed = " + armed);
			System.out.println("Pressed = " + pressed);
			System.out.println("Rollover = " + rollover);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
