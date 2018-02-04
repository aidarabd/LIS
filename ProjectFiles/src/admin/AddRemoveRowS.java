package admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddRemoveRowS {

	public JFrame frame;
	public JTextField titleText;
	public JTextField textFieldd_1;
	public JTextField textFieldd_2;
	public JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRemoveBook window = new AddRemoveBook();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public AddRemoveRowS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 397, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBookAdmin = new JLabel("Book Admin");
		lblBookAdmin.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBookAdmin.setBounds(120, 11, 174, 25);
		frame.getContentPane().add(lblBookAdmin);
		
		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setBounds(27, 58, 113, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setBounds(27, 104, 113, 25);
		frame.getContentPane().add(lblAuthor);
		
		JLabel lblPublishedYear = new JLabel("Published Year");
		lblPublishedYear.setBounds(27, 151, 113, 25);
		frame.getContentPane().add(lblPublishedYear);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(27, 197, 113, 25);
		frame.getContentPane().add(lblCategory);
		
		titleText = new JTextField();
		titleText.setBounds(146, 63, 161, 20);
		frame.getContentPane().add(titleText);
		titleText.setColumns(10);
		
		textFieldd_1 = new JTextField();
		textFieldd_1.setColumns(10);
		textFieldd_1.setBounds(146, 106, 161, 20);
		frame.getContentPane().add(textFieldd_1);
		
		textFieldd_2 = new JTextField();
		textFieldd_2.setColumns(10);
		textFieldd_2.setBounds(146, 153, 161, 20);
		frame.getContentPane().add(textFieldd_2);
		
		JButton btnAddBook = new JButton("ADD BOOK");
		btnAddBook.setBackground(Color.GREEN);
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = titleText.getText();
				String author = textFieldd_1.getText();
				String category = textField.getText();
				String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
				String username = "root";
				String passwordDB = "paroljok";
				PreparedStatement pst =null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, passwordDB	);
				    pst = conn.prepareStatement("insert into books values(null,?,?,?,?,null)");
				    pst.setString(1, title);
				    pst.setString(2, author);
				    pst.setString(3, "free");
				    pst.setString(4, category);
				    
				    pst.execute();
				    conn.close();
				    System.out.println("kowuldu");
				    JOptionPane.showMessageDialog(null, "Book successfully added to library","Action completed", JOptionPane.INFORMATION_MESSAGE);
				    }
				catch(Exception n) {
					System.out.println(n);
				}
			}
		});
		btnAddBook.setBounds(49, 284, 113, 23);
		frame.getContentPane().add(btnAddBook);
		
		JButton btnRemoveBook = new JButton("REMOVE BOOK");
		btnRemoveBook.setForeground(Color.white);
		btnRemoveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = titleText.getText();
				String author = textFieldd_1.getText();
				String category = textField.getText();
				String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
				String username = "root";
				String passwordDB = "paroljok";
				PreparedStatement pst =null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, passwordDB	);
				    pst = conn.prepareStatement("delete from books where title=? and author=? and category=?");
				    pst.setString(1, title);
				    pst.setString(2, author);
				    pst.setString(3, category);
				    
				    pst.executeUpdate();
				    conn.close();
				    JOptionPane.showMessageDialog(null, "Book successfully deleted","Action completed", JOptionPane.INFORMATION_MESSAGE);
				    }
				catch(Exception n) {
					System.out.println(n);
				}
			}
		});
		btnRemoveBook.setBackground(Color.RED);
		btnRemoveBook.setBounds(212, 284, 113, 23);
		frame.getContentPane().add(btnRemoveBook);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(141, 340, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(146, 199, 161, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
