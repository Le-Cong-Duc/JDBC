package ConnectJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBC_3 {
	public static void main(String[] args) {
		try {

			Connection conn = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-K7HVB4T:1433;databaseName=STUDENT";
			String username = "sa";
			String password = "18112005";

			conn = DriverManager.getConnection(url, username, password);

			Statement stm = conn.createStatement();

			String sql_1 = "INSERT INTO STUDENTS\r\n" + "VALUES(9,'Tai',8,5,9,(8+5+9)/3)";

			stm.executeUpdate(sql_1);
			System.out.println("da ket noi thanh cong " + sql_1);
			
			String sql = "SELECT*FROM STUDENTS";

			ResultSet rs = stm.executeQuery(sql);

			ResultSetMetaData rsm = rs.getMetaData();

			int col_num = rsm.getColumnCount();

			for (int i = 1; i <= col_num; i++) {

				System.out.println(rsm.getColumnLabel(i) + " \t");
				System.out.println("");
			}
			while (rs.next()) {

				System.out.println(rs.getInt(1) + " \t");
				System.out.println("");
				System.out.println(rs.getString(2) + "\t");
				System.out.println("");
				System.out.println(rs.getFloat(3) + "\t");
				System.out.println("");
				System.out.println(rs.getFloat(4) + "\t");
				System.out.println("");
				System.out.println(rs.getFloat(5) + "\t");
				System.out.println("");
				System.out.println(rs.getFloat(6) + "\t");

			}
			conn.close();

		} catch (Exception e) {
			System.out.println("Error");
		}
	}
}
