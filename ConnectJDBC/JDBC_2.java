package ConnectJDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBC_2 {
	public static void main(String[] args) {
		try {
			
			Connection conn = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-K7HVB4T:1433;databaseName=STUDENT";
			String username = "sa";
			String password = "18112005";
			
			conn = DriverManager.getConnection(url, username, password);
			
			Statement stm = conn.createStatement();
			
			String sql = "SELECT*FROM STUDENTS";
			
			ResultSet rs = stm.executeQuery(sql);
			
			ResultSetMetaData rsm = rs.getMetaData();
			
			int col_num  = rsm.getColumnCount();
			
			for(int i = 1 ; i <= col_num ; i++) {
				System.out.println(rsm.getColumnLabel(i)+"  ");
			}
			
			conn.close();
			
		}catch(Exception e) {
			System.out.println("Error");
		}
	}

}
