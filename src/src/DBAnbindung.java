package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DBAnbindung implements DBInterface {
	
	private Connection con = null;
	private Statement stt = null;
	
	private String url = "jdbc:mysql://localhost:3306/WhatsApp";

	public DBAnbindung () {
		
		
		
	}
	
	public void DBinit() {
		
		try {
			
			con = DriverManager.getConnection(url +"?user=root" + "&password=&serverTimezone=UTC");
			
			stt = con.createStatement();
			
		} catch (Exception ioe) {
			
			ioe.printStackTrace();
			
		}
		
	}
	
	public String execute (String statement) {
		
		StringBuilder sb = new StringBuilder(10000);
		
		try {
		
			ResultSet rs = stt.executeQuery(statement);
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
			
			for (String current : columnNames)
				sb.append(current + "\t");
			
			sb.append("\n\n");
			
			while (rs.next()) {
				
				for (int i = 0; i < columCount; i++) {
					
					switch (columnType[i]) {
					
					case -5: sb.append("" + rs.getInt(columnNames[i]) + "\t"); break;
					case 12: sb.append("" + rs.getString(columnNames[i]) + "\t"); break;
					case 92: sb.append("" + rs.getTime(columnNames[i]) + "\t"); break;
					case -4: sb.append("" + rs.getBlob(columnNames[i]) + "\t"); break;
					
					}
					
				}
				
				sb.append("\n");
			
			}
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return sb.toString();
		
	}
	
}
