package internship.BookService;


import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import com.google.gson.Gson;

import internship.BookService.controllers.BookController;
import internship.BookService.models.Book;
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
	
	
	@Test
	public void saveShouldReturnSavedBook() throws Exception {
		Category c = new Category(new Long(2), "category1");
		Book book = new Book(new Long(1), "title1", "Author1", c, 10, Book.State.ACTIVE, 10);
		
		when(bookService.save(book)).thenReturn(book);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/book/save").contentType(MediaType.APPLICATION_JSON_UTF8).content((new Gson().toJson(book)))).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
	}
	
/*	@Test
	public void getAllShouldReturnAllBooks() throws Exception{
		List<Book> list = new ArrayList<Book>();
		Book book1 = new Book(new Long(1), "title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Book book2 = new Book(new Long(2), "title2", "Author2", new Category("category2"), 20, Book.State.ACTIVE, 20);

        list.add(book1);
        list.add(book2);
        
        String json = new Gson().toJson(list );
     
        when(bookService.getAll()).thenReturn((ArrayList<Book>) list);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/book/getAll")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(json));;
	}
	
	@Test
	public void disableShouldSetBookToDisabled() throws Exception {
		Book book = new Book(new Long(1), "title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
        when(bookService.disable(book.getId())).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/book/disable/{id}",book.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("true"));
	}

	@Test
	public void editBookShouldReturnTrue() throws Exception {
		Book book = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Book edit = new Book(new Long(1),"t", "A", new Category("c"), 1, Book.State.DELETED, 1);
		when(bookService.edit(edit)).thenReturn(true);
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/book/edit").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(edit))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("true"));
	}  */
}
