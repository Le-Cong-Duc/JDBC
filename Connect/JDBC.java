package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class JDBC {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-K7HVB4T:1433;databaseName= QUANLI";
			String username = "sa";
			String password = "18112005";

			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return conn;
	}

}
