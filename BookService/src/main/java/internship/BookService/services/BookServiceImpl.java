package internship.BookService.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.BookService.DTO.BookDTO;
import internship.BookService.converters.BookConverter;
import internship.BookService.models.Book;
import internship.BookService.models.Category;
import internship.BookService.repositories.BookRepository;
import internship.BookService.repositories.CategoryRepository;

/**
 * Represents service for BookApplication. Implementation of BookService.
 * 
 * @author r.bogojevic
 *
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	public BookRepository bookRep;
	@Autowired
	public CategoryRepository categoryRep;

	@Override
	public BookDTO save(BookDTO book) {
		if (bookRep.findByTitle(book.getTitle()) == null) {
			bookRep.save(BookConverter.toEntity(book));

			return book;
		}

		return null;
	}

	@Override
	public Book disable(Long id) {
		Book b = bookRep.getOne(id);
		b.setState(Book.State.DELETED);
		bookRep.save(b);

		return b;
	}

	@Override
	public BookDTO edit(BookDTO book) {
		Book b = bookRep.getOne(book.getId());
		b.edit(BookConverter.toEntity(book));
		bookRep.save(b);

		return book;
	}

	@Override
	public List<Book> getAll() {

		return bookRep.findAll();
	}

	@Override
	public List<Book> getTopTen() {
		ArrayList<Book> books = (ArrayList<Book>) bookRep.findAll();
		books.sort(Comparator.comparing(Book::getSoldAmount).reversed());
		if (books.size() >= 10) {
			books = new ArrayList<>(books.subList(0, 10));
		} else {
			books = new ArrayList<>(books.subList(0, books.size()));
		}

		return books;
	}

	@Override
	public List<Book> sort(String input) {

		return bookRep.findLike(input);
	}

	@Override
	public boolean addCategory(String name) {
		if (categoryRep.findByName(name) != null) {

			return false;
		}

		categoryRep.save(new Category(name));

		return true;
	}

	@Override
	public List<Book> getByCategoryId(Long id) {

		return bookRep.findByCategoryId(id);
	}

	@Override
	public List<Category> getAllCategories() {

		return categoryRep.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRep.getOne(id);
	}

}
