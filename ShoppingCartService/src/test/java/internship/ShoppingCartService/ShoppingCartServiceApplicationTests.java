package internship.ShoppingCartService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
	ShoppingCartServiceImpl shoppingCartService;

	@Mock
	ShoppingCartRepository shoppingCartRepository;
	@Mock
	CartItemRepository cartItemRepository;
	@Mock
	BookRepository bookRep;
	@Mock
	ShoppingCart sessionShoppingCart;

	@Test
	public void addItemTest() {

		Book b1 = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, 20, 20);
		Book b2 = new Book(new Long(2), "Title2", "Author2", new Category("cat2"), 22, 22, 22);

		List<CartItem> listOfItems = new ArrayList<CartItem>();
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);

		Mockito.when(shoppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);
		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);

		shoppingCartService.addItem(Optional.of(shopingCart.getId()), 5, b1.getId());

		Mockito.verify(shoppingCartRepository, Mockito.times(1)).save(shopingCart);

		Mockito.when(shoppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);
		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);

		listOfItems = new ArrayList<CartItem>();
		listOfItems.add(new CartItem(new Long(22), b1, 5));
		shopingCart = new ShoppingCart(new Long(2), listOfItems);

		Mockito.when(shoppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);

		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);

		Mockito.when(bookRep.getOne(b2.getId())).thenReturn(b2);

		shoppingCartService.addItem(Optional.of(shopingCart.getId()), 5, b1.getId());
		assertEquals(false, shoppingCartService.addItem(Optional.of(shopingCart.getId()), 5, b1.getId()));

		listOfItems = new ArrayList<CartItem>();
		listOfItems.add(new CartItem(new Long(22), b1, 5));
		shopingCart = new ShoppingCart(new Long(2), listOfItems);

		Mockito.when(shoppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);

		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);

		Mockito.when(bookRep.getOne(b2.getId())).thenReturn(b2);

		assertEquals(true, shoppingCartService.addItem(Optional.of(shopingCart.getId()), 5, b2.getId()));

		shoppingCartService.addItem(Optional.empty(), 5, b1.getId());

	}

	@Test
	public void addItemSessionCartTest() {

		Book b1 = new Book(new Long(3), "Title", "Author1", new Category("cat"), 20, 20, 20);

		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);

		assertTrue(shoppingCartService.addItem(Optional.empty(), 5, b1.getId()));

		System.out.println(shoppingCartService.getCart(Optional.empty()));

		List<CartItem> listOfItems = new ArrayList<CartItem>();
		listOfItems = new ArrayList<CartItem>();
		listOfItems.add(new CartItem(new Long(22), b1, 5));

		Mockito.when(sessionShoppingCart.getItemList()).thenReturn(listOfItems);

		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);

		shoppingCartService.addItem(Optional.empty(), 5, b1.getId());

		Mockito.when(sessionShoppingCart.getItemList()).thenReturn(listOfItems);
		Mockito.when(sessionShoppingCart.checkBook(b1.getId())).thenReturn(true);
		Mockito.when(bookRep.getOne(b1.getId())).thenReturn(b1);

		shoppingCartService.addItem(Optional.empty(), 5, b1.getId());
	}

	@Test
	public void changeQuantityTest() {

		Book book1 = new Book(new Long(1), "title1", "Author1", new Category("category1"), 10, 10, 10);
		CartItem cI1 = new CartItem(new Long(2), book1, 5);

		Mockito.when(cartItemRepository.getOne(cI1.getId())).thenReturn(cI1);

		boolean response = shoppingCartService.changeQuantity(Optional.of(cI1.getId()), 50, cI1.getId());

		assertEquals(true, response);

		shoppingCartService.changeQuantity(Optional.empty(), 5, book1.getId());

	}

	@Test
	public void changeQuantitySessionCartTest() {

		Book book1 = new Book(new Long(1), "title1", "Author1", new Category("category1"), 10, 10, 10);
		CartItem cI1 = new CartItem(new Long(2), book1, 5);
		ArrayList<CartItem> itemList = new ArrayList<CartItem>();
		itemList.add(cI1);

		Mockito.when(sessionShoppingCart.getItemList()).thenReturn(itemList);

		boolean response = shoppingCartService.changeQuantity(Optional.empty(), 50, book1.getId());

		assertEquals(true, response);

		shoppingCartService.changeQuantity(Optional.empty(), 5, book1.getId());

		response = shoppingCartService.changeQuantity(Optional.empty(), 50, (long) 5);

		assertEquals(false, response);

		shoppingCartService.changeQuantity(Optional.empty(), 5, book1.getId());

	}

	@Test
	public void emptyCarttest() {

		Book book1 = new Book(new Long(1), "title1", "Author1", new Category("category1"), 10, 10, 10);
		Book book2 = new Book(new Long(2), "title1", "Author1", new Category("category1"), 10, 10, 10);
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		CartItem cI1 = new CartItem(new Long(2), book1, 2);
		CartItem cI2 = new CartItem(new Long(2), book2, 2);
		listOfItems.add(cI1);
		listOfItems.add(cI2);
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);

		Mockito.when(shoppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);

		ShoppingCart response = shoppingCartService.emptyCart(Optional.of(shopingCart.getId()));

		assertNotNull(response);
		assertEquals(0, listOfItems.size());

		Mockito.when(shoppingCartRepository.getOne(shopingCart.getId())).thenReturn(null);

		assertNull(shoppingCartService.emptyCart(Optional.of(shopingCart.getId())));

		shoppingCartService.emptyCart(Optional.empty());

	}

	@Test
	public void removeItemTest() {

		Book book1 = new Book(new Long(1), "title1", "Author1", new Category("category1"), 10, 10, 10);
		Book book2 = new Book(new Long(2), "title1", "Author1", new Category("category1"), 10, 10, 10);
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		CartItem cI1 = new CartItem(new Long(2), book1, 2);
		CartItem cI2 = new CartItem(new Long(2), book2, 2);
		listOfItems.add(cI1);
		listOfItems.add(cI2);
		ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);

		Mockito.when(shoppingCartRepository.getOne(shopingCart.getId())).thenReturn(shopingCart);

		boolean response = shoppingCartService.removeItem(Optional.of(shopingCart.getId()), cI1.getId());

		assertEquals(true, response);
		assertEquals(1, listOfItems.size());

		shoppingCartService.removeItem(Optional.empty(), book1.getId());

	}

	@Test
	public void removeItemSessionCartTest() {

		Book book1 = new Book(new Long(1), "title1", "Author1", new Category("category1"), 10, 10, 10);
		Book book2 = new Book(new Long(2), "title1", "Author1", new Category("category1"), 10, 10, 10);
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		CartItem cI1 = new CartItem(new Long(2), book1, 2);
		CartItem cI2 = new CartItem(new Long(2), book2, 2);
		listOfItems.add(cI1);
		listOfItems.add(cI2);

		Mockito.when(sessionShoppingCart.getItemList()).thenReturn(listOfItems);

		boolean response = shoppingCartService.removeItem(Optional.empty(), book1.getId());

		assertEquals(true, response);
		assertEquals(1, listOfItems.size());

		shoppingCartService.removeItem(Optional.empty(), book1.getId());

	}

	@Test
	public void getCartTest() {
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		Optional<ShoppingCart> cart = Optional.of(new ShoppingCart(new Long(2), listOfItems));

		Mockito.when(shoppingCartRepository.findById(cart.get().getId())).thenReturn(cart);
		ShoppingCart sc = shoppingCartService.getCart(Optional.of(cart.get().getId()));

		assertEquals(cart.get(), sc);

		Mockito.when(shoppingCartRepository.findById(cart.get().getId())).thenReturn(Optional.empty());
		sc = shoppingCartService.getCart(Optional.of(cart.get().getId()));

		assertEquals(null, shoppingCartService.getCart(Optional.of(cart.get().getId())));

		shoppingCartService.getCart(Optional.empty());

	}

	@Test
	public void getSessionCartTest() {

		assertNotNull(shoppingCartService.getCart(Optional.empty()));
	}

	@Test
	public void getCartItemsTest() {

		List<CartItem> listOfItems = new ArrayList<CartItem>();
		Optional<ShoppingCart> cart = Optional.of(new ShoppingCart(new Long(2), listOfItems));

		Mockito.when(shoppingCartRepository.getOne(cart.get().getId())).thenReturn(cart.get());

		assertEquals(listOfItems, shoppingCartService.getCartItems(Optional.of(cart.get().getId())));

		shoppingCartService.getCartItems(Optional.empty());

	}
}
