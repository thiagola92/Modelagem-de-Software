package analog;

import javax.swing.JFrame;

public class AnalogFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int width = 450;
	private int height = 450;
	
	private double oneOf60 = 2*Math.PI/60;		// Andar um ponteiro de 60 elementos
	private double oneOf12 = 2*Math.PI/12;		// Andar um ponteiro de 12 elementos
	
	private AnalogPanel panel;
	
	public AnalogFrame() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(width, height);
		
		panel = new AnalogPanel(width, height, this.getGraphics());

		this.setTime(15, 0, 0);
		
		this.add(panel);
	}
	
	public void setTime(int hour, int min, int sec) {
		setSec(sec);
		setMin(min, sec);
		setHour(hour, min);
	}
	
	private void setHour(int hour, int min) {
		double length = height/4;
		double pointX = (width/2) + length*Math.cos((oneOf12*hour) - (oneOf12*3) + (oneOf12*min/60));
		double pointY = (height/2) + length*Math.sin((oneOf12*hour) - (oneOf12*3) + (oneOf12*min/60));
		
		panel.setHour(pointX, pointY);
	}
	
	private void setMin(int min, int sec) {
		double length = height/3;
		double pointX = (width/2) + length*Math.cos((oneOf60*min) - (oneOf60*15) + (oneOf60*sec/60));
		double pointY = (height/2) + length*Math.sin((oneOf60*min) - (oneOf60*15) + (oneOf60*sec/60));
		
		panel.setMin(pointX, pointY);
	}
	
	private void setSec(int sec) {
		double length = height/2;
		double pointX = (width/2) + length*Math.cos((oneOf60*sec) - (oneOf60*15));
		double pointY = (height/2) + length*Math.sin((oneOf60*sec) - (oneOf60*15));
		
		panel.setSec(pointX, pointY);
	}

}
