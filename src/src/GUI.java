package src;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton[] buttons = new JButton[10];
	
	private JPanel ausgabe = new JPanel();
	private JPanel sqlInput = new JPanel();
	private JPanel buttonPanel = new JPanel();
	
	public GUI () {
		
		GridLayout layout = new GridLayout(1,3);
		this.setLayout(layout);
		
		buttonPanel.setLayout(new GridLayout(2,5));
		
		for (int i = 0; i < 10; i++) {
			
			JButton b = new JButton("" + (i+1));
			buttons[i] = b;
			buttonPanel.add(b);
			
		}
		
		add(ausgabe);
		add(sqlInput);
		add(buttonPanel);
		
	}
	
}
