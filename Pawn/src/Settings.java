import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class Settings extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement pst,pst1;
	ResultSet rs;
	
	/**
	 * Create the frame.
	 */
	public Settings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 11, 268, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Change User Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(56, 11, 157, 25);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Old User name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 71, 93, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New User name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 105, 107, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Retype User name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 143, 131, 17);
		panel.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(151, 68, 107, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(151, 102, 107, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(151, 140, 107, 20);
		panel.add(textField_2);
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home h=new Home();
				h.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(10, 193, 89, 35);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Change");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "fill the fields inside the panel");
				}
				else {
					String sql="select * from user where username=?";
					try{
						con = DriverManager.getConnection("jdbc:mysql://localhost/pawn","root","");
						pst=con.prepareStatement(sql);
						pst.setString(1, textField.getText());
						rs=pst.executeQuery();
						if(rs.next())
						{		
							if(textField_1.getText().equals(textField_2.getText())) {
								JOptionPane.showMessageDialog(null,"Username Changed Succesfully");
								String user=textField_1.getText();
								
								pst1 = con.prepareStatement("update user set username=? where id =?");
								pst1.setString(1, user);
								pst1.setDouble(2, 1);
								pst1.executeUpdate();	
								
								textField.setText("");
								textField_1.setText("");
								textField_2.setText("");
							}
							else {
								JOptionPane.showMessageDialog(null,"New Usernames not matched");
							}
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Old user name is wrong");
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null,ex);
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(169, 193, 89, 35);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(288, 11, 268, 239);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblChangePassword.setBounds(56, 11, 157, 25);
		panel_1.add(lblChangePassword);
		
		JLabel lblNewLabel_1_1 = new JLabel("Old Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 71, 93, 14);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("New Password");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 105, 107, 14);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Retype Password");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(10, 143, 131, 17);
		panel_1.add(lblNewLabel_3_1);
		
		JButton btnNewButton_2 = new JButton("Cancel");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home h=new Home();
				h.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_2.setBounds(10, 193, 89, 35);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("Change");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(passwordField.getText().equals("")||passwordField_1.getText().equals("")||passwordField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "fill the fields inside the panel");
				}
				else {
					String sql="select * from user where password=?";
					try{
						con = DriverManager.getConnection("jdbc:mysql://localhost/pawn","root","");
						pst=con.prepareStatement(sql);
						pst.setString(1, passwordField.getText());
						rs=pst.executeQuery();
						if(rs.next())
						{		
							if(passwordField_1.getText().equals(passwordField_2.getText())) {
								JOptionPane.showMessageDialog(null,"Password Changed Succesfully");
								String pass=passwordField_1.getText();
								
								pst1 = con.prepareStatement("update user set password=? where id =?");
								pst1.setString(1, pass);
								pst1.setDouble(2, 1);
								pst1.executeUpdate();	
								
								passwordField.setText("");
								passwordField_1.setText("");
								passwordField_2.setText("");
							}
							else {
								JOptionPane.showMessageDialog(null,"New Passwords not matched");
							}
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Old Password is wrong");
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null,ex);
					}
				}
				/*String sql="select * from user where username=? and password=?";
				try{
					con = DriverManager.getConnection("jdbc:mysql://localhost/pawn","root","");
					pst=con.prepareStatement(sql);
					pst.setString(1, textField.getText());
					pst.setString(2,passwordField.getText());
					rs=pst.executeQuery();
					if(rs.next())
					{		
					JOptionPane.showMessageDialog(null,"Login Succesful");
						dispose();
						Home h= new Home();
						h.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "username and password do not matched");
						textField.setText("");
						passwordField.setText("");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,ex);
				}*/
			}
		});
		btnNewButton_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(169, 193, 89, 35);
		panel_1.add(btnNewButton_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(151, 70, 107, 20);
		panel_1.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField_1.setBounds(151, 104, 107, 20);
		panel_1.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField_2.setBounds(151, 141, 107, 20);
		panel_1.add(passwordField_2);
		
		JButton btnNewButton_1_2 = new JButton("Home");
		Image img = new ImageIcon(this.getClass().getResource("home-icon.png")).getImage();
		btnNewButton_1_2.setIcon(new ImageIcon(img));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home h=new Home();
				h.setVisible(true);
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_2.setBounds(324, 284, 145, 48);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("Exit");
		Image img1 = new ImageIcon(this.getClass().getResource("close-icon.png")).getImage();
		btnNewButton_1_3.setIcon(new ImageIcon(img1));
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_3.setBounds(71, 284, 145, 46);
		contentPane.add(btnNewButton_1_3);
	}
}
