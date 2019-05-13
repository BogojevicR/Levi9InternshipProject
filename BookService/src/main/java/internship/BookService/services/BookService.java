package internship.BookService.services;

import java.util.ArrayList;

import internship.BookService.models.Book;

public interface BookService {

	Book save(Book book);
	Book disable(Long id);
	boolean edit(Book book);
	ArrayList<Book> getAll();
	ArrayList<Book> getTopTen();
	ArrayList<Book> sort(String input);
	boolean addCategory(String name);
	ArrayList<Book> getByCategoryId(Long id);
	
}
