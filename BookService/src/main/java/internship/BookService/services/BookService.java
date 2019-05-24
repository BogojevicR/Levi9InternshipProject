package internship.BookService.services;

import java.util.List;

import internship.BookService.DTO.BookDTO;
import internship.BookService.models.Book;
import internship.BookService.models.Category;

/**
 * Interface for BookService.
 * 
 * @author r.bogojevic
 *
 */
public interface BookService {

	/**
	 * Saves selected book in database.
	 * 
	 * @param book book for saving.
	 * @return book if it's saved, null if it's not.
	 */
	BookDTO save(BookDTO book);

	/**
	 * Remove book with chosen id from sale.
	 * 
	 * @param id from book for disable.
	 * @return Book entity with chosen id.
	 */
	Book disable(Long id);

	/**
	 * Edits selected book.
	 * 
	 * @param book book for editing.
	 * @return boolean value if book is edited.
	 */
	BookDTO edit(BookDTO book);

	/**
	 * Collects all existing books in database.
	 * 
	 * @return List of all books.
	 */
	List<Book> getAll();

	/**
	 * Collects top ten sold books.
	 * 
	 * @return List of top ten sold books.
	 */
	List<Book> getTopTen();

	/**
	 * Sorts all books by author or title.
	 * 
	 * @param input value for sorting.
	 * @return List of all books which have input value in author or title.
	 */
	List<Book> sort(String input);

	/**
	 * Adds new category.
	 * 
	 * @param name name of category.
	 * @return returns whether category is saved or not in boolean value.
	 */
	boolean addCategory(String name);

	/**
	 * Collects all books from chosen category.
	 * 
	 * @param id id of category.
	 * @return List of books which are in chosen category.
	 */
	List<Book> getByCategoryId(Long id);

	List<Category> getAllCategories();

	Category getCategoryById(Long id);
	
	List<Book> getActiveBooks();
	
	Book getBookById(Long id);

}
