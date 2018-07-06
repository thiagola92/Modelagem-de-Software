package analog;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class AnalogPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;

	private BufferedImage clockImage;
	
	private Line2D.Double hour;
	private Line2D.Double min;
	private Line2D.Double sec;
	
	private Graphics graphics;

	public AnalogPanel(int width, int height, Graphics graphics) {
		
		this.width = width;
		this.height = height;
		this.graphics = graphics;
		
		try {
			clockImage = ImageIO.read(new File("img\\analogico.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		graphics.drawImage(clockImage, 0, 0, null);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2D = (Graphics2D) g;
		
		g.drawImage(clockImage, 0, 0, null);

		g2D.setStroke(new BasicStroke(3));
		
		g2D.setColor(Color.RED);
		if (hour != null) g2D.draw(hour);

		g2D.setColor(Color.BLUE);
		if (min != null) g2D.draw(min);

		g2D.setColor(Color.YELLOW);
		if (sec != null) g2D.draw(sec);
	}

	public void setHour(double pointX, double pointY) {
		this.hour = new Line2D.Double(this.width/2, this.height/2, pointX, pointY);
		paintComponent(graphics);
	}

	public void setMin(double pointX, double pointY) {
		this.min = new Line2D.Double(this.width/2, this.height/2, pointX, pointY);
		paintComponent(graphics);
	}

	public void setSec(double pointX, double pointY) {
		this.sec = new Line2D.Double(this.width/2, this.height/2, pointX, pointY);
		paintComponent(graphics);
	}
	
}
