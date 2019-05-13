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

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	public BookServiceImpl bookService;
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<Book> save(@RequestBody Book book) {
		System.out.println(book);
		Book response = bookService.save(book);
		return new ResponseEntity<Book>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.PUT)
	public boolean edit(@RequestBody Book book) {
		return bookService.edit(book);
	}
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Book>> getAll(){
		return new ResponseEntity<ArrayList<Book>>(bookService.getAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "disable/{id}", method = RequestMethod.PUT )
	public ResponseEntity<Book> disable(@PathVariable Long id) {
		return new ResponseEntity<Book>(bookService.disable(id),HttpStatus.OK);
	}
	
	@RequestMapping(value = "getTopTen", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Book>> getTopTen(){
		return new ResponseEntity<ArrayList<Book>>(bookService.getTopTen(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "sort/{input}", method = RequestMethod.GET )
	public ResponseEntity<ArrayList<Book>> sort(@PathVariable String input){
		return new ResponseEntity<ArrayList<Book>>(bookService.sort(input),HttpStatus.OK);
	}
	
	@RequestMapping(value = "addCategory/{name}", method = RequestMethod.POST )
	public ResponseEntity<Boolean> addCategory(@PathVariable String name) {
		return new ResponseEntity<Boolean>(bookService.addCategory(name),HttpStatus.OK);
	}
	
	@RequestMapping(value = "getByCategory/{id}", method = RequestMethod.GET )
	public ResponseEntity<ArrayList<Book>> getByCategoryId(@PathVariable Long id){
		return new ResponseEntity<ArrayList<Book>>(bookService.getByCategoryId(id),HttpStatus.OK);
	}
}
