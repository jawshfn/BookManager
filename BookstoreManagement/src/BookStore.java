import java.util.ArrayList;
import java.util.List;

public class BookStore {
	
	private List<Book> books = new ArrayList<>();

	public void addBook(Book book) {
		books.add(book);
	}

	public void removeBook(String isbn) {
		for (Book book : books) {
			if (book.getISBN().equals(isbn)) {
				book.setQuantity(book.getQuantity() - 1);

				if (book.getQuantity() == 0) {
					books.remove(book);
				}

				break;
			}
		}
	}

	public void removeAllBooks(String isbn) {
		books.removeIf(book -> book.getISBN().equals(isbn));
	}

	public boolean deleteBook(String ISBN) {
		return books.removeIf(book -> book.getISBN().equals(ISBN));
	}

	public Book findBook(String ISBN) {
		for (Book book : books) {
			if (book.getISBN().equals(ISBN)) {
				return book;
			}
		}
		return null;
	}

	public void updateBook(String ISBN, Book newBook) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getISBN().equals(ISBN)) {
				books.set(i, newBook);
				return;
			}
		}
	}

	public List<Book> getAllBooks() {
		return books;
	}
}
