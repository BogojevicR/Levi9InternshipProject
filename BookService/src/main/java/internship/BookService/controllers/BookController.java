package internship.BookService.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	public boolean save(@RequestBody Book book) {
		return bookService.save(book);
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.PUT)
	public boolean edit(@RequestBody Book book) {
		return bookService.edit(book);
	}
	
	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public ArrayList<Book> getAll(){
		return bookService.getAll();
	}
	
	@RequestMapping(value = "disable/{id}", method = RequestMethod.PUT )
	public boolean disable(@PathVariable Long id) {
		return bookService.disable(id);
	}
	
	@RequestMapping(value = "getTopTen", method=RequestMethod.GET)
	public ArrayList<Book> getTopTen(){
		return bookService.getTopTen();
	}
	
	@RequestMapping(value = "sort/{input}", method = RequestMethod.GET )
	public ArrayList<Book> sort(@PathVariable String input){
		return bookService.sort(input);
	}
	
	@RequestMapping(value = "addCategory/{name}", method = RequestMethod.POST )
	public boolean addCategory(@PathVariable String name) {
		return bookService.addCategory(name);
	}
	
	@RequestMapping(value = "getByCategory/{id}", method = RequestMethod.GET )
	public ArrayList<Book> getByCategoryId(@PathVariable Long id){
		return bookService.getByCategoryId(id);
	}
}
