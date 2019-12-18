package src;

public class Config {

	private final static String urlWhatsApp = "jdbc:mysql://localhost:3306/whatsapp";
	private final static String zugangsdaten = "?user=root" + "&password=&serverTimezone=UTC";
	public final static String url = Config.urlWhatsApp + Config.zugangsdaten;
	
}
