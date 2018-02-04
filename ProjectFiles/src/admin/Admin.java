package admin;
import java.awt.*;
import javax.swing.*;



import java.sql.*;
import java.awt.event.*;
import java.util.*;
public class Admin {

	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		
		//------------------- Gui Run -----------------------
				EventQueue.invokeLater(new Runnable() {
					public void run(){
						try {
							Admin window = new Admin();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
	}

	public Admin() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Log In System");
		lblNewLabel.setBounds(180, 11, 111, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		
		lblNewLabel_1.setBounds(68, 71, 64, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setBounds(68, 148, 64, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(155, 68, 216, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(155, 145, 216, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnBack = new JButton("EXIT");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnBack.setBounds(293, 206, 89, 23);
		frame.getContentPane().add(btnBack);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 192, 408, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 42, 408, 2);
		frame.getContentPane().add(separator_1);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usernameF = textField.getText();
				String passwordF = passwordField.getText();
				String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
				String username = "root";
				String password = "paroljok";
				ArrayList<String> usernames = new ArrayList<String>();
				ArrayList<String> passwords = new ArrayList<String>();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, password);
				    if (conn!=null) {
				    	System.out.println("connected");
				    }
				    Statement statement = conn.createStatement();
					ResultSet result = statement.executeQuery("select * from admin");
					
					
					while (result.next()) {
						String usern = result.getString("username");
						String passw = result.getString("password");
						
						usernames.add(usern);
						passwords.add(passw);
						
					}
				}
				catch(Exception s) {
					System.out.println(s);
				}
				
				for (int i=0; i<usernames.size(); i++) {
					if(usernames.get(i).equals(usernameF) && passwords.get(i).equals(passwordF)) {
						BookAdmin b = new BookAdmin();
						b.frame.setVisible(true);
						frame.dispose();
					}
					
				}
				/*else {
					JOptionPane.showMessageDialog(null, "Invalid","LogIn error", JOptionPane.ERROR_MESSAGE);
					textField.setText(null);
					passwordField.setText(null);
				}*/
			}
		});
		btnLogIn.setBounds(134, 206, 89, 23);
		frame.getContentPane().add(btnLogIn);
	}
	


	
}
