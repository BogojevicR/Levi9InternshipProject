package internship.BookService.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import internship.BookService.models.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	Book findByTitle(String title);

	@Query("SELECT b FROM Book b WHERE b.author LIKE %:input% OR b.title LIKE %:input%")
	ArrayList<Book> findLike(@Param("input")String input);
	
	ArrayList<Book> findByCategoryId(Long id);
}
