package src;

public interface DBInterface {
	/**
	 * initiallisiert Datenbank
	 */
	void DBinit();
	
	/**
	 * führt SQL-Statements auf Datenbank aus und gibt Ergebnisse zurück
	 * @param statment, SQL-Statement
	 * @return String mit Ergebnissen der SQL-Abfrage
	 */
	String execute(String statment);

}
