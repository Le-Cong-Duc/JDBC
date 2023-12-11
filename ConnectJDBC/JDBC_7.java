package ConnectJDBC;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JDBC_7 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		Vector vData = null;
		Vector vTitle = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://DESKTOP-K7HVB4T:1433;databaseName=STUDENT";
			String user = "sa";
			String pass = "18112005";

			Connection conn = DriverManager.getConnection(url, user, pass);

			Statement stm = conn.createStatement();

			ResultSet rs = stm.executeQuery("SELECT*FROM STUDENTS");
			ResultSetMetaData rsm = rs.getMetaData();

			int num_column = rsm.getColumnCount();

			vTitle = new Vector(num_column);
			for (int i = 1; i <= num_column; i++) {
				vTitle.add(rsm.getColumnLabel(i));
			}

			vData = new Vector(10, 10);

			while (rs.next()) {
				Vector row = new Vector(num_column);
				for (int i = 1; i <= num_column; i++) 
					row.add(rs.getString(i));
					vData.add(row);		
			}
			rs.close();
			stm.close();
			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
		
		JScrollPane table = new JScrollPane(new JTable(vData, vTitle));

		JFrame fame = new JFrame();
		fame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		fame.setLocation(200,100);
		fame.setSize(600, 480);
		fame.setContentPane(table);
		fame.show();
	}

}
