package ConnectJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class JDBC_6 {
	public static void main(String[] args) {
		int id[] = { 39, 41, 42 };
		String[] names = { "Duc", "Duong", "Quan" };
		float[] math = { 7, 8, 7 };
		float[] phys = { 8, 9, 8 };
		float[] chem = { 6, 7, 9 };
		try {

			Connection conn = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-K7HVB4T:1433;databaseName=STUDENT";
			String username = "sa";
			String password = "18112005";

			conn = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO STUDENTS (id,Name,Math,Phys,Chem,Aver) VALUES (?,?,?,?,?,?)";
			PreparedStatement pstm = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);

			for (int i = 0; i < id.length; i++) {
				pstm.setInt(1, id[i]);
				pstm.setString(2, names[i]);
				pstm.setFloat(3, math[i]);
				pstm.setFloat(4, phys[i]);
				pstm.setFloat(5, chem[i]);
				pstm.setFloat(6, (math[i] + phys[i] + chem[i]) / 3);

				pstm.executeUpdate();
			}
			
			conn.commit();
			pstm = conn.prepareStatement("SELECT*FROM STUDENTS");
			ResultSet rs = pstm.executeQuery();

			ResultSetMetaData rsm = rs.getMetaData();
			int col_num = rsm.getColumnCount();
			for (int i = 1; i <= col_num; i++) {
				System.out.println(rsm.getColumnLabel(i) + " ");
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

			pstm.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
