import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


//import com.ibm.icu.text.DateFormat;
//import com.ibm.icu.text.SimpleDateFormat;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	/**
	 * Create the frame.
	 */
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(73, 122, 109, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(73, 170, 109, 20);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		textField.setBounds(213, 116, 220, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Exit");
		Image img11111111 = new ImageIcon(this.getClass().getResource("close-icon.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img11111111));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnNewButton.setBounds(341, 230, 121, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		Image img111111111 = new ImageIcon(this.getClass().getResource("login-icon.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img111111111));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql="select * from user where username=? and password=?";
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
				}
			}
		});
		
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnNewButton_1.setBounds(168, 230, 134, 44);
		contentPane.add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.BOLD, 15));
		passwordField.setBounds(213, 159, 220, 31);
		contentPane.add(passwordField);
	}
}
