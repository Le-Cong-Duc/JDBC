package JDBC_8;

import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class StudentManagement extends JFrame implements ActionListener, MouseListener {

	Connection conn;
	Statement stm;
	ResultSet rs;

	Vector vData = new Vector();
	Vector vTitle = new Vector();

	JTable table;
	JScrollPane scroll;
	DefaultTableModel model;

	JButton Insert;
	JButton Delete;
	JButton Update;

	int selectRow = 0;

	// Tạo giao diện chính
	public StudentManagement(String s) {
		super(s);
		try {
			conn = JDBCUtil.getInstance();
			stm = conn.createStatement();

			JPanel panel = new JPanel();

			Insert = new JButton("Insert");
			Insert.addActionListener(this);
			Delete = new JButton("Delete");
			Delete.setForeground(Color.red);
			Delete.addActionListener(this);
			Update = new JButton("Update");
			Update.addActionListener(this);

			panel.add(Insert);
			panel.add(Delete);
			panel.add(Update);

			this.add(panel, "South");

			reload();

			table = new JTable();
			model = new DefaultTableModel(vData, vTitle);
			table.setModel(model);
			table.addMouseListener(this);

			scroll = new JScrollPane();
			scroll.setViewportView(table);

			this.getContentPane().add(scroll, "North");

			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("Student Manager");
			this.setLocation(300, 50);
			this.setSize(700, 600);
			this.setVisible(true);

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	// Gắn dữ liệu vào các nút bấm
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ac = e.getActionCommand();
		if (ac.equals("Delete")) {
			delete();
		}
		if (ac.equals("Insert")) {
			insert();
		}
		if (ac.equals("Update")) {
			update();
		}

	}

	// Gắn dữ liệu từ Database vào JTable
	public void reload() {
		try {
			vData.clear();
			vTitle.clear();
			String sql = "SELECT*FROM STUDENTS";
			ResultSet rs = stm.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();

			// tạo biến tượng trưng cho số cột của Database
			int num_colum = rsm.getColumnCount();

			// Gắn các cột đó vào vTitle
			for (int i = 1; i <= num_colum; i++) {
				vTitle.add(rsm.getColumnLabel(i));
			}

			while (rs.next()) {
				// Tạo 1 vector có kích thước các cột như Database
				Vector row = new Vector(num_colum);

				// gắn dữ liệu trong Database vào Vector row
				row.add(rs.getInt(1));
				row.add(rs.getString(2));
				row.add(rs.getFloat(3));
				row.add(rs.getFloat(4));
				row.add(rs.getFloat(5));
				row.add(rs.getFloat(6));

				// Gắn dữ liệu row vào vData
				vData.add(row);

			}
			rs.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	// Tạo phương thức xóa để gắn vào ActionListener
	public void delete() {

		if (selectRow >= 0) {
			int choice = JOptionPane.showConfirmDialog(null, "Xóa sinh viên ?", "Có", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				try {
					Vector st = (Vector) vData.elementAt(selectRow);

					String sql = "DELETE FROM STUDENTS WHERE ID = " + st.elementAt(0) + "";

					stm.executeUpdate(sql);

					vData.remove(selectRow);

					model.fireTableDataChanged();

				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Chọn hàng để xóa ");
		}

	}

	public void insert() {
		if (selectRow > 0) {
			new UpdateForm("Insert form", this, "", "", "0", "0", "0");
		} else {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng ");
		}
	}

	public void update() {

		// Vector st = (Vector) vData.elementAt(selectRow);  

//		if (selectRow > 0) {

		Vector st = (Vector) vData.elementAt(selectRow);
		
		new UpdateForm("Update form", this,"","","","","");
//		} else {
//			JOptionPane.showMessageDialog(null, "Please choice the row !");
//		}
	
	}

	// Cài đặt con trỏ chọn hàng
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		selectRow = table.getSelectedRow();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new StudentManagement("Student managerment");
	}
}
