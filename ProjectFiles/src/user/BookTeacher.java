package user;

import java.util.Date;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JDesktopPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Books.Books;
import Books.IssuedBooks;
import Books.isbupdated;
import admin.AddRemoveRowS;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class BookTeacher {
	
	
	public JFrame frame;
	private JTextField textField;
	private JTable table_book;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookTeacher window = new BookTeacher();
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
	public BookTeacher() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public ArrayList<Books> bookList(){
		ArrayList<Books> bookslist = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
		String username = "root";
		String password = "paroljok";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		    Connection conn = DriverManager.getConnection(url, username, password);
		    if (conn!=null) {
		    	System.out.println("connected");
		    }
		    Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from books");
			Books books;
			while (result.next()) {
				books = new Books(result.getInt("bookID"), result.getString("title"), result.getString("author"), result.getString("category"), result.getDouble("rating"));
				bookslist.add(books);
				System.out.println(books);
			}
			conn.close();
		}
		catch(Exception s) {
			System.out.println(s);
		}
		return bookslist;
	}

	public void show_books() {
		ArrayList<Books> list = bookList();
		DefaultTableModel model = (DefaultTableModel)table_book.getModel();
		model.setRowCount(0);
		Object[] row = new Object[5];
		for (int i=0; i<list.size(); i++) {
			row[0] = list.get(i).getID();
			row[1] = list.get(i).getTitle();
			row[2] = list.get(i).getAuthor();
			row[3] = list.get(i).getCategory();
			row[4] = list.get(i).getRating();
			model.addRow(row);
		}
	}
	
	///////////////// - Category Selection - ////////////////////////////
	
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(102, 205, 170));
		frame.setBounds(100, 100, 767, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
				
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setBounds(12, 60, 161, 30);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("choose category");
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
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JComboBox comboBoxS = new JComboBox();
		comboBoxS.setBounds(510, 48, 102, 20);
		frame.getContentPane().add(comboBoxS);
		comboBoxS.addItem("Search by");
		comboBoxS.addItem("bookID");
		comboBoxS.addItem("title");
		comboBoxS.addItem("author");
		comboBoxS.addItem("category");
		comboBoxS.addItem("<rating");

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel lblTeacherBookMenu = new JLabel("User Book Menu");
		lblTeacherBookMenu.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTeacherBookMenu.setBounds(278, 11, 184, 30);
		frame.getContentPane().add(lblTeacherBookMenu);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		textField = new JTextField();
		textField.setBounds(311, 48, 184, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//////////////////////////////// Search Button ////////////////////////////////////////////////////////////////////////////
				ArrayList<Books> bookslists = new ArrayList<>();
				String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
				String username = "root";
				String password = "paroljok";
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, password);
				    if (conn!=null) {
				    	System.out.println("connected");
				    }
				    Statement statement = conn.createStatement();
				    String valueS = textField.getText();
					String comboS = (String)comboBoxS.getSelectedItem();
				    ResultSet result = statement.executeQuery("select * from books where (" + comboS +"  = '" + valueS + "')");
					Books books;
					while (result.next()) {
						books = new Books(result.getInt("bookID"), result.getString("title"), result.getString("author"), result.getString("category"), result.getDouble("rating"));
						bookslists.add(books);
					}
					conn.close();
				}
				catch(Exception s) {
					System.out.println(s);
				}
				
				ArrayList<Books> list = bookslists;
				DefaultTableModel model = (DefaultTableModel)table_book.getModel();
				
				Object[] row = new Object[5];
				model.setRowCount(0);
				
				for (int i=0; i<list.size(); i++) {
					row[0] = list.get(i).getID();
					row[1] = list.get(i).getTitle();
					row[2] = list.get(i).getAuthor();
					row[3] = list.get(i).getCategory();
					row[4] = list.get(i).getRating();
					
					model.addRow(row);
				}
				//model.setRowCount(0);
			//////////////////////////////////////////////////////////////////////////////////////////////////////
			}});
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.setBounds(622, 47, 95, 23);
		frame.getContentPane().add(btnSearch);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton btnNewButton_3 = new JButton("View My Books");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<isbupdated> Ibookslistu = new ArrayList<>();
				String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
				String username = "root";
				String password = "paroljok";
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, password);
				    if (conn!=null) {
				    	System.out.println("connected");
				    }
				    Statement statement = conn.createStatement();
					ResultSet result = statement.executeQuery("select books.bookID, author, title, IssuedDate, isbupdated.operation from books left join isbupdated on "
							+ "books.bookID = isbupdated.bookID where isbupdated.userID = '"+Teacher.getUserId()+"'");
					
					
					isbupdated books;
					while (result.next()) {
						books = new isbupdated(result.getInt("books.bookID"), result.getString("books.author"), result.getString("books.title"), result.getDate("IssuedDate"), result.getString("isbupdated.operation"));
						Ibookslistu.add(books); 	
					}
					conn.close();
				}
				catch(Exception s) {
					System.out.println(s);
				}
				ArrayList<isbupdated> Ilistu = Ibookslistu;
				DefaultTableModel model = (DefaultTableModel)table_book.getModel();
				model.setRowCount(0);
				Object[] row = new Object[5];
				for (int i=0; i<Ilistu.size(); i++) {
					row[0] = Ilistu.get(i).getBookID();
					row[1] = Ilistu.get(i).getAuthor();
					row[2] = Ilistu.get(i).getBookTitle();
					row[3] = Ilistu.get(i).getDate();
					//row[3] = Ilist.get(i).getDate();
					row[4] = Ilistu.get(i).getOperation();
					model.addRow(row);
				}
			}
		});
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.setBounds(10, 338, 161, 31);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Issue/Return Books");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.print(Teacher.getUserId());
				requestBook r = new requestBook();
				r.frame.setVisible(true);
			}
		});
		btnNewButton_4.setBackground(new Color(192, 192, 192));
		btnNewButton_4.setBounds(10, 242, 161, 30);
		frame.getContentPane().add(btnNewButton_4);
			
		JButton btnNewButton_5 = new JButton("View History");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ArrayList<IssuedBooks> Ibookslist = new ArrayList<>();
				String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
				String username = "root";
				String password = "paroljok";
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, password);
				    if (conn!=null) {
				    	System.out.println("connected");
				    }
				    Statement statement = conn.createStatement();
					ResultSet result = statement.executeQuery("select books.bookID, author, title, IssuedDate, issuebook.operation from books left join issuebook on books.bookID = issuebook.bookID where issuebook.userID = '"+Teacher.getUserId()+"'");
					
					
					IssuedBooks books;
					while (result.next()) {
						books = new IssuedBooks(result.getInt("bookID"), result.getString("author"), result.getString("title"), result.getDate("IssuedDate"), result.getString("operation"));
						Ibookslist.add(books); 	
					}
					conn.close();
				}
				catch(Exception s) {
					System.out.println(s);
				}
				ArrayList<IssuedBooks> Ilist = Ibookslist;
				DefaultTableModel model = (DefaultTableModel)table_book.getModel();
				model.setRowCount(0);
				Object[] row = new Object[5];
				for (int i=0; i<Ilist.size(); i++) {
					row[0] = Ilist.get(i).getBookID();
					row[1] = Ilist.get(i).getAuthor();
					row[2] = Ilist.get(i).getBookTitle();
					row[3] = Ilist.get(i).getDate();
					//row[3] = Ilist.get(i).getDate();
					row[4] = Ilist.get(i).getOperation();
					model.addRow(row);
				}
			//////////////////////////////////////////////////////////////////////////////////////////////////////
			}});
			
		
		btnNewButton_5.setBackground(new Color(192, 192, 192));
		btnNewButton_5.setBounds(10, 291, 161, 30);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Teacher t = new Teacher();
				t.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBackground(new Color(70, 130, 180));
		btnBack.setBounds(13, 421, 66, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(94, 421, 74, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnViewBooks = new JButton("view selected books");
		btnViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					ArrayList<Books> bookslistcat = new ArrayList<>();
					String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
					String username = "root";
					String password = "paroljok";
					ArrayList<Integer> booksl = new ArrayList<Integer>();
					try {
						Class.forName("com.mysql.jdbc.Driver");
					    Connection conn = DriverManager.getConnection(url, username, password);
					    if (conn!=null) {
					    	System.out.println("connected");
					    }
					    Statement statement = conn.createStatement();
					    String value = (String)comboBox.getSelectedItem();
						ResultSet result = statement.executeQuery("select * from books where (category = '" + value + "')");
					  
						Books books;
						while (result.next()) {
							books = new Books(result.getInt("bookID"), result.getString("title"), result.getString("author"), result.getString("category"), result.getDouble("rating"));
							bookslistcat.add(books);
						}
						conn.close();
					}
					catch(Exception s) {
						System.out.println(s);
					}
					
					ArrayList<Books> list = bookslistcat;
					DefaultTableModel model = (DefaultTableModel)table_book.getModel();
					
					Object[] row = new Object[5];
					model.setRowCount(0);
					for (int i=0; i<list.size(); i++) {
						row[0] = list.get(i).getID();
						row[1] = list.get(i).getTitle();
						row[2] = list.get(i).getAuthor();
						row[3] = list.get(i).getCategory();
						row[4] = list.get(i).getRating();
						model.addRow(row);
					}
				
			}
		});
		btnViewBooks.setBounds(10, 123, 161, 30);
		frame.getContentPane().add(btnViewBooks);
		btnViewBooks.setBackground(new Color(192, 192, 192));
		
		JButton btnNewButton = new JButton("View all Books");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show_books();
				
			}
		});
		btnNewButton.setBounds(10, 180, 161, 31);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 112, 511, 332);
		frame.getContentPane().add(scrollPane);
		scrollPane.setViewportView(table_book);
		
		
		
		table_book = new JTable();
		table_book.addMouseListener(new MouseAdapter() {
			requestBook ad = new requestBook();
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = table_book.getSelectedRow();
				TableModel model = table_book.getModel();
				
				String title = model.getValueAt(index, 1).toString(); 
				String author = model.getValueAt(index, 2).toString();
			
				ad.frame.setVisible(true);
				//ad.frame.pack();
				ad.frame.setLocationRelativeTo(null);
				ad.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				requestBook.textField.setText(title);
				requestBook.textField_1.setText(author);
			}
		});
		scrollPane.setViewportView(table_book);
		table_book.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, "", null, null},
			},
			new String[] {
				"bookID", "title", "author", "category", "rating", "quantity"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Object.class, double.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_book.getColumnModel().getColumn(0).setPreferredWidth(46);
		table_book.getColumnModel().getColumn(1).setPreferredWidth(127);
		table_book.getColumnModel().getColumn(1).setMinWidth(23);
		table_book.getColumnModel().getColumn(1).setMaxWidth(150);
		table_book.getColumnModel().getColumn(2).setPreferredWidth(123);
		table_book.getColumnModel().getColumn(3).setPreferredWidth(120);
		table_book.getColumnModel().getColumn(4).setPreferredWidth(49);
		table_book.getColumnModel().getColumn(5).setPreferredWidth(50);
		table_book.setColumnSelectionAllowed(true);
		
		
	
		
		}
}
