package Sign_In;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Connect.JDBC;
import Menu.Menu;
import Sign_Up.Sign_Up;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Sign_In extends JFrame implements ActionListener {

	Connection conn = null;

	PreparedStatement pstm;
	ResultSet rs;

	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtPass;

	public static void main(String[] args) {
		try {
			Sign_In signIn = new Sign_In();
			signIn.setLocationRelativeTo(null);
			signIn.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Sign_In() {
		this.setForeground(new Color(255, 0, 0));
		this.setBackground(new Color(0, 128, 0));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 439, 434);
		Image img = Toolkit.getDefaultToolkit().createImage(Menu.class.getResource("1.png"));
		this.setIconImage(img);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblSignIn = new JLabel("Sign In");
		lblSignIn.setForeground(new Color(255, 255, 255));
		lblSignIn.setFont(new Font("Cambria Math", Font.BOLD, 32));
		lblSignIn.setBounds(54, 49, 270, 41);
		contentPane.add(lblSignIn);

		JLabel lblName = new JLabel("Username : ");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(64, 114, 79, 22);
		contentPane.add(lblName);

		txtName = new JTextField();
		txtName.setBounds(153, 114, 208, 22);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPass.setForeground(new Color(255, 255, 255));
		lblPass.setBounds(64, 177, 79, 22);
		contentPane.add(lblPass);

		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(this);
		btnSignIn.setForeground(new Color(255, 0, 0));
		btnSignIn.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignIn.setBounds(54, 239, 102, 33);
		contentPane.add(btnSignIn);

		JLabel lblNewLabel_2 = new JLabel("If you haven't account , Please click here");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(58, 309, 239, 22);
		contentPane.add(lblNewLabel_2);

		txtPass = new JPasswordField();
		txtPass.setBounds(153, 177, 208, 22);
		contentPane.add(txtPass);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 354, 423, 41);
		contentPane.add(panel);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(this);
		btnSignUp.setForeground(new Color(0, 0, 0));
		btnSignUp.setBackground(new Color(192, 192, 192));
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSignUp.setBounds(307, 309, 89, 23);
		contentPane.add(btnSignUp);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setBounds(10, 11, 55, 14);
		contentPane.add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if (ac.equals("Back")) {
			Menu menu = new Menu();
			menu.setVisible(true);
			menu.setLocationRelativeTo(null);
			this.dispose();
		}
		if (ac.equals("Sign In")) {

			try {
				conn = JDBC.getConnection();

				String sql = "SELECT * FROM  USERS WHERE USERNAME = ? AND PASS = ? ";
				String name = txtName.getText();
				String pass = txtPass.getText();

				pstm = conn.prepareStatement(sql);

				pstm.setString(1, name);
				pstm.setString(2, pass);

				rs = pstm.executeQuery();

				if (rs.next()) {
					name = rs.getString(1);
					pass = rs.getString(2);

					JOptionPane.showMessageDialog(null, "Successfully !");
				} else if (name.equals("") && pass.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill username and password");
				} else if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill username");
				} else if (pass.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill password");
				} else {
					JOptionPane.showMessageDialog(null, "Wrong account or password !");
				}

				pstm.close();
				rs.close();
				conn.close();
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		if (ac.equals("Sign Up")) {
			Sign_Up signUp = new Sign_Up();
			signUp.setVisible(true);
			signUp.setLocationRelativeTo(null);
			this.dispose();
		}
	}
}
