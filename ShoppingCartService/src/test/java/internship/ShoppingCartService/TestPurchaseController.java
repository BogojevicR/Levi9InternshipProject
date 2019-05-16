package internship.ShoppingCartService;

import java.util.ArrayList;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.Book.State;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Category;
import internship.ShoppingCartService.models.Purchase;
import internship.ShoppingCartService.models.Purchase;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.User.Role;
import internship.ShoppingCartService.services.PurchaseServiceImpl;
import internship.ShoppingCartService.services.ShoppingCartServiceImpl;



@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class TestPurchaseController {

	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public PurchaseServiceImpl receiptService;
	
	@MockBean
	public ShoppingCartServiceImpl cartService;
	
/*	@Test 
	public void buyNowShouldReturnRecipt() throws Exception{
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, State.ACTIVE, 20);
		u.setPurchases(new ArrayList<Purchase>());
		CartItem ci= new CartItem(b, 5);
		Purchase purchase = new Purchase();
		purchase.getItemList().add(ci);
		purchase.calculateTotalPrice();
		
		Mockito.when(receiptService.buyNow(u.getId(), 5, b.getId())).thenReturn(purchase);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/reciept/buyNow/{userId}/{quantity}/{bookId}",u.getId(), 5, b.getId()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		Mockito.verify(receiptService).buyNow(u.getId(), 5, b.getId());
	}
	
	@Test 
	public void buyNowShouldReturnNull() throws Exception{
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, State.ACTIVE, 20);
		u.setPurchases(new ArrayList<Purchase>());
		CartItem ci= new CartItem(b, 5);
		Purchase Purchase = new Purchase();
		Purchase.getItemList().add(ci);
		Purchase.calculateTotalPrice();
		
		Mockito.when(receiptService.buyNow(u.getId(), 5, b.getId())).thenReturn(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/reciept/buyNow/{userId}/{quantity}/{bookId}",u.getId(), 5, b.getId()))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		Mockito.verify(receiptService).buyNow(u.getId(), 5, b.getId());
	}
	
	@Test 
	public void buyCartShouldReturnRecipt() throws Exception{
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, State.ACTIVE, 20);
		u.setPurchases(new ArrayList<Purchase>());
		CartItem ci= new CartItem(b, 5);
		Purchase purchase = new Purchase();
		purchase.getItemList().add(ci);
		purchase.calculateTotalPrice();
		
		Mockito.when(receiptService.buyCart(u.getId())).thenReturn(purchase);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/reciept/buyCart/{userId}",u.getId()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		Mockito.verify(receiptService).buyCart(u.getId());
	}
	
	@Test 
	public void buyCartShouldReturnNull() throws Exception{
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, State.ACTIVE, 20);
		u.setPurchases(new ArrayList<Purchase>());
		CartItem ci= new CartItem(b, 5);
		Purchase purchase = new Purchase();
		purchase.getItemList().add(ci);
		purchase.calculateTotalPrice();
		
		Mockito.when(receiptService.buyCart(u.getId())).thenReturn(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/reciept/buyCart/{userId}",u.getId()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		Mockito.verify(receiptService).buyCart(u.getId());
	}
	
	
*/
	
}

