import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.Color;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setResizable(false);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setSize(new Dimension(1650, 1080));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 857, 773);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAditional = new JButton("Aditional");
		Image img111111 = new ImageIcon(this.getClass().getResource("Add-icon.png")).getImage();
		btnAditional.setIcon(new ImageIcon(img111111));
		btnAditional.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Additional a=new Additional();
				a.setVisible(true);
			}
		});
		btnAditional.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnAditional.setBounds(35, 169, 173, 45);
		contentPane.add(btnAditional);
		
		JButton btnRecover = new JButton("Recover");
		Image img1 = new ImageIcon(this.getClass().getResource("recover-icon.png")).getImage();
		btnRecover.setIcon(new ImageIcon(img1));
		btnRecover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Recover r=new Recover();
				r.setVisible(true);
			}
		});
		btnRecover.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnRecover.setBounds(35, 250, 173, 45);
		contentPane.add(btnRecover);
		
		JButton btnRecoverd = new JButton("Recoverd");
		Image img11 = new ImageIcon(this.getClass().getResource("recovered-icon.png")).getImage();
		btnRecoverd.setIcon(new ImageIcon(img11));
		btnRecoverd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Recoverd r=new Recoverd();
				r.setVisible(true);
			}
		});
		btnRecoverd.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnRecoverd.setBounds(35, 330, 173, 45);
		contentPane.add(btnRecoverd);
		
		JButton btnSettings = new JButton("Settings");
		Image img111 = new ImageIcon(this.getClass().getResource("Settings-icon.png")).getImage();
		btnSettings.setIcon(new ImageIcon(img111));
			btnSettings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					Settings s=new Settings();
					s.setVisible(true);
				}
		});
		btnSettings.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnSettings.setBounds(35, 405, 173, 45);
		contentPane.add(btnSettings);
		
		JButton btnExit = new JButton("Exit");
		Image img1111 = new ImageIcon(this.getClass().getResource("close-icon.png")).getImage();
		btnExit.setIcon(new ImageIcon(img1111));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnExit.setBounds(35, 655, 173, 45);
		contentPane.add(btnExit);
		
		JButton btnPawn = new JButton("Pawn");
		Image img11111 = new ImageIcon(this.getClass().getResource("pawn-icon.png")).getImage();
		btnPawn.setIcon(new ImageIcon(img11111));
		btnPawn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Pawning p=new Pawning();
				p.setVisible(true);
			}
		});
		btnPawn.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnPawn.setBounds(35, 93, 173, 45);
		contentPane.add(btnPawn);
		
		JLabel lblNewLabel_1 = new JLabel("PAWNING MANAGEMENT SYSTEM");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Albertus Extra Bold", Font.BOLD, 30));
		lblNewLabel_1.setBounds(205, 6, 614, 76);
		contentPane.add(lblNewLabel_1);
		
		JButton btnBackup = new JButton("Backup");
		Image img4 = new ImageIcon(this.getClass().getResource("Backup-icon.png")).getImage();
		btnBackup.setIcon(new ImageIcon(img4));
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object savePath = null;
				File saveFile = new File((String) savePath);
				if (!saveFile.exists()) {// If the directory does not exist
					saveFile.mkdirs();// create folder
				}
				if(!((String) savePath).endsWith(File.separator)){
					savePath = savePath + File.separator;
				}

				PrintWriter printWriter = null;
				BufferedReader bufferedReader = null;
				try {
					try {
						printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + getName()), "utf8"));
					} catch (UnsupportedEncodingException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					} catch (FileNotFoundException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					String host = null;
					String ip = null;
					String password = null;
					String databaseName = null;
					Process process = null;
					try {
						process = Runtime.getRuntime().exec(" mysqldump -h" + ip + " -P" + host + " -u" + getName() + " -p" + password + " --default-character-set=utf8 " + databaseName);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					InputStreamReader inputStreamReader = null;
					try {
						inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
					} catch (UnsupportedEncodingException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					bufferedReader = new BufferedReader(inputStreamReader);
					String line;
					try {
						while((line = bufferedReader.readLine())!= null){
							printWriter.println(line);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					printWriter.flush();
					/*if(process.waitFor() == 0){//0 Indicates that the thread is terminated normally.
						return true;
					}*/
				} finally {
					try {
						if (bufferedReader != null) {
							bufferedReader.close();
						}
						if (printWriter != null) {
							printWriter.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				//return false;
			}
			
		});
		btnBackup.setFont(new Font("Sitka Display", Font.BOLD, 18));
		btnBackup.setBounds(35, 480, 173, 45);
		contentPane.add(btnBackup);
	}
}
