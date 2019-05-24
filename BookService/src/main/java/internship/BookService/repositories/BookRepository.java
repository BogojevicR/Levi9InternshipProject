package internship.BookService.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import internship.BookService.models.Book;

/**
 * Represents connection for Book model and Book table in database.
 * 
 * @author r.bogojevic
 *
 */
public interface BookRepository extends JpaRepository<Book, Long> {

	/**
	 * Finds book by its title from database.
	 * 
	 * @param title chosen value of title.
	 * @return book with chosen title.
	 */
	Book findByTitle(String title);

	/**
	 * Finds List of books which contain input value in author or title.
	 * 
	 * @param input value for sorting.
	 * @return List of sorted books.
	 */
	@Query("SELECT b FROM Book b WHERE b.state = 0 AND b.author LIKE %:input% OR b.title LIKE %:input%")
	ArrayList<Book> findLike(@Param("input") String input);

	/**
	 * Finds List of books by category id.
	 * 
	 * @param id of category.
	 * @return List of books from chosen category.
	 */
	ArrayList<Book> findByCategoryId(Long id);

	/**
	 * Finds List of books sorted by number of sold copy.
	 * 
	 * @return List of books sorted by number of copy.
	 */
	@Query("SELECT b FROM Book b ORDER BY sold_amount DESC")
	ArrayList<Book> findTopTen();
	
	@Query("SELECT b FROM Book b where state = 0")
	ArrayList<Book> getActiveBooks();
}
