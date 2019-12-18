package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DBAnbindung implements DBInterface {
	
	/**
	 * eine Verbindung zur Datenbank
	 */
	private Connection con = null;
	
	/**
	 * sp�tere SQL-Abfrage
	 */
	private PreparedStatement ps = null;
	
	/**
	 * String mit url und Zugangsdaten zur Datenbank
	 */
	private String url;

	/**
	 * Konstruktor, initiallisiert DB Anbindung
	 */
	public DBAnbindung () {
		
		url = "jdbc:mysql://localhost:3306/whatsapp";
		DBinit();
		
	}
	/**
	 * stellt Verbindung zur DB her
	 */
	public void DBinit() {
		
		try {
			
			con = DriverManager.getConnection(url +"?user=root" + "&password=&serverTimezone=UTC");
			
//			stt = con.createStatement();
			
		} catch (Exception ioe) {
			con = null;
			ioe.printStackTrace();
			
		}
		
	}
	/**
	 * f�hrt gegebene SQL-Abfrage auf Datenbank aus und liefert Ergebnis zur�ck
	 * @param statement, SQL-Statement das ausgegeben werden soll
	 * @return String mit Ergebnis der SQL-Abfrage
	 */
	public String execute (String statement) {
		
		StringBuilder sb = new StringBuilder(10000);
		
		try {
			ps = con.prepareStatement(statement);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columCount = rsmd.getColumnCount();
			int[] columnType = new int[columCount];
			String[] columnNames = new String[columCount];
			
			for (int i = 0; i < columCount; i++) {
				
				columnType[i] = rsmd.getColumnType(i+1);
				
			}
			
			for (int i = 0; i < columCount; i++) {
				
				columnNames[i] = rsmd.getColumnName(i+1);
				
			}
			
			for (int current : columnType)
				System.out.println(current);
			
			for (String current : columnNames)
				sb.append(current + "\t");
			
			sb.append("\n\n");
			
			while (rs.next()) {
				
				for (int i = 0; i < columCount; i++) {
										
//					switch (columnType[i]) {
//					
//					case -5: sb.append("" + rs.getInt(columnNames[i])); break;
//					case 12: sb.append("" + rs.getString(columnNames[i])); break;
//					case 92: sb.append("" + rs.getTime(columnNames[i])); break;
//					case -4: sb.append("" + rs.getBlob(columnNames[i])); break;
//					case -7: sb.append("" + (rs.getBoolean(columnNames[i]) ? "TRUE" : "FALSE")); break;
//					
//		 			}
					
					sb.append(rs.getString(columnNames[i])); 
					sb.append("\t");
					
				}
				
				sb.append("\n");
			
			}
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Done");
		return sb.toString();
		
	}
	
	public String executePs (String statementÜbergeben, String[] argumente) {
		
		String statement = statementÜbergeben;
		
		StringBuilder sb = new StringBuilder(10000);
		
		int counter = 0;
		
		try {
			
			ps = con.prepareStatement(statement);
			
			for (int i = 0; i < argumente.length; i++) {
				
				ps.setString((i+1), argumente[i]);
				
			}
			
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columCount = rsmd.getColumnCount();
			int[] columnType = new int[columCount];
			String[] columnNames = new String[columCount];
			
			for (int i = 0; i < columCount; i++) {
				
				columnType[i] = rsmd.getColumnType(i+1);
				
			}
			
			for (int i = 0; i < columCount; i++) {
				
				columnNames[i] = rsmd.getColumnName(i+1);
				
			}
			
			for (int current : columnType)
				System.out.println(current);
			
			for (String current : columnNames)
				sb.append(current + "\t");
			
			sb.append("\n\n");
			
			while (rs.next()) {
				
				counter++;
				for (int i = 0; i < columCount; i++) {
										
//				switch (columnType[i]) {
//					
//					case -5: sb.append("" + rs.getInt(columnNames[i])); break;
//					case 12: sb.append("" + rs.getString(columnNames[i])); break;
//					case 92: sb.append("" + rs.getTime(columnNames[i])); break;
//					case -4: sb.append("" + rs.getBlob(columnNames[i])); break;
//					case -7: sb.append("" + (rs.getBoolean(columnNames[i]) ? "TRUE" : "FALSE")); break;
//					
//		 			}
					
					sb.append(rs.getString(columnNames[i])); 
					sb.append("\t");
					
				}
				
				sb.append("\n");
			
			}
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		if (counter == 0)
			sb.append("Keine Suchergebnisse.");
		
		return sb.toString();
		
	}
	
}
