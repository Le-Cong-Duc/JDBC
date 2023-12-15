package JDBC_8;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UpdateForm extends JFrame implements ActionListener {

	StudentManagement studentManager;

	JLabel lblName;
	JLabel lblMath;
	JLabel lblPhys;
	JLabel lblChem;

	JTextField txtName;
	JTextField txtMath;
	JTextField txtPhys;
	JTextField txtChem;

	JButton Ok;
	JButton Cancel;
	String id;

	public UpdateForm(String s, StudentManagement st, String i, String name, String math, String phys, String chem) {
		super(s);

		studentManager = st;

		Container panel = this.getContentPane();
		panel.setLayout(new GridLayout(6, 2));

		lblName = new JLabel("Name :");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtName = new JTextField();
		panel.add(lblName);
		panel.add(txtName);

		lblMath = new JLabel("Math :");
		lblMath.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtMath = new JTextField();
		panel.add(lblMath);
		panel.add(txtMath);

		lblPhys = new JLabel("Phys :");
		lblPhys.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtPhys = new JTextField();
		panel.add(lblPhys);
		panel.add(txtPhys);

		lblChem = new JLabel("Chem :");
		lblChem.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtChem = new JTextField();
		panel.add(lblChem);
		panel.add(txtChem);

		JLabel lbl = new JLabel("");
		JLabel lbl_1 = new JLabel("");
		panel.add(lbl);
		panel.add(lbl_1);

		Ok = new JButton("Ok");
		Ok.addActionListener(this);
		Cancel = new JButton("Cancel");
		Cancel.setForeground(Color.red);
		Cancel.addActionListener(this);
		panel.add(Ok);
		panel.add(Cancel);

		this.setSize(300, 200);
//		this.setLocation(250, 100);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		id = i;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Ok")) {
			Insert();
		} else if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}

	}

	public void Insert() {

		try {
			String name = txtName.getText();
			float math = Float.parseFloat(txtMath.getText());
			float phys = Float.parseFloat(txtPhys.getText());
			float chem = Float.parseFloat(txtChem.getText());
			String sql = "";
			if (this.getTitle().equals("Insert form")) {
				sql = "INSERT INTO STUDENTS (HOVATEN,MATH,PHYS,CHEM,AVER) " + "VALUES ( '" + name + "'," + math + ","
						+ phys + "," + chem + ", " + (math + phys + chem) / 3 + ")";
			} else {
				sql = "UPDATE STUDENTS "
						+ " SET NAMEE = '" + name + "', MATH=" + math + ", PHYS=" + phys + ", CHEM=" + chem +", AVER=" + (math + phys + chem) / 3 
						+ "  WHERE ID=" + id + "";
				
			}

			studentManager.stm.executeUpdate(sql);
			studentManager.reload();
			studentManager.model.fireTableDataChanged();

			this.dispose();

			this.dispose();
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "ERROR " + e.getMessage());
		}

	}

}
