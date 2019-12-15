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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton[] buttons = new JButton[10];
	private JTextField sqlInputField = new JTextField(100);
	private static JTextArea anzeige = new JTextArea();
	JButton execute;
	
	private static DBInterface db;
	
	public static void main (String[] args) {
		
		GUI gui = new GUI();
		db = new DBAnbindung();
		
	}
	
	public GUI () {
		
		GridLayout layout = new GridLayout(3,1);
		this.setLayout(layout);
		this.setSize(new Dimension(1500,700));
		this.setMinimumSize(new Dimension(1500,600));
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
		execute = new JButton("Ausführen");

		sqlInput.setSize(new Dimension(100,700));
		sqlInput.setLayout(new GridBagLayout());
		sqlInput.setOpaque(false);
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 1; c.gridy = 0;
		sqlInput.add(sqlInputField, c);
		
		c.gridx = 2; c.gridy = 0;
		execute.addActionListener(this);
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
		
		if ((JButton) e.getSource() == execute) {
			
			anzeigen(db.execute(sqlInputField.getText()));
			
		}
		
		if ((JButton) e.getSource() == buttons[0]) {
			
			anzeigen((db.execute("SELECT dauer FROM telefonat WHERE id = 4;")));
			
		} else if ((JButton) e.getSource() == buttons[1]) {
			
			anzeigen((db.execute("SELECT id FROM chat WHERE gepinnt = false;")));
			
		} else if ((JButton) e.getSource() == buttons[2]) {
			
			anzeigen((db.execute("SELECT telefonnummer FROM account WHERE id = 3;")));
			
		} else if ((JButton) e.getSource() == buttons[3]) {
			
			anzeigen((db.execute("SELECT id FROM statistik WHERE bytesGesendet = 93004 AND account_id = 1;")));
			
		} else if ((JButton) e.getSource() == buttons[4]) {
			
			anzeigen((db.execute("SELECT groeßeByte FROM verlauf WHERE chat_id = 4 AND anzNachrichten = 5;")));
			
		} else if ((JButton) e.getSource() == buttons[5]) {
			
			anzeigen((db.execute("SELECT nutzername FROM profil WHERE account_id IN (SELECT verfasser FROM nachricht WHERE id = 3);")));
			
		} else if ((JButton) e.getSource() == buttons[6]) {
			
			anzeigen((db.execute("SELECT dateiPfad FROM datei WHERE id IN (SELECT profilbild FROM profil WHERE account_id = 2);")));
			
		} else if ((JButton) e.getSource() == buttons[7]) {
			
			anzeigen((db.execute("SELECT dateiPfad FROM datei WHERE id IN (SELECT profilbild FROM profil WHERE account_id = 2);")));
			
		} else if ((JButton) e.getSource() == buttons[8]) {
			
			anzeigen((db.execute("SELECT a.account_id FROM (SELECT account_id FROM accountInChat WHERE chat_id = 11) a INNER JOIN (SELECT account_id FROM profil WHERE profilbild IS NULL) b ON a.account_id = b.account_id;")));
			
		} else if ((JButton) e.getSource() == buttons[9]) {
			
			anzeigen((db.execute("SELECT dateiPfad FROM datei WHERE datei.id IN (SELECT datei_id FROM nachricht WHERE verlauf_id IN ( SELECT id FROM verlauf WHERE chat_id = 4 ) AND datei_id IS NOT NULL);")));
			
		}
		
	}
	
}
