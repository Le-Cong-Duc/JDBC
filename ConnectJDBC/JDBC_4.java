package ConnectJDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class JDBC_4 {
	public static void main(String[] args) {
		try {

			Connection conn = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-K7HVB4T:1433;databaseName=STUDENT";
			String username = "sa";
			String password = "18112005";

			conn = DriverManager.getConnection(url, username, password);

			DatabaseMetaData dbm = conn.getMetaData();

			ResultSet rs = dbm.getTables(null, null, "PERSONS", null);

			while (rs.next()) {
				System.out.println(rs.getString(3));
			}
			
			conn.close();

		} catch (Exception e) {
			System.out.println("Error");
		}
	}
}
