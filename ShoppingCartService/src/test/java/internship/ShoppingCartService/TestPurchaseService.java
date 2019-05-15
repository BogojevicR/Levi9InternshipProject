package internship.ShoppingCartService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.Book.State;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Category;
import internship.ShoppingCartService.models.Purchase;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.User.Role;
import internship.ShoppingCartService.repositories.BookRepository;
import internship.ShoppingCartService.repositories.OrderRepository;
import internship.ShoppingCartService.repositories.ShoppingCartRepository;
import internship.ShoppingCartService.repositories.UserRepository;
import internship.ShoppingCartService.services.PurchaseServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestPurchaseService {

	@InjectMocks
	PurchaseServiceImpl purchaseRep;

	@Mock
	OrderRepository receiptRep;
	@Mock
	BookRepository bookRep;
	@Mock
	ShoppingCartRepository shoppingCartRep;
	@Mock
	UserRepository userRep;
	
	@Test
	public void buyNowTest() {
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, State.ACTIVE, 20);
		u.setPurchases(new ArrayList<Purchase>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		when(bookRep.getOne(new Long(3))).thenReturn(b);
		// Cart has items	
		assertNotNull(purchaseRep.buyNow(u.getId(),20,b.getId()));
		
		// Cart item have more quantity than stock
		assertNull(purchaseRep.buyNow(u.getId(),30,b.getId()));
		
		u = new User(new Long(2),"rale",Role.ADMIN,"rale",null);
		when(userRep.getOne(new Long(2))).thenReturn(u);
		// User is Admin
		assertNull(purchaseRep.buyNow(u.getId(),20,b.getId()));
		
	}
	
	@Test
	public void buyCartTest() {
		ShoppingCart cart = new ShoppingCart(new Long(1));
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, State.ACTIVE, 20);
		CartItem ci = new CartItem(b, 5);
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		u.setPurchases(new ArrayList<Purchase>());
		
		// Cart is Empty
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(purchaseRep.buyCart(u.getId()));
		
		//Cart has items
		cart.getItemList().add(ci);
		u  = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		u.setPurchases(new ArrayList<Purchase>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNotNull(purchaseRep.buyCart(u.getId()));
		
		//Cart item have more quantity than stock
		ci.setQuantity(25);
		cart.getItemList().add(ci);
		u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		u.setPurchases(new ArrayList<Purchase>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(purchaseRep.buyCart(u.getId()));
		
		// User is ADMIN
		u = new User(new Long(2),"rale",Role.ADMIN,"rale",cart);
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(purchaseRep.buyCart(u.getId()));
	}
	
}

