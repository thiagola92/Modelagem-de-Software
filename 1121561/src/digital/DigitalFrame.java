package digital;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class DigitalFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private Container panel;
	
	private JLabel hour;
	private JLabel twoDots;
	private JLabel min;
	
	private boolean hourSelected;
	private boolean minSelected;
	
	public DigitalFrame() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		panel = this.getContentPane();
		panel.setLayout(new GridBagLayout());
		
		hour = new JLabel("15");
		twoDots = new JLabel(":");
		min = new JLabel("00");
		
		hourSelected = false;
		minSelected = false;
		
		Font f = new Font(hour.getFont().getName(), hour.getFont().getStyle(), hour.getFont().getSize()*10);
		hour.setFont(f);
		twoDots.setFont(f);
		min.setFont(f);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		panel.add(hour);
		c.gridx = 1;
		panel.add(twoDots);
		c.gridx = 2;
		panel.add(min);
		
		this.pack();
		
		(new DigitalBlink(this)).start();
	}
	
	public void setTime(int hour, int min) {
		this.setHour(hour);
		this.setMin(min);
	}

	private void setHour(int hour) {
		if (hour >= 10)
			this.hour.setText("" + hour);
		else
			this.hour.setText("0" + hour);
	}

	private void setMin(int min) {
		if (min >= 10)
			this.min.setText("" + min);
		else
			this.min.setText("0" + min);
	}
	
	public boolean isHourSelected() {
		return hourSelected;
	}

	public boolean isMinSelected() {
		return minSelected;
	}

	public void setHourSelected(boolean s) {
		this.hourSelected = s;
	}
	
	public void setMinSelected(boolean s) {
		this.minSelected = s;
	}
	
	public void hourColor(Color c) {
		this.hour.setForeground(c);
	}
	
	public void minColor(Color c) {
		this.min.setForeground(c);
	}
	
	public void setHourVisible(boolean b) {
		
		if (b == true) {
			hour.setForeground(this.getBackground());
		} else {
			hour.setForeground(Color.RED);
		}
		
	}
	
	public void setMinVisible(boolean b) {
		
		if (b == true) {
			this.min.setForeground(this.getBackground());
		} else {
			this.min.setForeground(Color.RED);
		}
		
	}

}
