package user;

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import java.util.Date;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class requestBook {
	int qu;
	int id;
	int bookIDforU;

	
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}

	public JFrame frame;
	public static JTextField textField;
	public static JTextField textField_1;
	public JPanel panel;
	/**	
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					requestBook window = new requestBook();
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
	public requestBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 425, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRequestBook = new JLabel("Book Management");
		lblRequestBook.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRequestBook.setBounds(115, 11, 193, 39);
		frame.getContentPane().add(lblRequestBook);
		
		JButton btnNewButton = new JButton("Request Book");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(Teacher.getUserId());
				ArrayList<Integer> bookIDList = new ArrayList<Integer>();
				try {
					String bookTitle = textField.getText();
					String bookAuthor = textField_1.getText();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true", "root", "paroljok");
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select * from books where (title='" +bookTitle+ "' and author='" +bookAuthor+ "')");
					PreparedStatement pst = con.prepareStatement("insert into issuebook values (null,?,?,?, ?)" );
					while (rs.next()) {
						int idd = rs.getInt("bookID");
						bookIDList.add(idd);
					}
					pst.setInt(1, Teacher.getUserId());
					pst.setInt(2, bookIDList.get(0));
					pst.setDate(3, getCurrentDate());
					pst.setString(4, "issued");
					pst.execute();
					con.close();	
				
					
				}
				catch(Exception e) {
					System.out.println(e);
				}
				////////////////////////////// Secon try c //////////////////////////////
				try {

					String bookTitle = textField.getText();
					String bookAuthor = textField_1.getText();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true", "root", "paroljok");
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select * from books where (title='" +bookTitle+ "' and author='" +bookAuthor+ "')");
					PreparedStatement pst = con.prepareStatement("insert into isbupdated values (null,?,?,?, ?)" );
					while (rs.next()) {
						int idd = rs.getInt("bookID");
						bookIDList.add(idd);
					}
					pst.setInt(1, Teacher.getUserId());
					pst.setInt(2, bookIDList.get(0));
					pst.setDate(3, getCurrentDate());
					pst.setString(4, "issued");
					pst.execute();
					con.close();	
					
					System.out.print("book id = " + bookIDList.get(0));
					System.out.println("user id = " + Teacher.getUserId());
				}
				catch(Exception a) {
					System.out.println("second try c " + a);
				}
				String message = "Book successfully added to your private temporary library. You should return the book in 3 weeks, in another case you will have to pay a fine";
				JOptionPane.showMessageDialog(null, message ,"Action completed", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setBounds(44, 222, 113, 36);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(Teacher.getUserId());
				ArrayList<Integer> bookIDList = new ArrayList<Integer>();
				
				try {
					String bookTitle = textField.getText();
					String bookAuthor = textField_1.getText();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true", "root", "paroljok");
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select * from books where (title='" +bookTitle+ "' and author='" +bookAuthor+ "')");
					while (rs.next()) {
						int bookID = rs.getInt("bookID");
						bookIDList.add(bookID);
					}
					System.out.print("book id = " + bookIDList.get(0));
					System.out.println("user id = " + Teacher.getUserId());
					PreparedStatement pst = con.prepareStatement("insert into issuebook values (null,?,?,?, ?)" );
					pst.setInt(1, Teacher.getUserId());
					pst.setInt(2, bookIDList.get(0));
					pst.setDate(3, getCurrentDate());
					pst.setString(4, "returned");
					pst.execute();
					con.close();
				}
				catch(Exception e) {
					System.out.println(e);
				}
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				ArrayList<Integer> bookIDListu = new ArrayList<Integer>();
				try {
					String bookTitle = textField.getText();
					String bookAuthor = textField_1.getText();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true", "root", "paroljok");
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery("select * from books where (title='" +bookTitle+ "' and author='" +bookAuthor+ "')");
					while (rs.next()) {
						bookIDforU = rs.getInt("bookID");
						bookIDListu.add(bookIDforU);
					}
				
					PreparedStatement pst = con.prepareStatement("DELETE FROM isbupdated WHERE bookID=?" );
					pst.setInt(1, bookIDListu.get(0));
					pst.executeUpdate();
					con.close();
				}
				catch(Exception a) {
					System.out.println(a);
				}
				
				Rate r = new Rate();
				r.frame.setVisible(true);
				/////////////////////////////  RATE RATE RATE RATE RATE RATE RATE RATE RATE RATE RATE RATE RATE  /////////////////////////////////////////////////////
			
				
				
			}	
		});
		btnReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReturnBook.setBackground(SystemColor.menu);
		btnReturnBook.setBounds(237, 222, 113, 36);
		frame.getContentPane().add(btnReturnBook);
		
		JLabel lblNewLabel = new JLabel("Book title");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(44, 90, 113, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblBookAuthor = new JLabel("Book author");
		lblBookAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookAuthor.setBounds(44, 159, 113, 14);
		frame.getContentPane().add(lblBookAuthor);
		
		textField = new JTextField();
		textField.setBounds(149, 89, 169, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(146, 158, 169, 20);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton_1 = new JButton("Back Page");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.setBounds(129, 300, 135, 39);
		frame.getContentPane().add(btnNewButton_1);
	}
}
