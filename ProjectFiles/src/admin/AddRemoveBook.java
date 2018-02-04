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

public class AddRemoveBook {

	 JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
	 */
	public AddRemoveBook() {
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
		
		textField = new JTextField();
		textField.setBounds(146, 63, 161, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(146, 106, 161, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(146, 153, 161, 20);
		frame.getContentPane().add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(146, 199, 161, 20);
		frame.getContentPane().add(comboBox);
		
		comboBox.addItem("choose category");
		comboBox.addItem("biography");
		comboBox.addItem("fantasy");
		comboBox.addItem("Social Science fiction");
		comboBox.addItem("historical fiction");
		comboBox.addItem("modern Literary");
		comboBox.addItem("novel");
		comboBox.addItem("non-fiction");
		comboBox.addItem("journals");
		comboBox.addItem("other");
		comboBox.addItem("magazines");
		comboBox.addItem("dictionaries");
		
		JButton btnAddBook = new JButton("ADD BOOK");
		btnAddBook.setBackground(Color.GREEN);
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = textField.getText();
				String author = textField_1.getText();
				String category = (String) comboBox.getSelectedItem();
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
				String title = textField.getText();
				String author = textField_1.getText();
				String category = (String) comboBox.getSelectedItem();
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
	}
}
