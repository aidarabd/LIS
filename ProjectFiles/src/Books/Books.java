package Books;

public class Books {
	private int bookID, quantity;
	private double rating;
	private String title, author, category;
	public Books(int ID, String title, String author, String category, double rating) {
		this.bookID = ID;
		this.title = title;
		this.author = author;
		this.category = category;
		this.rating = rating;
	
	}
	public int getID() {
		return bookID;
	}
	public double getRating() {
		return rating;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	
	public String getCategory() {
		return category;
	}
	public int getQuantity() {
		return quantity;
	}
	@Override
	public String toString() {
		return "Books [bookID=" + bookID + ", quantity=" + quantity + ", rating=" + rating + ", title=" + title
				+ ", author=" + author + ", category=" + category + "]";
	}
}
