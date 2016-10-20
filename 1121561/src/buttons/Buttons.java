package buttons;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Buttons extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JButton buttonA;
	private JButton buttonB;
	
	private Container panel;

	public Buttons() {
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(500,200));
		
		panel = this.getContentPane();
		panel.setLayout(new GridBagLayout());
		
		buttonA = new JButton("A");
		buttonB = new JButton("B");
		
		GridBagConstraints c = new GridBagConstraints();
		Dimension d = new Dimension(10,15);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0;
		c.weighty = 0;
		panel.add(Box.createRigidArea(d), c);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 1;
		panel.add(Box.createRigidArea(d), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		panel.add(buttonA, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 1;
		panel.add(Box.createRigidArea(d), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		panel.add(buttonB, c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 1;
		panel.add(Box.createRigidArea(d), c);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0;
		c.weighty = 0;
		panel.add(Box.createRigidArea(d), c);
		
		this.pack();
	}
}
