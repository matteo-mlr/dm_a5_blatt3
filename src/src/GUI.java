package src;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Button Array in dem alle Buttons liegen, welche die vorgefertigten SQL-Statements ausfuehren
	 */
	private JButton[] buttons = new JButton[10];
	/**
	 * Textfeld in dem manuell SQL-Statements eingegeben werden koennen
	 */
	private JTextField sqlInputField = new JTextField(100);
	/**
	 * Textfeld in dem die Rueckgabe der SQL-Abfragen angezeigt wird
	 */
	private static JTextArea anzeige = new JTextArea();
	/**
	 * Button um den Inhalt in sqlInputField auszufuehren
	 */
	JButton execute;
	/**
	 * Interface zur Kommunikation mit der DBAnbindung
	 */
	private static iDatenzugriff db;
	
	private static GUI gui;
	
	public static void main (String[] args) {
		
		gui = new GUI();
		db = new DBAnbindung();
		db.DBoeffnen();
		
	}
	/**
	 * Konstruktor, setzt notwendige werte fuer GUI bzw JFrame
	 */
	public GUI () {
		
		GridLayout layout = new GridLayout(3,1);
		this.setLayout(layout);
		this.setSize(new Dimension(1500,700));
		this.setMinimumSize(new Dimension(1500,600));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				db.DBschliessen();
				gui.dispose();
				System.exit(0);
			}

			
		});
		
		add(ausgabeInit());
		add(sqlInputInit());
		add(buttonPanelInit());
		
		this.setVisible(true);
		
	}
	
	
	/**
	 * Erstellt Anzeigebereich fuer die Ergebnisse der SQL Abfragen
	 * @return JPanel, die Ausgabe der eingegebenen SQL Abfragen
	 */
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
	/**
	 * dem JPanel wird das sqlInputField hinzugefuegt um manuelle SQL-Eingaben zu ermoeglichen
	 * @return JPanel,  fuer die manuelle eingabe der SQL Statements
	 */
	private JPanel sqlInputInit () {
		
		JPanel sqlInput = new JPanel();
		execute = new JButton("Ausfuehren");

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
	/**
	 * Panel in dem die Buttons eingefuegt werden fuer die vorgefertigten SQL-Statements
	 * @return JPanel, in welchem die Buttons mit den vorgefertigten SQL-Statements liegen 
	 */
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
	/**
	 * fuegt die Ergebnisse der SQL Abfragen der Anzeige hinzu
	 */
	private void anzeigen (String text) {
		
		anzeige.setText(text);
		
	}
	/**
	 * wird aufgerufen wenn in der GUI ein Button betaetigt wird und definiert die Funktionsweise des Buttons
	 * @param e, ist das ActionEvent dass durch das betaetigen eines Buttons ausgeloest wird
	 */
	public void actionPerformed (ActionEvent e) {
		
		if ((JButton) e.getSource() == execute) {
			
			anzeigen(db.execute(sqlInputField.getText()));
			
		}
		
		if ((JButton) e.getSource() == buttons[0]) {
			
			String[] argumente = new String[1];
			argumente[0] = JOptionPane.showInputDialog("Telefontat-ID:");
			anzeigen((db.executePs("SELECT dauer FROM telefonat WHERE id = ?;", argumente)));
							
		} else if ((JButton) e.getSource() == buttons[1]) {
			
			String[] argumente = new String[1];
			String gepinnt = JOptionPane.showInputDialog("Gepinnt? (Ja / Nein):");
			if (gepinnt.equalsIgnoreCase("ja"))
				argumente[0] = "true";
			if (gepinnt.equalsIgnoreCase("nein"))
				argumente[0] = "false";
			anzeigen((db.executePs("SELECT id FROM chat WHERE gepinnt = ?;", argumente)));
			
		} else if ((JButton) e.getSource() == buttons[2]) {
			
			String[] argumente = new String[1];
			argumente[0] = JOptionPane.showInputDialog("Account-ID:");
			anzeigen((db.executePs("SELECT telefonnummer FROM account WHERE id = ?;", argumente)));
			
		} else if ((JButton) e.getSource() == buttons[3]) {
			
			String[] argumente = new String[2];
			argumente[0] = JOptionPane.showInputDialog("Gesendete Bytes:");
			argumente[1] = JOptionPane.showInputDialog("Account-ID:");
			anzeigen((db.executePs("SELECT id FROM statistik WHERE bytesGesendet = ? AND account_id = ?;", argumente)));
			
		} else if ((JButton) e.getSource() == buttons[4]) {
			
			String[] argumente = new String[2];
			argumente[0] = JOptionPane.showInputDialog("Chat-ID:");
			argumente[1] = JOptionPane.showInputDialog("Nachrichtenanzahl:");
			anzeigen((db.executePs("SELECT groeßeByte FROM verlauf WHERE chat_id = ? AND anzNachrichten = ?;", argumente)));
			
		} else if ((JButton) e.getSource() == buttons[5]) {
			
			String[] argumente = new String[1];
			argumente[0] = JOptionPane.showInputDialog("Nachricht-ID:");
			anzeigen((db.execute("SELECT nutzername FROM profil WHERE account_id IN (SELECT verfasser FROM nachricht WHERE id = 3);")));
			
		} else if ((JButton) e.getSource() == buttons[6]) {
			
			String[] argumente = new String[1];
			argumente[0] = JOptionPane.showInputDialog("Account-ID:");
			anzeigen((db.executePs("SELECT dateiPfad FROM datei WHERE id IN (SELECT profilbild FROM profil WHERE account_id = ?);", argumente)));
			
		} else if ((JButton) e.getSource() == buttons[7]) {
			
			String[] argumente = new String[1];
			argumente[0] = JOptionPane.showInputDialog("Account-ID:");
			anzeigen((db.executePs("SELECT dateiPfad FROM datei WHERE id IN (SELECT profilbild FROM profil WHERE account_id = ?);", argumente)));
			
		} else if ((JButton) e.getSource() == buttons[8]) {
			
			String[] argumente = new String[1];
			argumente[0] = JOptionPane.showInputDialog("Chat-ID:");

			String eingabe = JOptionPane.showInputDialog("Profilbild? (Ja / Nein):");
			
			if (eingabe.equalsIgnoreCase("ja"))
				anzeigen((db.executePs("SELECT a.account_id FROM (SELECT account_id FROM accountInChat WHERE chat_id = ?) a INNER JOIN (SELECT account_id FROM profil WHERE profilbild IS NOT NULL) b ON a.account_id = b.account_id;", argumente)));
			if (eingabe.equalsIgnoreCase("nein"))
				anzeigen((db.executePs("SELECT a.account_id FROM (SELECT account_id FROM accountInChat WHERE chat_id = ?) a INNER JOIN (SELECT account_id FROM profil WHERE profilbild IS NULL) b ON a.account_id = b.account_id;", argumente)));
			
			
		} else if ((JButton) e.getSource() == buttons[9]) {
			
			String[] argumente = new String[1];
			argumente[0] = JOptionPane.showInputDialog("Chat-ID:");
			anzeigen((db.executePs("SELECT dateiPfad FROM datei WHERE datei.id IN (SELECT datei_id FROM nachricht WHERE verlauf_id IN ( SELECT id FROM verlauf WHERE chat_id = ? ) AND datei_id IS NOT NULL);", argumente)));
			
		}
		else {
			System.out.println("Fehler!");
		}
	}
	
}
