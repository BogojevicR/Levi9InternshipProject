package internship.BookService.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import internship.BookService.models.Book;
import internship.BookService.services.BookServiceImpl;
/**
 * REST Controller for book application.
 *  
 * @author r.bogojevic
 *
 */
@RestController
@RequestMapping("/api/book")
public class BookController {
	/**
	 * Implemented BookService.
	 */
	@Autowired
	public BookServiceImpl bookService;
	/**
	 * Method for saving a book in database.
	 * @param book book which will be saved.
	 * @return book which is saved, together with HTTP status.
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<Book> save(@RequestBody Book book) {
		System.out.println(book);
		Book response = bookService.save(book);
		return new ResponseEntity<Book>(response, HttpStatus.OK);
	}
	/**
	 * Method for editing a book.
	 * @param book book for editing.
	 * @return boolean value if book is edited, together with HTTP status.
	 */
	@RequestMapping(value = "edit", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> edit(@RequestBody Book book) {
		return new ResponseEntity<Boolean>(bookService.edit(book),HttpStatus.OK);
	}
	/**
	 * Collects all existing books in database.
	 * @return List of all books in database, together with HTTP status.
	 */
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Book>> getAll(){
		return new ResponseEntity<ArrayList<Book>>(bookService.getAll(),HttpStatus.OK);
	}
	/**
	 * Remove book with chosen id from sale.
	 * @param id from book for disable.
	 * @return returns Book entity, together with HTTP status.
	 */
	@RequestMapping(value = "disable/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Book> disable(@PathVariable Long id) {
		return new ResponseEntity<Book>(bookService.disable(id),HttpStatus.OK);
	}
	/**
	 * Collects top ten most sold books.
	 * @return returns top ten sold books, together with HTTP status.
	 */
	@RequestMapping(value = "getTopTen", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Book>> getTopTen(){
		return new ResponseEntity<ArrayList<Book>>(bookService.getTopTen(),HttpStatus.OK);
	}
	/**
	 * Sorts all books in database by author or title.
	 * @param input value for sorting.
	 * @return List of books which have input value in author or title, together with HTTP status.
	 */
	@RequestMapping(value = "sort/{input}", method = RequestMethod.GET )
	public ResponseEntity<ArrayList<Book>> sort(@PathVariable String input){
		return new ResponseEntity<ArrayList<Book>>(bookService.sort(input),HttpStatus.OK);
	}
	/**
	 * Adds category to database.
	 * @param name name of category.
	 * @return returns whether category is saved or not in boolean value, together with HTTP status.
	 */
	@RequestMapping(value = "addCategory/{name}", method = RequestMethod.POST )
	public ResponseEntity<Boolean> addCategory(@PathVariable String name) {
		return new ResponseEntity<Boolean>(bookService.addCategory(name),HttpStatus.OK);
	}
	/**
	 * Collects all books from chosen category.
	 * @param id id of category.
	 * @return List of books which are in chosen category, together with HTTP status.
	 */
	@RequestMapping(value = "getByCategory/{id}", method = RequestMethod.GET )
	public ResponseEntity<ArrayList<Book>> getByCategoryId(@PathVariable Long id){
		return new ResponseEntity<ArrayList<Book>>(bookService.getByCategoryId(id),HttpStatus.OK);
	}
}
