package Menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.TitledBorder;

import Sign_In.Sign_In;
import Sign_Up.Sign_Up;

import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class Menu extends JFrame implements ActionListener {

	private JPanel background_1;
	private JPanel background_2;
	private JLabel lbl;
	JButton btnSignIn;
	JButton btnSignUp;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		Image img = Toolkit.getDefaultToolkit().createImage(Menu.class.getResource("1.png"));
		this.setIconImage(img);
		background_1 = new JPanel();
		background_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		background_1.setLayout(null);
		background_1.setBackground(new Color(0, 128, 64));
		this.setContentPane(background_1);

		background_2 = new JPanel();
		background_2.setBackground(new Color(255, 255, 255));
		background_2.setLayout(null);
		background_2.setBounds(0, 25, 434, 201);
		background_1.add(background_2);

		lbl = new JLabel("USER MANAGEMENT");
		lbl.setForeground(new Color(255, 0, 0));
		lbl.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Sitka Banner", Font.BOLD, 17));
		lbl.setBounds(109, 31, 220, 51);
		background_2.add(lbl);

		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(this);
		ImageIcon imgg = new ImageIcon(Toolkit.getDefaultToolkit().createImage(Menu.class.getResource("icon.jpg")));
		btnSignIn.setIcon(imgg);
		btnSignIn.setBounds(164, 93, 98, 31);
		background_2.add(btnSignIn);

		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(this);
		btnSignUp.setBounds(164, 137, 98, 31);
		background_2.add(btnSignUp);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if (ac.equals("Sign In")) {
			Sign_In signIn = new Sign_In();
			signIn.setLocationRelativeTo(null);
			signIn.setVisible(true);
			this.dispose();
		}
		if (ac.equals("Sign Up")) {
			Sign_Up signUp = new Sign_Up();
			signUp.setLocationRelativeTo(null);
			signUp.setVisible(true);
			this.dispose();
		}
	}
}
