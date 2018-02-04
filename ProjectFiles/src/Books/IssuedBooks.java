package Books;

import java.util.Date;

public class IssuedBooks {
	int bookID;
	String author;
	String bookTitle;
	Date date;
	String operation;
	public IssuedBooks(int bookID, String author, String title, Date date, String operation) {
		this.bookID = bookID;
		this.author = author;
		this.bookTitle = title;
		this.date = date;
		this.operation = operation;
	}
	public int getBookID() {
		return bookID;
	}
	public String getAuthor() {
		return author;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public Date getDate() {
		return date;
	}
	public String getOperation() {
		return operation;
	}
	
}
