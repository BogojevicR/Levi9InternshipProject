package internship.ShoppingCartService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;

import internship.ShoppingCartService.DTO.AdressDTO;
import internship.ShoppingCartService.DTO.UserInfoDTO;
import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Category;
import internship.ShoppingCartService.models.Purchase;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.User.Role;
import internship.ShoppingCartService.repositories.AdressRepository;
import internship.ShoppingCartService.repositories.BookRepository;
import internship.ShoppingCartService.repositories.CartItemRepository;
import internship.ShoppingCartService.repositories.PurchaseRepository;
import internship.ShoppingCartService.repositories.ShoppingCartRepository;
import internship.ShoppingCartService.repositories.UserInfoRepository;
import internship.ShoppingCartService.repositories.UserRepository;
import internship.ShoppingCartService.services.PurchaseServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestPurchaseService {

	@InjectMocks
	PurchaseServiceImpl purchaseService;

	@Mock
	PurchaseRepository purchaseRep;
	@Mock
	BookRepository bookRep;
	@Mock
	ShoppingCartRepository shoppingCartRep;
	@Mock
	UserRepository userRep;
	@Mock
	CartItemRepository cartItemRep;
	@Mock
	AdressRepository adressRep;
	@Mock
	UserInfoRepository userInfoRep;
	
	protected MockHttpSession mockSession;

	
	
	@Mock
	ShoppingCart sessionShoppingCart;

	
	@Test
	public void buyNowTest() {
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, 20, 20);
		u.setPurchases(new ArrayList<Purchase>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		when(bookRep.getOne(new Long(3))).thenReturn(b);

		assertNotNull(purchaseService.buyNow(u.getId(),20,b.getId()));
		

		assertNull(purchaseService.buyNow(u.getId(),30,b.getId()));
		
		u = new User(new Long(2),"rale",Role.ADMIN,"rale",null);
		when(userRep.getOne(new Long(2))).thenReturn(u);

		assertNull(purchaseService.buyNow(u.getId(),20,b.getId()));
		
	}
	
	@Test
	public void buyCartTest() {
		ShoppingCart cart = new ShoppingCart(new Long(1));
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, 20, 20);
		CartItem ci = new CartItem(b, 5);
		User u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		u.setPurchases(new ArrayList<Purchase>());
		
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(purchaseService.buyCart(u.getId()));
		
		cart.getItemList().add(ci);
		u  = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		u.setPurchases(new ArrayList<Purchase>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNotNull(purchaseService.buyCart(u.getId()));
		
		ci.setQuantity(25);
		cart.getItemList().add(ci);
		u = new User(new Long(2),"rale",Role.CUSTOMER,"rale",cart);
		u.setPurchases(new ArrayList<Purchase>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(purchaseService.buyCart(u.getId()));
		
		u = new User(new Long(2),"rale",Role.ADMIN,"rale",cart);
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(purchaseService.buyCart(u.getId()));
	}
	
	@Test
	public void buyCartUnauthTest() {
		UserInfoDTO info = new UserInfoDTO(new Long(1),"Rale","Bogojevic","rale@gmail.com","064",new AdressDTO(new Long(2), "Novi Sad", "Serbia", "Street", "22"));

		List<CartItem> listOfItems = new ArrayList<CartItem>();	
		when(sessionShoppingCart.getItemList()).thenReturn(listOfItems);
		assertNull(purchaseService.buyCartUnauth(info));	
		
		
		Book book1 = new Book(new Long(3),"title1", "Author1", new Category("category1"), 10, 10, 10);
		CartItem cI1 = new CartItem(new Long(2), book1, 2);
		listOfItems.add(cI1);
		
		
		when(sessionShoppingCart.getItemList()).thenReturn(listOfItems);
		when(cartItemRep.save(cI1)).thenReturn(cI1);
		when(bookRep.getOne(new Long(3))).thenReturn(book1);
		
		purchaseService.buyCartUnauth(info);
						
		listOfItems = new ArrayList<CartItem>();		
		cI1 = new CartItem(new Long(2), book1, 22222);
		listOfItems.add(cI1);
		Mockito.when(sessionShoppingCart.getItemList()).thenReturn(listOfItems);
		when(cartItemRep.save(cI1)).thenReturn(cI1);
		
		when(bookRep.getOne(new Long(3))).thenReturn(book1);
		
		purchaseService.buyCartUnauth(info);
		assertNull(purchaseService.buyCartUnauth(info));	

	}

	@Test
	public void buyNowUnauthCart() {
		
		UserInfoDTO info = new UserInfoDTO(new Long(1),"Rale","Bogojevic","rale@gmail.com","064",new AdressDTO(new Long(2), "Novi Sad", "Serbia", "Street", "22"));
		
		Book book1 = new Book(new Long(3),"title1", "Author1", new Category("category1"), 10, 10, 10);
		
		when(bookRep.getOne(book1.getId())).thenReturn(book1);
		
		purchaseService.buyNowUnauth(5, book1.getId(), info);
		
		assertNull(purchaseService.buyNowUnauth(55555, book1.getId(), info));	


	}
	
	
}

