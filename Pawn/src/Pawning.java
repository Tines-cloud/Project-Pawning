import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Pawning extends JFrame {

	protected static final String WindowEvent = null;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pawning frame = new Pawning();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void clearFields() {
		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_8.setText("");
		//to clear a textArea You need to done it on in the Button action
		//showDate();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	/*public void showDate() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		textField_6.setText(dateFormat.format(date));
	}*/
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

	public Pawning() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//showDate();
			  	try 
		    	{
		    		con = DriverManager.getConnection("jdbc:mysql://localhost/pawn","root","");
				    pst = con.prepareStatement("select * from pawning");
				    rs = pst.executeQuery();
				    while (rs.next()) {
				    	//Retrieving details from the database and storing it in the String variables
		                String sheet = rs.getString("sheet_no");
		                String name = rs.getString("name");
		                String nic = rs.getString("nic_no");
		                String contact = rs.getString("contact_no");
		                String goldtype = rs.getString("gold_type");
		                double weight = rs.getDouble("weight");
		                String dateofpawning = rs.getString("pawning_date");
		                double totalvalue = rs.getDouble("total_cost");
		                double initialamount = rs.getDouble("initial_amount");
		                String remark = rs.getString("remark");
		                
		                DefaultTableModel model= (DefaultTableModel)table.getModel();
						model.addRow(new Object[] {sheet,name,nic,contact,goldtype,weight,dateofpawning,totalvalue,initialamount,remark});	                   		               
		            }
		    	} 
		    	catch (SQLException ex) 
		    	 {
		    		JOptionPane.showMessageDialog(null,ex);
			  } 
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 956, 714);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Connect();
		
		JLabel lblNewLabel = new JLabel("Sheet No");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel.setBounds(21, 11, 87, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblName.setBounds(21, 43, 87, 21);
		contentPane.add(lblName);
		
		JLabel lblNicNo = new JLabel("NIC No");
		lblNicNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNicNo.setBounds(21, 81, 87, 21);
		contentPane.add(lblNicNo);
		
		JLabel lblContactNo = new JLabel("Contact No");
		lblContactNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblContactNo.setBounds(21, 113, 87, 21);
		contentPane.add(lblContactNo);
		
		JLabel lblGoldType = new JLabel("Gold Type");
		lblGoldType.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblGoldType.setBounds(21, 150, 87, 21);
		contentPane.add(lblGoldType);
		
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblWeight.setBounds(448, 11, 87, 21);
		contentPane.add(lblWeight);
		
		JLabel lblDateOfPawning = new JLabel("Date of Pawning");
		lblDateOfPawning.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblDateOfPawning.setBounds(448, 43, 123, 21);
		contentPane.add(lblDateOfPawning);
		
		JLabel lblValue = new JLabel("Total Value");
		lblValue.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblValue.setBounds(448, 81, 139, 21);
		contentPane.add(lblValue);
		
		JLabel lblInitialAmount = new JLabel("Initial Amount");
		lblInitialAmount.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblInitialAmount.setBounds(448, 113, 123, 21);
		contentPane.add(lblInitialAmount);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblRemark.setBounds(21, 192, 87, 21);
		contentPane.add(lblRemark);
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField.setBounds(157, 11, 165, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(157, 45, 165, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_2.setColumns(10);
		textField_2.setBounds(157, 83, 165, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_3.setColumns(10);
		textField_3.setBounds(157, 115, 165, 20);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_4.setColumns(10);
		textField_4.setBounds(157, 152, 165, 20);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_5.setColumns(10);
		textField_5.setBounds(675, 13, 165, 20);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_6.setColumns(10);
		textField_6.setBounds(675, 45, 165, 20);
		contentPane.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_7.setColumns(10);
		textField_7.setBounds(675, 83, 165, 20);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_8.setColumns(10);
		textField_8.setBounds(675, 115, 165, 20);
		contentPane.add(textField_8);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(157, 192, 414, 127);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("Add");
		Image img11111 = new ImageIcon(this.getClass().getResource("Add-icon.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img11111));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().equals("")||textField_1.getText().equals("")||textField_2.getText().equals("")||textField_3.getText().equals("")||textField_4.getText().equals("")||textField_5.getText().equals("")||textField_6.getText().equals("")||textField_7.getText().equals("")||textField_8.getText().equals("")||textArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Fill All the Fields");
				}
				else {
					try {
						String sheet,name,nic,contact,goldtype,pawningdate,remark;
						double weight,totalvalue,initialamount;
						sheet=textField.getText();
						name=textField_1.getText();
						nic=textField_2.getText();
						contact=textField_3.getText();
						goldtype=textField_4.getText();
						weight=Double.parseDouble(textField_5.getText());
						pawningdate=textField_6.getText();
						totalvalue=Double.parseDouble(textField_7.getText());
						initialamount=Double.parseDouble(textField_8.getText());
						remark=textArea.getText();
			//sql section
						
						pst = con.prepareStatement("INSERT INTO `pawning`(`sheet_no`, `name`, `nic_no`, `contact_no`, `gold_type`, `weight`, `pawning_date`, `total_cost`, `initial_amount`, `remark`,`totalamount`) values(?,?,?,?,?,?,?,?,?,?,?)");
						pst.setString(1, sheet);
						pst.setString(2, name);
						pst.setString(3, nic);
						pst.setString(4, contact);
						pst.setString(5, goldtype);
						pst.setDouble(6, weight);
						pst.setString(7, pawningdate);
						pst.setDouble(8, totalvalue);
						pst.setDouble(9, initialamount);
						pst.setString(10, remark);
						pst.setDouble(11, initialamount);
						pst.executeUpdate();
						
						DefaultTableModel model= (DefaultTableModel)table.getModel();
						model.addRow(new Object[] {textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),Double.parseDouble(textField_5.getText()),textField_6.getText(),Double.parseDouble(textField_7.getText()),Double.parseDouble(textField_8.getText()),textArea.getText()});
						
															
						clearFields();
						textArea.setText("");
						JOptionPane.showMessageDialog(null,"Succesfuly Added");
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null,e1);
					}
				}
			}
		});
		
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		btnNewButton.setBounds(597, 150, 129, 49);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		Image img111111 = new ImageIcon(this.getClass().getResource("update-icon.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img111111));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i=table.getSelectedRow();
				if(i>=0) {
					try {
						String sheet,name,nic,contact,goldtype,pawningdate,remark;
						double weight,totalvalue,initialamount;
						sheet=textField.getText();
						name=textField_1.getText();
						nic=textField_2.getText();
						contact=textField_3.getText();
						goldtype=textField_4.getText();
						weight=Double.parseDouble(textField_5.getText());
						pawningdate=textField_6.getText();
						totalvalue=Double.parseDouble(textField_7.getText());
						initialamount=Double.parseDouble(textField_8.getText());
						remark=textArea.getText();
									
						pst = con.prepareStatement("update pawning set name= ?,nic_no=?,contact_no=?,gold_type=?,weight=?,pawning_date=?,total_cost=?,initial_amount=?,remark=?,totalamount=? where sheet_no =?");
						pst.setString(1, name);
						pst.setString(2, nic);
						pst.setString(3, contact);
						pst.setString(4, goldtype);
						pst.setDouble(5, weight);
						pst.setString(6, pawningdate);
						pst.setDouble(7, totalvalue);
						pst.setDouble(8, initialamount);
						pst.setString(9, remark);
						pst.setDouble(10, initialamount);
						pst.setString(11, sheet);
						pst.executeUpdate();				           
						
						 
						DefaultTableModel model= (DefaultTableModel)table.getModel();
						model.setValueAt(textField.getText(), i, 0);
						model.setValueAt(textField_1.getText(), i, 1);
						model.setValueAt(textField_2.getText(), i, 2);
						model.setValueAt(textField_3.getText(), i, 3);
						model.setValueAt(textField_4.getText(), i, 4);
						model.setValueAt(Double.parseDouble(textField_5.getText()), i, 5);
						model.setValueAt(textField_6.getText(), i, 6);
						model.setValueAt(Double.parseDouble(textField_7.getText()), i, 7);
						model.setValueAt(Double.parseDouble(textField_8.getText()), i, 8);
						model.setValueAt(textArea.getText(), i, 9);
						
						clearFields();
						textArea.setText("");
						JOptionPane.showMessageDialog(null,"Succesfuly Updated");
						
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null,e1);
					}					
				}
				else {
					JOptionPane.showMessageDialog(null,"Select a Row First");
				}
			}
		});
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		btnNewButton_1.setBounds(756, 150, 129, 49);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		Image img1111111 = new ImageIcon(this.getClass().getResource("delete-icon.png")).getImage();
		btnNewButton_2.setIcon(new ImageIcon(img1111111));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i=table.getSelectedRow();
				if(i>=0) {
					try {
						String sheet=textField.getText();
		
					 try {
							pst = con.prepareStatement("delete from pawning where sheet_no =?");
				            pst.setString(1, sheet);
				            pst.executeUpdate();
						}
		
			            catch (SQLException e1) {
			            	JOptionPane.showMessageDialog(null,e1);
						}
	
						DefaultTableModel model= (DefaultTableModel)table.getModel();
						model.removeRow(i);
						JOptionPane.showMessageDialog(null,"Succesfuly Deleted");
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null,e1);
					}	
				}
				else {
					JOptionPane.showMessageDialog(null,"Select a Row First");
				}
			}
		});
		btnNewButton_2.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_2.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		btnNewButton_2.setBounds(597, 210, 129, 49);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Clear");
		Image img11111111 = new ImageIcon(this.getClass().getResource("Clear-icon.png")).getImage();
		btnNewButton_3.setIcon(new ImageIcon(img11111111));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
				textArea.setText("");
			}
		});
		btnNewButton_3.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_3.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		btnNewButton_3.setBounds(756, 210, 129, 48);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Home");
		Image img111111111 = new ImageIcon(this.getClass().getResource("home-icon.png")).getImage();
		btnNewButton_4.setIcon(new ImageIcon(img111111111));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				Home h=new Home();
				h.setVisible(true);
			}
		});
		btnNewButton_4.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_4.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		btnNewButton_4.setBounds(597, 270, 129, 49);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Exit");
		Image img1111111111 = new ImageIcon(this.getClass().getResource("close-icon.png")).getImage();
		btnNewButton_5.setIcon(new ImageIcon(img1111111111));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_5.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_5.setFont(new Font("Yu Gothic Medium", Font.BOLD, 17));
		btnNewButton_5.setBounds(756, 269, 129, 49);
		contentPane.add(btnNewButton_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 344, 920, 320);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model= (DefaultTableModel)table.getModel();
				int i=table.getSelectedRow();
				textField.setText(model.getValueAt(i, 0).toString());
				textField_1.setText(model.getValueAt(i, 1).toString());
				textField_2.setText(model.getValueAt(i, 2).toString());
				textField_3.setText(model.getValueAt(i, 3).toString());
				textField_4.setText(model.getValueAt(i, 4).toString());
				textField_5.setText(model.getValueAt(i, 5).toString());
				textField_6.setText(model.getValueAt(i, 6).toString());
				textField_7.setText(model.getValueAt(i, 7).toString());
				textField_8.setText(model.getValueAt(i, 8).toString());
				textArea.setText(model.getValueAt(i, 9).toString());
									
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sheet No", "Name", "NIC No", "Contact No", "Gold Type", "Weight", "Pawning Date", "Total Value", "Initial Amount", "Remark"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Double.class, String.class, Double.class, Double.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);
		table.getColumnModel().getColumn(9).setResizable(false);
		table.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(table);
		
	}
}
