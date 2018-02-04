package user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rate {

	public JFrame frame;
	public static JRadioButton one, two, three, four, five;
    public static int BookId;

    
	public ArrayList<Integer> ar = new ArrayList<Integer>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rate window = new Rate();
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
	public Rate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	int rating;
	  public ButtonGroup bg = new ButtonGroup();
	private void initialize() {
		
		bg.add(one);
		bg.add(two);
		bg.add(three);
		bg.add(four);
		bg.add(five);
		frame = new JFrame();
		frame.setBounds(100, 100, 555, 211);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("If you enjoyed the book, please RATE IT");
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 23));
		lblNewLabel.setBounds(47, 24, 529, 56);
		frame.getContentPane().add(lblNewLabel);
		
		JRadioButton one = new JRadioButton("1");
		one.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating = 1;
			}
		});
		one.setBounds(103, 87, 50, 23);
		frame.getContentPane().add(one);
		
		JRadioButton two = new JRadioButton("2");
		two.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating =2;
			}
		});
		two.setBounds(174, 87, 50, 23);
		frame.getContentPane().add(two);
		
		JRadioButton three = new JRadioButton("3");
		three.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating = 3;
			}
		});
		three.setBounds(247, 87, 50, 23);
		frame.getContentPane().add(three);
		
		JRadioButton four = new JRadioButton("4");
		four.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating =4;
			}
		});
		four.setBounds(316, 87, 50, 23);
		frame.getContentPane().add(four);
		
		JRadioButton five = new JRadioButton("5");	
		five.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rating =5;
			}
		});
		five.setBounds(368, 87, 50, 23);
		frame.getContentPane().add(five);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			frame.setVisible(false);
			
			String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
			String username = "root";
			String password = "paroljok";
		
			boolean isRated = true;
			try {
				Class.forName("com.mysql.jdbc.Driver");
			    Connection conn = DriverManager.getConnection(url, username, password);
			    Statement statement = conn.createStatement();
			    ResultSet result = statement.executeQuery("select books.bookID from books where title= '" + requestBook.textField.getText()+ "' and author = '"+requestBook.textField_1.getText()+"'");
			 
			    while (result.next()) {
			    	BookId = result.getInt("books.bookID");
			    	ar.add(BookId);
			    }
		
				conn.close();
				
			}
				catch(Exception a) {
					System.out.println(a);
				}
			rate();	
			ArrayList<Integer> bookList = new ArrayList<Integer>();
			ArrayList<Integer> userList = new ArrayList<Integer>();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			    Connection conn = DriverManager.getConnection(url, username, password);
			    Statement statement = conn.createStatement();
			    ResultSet result = statement.executeQuery("select * from rating");
			  
			    while (result.next()) {
			    	int ui = result.getInt("userID");
			    	int bi = result.getInt("bookID");
			    	
			    	bookList.add(bi);
			    	userList.add(ui);
			    }
			   for (int i=0; i<userList.size(); i++) {
				   if (userList.get(i).equals(Teacher.getUserId()) && bookList.get(i).equals(ar.get(0))) {
					   //JOptionPane.showMessageDialog(null, "You have already rated this book!","Rerating", JOptionPane.ERROR_MESSAGE);
					   isRated = false;
				   }
			   }
			   System.out.println("//////////////////////" + isRated);
				   if (isRated==true) {
					   PreparedStatement pst = conn.prepareStatement("insert into rating values(null, ?,?,?,?,?)");
						pst.setInt(1, Teacher.getUserId());
						pst.setInt(2,  ar.get(0));
						pst.setString(3,  requestBook.textField.getText());
						pst.setString(4,  requestBook.textField_1.getText());
						pst.setInt(5, rating);
						pst.execute();
				   }
				   else {
					   JOptionPane.showMessageDialog(null, "You have already rated this book!","Rerating", JOptionPane.ERROR_MESSAGE);
					   
				   }
			   conn.close();
			   
			}
			catch(Exception t) {
				System.out.println("t");
			}
			
			}
		});	
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setBounds(135, 117, 89, 44);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL\r\n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBackground(new Color(128, 128, 128));
		btnNewButton_1.setFont(new Font("Tahoma", Font.ITALIC, 15));
		btnNewButton_1.setBounds(286, 117, 101, 44);
		frame.getContentPane().add(btnNewButton_1);
	}
	/*
	public void getRating() {
		if (one.isSelected()) {
			System.out.println(1);
		}	
		else if(two.isSelected()) {
			return 2;
		}
		else if(three.isSelected()) {
			return 3;
		}
		else if(four.isSelected()) {
			return 4;
		}
		else if(five.isSelected()) {
			return 5;
		}
		else {
			System.out.println(0);
		}
		return 0;
		*/
////////////////////////////////////////////////////////////////////CALCULATE THE RATING CALCULATE THE RATING CALCULATE THE RATING//////////////////////////////////// 
	public void rate() {
		double counter = 0;
		double sum=0;
		double frating;
		String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
		String username = "root";
		String password = "paroljok";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    Connection conn = DriverManager.getConnection(url, username, password);
		    Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery("select * from rating where bookID='"+ar.get(0)+"'");
		    System.out.println("boooook id " + ar.get(0));
		    while(result.next()) {
		    	counter++;
		    	sum=sum+result.getInt("rating");
		    }
		    frating = sum/counter;
		    System.out.println(sum);
		    System.out.println(counter);
		    System.out.println(frating);
		    System.out.println("USER ID "+ ar.get(0));
		    PreparedStatement prst = conn.prepareStatement("update books set rating=? where bookID=?");
		    prst.setDouble(1, frating);
		    prst.setInt(2, ar.get(0));
		    prst.execute();
		    conn.close();
		}
		catch(Exception w) {
			System.out.println(w);
		}
	}
}
		
	
	

