package JDBC_8;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class JDBCUtil {

	public static Connection getInstance() {
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-K7HVB4T:1433;databaseName=STUDENT";
			String username = "sa";
			String password = "18112005";

			conn = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return conn;
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
