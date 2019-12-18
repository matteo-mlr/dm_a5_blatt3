package src;

public class Config {

	/**
	 * url f�r WhatsApp in phpmyadmin
	 */
	private final static String DATENBANK_URL = "jdbc:mysql://localhost:3306/whatsapp";
	
	/**
	 * zugangsdaten f�r Datenbank "WhatsApp"
	 */
	private final static String ZUGANGSDATEN = "?user=root" + "&password=&serverTimezone=UTC";
	
	/**
	 * zusammengesetzte url f�r Datenbankzugriff
	 */
	public final static String URL = Config.DATENBANK_URL + Config.ZUGANGSDATEN;
	
}
