package internship.ShoppingCartService;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Category;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.repositories.BookRepository;
import internship.ShoppingCartService.repositories.CartItemRepository;
import internship.ShoppingCartService.repositories.ShoppingCartRepository;
import internship.ShoppingCartService.services.ShoppingCartServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartServiceApplicationTests {

	@InjectMocks
	ShoppingCartServiceImpl soppingCartService;
	
	@Mock
	ShoppingCartRepository soppingCartRepository;
	
	@Mock
    CartItemRepository cartItemRepository;
	
	@Mock
	BookRepository bookRep;
	
	

	
	@Test
	public void addItemTest() {
		
		Book b1 = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, 20, 20);

		List<CartItem> listOfItems = new ArrayList<CartItem>();		
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
		
		Mockito.when(soppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);
		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);
		
		soppingCartService.addItem(Optional.of(shopingCart.getId()), 5,b1.getId());
		
	    Mockito.verify(soppingCartRepository, Mockito.times(1)).save(shopingCart);
	    
	    //Book exist in cart
	    Mockito.when(soppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);
		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);

		listOfItems = new ArrayList<CartItem>();		
		listOfItems.add(new CartItem(b1,5));
		shopingCart = new ShoppingCart(new Long(2), listOfItems);
		Mockito.when(soppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);
		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);
		soppingCartService.addItem(Optional.of(shopingCart.getId()), 5,b1.getId());

	}
	
	@Test
	public void changeQuantitytest() {
		
		Book book1 = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		CartItem cI1 = new CartItem(new Long(2),book1, 5);
	
		Mockito.when(cartItemRepository.getOne(cI1.getId())).thenReturn(cI1);
		
		boolean response = soppingCartService.changeQuantity(Optional.of(cI1.getId()), 50, cI1.getId());
		
		assertEquals(true, response);

	//	response = soppingCartService.changeQuantity(Optional.empty(), 50, cI1.getId());

		
	}
	
		@Test
		public void emptyCarttest() {
		
		Book book1 = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		Book book2 = new Book(new Long(2),"title1", "Author1", new Category("category1"), 10, 10, 10);
		List<CartItem> listOfItems = new ArrayList<CartItem>();		
		CartItem cI1 = new CartItem(new Long(2), book1, 2);
		CartItem cI2 = new CartItem(new Long(2), book2, 2);
		listOfItems.add(cI1);
		listOfItems.add(cI2);
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
		
		Mockito.when(soppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);
		
		boolean response = soppingCartService.emptyCart(Optional.of(shopingCart.getId()));
		
		assertEquals(true, response);
		assertEquals(0, listOfItems.size());
				
		Mockito.when(soppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);
		
	}
	
	@Test
	public void removeItemTest() {
		
		Book book1 = new Book(new Long(1),"title1", "Author1", new Category("category1"), 10, 10, 10);
		Book book2 = new Book(new Long(2),"title1", "Author1", new Category("category1"), 10, 10, 10);
		List<CartItem> listOfItems = new ArrayList<CartItem>();		
		CartItem cI1 = new CartItem(new Long(2), book1, 2);
		CartItem cI2 = new CartItem(new Long(2), book2, 2);
		listOfItems.add(cI1);
		listOfItems.add(cI2);
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
		
		Mockito.when(soppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);
		
		boolean response = soppingCartService.removeItem(Optional.of(shopingCart.getId()), cI1.getId());
		
		assertEquals(true, response);
		assertEquals(1, listOfItems.size());
	}
	
}
