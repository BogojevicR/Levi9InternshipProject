package internship.ShoppingCartService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Category;
import internship.ShoppingCartService.models.Receipt;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.Book.State;
import internship.ShoppingCartService.models.User.Role;
import internship.ShoppingCartService.repositories.BookRepository;
import internship.ShoppingCartService.repositories.ReceiptItemRepository;
import internship.ShoppingCartService.repositories.ReceiptRepository;
import internship.ShoppingCartService.repositories.ShoppingCartRepository;
import internship.ShoppingCartService.repositories.UserRepository;
import internship.ShoppingCartService.services.ReceiptServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestReceiptService {

	@InjectMocks
	ReceiptServiceImpl receiptService;
	@Mock
	ReceiptItemRepository receiptItemRep;
	@Mock
	ReceiptRepository receiptRep;
	@Mock
	BookRepository bookRep;
	@Mock
	ShoppingCartRepository shoppingCartRep;
	@Mock
	UserRepository userRep;
	
	@Test
	public void buyNowTest() {
		
		ShoppingCart cart = new ShoppingCart(new Long(1));
		User u = new User(new Long(2),"Radovan","Bogojevic","rale@gmail.com",Role.CUSTOMER,"rale",cart);
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, State.ACTIVE, 20);
		u.setReceipts(new ArrayList<Receipt>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		when(bookRep.getOne(new Long(3))).thenReturn(b);
		// Cart has items	
		assertNotNull(receiptService.buyNow(u.getId(),20,b.getId()));
		
		// Cart item have more quantity than stock
		assertNull(receiptService.buyNow(u.getId(),30,b.getId()));
		
		u = new User(new Long(2),"Radovan","Bogojevic","rale@gmail.com",Role.ADMIN,"rale",cart);
		when(userRep.getOne(new Long(2))).thenReturn(u);
		// User is Admin
		assertNull(receiptService.buyNow(u.getId(),20,b.getId()));
		
	}
	
	@Test
	public void buyCartTest() {
		ShoppingCart cart = new ShoppingCart(new Long(1));
		Book b = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, State.ACTIVE, 20);
		CartItem ci = new CartItem(b, 5);
		User u = new User(new Long(2),"Radovan","Bogojevic","rale@gmail.com",Role.CUSTOMER,"rale",cart);
		u.setReceipts(new ArrayList<Receipt>());
		
		// Cart is Empty
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(receiptService.buyCart(u.getId()));
		
		//Cart has items
		cart.getItemList().add(ci);
		u = new User(new Long(2),"Radovan","Bogojevic","rale@gmail.com",Role.CUSTOMER,"rale",cart);
		u.setReceipts(new ArrayList<Receipt>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNotNull(receiptService.buyCart(u.getId()));
		
		//Cart item have more quantity than stock
		ci.setQuantity(25);
		cart.getItemList().add(ci);
		u = new User(new Long(2),"Radovan","Bogojevic","rale@gmail.com",Role.CUSTOMER,"rale",cart);
		u.setReceipts(new ArrayList<Receipt>());
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(receiptService.buyCart(u.getId()));
		
		// User is ADMIN
		u = new User(new Long(2),"Radovan","Bogojevic","rale@gmail.com",Role.ADMIN,"rale",cart);
		when(userRep.getOne(new Long(2))).thenReturn(u);
		assertNull(receiptService.buyCart(u.getId()));
	}
	
}

