package src;

public interface iDatenzugriff {
	
	/**
	 * fï¿½hrt SQL-Statements auf Datenbank aus und gibt Ergebnisse zurï¿½ck
	 * @param statment, SQL-Statement
	 * @return String mit Ergebnissen der SQL-Abfrage
	 */
	String execute(String statment);
	
	/**
	 * fuehrt gegebene SQL-Abfrage auf Datenbank aus und liefert Ergebnis zurueck mit PreparedStatements
	 * @param statementuebergeben, SQL-Statement das ausgegeben werden soll
	 * @param argumente, die Werte welche man in das JOptionPane eingibt
	 * @return String mit Ergebnis der SQL-Abfrage
	 */
	String executePs(String statment, String[] argumente);
	/**
	 * oeffnet Verbindung zur DB mit angegebener URL
	 */
	void DBoeffnen();
	/**
	 * schließt verbindung zur DB wieder
	 */
	void DBschliessen();

}
