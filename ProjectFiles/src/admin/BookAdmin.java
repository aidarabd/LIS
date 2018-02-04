package admin;


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
import javax.swing.JOptionPane;
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
import user.Teacher;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
	
public class BookAdmin {

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
					BookAdmin window = new BookAdmin();
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
	public BookAdmin() {
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
	
	///////////////////////////////////////////////////////////// - Category Selection - /////////////////////////////////////////////////////////////////
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(211, 211, 211));
		frame.setBounds(100, 100, 767, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 43, 111, 30);
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
		comboBoxS.addItem("ID");
		comboBoxS.addItem("title");
		comboBoxS.addItem("author");
		comboBoxS.addItem("category");
		comboBoxS.addItem("<rating");

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel lblTeacherBookMenu = new JLabel("Admin Book Menu");
		lblTeacherBookMenu.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTeacherBookMenu.setBounds(278, 11, 184, 30);
		frame.getContentPane().add(lblTeacherBookMenu);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		textField = new JTextField();
		textField.setBounds(331, 48, 164, 20);
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
		JButton btnNewButton_3 = new JButton("Add / Remove Book");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRemoveBook arb = new AddRemoveBook();
				arb.frame.setVisible(true);
			}
		});
		btnNewButton_3.setBackground(new Color(211, 211, 211));
		btnNewButton_3.setBounds(10, 242, 144, 31);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Issued Books");
		btnNewButton_4.addActionListener(new ActionListener() {
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
					ResultSet result = statement.executeQuery("select books.bookID, author, title, IssuedDate, isbupdated.operation from books left join isbupdated on books.bookID = isbupdated.bookID where isbupdated.operation = 'issued'");
					
					
					isbupdated books;
					while (result.next()) {
						books = new isbupdated(result.getInt("bookID"), result.getString("author"), result.getString("title"), result.getDate("IssuedDate"), result.getString("operation"));
						System.out.println(result.getInt("bookID")+" " +result.getString("author")+" "+ result.getString("title")+result.getDate("IssuedDate") +result.getString("operation"));
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
		btnNewButton_4.setBackground(new Color(211, 211, 211));
		btnNewButton_4.setBounds(10, 180, 144, 30);
		frame.getContentPane().add(btnNewButton_4);
		//////////////////////////////////////////////////// RETURNED RETURNED RETURNED RETURNED RETURNED RETURNED RETURNED RETURNED RETURNED RETURNED ///////////////////////////////////////////
		JButton btnNewButton_5 = new JButton("HISTORY");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
					ResultSet result = statement.executeQuery("select books.bookID, author, title, IssuedDate, issuebook.operation from books left join issuebook on books.bookID = issuebook.bookID");
					
					
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
			}});
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		btnNewButton_5.setBackground(new Color(211, 211, 211));
		btnNewButton_5.setBounds(10, 304, 144, 30);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin t = new Admin();
				t.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBackground(new Color(70, 130, 180));
		btnBack.setBounds(10, 421, 60, 23);
		frame.getContentPane().add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				
			}
		});
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setBounds(80, 421, 74, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnViewBooks = new JButton("view selected books");
		btnViewBooks.setHorizontalAlignment(SwingConstants.LEFT);
		btnViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					ArrayList<Books> bookslistcat = new ArrayList<>();
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
		btnViewBooks.setBounds(122, 43, 150, 30);
		frame.getContentPane().add(btnViewBooks);
		btnViewBooks.setBackground(new Color(211, 211, 211));
		
		JButton btnNewButton = new JButton("View all Books");
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show_books();
				
			}
		});
		btnNewButton.setBounds(10, 122, 144, 31);
		frame.getContentPane().add(btnNewButton);
		
		
		/*table_book.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table_book.rowAtPoint(evt.getPoint());
		        int col = table_book.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		        	JOptionPane.showMessageDialog(null, "What do you want to do","Action completed", JOptionPane.QUESTION_MESSAGE);
		        }
		    }
		});*/
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 123, 511, 322);
		frame.getContentPane().add(scrollPane);
		
		table_book = new JTable();
		table_book.addMouseListener(new MouseAdapter() {
			AddRemoveRowS ad = new AddRemoveRowS();
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table_book.getSelectedRow();
				TableModel model = table_book.getModel();
				
				String title = model.getValueAt(index, 1).toString(); 
				String author = model.getValueAt(index, 2).toString();
				String category = model.getValueAt(index, 3).toString();
			
				ad.frame.setVisible(true);
				//ad.frame.pack();
				ad.frame.setLocationRelativeTo(null);
				ad.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				ad.titleText.setText(title);
				ad.textFieldd_1.setText(author);
				ad.textField.setText(category);
			}
		});
		scrollPane.setViewportView(table_book);
		table_book.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "title", "author", "category", "rating"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Object.class, double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_book.getColumnModel().getColumn(0).setPreferredWidth(37);
		table_book.getColumnModel().getColumn(1).setPreferredWidth(127);
		table_book.getColumnModel().getColumn(1).setMinWidth(23);
		table_book.getColumnModel().getColumn(1).setMaxWidth(150);
		table_book.getColumnModel().getColumn(2).setPreferredWidth(123);
		table_book.getColumnModel().getColumn(3).setPreferredWidth(120);
		table_book.getColumnModel().getColumn(4).setPreferredWidth(49);
		table_book.setColumnSelectionAllowed(true);
		
		JButton btnClearHistory = new JButton("Clear History");
		btnClearHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = "jdbc:mysql://localhost:3306/lis?verifyServerCertificate=false&useSSL=true";
				String username = "root";
				String password = "paroljok";
				try {
					Class.forName("com.mysql.jdbc.Driver");
				    Connection conn = DriverManager.getConnection(url, username, password);
				    Statement st = conn.createStatement();
				    ResultSet res = st.executeQuery("select * from issuebook");
				    
				    int counter=0;
				    while (res.next()) {
				    	counter++;
				    }
				    int option = JOptionPane.showConfirmDialog(frame, "Please take into account you are going to delete all "+counter+ "records ", "confirm message", JOptionPane.WARNING_MESSAGE);
					if (option == JOptionPane.OK_OPTION) {
						st.executeUpdate("truncate issuebook");
					}
				    conn.close();
				}
				catch(Exception s) {
					System.out.println(s);
				}
			}
		});
		btnClearHistory.setBackground(new Color(211, 211, 211));
		btnClearHistory.setBounds(10, 357, 144, 30);
		frame.getContentPane().add(btnClearHistory);
		}
}
