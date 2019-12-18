package src;

public class Config {

	/**
	 * url für WhatsApp in phpmyadmin
	 */
	private final static String urlWhatsApp = "jdbc:mysql://localhost:3306/whatsapp";
	
	/**
	 * zugangsdaten für Datenbank "WhatsApp"
	 */
	private final static String zugangsdaten = "?user=root" + "&password=&serverTimezone=UTC";
	
	/**
	 * zusammengesetzte url für Datenbankzugriff
	 */
	public final static String url = Config.urlWhatsApp + Config.zugangsdaten;
	
}
