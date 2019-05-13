package internship.BookService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import internship.BookService.models.Book;
import internship.BookService.models.Category;
import internship.BookService.repositories.BookRepository;
import internship.BookService.repositories.CategoryRepository;
import internship.BookService.services.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestBookService {
	
	@InjectMocks
	BookServiceImpl bookService;
	
	@Mock
	BookRepository bookRep;
	
	@Mock
	CategoryRepository categoryRep;

	
	@Test
	public void saveTest(){
		Book book = new Book("title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
	    bookService.save(book);
	    verify(bookRep, times(1)).save(book);
	    verify(bookRep).save(book);
	 }
	
	@Test
	public void saveBookWithSameTitleTest() {
		Book book = new Book("title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		bookService.save(book);
		
		when(bookRep.findByTitle(book.getTitle())).thenReturn(book);
		
		bookService.save(book);
		
		verify(bookRep, times(1)).save(book);
		verify(bookRep).save(book);
	}
	 
	@Test
	public void disableTest() {
		Book book = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		when(bookRep.getOne(book.getId())).thenReturn(book);
		bookService.disable(book.getId());
		assertEquals(book.getState(), Book.State.DELETED);
		
		//Book doesnt exist test
		when(bookRep.getOne(book.getId())).thenReturn(null);
		verify(bookRep,times(1)).save(book);
	}

	
	@Test
	public void editTest() {
		Book book = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Book edit = new Book(new Long(1),"t", "A", new Category("c"), 1, Book.State.DELETED, 1);
		
		when(bookRep.getOne(book.getId())).thenReturn(book);
		bookService.edit(edit);
		
		assertEquals(book.getTitle(), edit.getTitle());
		assertEquals(book.getAuthor(), edit.getAuthor());
		assertEquals(book.getCategory(), edit.getCategory());
		assertEquals(book.getState(), edit.getState());
		assertEquals(book.getPrice(), edit.getPrice(),0);
		assertEquals(book.getQuantity(), edit.getQuantity(),0);
		assertEquals(book.getSoldAmount(), edit.getSoldAmount(),0);

		//Book doesnt exist text
		when(bookRep.getOne(book.getId())).thenReturn(null);
		
		boolean response = bookService.edit(edit);
		
		assertEquals(false, response);
		verify(bookRep,times(2)).getOne(book.getId());
	}
	
	
	@Test
	public void getAllTest() {
		List<Book> list = new ArrayList<Book>();
		Book book1 = new Book("title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Book book2 = new Book("title2", "Author2", new Category("category2"), 20, Book.State.ACTIVE, 20);
		Book book3 = new Book("title3", "Author3", new Category("category3"), 30, Book.State.ACTIVE, 30);

        list.add(book1);
        list.add(book2);
        list.add(book3);
         
        when(bookRep.findAll()).thenReturn(list);
         

         
        assertEquals(3, bookService.getAll().size());
        verify(bookRep, times(1)).findAll();
	}
	
	@Test
	public void getTopTen() {
		List<Book> list = new ArrayList<Book>();
		List<Book> resultList = new ArrayList<Book>();
		Category c = new Category(new Long(1),"name");
		Book book1 = new Book(new Long(1), "title1", "Author1", c, 10, Book.State.ACTIVE, 10, 10);
		Book book2 = new Book(new Long(2), "title2", "Author2", c, 20, Book.State.ACTIVE, 20, 20);
		Book book3 = new Book(new Long(3), "title1", "Author1", c, 10, Book.State.ACTIVE, 30, 30);
		Book book4 = new Book(new Long(4), "title2", "Author2", c, 20, Book.State.ACTIVE, 40, 40);
		Book book5 = new Book(new Long(5), "title1", "Author1", c, 10, Book.State.ACTIVE, 50, 50);
		Book book6 = new Book(new Long(6), "title2", "Author2", c, 20, Book.State.ACTIVE, 60, 60);
		Book book7 = new Book(new Long(7), "title1", "Author1", c, 10, Book.State.ACTIVE, 70, 70);
		Book book8 = new Book(new Long(8), "title2", "Author2", c, 20, Book.State.ACTIVE, 80, 80);
		Book book9 = new Book(new Long(9), "title1", "Author1", c, 10, Book.State.ACTIVE, 90, 90);
		Book book10 = new Book(new Long(10), "title2", "Author2", c, 20, Book.State.ACTIVE, 100, 100);
		Book book11 = new Book(new Long(11), "title2", "Author2", c, 20, Book.State.ACTIVE, 110, 110);
		
		list.add(book1);
		list.add(book2);
		list.add(book3);
		list.add(book4);
		list.add(book5);
		list.add(book6);
		list.add(book7);
		list.add(book8);
		list.add(book9);

		resultList.add(book9);
		resultList.add(book8);
		resultList.add(book7);
		resultList.add(book6);
		resultList.add(book5);
		resultList.add(book4);
		resultList.add(book3);
		resultList.add(book2);
		resultList.add(book1);
		//When list have less than 10 books
		when(bookRep.findAll()).thenReturn(list);	     
	    assertEquals(resultList, bookService.getTopTen());

	    list.add(book10);
		list.add(book11);
		
		resultList.add(0,book10);
		resultList.add(0,book11);
		resultList.remove(10);
		//When list have more than 10 books
		when(bookRep.findAll()).thenReturn(list);
		assertEquals(resultList, bookService.getTopTen());
		
	}
	
	@Test
	public void sortTest() {
		List<Book> resultList = new ArrayList<Book>();
		Category c = new Category(new Long(1),"name");
		Book book1 = new Book(new Long(1), "title1", "Author1", c, 10, Book.State.ACTIVE, 10, 10);
		Book book3 = new Book(new Long(3), "title1", "Author1", c, 10, Book.State.ACTIVE, 30, 30);

		resultList.add(book1);
		resultList.add(book3);
		
		when(bookRep.findLike("title1")).thenReturn((ArrayList<Book>) resultList);
		assertEquals(resultList, bookService.sort("title1"));
		verify(bookRep,times(1)).findLike("title1");
	}
	
	
	@Test
	public void addCategoryTest() {
		Category c = new Category("name");
		when(categoryRep.findByName(c.getName())).thenReturn(null);
		bookService.addCategory(c.getName());
		boolean response = bookService.addCategory(c.getName());
		assertEquals(true, response);

		//Category allready exists
		when(categoryRep.findByName(c.getName())).thenReturn(c);
	    response = bookService.addCategory(c.getName());
		assertEquals(false, response);
		
	}
	
	@Test
	public void getListByCategoryId() {
		List<Book> list = new ArrayList<Book>();
		Category c = new Category(new Long(1),"name");
		Book book1 = new Book("title1", "Author1", c, 10, Book.State.ACTIVE, 10);
		Book book2 = new Book("title2", "Author2", c, 20, Book.State.ACTIVE, 20);

		list.add(book1);
		list.add(book2);

		
		 when(bookRep.findByCategoryId(c.getId())).thenReturn((ArrayList<Book>) list);
		 
		 assertEquals(2, bookService.getByCategoryId(c.getId()).size());
		 verify(bookRep,times(1)).findByCategoryId(c.getId());
		
	}
		
}
