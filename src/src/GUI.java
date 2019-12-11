package src;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton[] buttons = new JButton[10];
	private JTextField sqlInputField = new JTextField(50);
	private JButton execute = new JButton("Ausführen");
	
	private JPanel ausgabe = new JPanel();
	private JPanel sqlInput = new JPanel();
	private JPanel buttonPanel = new JPanel();
	
	public static void main (String[] args) {
		
		GUI gui = new GUI();
		
	}
	
	public GUI () {
		
		GridLayout layout = new GridLayout(3,1);
		this.setLayout(layout);
		this.setVisible(true);
		this.setSize(new Dimension(500,500));
		
		buttonPanel.setLayout(new GridLayout(2,5));
		
		for (int i = 0; i < 10; i++) {
			
			JButton b = new JButton("" + (i+1));
			buttons[i] = b;
			buttonPanel.add(b);
			
		}
		
		JLabel anzeige = new JLabel();
		ausgabe.add(anzeige);
		
		sqlInput.setSize(new Dimension(100,500));
		sqlInput.setLayout(new GridLayout(1,2));
		sqlInput.add(sqlInputField);
		sqlInput.add(execute);
		
		add(ausgabe);
		add(sqlInput);
		add(buttonPanel);
		
	}
	
}
