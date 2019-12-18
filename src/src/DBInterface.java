package src;

public interface DBInterface {
	/**
	 * initiallisiert Datenbank
	 */
	void DBinit();
	
	/**
	 * f�hrt SQL-Statements auf Datenbank aus und gibt Ergebnisse zur�ck
	 * @param statment, SQL-Statement
	 * @return String mit Ergebnissen der SQL-Abfrage
	 */
	String execute(String statment);
	
	String executePs(String statment, String[] argumente);

}
