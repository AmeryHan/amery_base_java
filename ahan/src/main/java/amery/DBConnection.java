package amery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@xxxx.com:1525:xxx";
			Connection c = DriverManager.getConnection(url, "xxx",
					"xxx");
			Statement stmt = c.createStatement();
			String www = "SELECT * FROM wave ";
			ResultSet rs = stmt.executeQuery(www);
			while (rs.next()) {
				System.out.println("name: " + rs.getString("name"));
			}
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
