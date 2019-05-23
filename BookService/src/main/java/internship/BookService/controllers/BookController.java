package internship.BookService.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.BookService.DTO.BookDTO;
import internship.BookService.helpers.RequestSenderHelper;
import internship.BookService.models.Book;
import internship.BookService.models.Category;
import internship.BookService.services.BookServiceImpl;
/**
 * REST Controller for book application.
 *  
 * @author r.bogojevic
 *
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	public RequestSenderHelper requestSrvice;
	@Autowired
	public BookServiceImpl bookService;
	
	/**
	 * Method for saving a book in database.
	 * @param book book which will be saved.
	 * @return book which is saved, together with HTTP status.
	 */
	@PostMapping(value = "save")
	public ResponseEntity<BookDTO> save(@RequestBody BookDTO book, HttpServletRequest request) {
		/*try {
			requestSrvice.makeTokenCheck(requestSrvice.getCookie(request));
		} catch (IOException e) {
			
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}*/
		BookDTO response = bookService.save(book);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Method for editing a book.
	 * @param book book for editing.
	 * @return boolean value if book is edited, together with HTTP status.
	 */
	@PutMapping(value = "edit")
	public ResponseEntity<BookDTO> edit(@RequestBody BookDTO book, HttpServletRequest request) {
		try {
			requestSrvice.makeTokenCheck(requestSrvice.getCookie(request));
		} catch (IOException e) {
			
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(bookService.edit(book),HttpStatus.OK);
	}
	
	/**
	 * Collects all existing books in database.
	 * @return List of all books in database, together with HTTP status.
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "getAll")
	public ResponseEntity<List<Book>> getAll( HttpServletRequest request){
		
		return new ResponseEntity<>(bookService.getAll(),HttpStatus.OK);
	}
	
	/**
	 * Remove book with chosen id from sale.
	 * @param id from book for disable.
	 * @return returns Book entity, together with HTTP status.
	 */
	@PutMapping(value = "disable/{id}")
	public ResponseEntity<Book> disable(@PathVariable Long id, HttpServletRequest request) {
		try {
			requestSrvice.makeTokenCheck(requestSrvice.getCookie(request));
		} catch (IOException e) {
			
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(bookService.disable(id),HttpStatus.OK);
	}
	
	/**
	 * Collects top ten most sold books.
	 * @return returns top ten sold books, together with HTTP status.
	 */
	@GetMapping(value = "getTopTen")
	public ResponseEntity<List<Book>> getTopTen(){
		
		return new ResponseEntity<>(bookService.getTopTen(),HttpStatus.OK);
	}
	
	/**
	 * Sorts all books in database by author or title.
	 * @param input value for sorting.
	 * @return List of books which have input value in author or title, together with HTTP status.
	 */
	@GetMapping(value = "sort/{input}")
	public ResponseEntity<List<Book>> sort(@PathVariable String input){
		
		return new ResponseEntity<>(bookService.sort(input),HttpStatus.OK);
	}
	
	/**
	 * Adds category to database.
	 * @param name name of category.
	 * @return returns whether category is saved or not in boolean value, together with HTTP status.
	 */
	@PostMapping(value = "addCategory/{name}")
	public ResponseEntity<Boolean> addCategory(@PathVariable String name, HttpServletRequest request) {
		System.out.println("USAOOOO" + name);
		/*try {
			requestSrvice.makeTokenCheck(requestSrvice.getCookie(request));
		} catch (IOException e) {
		
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}*/
		
		return new ResponseEntity<>(bookService.addCategory(name),HttpStatus.OK);
	}
	
	/**
	 * Collects all books from chosen category.
	 * @param id id of category.
	 * @return List of books which are in chosen category, together with HTTP status.
	 */
	@GetMapping(value = "getByCategory/{id}")
	public ResponseEntity<List<Book>> getByCategoryId(@PathVariable Long id){

		return new ResponseEntity<>(bookService.getByCategoryId(id),HttpStatus.OK);
	}
	

	/**
	 * Collects all categories.
	 * @return List of all categories
	 */
	@GetMapping(value = "/getAllCategories")
	public ResponseEntity<List<Category>> getAllCategories(){		
		return new ResponseEntity<>(bookService.getAllCategories(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/getCategory/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id){
		Category category = bookService.getCategoryById(id);
		System.out.println("Kategorija je **" + category.getId());
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
}
