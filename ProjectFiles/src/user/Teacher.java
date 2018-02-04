package user;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
public class Teacher {

	public JFrame frame;
	private static JTextField textField;
	private JPasswordField passwordField;
	
	
	
	 
	public static int getUserId() {	
		ArrayList<Integer> UserId = new ArrayList<>();
		
		try {
			String usernameF = textField.getText();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lis", "root", "paroljok");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select userID from teacher where (username = '" + usernameF +"')");
			
			while(rs.next()) {
				int id = rs.getInt("userID");
				UserId.add(id);
			}
			con.close();
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
		return UserId.get(0);
	}
	
	
	public static void main() {
		
		
		
		
		//------------------- Gui Run -----------------------
				EventQueue.invokeLater(new Runnable() {
					public void run(){
						try {
							Teacher window = new Teacher();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				
	}

	public Teacher() {
		initialize();
	}

	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Login System");
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
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateTeacher.main();
				frame.setVisible(false);
			}
		});
		btnSignIn.setBounds(202, 206, 89, 23);
		frame.getContentPane().add(btnSignIn);
		
		JButton btnBack = new JButton("EXIT");
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				
			}
		});
		btnBack.setBounds(338, 206, 89, 23);
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
				ArrayList<String> usernamesT = new ArrayList<String>();
				ArrayList<String> passwordsT = new ArrayList<String>();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, password);
				    if (conn!=null) {
				    	System.out.println("connected");
				    }
				    Statement statement = conn.createStatement();
					ResultSet result = statement.executeQuery("select * from teacher");
					PreparedStatement pst = conn.prepareStatement("insert into lastLogged values(null, ?)");
					pst.setInt(1, getUserId());
					pst.execute();
					
					while (result.next()) {
						String usern = result.getString("username");
						String passw = result.getString("password");
						
						usernamesT.add(usern);
						passwordsT.add(passw);
						
					}
					conn.close();
				}
				catch(Exception s) {
					System.out.println(s);
				}
				
				for (int i=0; i<usernamesT.size(); i++) {
					if(usernamesT.get(i).equals(usernameF) && passwordsT.get(i).equals(passwordF)) {
						
						BookTeacher b = new BookTeacher();
						b.frame.setVisible(true);
						frame.setVisible(false);
					}
				}
				/*else {
					JOptionPane.showMessageDialog(null, "Invalid","LogIn error", JOptionPane.ERROR_MESSAGE);
					textField.setText(null);
					passwordField.setText(null);
				}*/
				
			if (usernameF!=null) {
				System.out.println(getUserId());
			}
			}
		});
		btnLogIn.setBounds(68, 206, 89, 23);
		frame.getContentPane().add(btnLogIn);
	}
}
