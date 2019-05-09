package internship.BookService.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.BookService.models.Book;
import internship.BookService.models.Book.State;
import internship.BookService.models.Category;
import internship.BookService.repositories.BookRepository;
import internship.BookService.repositories.CategoryRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	public BookRepository bookRep;
	@Autowired
	public CategoryRepository categoryRep;
	
	@Override
	public boolean save(Book book) {
		if(bookRep.findByTitle(book.getTitle()) == null) {
			bookRep.save(book);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean disable(Long id) {
		Optional<Book> b = bookRep.findById(id);
		if(b == null)
			return false;
		b.get().setState(Book.State.DELETED);
		bookRep.save(b.get());
		return true;
	}
	
	@Override
	public boolean edit(Book book) {
		Optional<Book> b = bookRep.findById(book.getId());
		if(b == null)
			return false;
		b.get().edit(book);
		bookRep.save(b.get());
		return true;
	}

	@Override
	public ArrayList<Book> getAll() {
		return (ArrayList<Book>) bookRep.findAll();
	}

	@Override
	public ArrayList<Book> getTopTen() {
		//TODO: Uradi preko querryija TopTen
		ArrayList<Book> books = (ArrayList<Book>) bookRep.findAll();
		books.sort(Comparator.comparing(Book::getSoldAmount).reversed());
		if(books.size() >= 10) {
			books = new ArrayList<>(books.subList(0,10));
		}else {
			books = new ArrayList<>(books.subList(0,books.size()));
		}
			
		return books;
	}

	@Override
	public ArrayList<Book> sort(String input) {
		return (ArrayList<Book>) bookRep.findLike(input);
		
	}

	@Override
	public boolean addCategory(String name) {
		System.out.println(name);
		if(categoryRep.findByName(name) != null)
			return false;
		categoryRep.save(new Category(name));
		return true;
	}

	@Override
	public ArrayList<Book> getByCategoryId(Long id) {
		
		return (ArrayList<Book>) bookRep.findByCategoryId(id);
	}


}
