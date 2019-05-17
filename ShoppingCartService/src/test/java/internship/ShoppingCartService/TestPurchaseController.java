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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import internship.ShoppingCartService.DTO.AdressDTO;
import internship.ShoppingCartService.DTO.UserInfoDTO;
import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Category;
import internship.ShoppingCartService.models.Purchase;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.User.Role;
import internship.ShoppingCartService.security.UserAccountService;
import internship.ShoppingCartService.services.PurchaseServiceImpl;
import internship.ShoppingCartService.services.ShoppingCartServiceImpl;



@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(secure = false)
public class TestPurchaseController {

	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public PurchaseServiceImpl purchaseService;
	
	@MockBean
	public ShoppingCartServiceImpl cartService;
	
	@MockBean
	public UserAccountService userAccountService;
	
	@Test 
	@WithMockUser(username = "admin", password = "123", authorities = "ADMIN")
	public void buyNowShouldReturnRecipt() throws Exception{
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		u.setPurchases(new ArrayList<Purchase>());
		CartItem ci= new CartItem(b, 5);
		Purchase purchase = new Purchase();
		purchase.getItemList().add(ci);
		purchase.calculateTotalPrice();
		
		Mockito.when(purchaseService.buyNow(u.getId(), 5, b.getId())).thenReturn(purchase);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/buyNow/{userId}/{quantity}/{bookId}",u.getId(), 5, b.getId()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		Mockito.verify(purchaseService).buyNow(u.getId(), 5, b.getId());
	}
	
	@Test 
	public void buyNowShouldReturnNull() throws Exception{
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		u.setPurchases(new ArrayList<Purchase>());
		CartItem ci= new CartItem(b, 5);
		Purchase Purchase = new Purchase();
		Purchase.getItemList().add(ci);
		Purchase.calculateTotalPrice();
		
		Mockito.when(purchaseService.buyNow(u.getId(), 5, b.getId())).thenReturn(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/buyNow/{userId}/{quantity}/{bookId}",u.getId(), 5, b.getId()))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		Mockito.verify(purchaseService).buyNow(u.getId(), 5, b.getId());
	}
	
	@Test 
	public void buyCartShouldReturnRecipt() throws Exception{
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		u.setPurchases(new ArrayList<Purchase>());
		CartItem ci= new CartItem(b, 5);
		Purchase purchase = new Purchase();
		purchase.getItemList().add(ci);
		purchase.calculateTotalPrice();
		
		Mockito.when(purchaseService.buyCart(u.getId())).thenReturn(purchase);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/buyCart/{userId}",u.getId()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		Mockito.verify(purchaseService).buyCart(u.getId());
	}
	
	@Test 
	public void buyCartShouldReturnNull() throws Exception{
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		u.setPurchases(new ArrayList<Purchase>());
		CartItem ci= new CartItem(b, 5);
		Purchase purchase = new Purchase();
		purchase.getItemList().add(ci);
		purchase.calculateTotalPrice();
		
		Mockito.when(purchaseService.buyCart(u.getId())).thenReturn(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/buyCart/{userId}",u.getId()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		Mockito.verify(purchaseService).buyCart(u.getId());
	}
	
	
	@Test 
	@WithMockUser(username = "admin", password = "123", authorities = "ADMIN")
	public void buyNowUnauthShouldReturnRecipt() throws Exception{
		Book b = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		CartItem ci= new CartItem(b, 5);
		Purchase purchase = new Purchase();
		purchase.getItemList().add(ci);
		purchase.calculateTotalPrice();
		UserInfoDTO info = new UserInfoDTO(new Long(1),"Rale","Bogojevic","rale@gmail.com","064",new AdressDTO(new Long(2), "Novi Sad", "Serbia", "Street", "22"));
		Mockito.when(purchaseService.buyNowUnauth(5, b.getId(), info)).thenReturn(purchase);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/buyNow/{quantity}/{bookId}", 5, b.getId()).contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(info)))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(purchaseService).buyNowUnauth(5, b.getId(), info);
	}
	
	@Test 
	public void buyNowUnauthShouldReturnNull() throws Exception{
		
		Book b = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		CartItem ci= new CartItem(b, 5);
		Purchase Purchase = new Purchase();
		Purchase.getItemList().add(ci);
		Purchase.calculateTotalPrice();
		UserInfoDTO info = new UserInfoDTO(new Long(1),"Rale","Bogojevic","rale@gmail.com","064",new AdressDTO(new Long(2), "Novi Sad", "Serbia", "Street", "22"));
		Mockito.when(purchaseService.buyNowUnauth(5, b.getId(), info)).thenReturn(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/buyNow/{quantity}/{bookId}", 5, b.getId()).contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(info)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		Mockito.verify(purchaseService).buyNowUnauth(5, b.getId(), info);
	}  
	
	@Test 
	public void buyCartUnauthShouldReturnRecipt() throws Exception{
		
		UserInfoDTO info = new UserInfoDTO(new Long(1),"Rale","Bogojevic","rale@gmail.com","064",new AdressDTO(new Long(2), "Novi Sad", "Serbia", "Street", "22"));
		Book b = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		CartItem ci= new CartItem(b, 5);
		Purchase purchase = new Purchase();
		purchase.getItemList().add(ci);
		purchase.calculateTotalPrice();
		
		Mockito.when(purchaseService.buyCartUnauth(info)).thenReturn(purchase);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/buyCart").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(info)))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		Mockito.verify(purchaseService).buyCartUnauth(info);
	}
	
	@Test 
	public void buyCartUnauthShouldReturnNull() throws Exception{
		
		UserInfoDTO info = new UserInfoDTO(new Long(1),"Rale","Bogojevic","rale@gmail.com","064",new AdressDTO(new Long(2), "Novi Sad", "Serbia", "Street", "22"));
		
		Mockito.when(purchaseService.buyCartUnauth(info)).thenReturn(null);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/purchase/buyCart/").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(info)))
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		Mockito.verify(purchaseService).buyCartUnauth(info);
	}
}

