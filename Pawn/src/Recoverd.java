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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import com.ibm.icu.text.DateFormat;
//import com.ibm.icu.text.SimpleDateFormat;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Recoverd extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recoverd frame = new Recoverd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void clearFields() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
	}
	
	Connection con;
	PreparedStatement pst,pst1;
	ResultSet rs,rs1;
	
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost/pawn","root","");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	        	JOptionPane.showMessageDialog(null,ex);
	        }
	        catch (SQLException ex) 
	        {
	        	JOptionPane.showMessageDialog(null,ex);
	        }
 
	    }

	/**
	 * Create the frame.
	 */
	public Recoverd() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
			  	try 
		    	{
		    		con = DriverManager.getConnection("jdbc:mysql://localhost/pawn","root","");
				    pst = con.prepareStatement("select * from pawning where pending=0");
				    rs = pst.executeQuery();
				    while (rs.next()) {
				    	//Retrieving details from the database and storing it in the String variables
		                String sheet = rs.getString("sheet_no");
		                String name = rs.getString("name");
		                String nic = rs.getString("nic_no");
		                String dateofpawning = rs.getString("pawning_date");
		                String date_of_recover = rs.getString("date_of_recover");
		                double totalamount = rs.getDouble("totalamount");
		                
		                DefaultTableModel model= (DefaultTableModel)table_1.getModel();
						model.addRow(new Object[] {sheet,name,nic,dateofpawning,date_of_recover,totalamount});	
		            }
		    	} 
		    	catch (SQLException ex) 
		    	 {
		    		JOptionPane.showMessageDialog(null,ex);
			  } 				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 652);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 864, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(10, 11, 844, 43);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sheet No");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 12, 84, 21);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(140, 12, 145, 20);
		panel_1.add(textField);
		
		JButton btnNewButton = new JButton("Search");
		Image img11111111111 = new ImageIcon(this.getClass().getResource("search-icon.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img11111111111));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				int j=model.getRowCount();
				for(int i=j-1;i>=0;i--) {
					model.removeRow(i);
				}
				
				 try {			          
			            String sheet_no = textField.getText();
			            con = DriverManager.getConnection("jdbc:mysql://localhost/pawn","root","");
		                pst = con.prepareStatement("select * from pawning where sheet_no = ? and pending=0");
		                pst.setString(1, sheet_no);
		                rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			            	String name = rs.getString("name");
			                String nic = rs.getString("nic_no");
			                String dateofpawning = rs.getString("pawning_date");
			                String totalvalue = rs.getString("total_cost");
			                String totalamount = rs.getString("totalamount");
			                
			                textField_1.setText(name);
			                textField_2.setText(nic);
			                textField_3.setText(dateofpawning);
			                textField_4.setText(totalvalue);
			                textField_5.setText(totalamount);
			            	try 
					    	{
			            		pst1 = con.prepareStatement("select * from additional where sheet_no=?");
			            		pst1.setString(1, sheet_no);
							    rs1 = pst1.executeQuery();
							    while (rs1.next()) {
							    	String date = rs1.getString("date");
					                double amount = rs1.getDouble("amount");
									model.addRow(new Object[] {date,amount});	
									
					            }
					    	} 
					    	catch (SQLException ex) 
					    	 {
					    		JOptionPane.showMessageDialog(null,ex);
						  } 
			                
			            }   
			            else
			            {
			            	if(textField.getText().equals("")) {
			            		JOptionPane.showMessageDialog(null,"fill the field and search");
			            	}
			            	else {
			            		JOptionPane.showMessageDialog(null,"no any sheet numbers regarding your input");
			            		clearFields();
			            	}
			            }
			            
			        } 
				
				 catch (SQLException ex) {
					 	JOptionPane.showMessageDialog(null,ex);
			        }
	
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnNewButton.setBounds(352, 0, 120, 43);
		panel_1.add(btnNewButton);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblName.setBounds(10, 79, 84, 21);
		panel.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(140, 79, 145, 20);
		panel.add(textField_1);
		
		JLabel lblNicNo = new JLabel("NIC No");
		lblNicNo.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNicNo.setBounds(10, 118, 84, 21);
		panel.add(lblNicNo);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(140, 118, 145, 20);
		panel.add(textField_2);
		
		JLabel lblDateOfPawning = new JLabel("Date of Pawning");
		lblDateOfPawning.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblDateOfPawning.setBounds(10, 158, 120, 21);
		panel.add(lblDateOfPawning);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(140, 158, 145, 20);
		panel.add(textField_3);
		
		JLabel lblTotalValue = new JLabel("Date of Recover");
		lblTotalValue.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTotalValue.setBounds(10, 195, 109, 21);
		panel.add(lblTotalValue);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(140, 195, 145, 20);
		panel.add(textField_4);
		
		JLabel lblGivenAmount = new JLabel("Given Amount");
		lblGivenAmount.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblGivenAmount.setBounds(346, 195, 120, 21);
		panel.add(lblGivenAmount);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textField_5.setColumns(10);
		textField_5.setBounds(476, 195, 145, 20);
		panel.add(textField_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 76, 303, 103);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "Additional Amount"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1_2 = new JButton("Home");
		Image img2 = new ImageIcon(this.getClass().getResource("home-icon.png")).getImage();
		btnNewButton_1_2.setIcon(new ImageIcon(img2));
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Home h=new Home();
				h.setVisible(true);
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_2.setBounds(718, 103, 109, 48);
		panel.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("Exit");
		Image img3 = new ImageIcon(this.getClass().getResource("close-icon.png")).getImage();
		btnNewButton_1_3.setIcon(new ImageIcon(img3));
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1_3.setBounds(718, 173, 109, 43);
		panel.add(btnNewButton_1_3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 265, 864, 346);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sheet No", "Name", "NIC No", "Date of Pawning", "Date of Recover", "Given amount"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setViewportView(table_1);
	}
}
