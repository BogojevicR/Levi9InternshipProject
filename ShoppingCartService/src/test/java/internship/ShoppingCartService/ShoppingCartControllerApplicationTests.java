package internship.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import internship.ShoppingCartService.controllers.ShoppingCartController;
import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Category;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.services.ShoppingCartServiceImpl;


@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingCartController.class)
public class ShoppingCartControllerApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private ShoppingCartServiceImpl shoppingCartService;
	
	@Test 
	public void getCartTest() throws Exception {
		
		List<CartItem> listOfItems = new ArrayList<CartItem>();		
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
	
	//	Mockito.when(shoppingCartService.getCart(shopingCart.getId())).thenReturn(shopingCart);
	
		String jsonCart = new Gson().toJson(shopingCart);
	
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/cart/getCart/{cartId}", shopingCart.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
	        	.andExpect(MockMvcResultMatchers.content().json(jsonCart));
		
	//	Mockito.verify(shoppingCartService).getCart(shopingCart.getId());
	
	}
	
	@Test 
	public void getCartItemsTest() throws Exception {
		
		Book book1 = new Book(new Long(2), "Kako bih bez tebe", "Gijom Muso", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Book book2 = new Book(new Long(2), "Alhemicar", "Paolo Koeljo", new Category("category1"), 10, Book.State.ACTIVE, 10);
		CartItem cI1 = new CartItem(new Long(2),book1, 5);	
		CartItem cI2 = new CartItem(new Long(3),book2, 5);
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		listOfItems.add(cI1);
		listOfItems.add(cI2);
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
		
		Optional<Long> id;
		id = Optional.of(shopingCart.getId());
	
<<<<<<< HEAD
		Mockito.when(shoppingCartService.getCartItems(id)).thenReturn(listOfItems);
=======
	//	Mockito.when(shoppingCartService.getCartItems(shopingCart.getId())).thenReturn(listOfItems);
>>>>>>> d6480361c934e83db7f0cc727f5510de050cbda1
	
		String jsonCartItems = new Gson().toJson(listOfItems);
	
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/cart/getCartItems/{cartId}", shopingCart.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().json(jsonCartItems));
		
<<<<<<< HEAD
		Mockito.verify(shoppingCartService).getCartItems(id);
=======
	//	Mockito.verify(shoppingCartService).getCartItems(shopingCart.getId());
>>>>>>> d6480361c934e83db7f0cc727f5510de050cbda1
	
	}
	
	@Test 
	public void addItemTest() throws Exception {
		
		Book book1 = new Book(new Long(2), "Kako bih bez tebe", "Gijom Muso", new Category("category1"), 10, Book.State.ACTIVE, 10);
		CartItem cI1 = new CartItem(new Long(2),book1, 5);	
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		listOfItems.add(cI1);
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
	
	//	Mockito.when(shoppingCartService.addItem(cI1.getId(), 5, book1.getId())).thenReturn(true);
	
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/addItem/{cartId}/{quantity}/{bookId}", shopingCart.getId(), 5, book1.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
	        	.andExpect(MockMvcResultMatchers.content().string("true"));
		
	//	Mockito.verify(shoppingCartService).addItem(cI1.getId(), 5, book1.getId());
	
	}
	
	@Test 
	public void removeItemTest() throws Exception {
		
		Book book1 = new Book(new Long(2), "Kako bih bez tebe", "Gijom Muso", new Category("category1"), 10, Book.State.ACTIVE, 10);
		CartItem cI1 = new CartItem(new Long(2),book1, 5);	
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		listOfItems.add(cI1);
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
	
	//	Mockito.when(shoppingCartService.removeItem(shopingCart.getId(), cI1.getId())).thenReturn(true);
		
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/cart/removeItem/{cartId}/{cartItemId}", shopingCart.getId(), cI1.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("true"));
		
	//	Mockito.verify(shoppingCartService).removeItem(shopingCart.getId(), cI1.getId());
	
	}
	
	@Test 
	public void changeQuantityTest() throws Exception {
		
		Book book1 = new Book(new Long(2), "Kako bih bez tebe", "Gijom Muso", new Category("category1"), 10, Book.State.ACTIVE, 10);
		CartItem cI1 = new CartItem(new Long(2),book1, 5);	
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		listOfItems.add(cI1);
	
	//	Mockito.when(shoppingCartService.changeQuantity(5, cI1.getId())).thenReturn(true);
	
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/cart/changeQuantity/{quantity}/{itemId}", 5, cI1.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("true"));
		
	//	Mockito.verify(shoppingCartService).changeQuantity(5, cI1.getId());
	
	}
	
	@Test 
	public void emptyCartTest() throws Exception {
		
		Book book1 = new Book(new Long(2), "Kako bih bez tebe", "Gijom Muso", new Category("category1"), 10, Book.State.ACTIVE, 10);
		Book book2 = new Book(new Long(2), "Alhemicar", "Paolo Koeljo", new Category("category1"), 10, Book.State.ACTIVE, 10);
		List<CartItem> listOfItems = new ArrayList<CartItem>();		
		CartItem cI1 = new CartItem(new Long(2), book1, 2);
		CartItem cI2 = new CartItem(new Long(2), book2, 2);
		listOfItems.add(cI1);
		listOfItems.add(cI2);
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
	
	//	Mockito.when(shoppingCartService.emptyCart(shopingCart.getId())).thenReturn(true);
	
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/cart/emptyCart/{cartId}", shopingCart.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("true"));
		
	//	Mockito.verify(shoppingCartService).emptyCart(shopingCart.getId());
	
	}
	
	
}
