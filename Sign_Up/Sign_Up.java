package Sign_Up;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Connect.JDBC;
import Menu.Menu;
import Sign_In.Sign_In;

public class Sign_Up extends JFrame implements ActionListener {

	Connection conn;
	PreparedStatement pstm;

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtPass;
	private JPasswordField txtPass_2;
	private JTextField txtName;

	public static void main(String[] args) {
		Sign_Up frame = new Sign_Up();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Sign_Up() {
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

		JLabel lblSignIn = new JLabel("Sign Up");
		lblSignIn.setForeground(new Color(255, 255, 255));
		lblSignIn.setFont(new Font("Cambria Math", Font.BOLD, 32));
		lblSignIn.setBounds(54, 36, 270, 41);
		contentPane.add(lblSignIn);

		JLabel lblName = new JLabel("Username : ");
		lblName.setForeground(new Color(255, 255, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName.setBounds(54, 135, 102, 22);
		contentPane.add(lblName);

		txtUserName = new JTextField();
		txtUserName.setBounds(188, 136, 208, 22);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		JLabel lblPass = new JLabel("Password :");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPass.setForeground(new Color(255, 255, 255));
		lblPass.setBounds(54, 181, 102, 22);
		contentPane.add(lblPass);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(this);
		btnSignUp.setForeground(new Color(255, 0, 0));
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSignUp.setBounds(54, 279, 102, 33);
		contentPane.add(btnSignUp);

		txtPass = new JPasswordField();
		txtPass.setBounds(188, 182, 208, 22);
		contentPane.add(txtPass);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 354, 423, 41);
		contentPane.add(panel);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(0, 0, 0));
		btnBack.setBounds(10, 11, 55, 14);
		contentPane.add(btnBack);

		JLabel lblPasswordAgain = new JLabel("Password again :");
		lblPasswordAgain.setForeground(Color.WHITE);
		lblPasswordAgain.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPasswordAgain.setBounds(54, 225, 134, 22);
		contentPane.add(lblPasswordAgain);

		txtPass_2 = new JPasswordField();
		txtPass_2.setBounds(188, 226, 208, 22);
		contentPane.add(txtPass_2);

		JLabel lblName_2 = new JLabel("Name : ");
		lblName_2.setForeground(Color.WHITE);
		lblName_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblName_2.setBounds(54, 88, 102, 22);
		contentPane.add(lblName_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(188, 87, 208, 22);
		contentPane.add(txtName);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if (ac.equals("Back")) {
			Menu menu = new Menu();
			menu.setVisible(true);
			this.dispose();
		}
		if (ac.equals("Sign Up")) {
			try {
				conn = JDBC.getConnection();

				String user = txtUserName.getText();
				String pass = txtPass.getText();
				String pass_2 = txtPass_2.getText();
				String name = txtName.getText();

				if (user.equals("") && pass.equals("") && name.equals("") && pass_2.equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill complete information !");
				} else if (user.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill username !");
				} else if (pass.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill password !");
				} else if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill name !");
				} else if (pass_2.equals("")) {
					JOptionPane.showMessageDialog(null, "please fill password again !");
				} else if (pass.equals(pass_2) == false) {
					JOptionPane.showMessageDialog(null, "Passwords are not same!");
					txtPass_2.setForeground(Color.red);
				} else {
					String sql = "INSERT INTO USERS VALUES ( ?,?,?)";
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, user);
					pstm.setString(2, pass);
					pstm.setString(3, name);

					pstm.execute();
					JOptionPane.showMessageDialog(null, "Sign Up Suscessfully");
					Sign_In signIn = new Sign_In();
					signIn.setVisible(true);
					signIn.setLocationRelativeTo(null);
					this.dispose();
				}

				conn.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

}
