package user;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
public class CreateTeacher {

	private JFrame frame;
	private JTextField nameText;
	private JTextField surnameText;
	private JTextField dbText;
	private JPasswordField passwordField;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateTeacher window = new CreateTeacher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateTeacher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSignInSystem = new JLabel("Sign In System");
		lblSignInSystem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSignInSystem.setBounds(158, 11, 203, 51);
		frame.getContentPane().add(lblSignInSystem);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(49, 81, 411, 15);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(49, 107, 88, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLastName.setBounds(49, 151, 88, 34);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDateOfBirth.setBounds(49, 196, 88, 28);
		frame.getContentPane().add(lblDateOfBirth);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(49, 235, 88, 34);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(49, 280, 88, 28);
		frame.getContentPane().add(lblAddress);
		
		nameText = new JTextField();
		nameText.setBounds(170, 107, 170, 20);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);
		
		surnameText = new JTextField();
		surnameText.setColumns(10);
		surnameText.setBounds(170, 160, 170, 20);
		frame.getContentPane().add(surnameText);
		
		dbText = new JTextField();
		dbText.setColumns(10);
		dbText.setBounds(170, 202, 170, 20);
		frame.getContentPane().add(dbText);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = nameText.getText();
				String surname = surnameText.getText();
				String newUsername = name+"."+surname;  
				String password = passwordField.getText();
				String address = textField_3.getText();
				/////////////////////////// - Data Base - ////////////////////////////////
				String url = "jdbc:mysql://localhost:3306/lis";
				String username = "root";
				String passwordDB = "paroljok";
				PreparedStatement pst =null;
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, passwordDB	);
				    pst = conn.prepareStatement("insert into teacher values(null,?,?,?,?,?)");
				    pst.setString(1, name);
				    pst.setString(2, surname);
				    pst.setString(3, newUsername);
				    pst.setString(4, address);
				    pst.setString(5, password);
				    pst.execute();
				    conn.close();
				    System.out.println("kowuldu");
				    }
				catch(Exception n) {
					System.out.println(n);
				}
			
			}
		});
			
		btnSubmit.setBounds(111, 348, 117, 52);
		frame.getContentPane().add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(170, 244, 170, 20);
		frame.getContentPane().add(passwordField);
		
		JLabel lblMmddyyyy = new JLabel("mm/dd/yyyy");
		lblMmddyyyy.setBounds(357, 205, 78, 14);
		frame.getContentPane().add(lblMmddyyyy);
		
		JButton btnNewButton = new JButton("back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Teacher t = new Teacher();
				 t.frame.setVisible(true);
				 frame.dispose();
			}
		});
		btnNewButton.setBounds(289, 348, 101, 52);
		frame.getContentPane().add(btnNewButton);
		
		textField_3 = new JTextField();
		textField_3.setBounds(170, 288, 170, 49);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
	}
}
