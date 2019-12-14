package src;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton[] buttons = new JButton[10];
	private JTextField sqlInputField = new JTextField(50);
	private JTextArea anzeige = new JTextArea();
	
	private static DBInterface db;
	
	public static void main (String[] args) {
		
		GUI gui = new GUI();
		db = new DBAnbindung();
		db.DBinit();
		
	}
	
	public GUI () {
		
		GridLayout layout = new GridLayout(3,1);
		this.setLayout(layout);
		this.setSize(new Dimension(900,600));
		this.setMinimumSize(new Dimension(800,500));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		add(ausgabeInit());
		add(sqlInputInit());
		add(buttonPanelInit());
		
		this.setVisible(true);
		
	}
	
	private JPanel ausgabeInit () {
		
		JPanel ausgabe = new JPanel();
		anzeige = new JTextArea();
		
		//JScrollPane scroll = new JScrollPane(anzeige);
		
		ausgabe.setLayout(new GridLayout(1,1));
		ausgabe.setOpaque(false);
//		ueberschrift.setVisible(true);
//		ueberschrift.setOpaque(false);
//		anzeige.setVisible(true);
//		anzeige.setOpaque(false);
		ausgabe.add(anzeige);
		
		return ausgabe; 
		
	}
	
	private JPanel sqlInputInit () {
		
		JPanel sqlInput = new JPanel();
		JButton execute = new JButton("Ausführen");

		sqlInput.setSize(new Dimension(100,500));
		sqlInput.setLayout(new GridBagLayout());
		sqlInput.setOpaque(false);
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1; c.gridy = 0;
		sqlInput.add(sqlInputField, c);
		
		c.gridx = 2; c.gridy = 0;
		sqlInput.add(execute, c);
		
		sqlInput.setOpaque(false);
		
		return sqlInput;
		
	}
	
	private JPanel buttonPanelInit () {
		
		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new GridLayout(2,5));
		
		for (int i = 0; i < 10; i++) {
			
			JButton b = new JButton("" + (i+1));
			b.addActionListener(this);
			buttons[i] = b;
			buttonPanel.add(b);
			
		}
		
		buttonPanel.setOpaque(false);
		
		return buttonPanel; 
		
	}
	
	private void anzeigen (String text) {
		
		anzeige.setText(text);
		
	}
	
	public void actionPerformed (ActionEvent e) {
		
		if ((JButton) e.getSource() == buttons[0]) {
			
			anzeigen((db.execute("SELECT * FROM profil;")));
			
		} else if ((JButton) e.getSource() == buttons[1]) {
			
			anzeigen((db.execute("SELECT dauer FROM telefonat WHERE id = 4;")));
			
		}
		
	}
	
}
