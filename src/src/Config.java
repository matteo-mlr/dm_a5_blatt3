package src;

public class Config {

	/**
	 * url f�r WhatsApp in phpmyadmin
	 */
	private final static String urlWhatsApp = "jdbc:mysql://localhost:3306/whatsapp";
	
	/**
	 * zugangsdaten f�r Datenbank "WhatsApp"
	 */
	private final static String zugangsdaten = "?user=root" + "&password=&serverTimezone=UTC";
	
	/**
	 * zusammengesetzte url f�r Datenbankzugriff
	 */
	public final static String url = Config.urlWhatsApp + Config.zugangsdaten;
	
}
