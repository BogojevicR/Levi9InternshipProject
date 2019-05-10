package internship.BookService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

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
	BookServiceImpl service;
	
	@Mock
	BookRepository bookRep;
	
	@Mock
	CategoryRepository categoryRep;

	
	@Test
	public void saveTest(){
		Book book = new Book("title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
	    service.save(book);
	    Mockito.verify(bookRep, Mockito.times(1)).save(book);
	 }
	
	@Test
	public void saveBookWithSameTitleTest() {
		Book book = new Book("title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		service.save(book);
		Mockito.when(bookRep.findByTitle(book.getTitle())).thenReturn(book);
		service.save(book);
		Mockito.verify(bookRep, Mockito.times(1)).save(book);
	}
	 
	@Test
	public void disableTest() {
		Book book = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Mockito.when(bookRep.getOne(book.getId())).thenReturn(book);
		service.disable(book.getId());
		assertEquals(book.getState(), Book.State.DELETED);
		
		//Book doesnt exist test
		Mockito.when(bookRep.getOne(book.getId())).thenReturn(null);
		boolean response = service.disable(book.getId());
		assertEquals(false, response);
	}

	
	@Test
	public void editTest() {
		Book book = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Book edit = new Book(new Long(1),"t", "A", new Category("c"), 1, Book.State.DELETED, 1);
		Mockito.when(bookRep.getOne(book.getId())).thenReturn(book);
		service.edit(edit);
		assertEquals(book.getTitle(), edit.getTitle());
		assertEquals(book.getAuthor(), edit.getAuthor());
		assertEquals(book.getCategory(), edit.getCategory());
		assertEquals(book.getState(), edit.getState());
		assertEquals(book.getPrice(), edit.getPrice(),0);
		assertEquals(book.getQuantity(), edit.getQuantity(),0);
		assertEquals(book.getSoldAmount(), edit.getSoldAmount(),0);
		
		//Book doesnt exist text
		Mockito.when(bookRep.getOne(book.getId())).thenReturn(null);
		boolean response = service.edit(edit);
		assertEquals(false, response);
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
         
        Mockito.when(bookRep.findAll()).thenReturn(list);
         

         
        assertEquals(3, service.getAll().size());
        Mockito.verify(bookRep, Mockito.times(1)).findAll();
	}
	
	@Test
	public void addCategoryTest() {
		Category c = new Category("name");
		Mockito.when(categoryRep.findByName(c.getName())).thenReturn(null);
		service.addCategory(c.getName());
		boolean response = service.addCategory(c.getName());
		assertEquals(true, response);

		//Category allready exists
		Mockito.when(categoryRep.findByName(c.getName())).thenReturn(c);
	    response = service.addCategory(c.getName());
		assertEquals(false, response);
	}
	
/*	@Test
	public void getListByCategoryId() {
		
	}
	*/
}
