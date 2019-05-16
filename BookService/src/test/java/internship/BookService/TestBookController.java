package internship.BookService;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import internship.BookService.models.Book;
import internship.BookService.models.Book.State;
import internship.BookService.models.Category;
import internship.BookService.services.BookServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class TestBookController {

	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public BookServiceImpl bookService;
	
	
/*	@Test
	public void saveShouldReturnSavedBook() throws Exception {
		Book b = new Book(new Long(3), "Title", "Author1", new Category(new Long(1), "cat"), 20, State.ACTIVE, 20);
		when(bookService.save(b)).thenReturn(b);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/book/save").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(b))).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(bookService).save(b);
	} 
	
	@Test
	public void getAllShouldReturnAllBooks() throws Exception{
		List<Book> list = new ArrayList<Book>();
		Book book1 = new Book(new Long(1), "title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Book book2 = new Book(new Long(2), "title2", "Author2", new Category("category2"), 20, Book.State.ACTIVE, 20);

        list.add(book1);
        list.add(book2);
        
        String json = new Gson().toJson(list );
     
        when(bookService.getAll()).thenReturn((ArrayList<Book>) list);
        
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/book/getAll")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(json));
        
        verify(bookService).getAll();
	} 
	
	@Test
	public void disableShouldSetBookToDisabled() throws Exception {
		Book book = new Book(new Long(1), "title1", "Author1", new Category(new Long(1),"category1"), 10, Book.State.ACTIVE, 10);
		Book response = new Book(new Long(1), "title1", "Author1", new Category(new Long(1),"category1"), 10, Book.State.DELETED, 10);
        
		when(bookService.disable(book.getId())).thenReturn(response);
        
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/book/disable/{id}",book.getId())).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(response)));
	}

	@Test
	public void editBookShouldReturnTrue() throws Exception {
		
		Book edit = new Book(new Long(1),"t", "A", new Category("c"), 1, Book.State.DELETED, 1);
		when(bookService.edit(edit)).thenReturn(true);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/book/edit").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(edit)))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		verify(bookService).edit(edit);
	}    
	
	@Test
	public void getTopTenShouldReturnNineBooks() throws Exception{
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

		resultList.add(book9);
		resultList.add(book8);
		resultList.add(book7);
		resultList.add(book6);
		resultList.add(book5);
		resultList.add(book4);
		resultList.add(book3);
		resultList.add(book2);
		resultList.add(book1);
		
		when(bookService.getTopTen()).thenReturn((ArrayList<Book>) resultList);	     
		
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/book/getTopTen")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(resultList)));;
		
        verify(bookService).getTopTen();
		
	}
	
	@Test
	public void getTopTenShouldReturnBestTenBooks() throws Exception{
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

		resultList.add(book9);
		resultList.add(book8);
		resultList.add(book7);
		resultList.add(book6);
		resultList.add(book5);
		resultList.add(book4);
		resultList.add(book3);
		resultList.add(book2);
		resultList.add(book1);
		resultList.add(0,book10);
		resultList.add(0,book11);
		resultList.remove(10);
		
		when(bookService.getTopTen()).thenReturn((ArrayList<Book>) resultList);	     
		
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/book/getTopTen"))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(resultList)));;
		
        verify(bookService).getTopTen();
	}
	
	@Test
	public void sortShouldReturnSortedBooks() throws Exception{
		List<Book> resultList = new ArrayList<Book>();
		Category c = new Category(new Long(1),"name");
		Book book1 = new Book(new Long(1), "title1", "Author1", c, 10, Book.State.ACTIVE, 10, 10);
		Book book2 = new Book(new Long(2), "title2", "Author2", c, 20, Book.State.ACTIVE, 20, 20);
		
		resultList.add(book1);
		resultList.add(book2);
		
		when(bookService.sort("title")).thenReturn((ArrayList<Book>) resultList);	    
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/book/sort/{input}","title"))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(resultList)));;
	
		verify(bookService).sort("title");
	}
	
	@Test
	public void addCategoryShouldReturnTrue() throws Exception{
		
		when(bookService.addCategory("category1")).thenReturn(true);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/book/addCategory/{name}","category1"))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("true"));
	
		verify(bookService).addCategory("category1");
	}
	
	@Test
	public void getByCategoryShouldReturnListOfBooks() throws Exception{
		List<Book> resultList = new ArrayList<Book>();
		Category c = new Category(new Long(1),"name");
		Book book1 = new Book(new Long(1), "title1", "Author1", c, 10, Book.State.ACTIVE, 10, 10);
		Book book2 = new Book(new Long(2), "title2", "Author2", c, 20, Book.State.ACTIVE, 20, 20);
		
		resultList.add(book1);
		resultList.add(book2);
		
		when(bookService.getByCategoryId(c.getId())).thenReturn((ArrayList<Book>) resultList);	    
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/book/getByCategory/{id}",c.getId()))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(resultList)));
	
		verify(bookService).getByCategoryId(c.getId());
	}
	
	*/
}
