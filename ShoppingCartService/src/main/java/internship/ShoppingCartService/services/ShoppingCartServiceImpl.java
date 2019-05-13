package internship.ShoppingCartService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.repositories.BookRepository;
import internship.ShoppingCartService.repositories.CartItemRepository;
import internship.ShoppingCartService.repositories.ShoppingCartRepository;

/**
 * This class represents service for shopping cart.
 * @author s.krasic
 *
 */

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	public CartItemRepository cartItemRep;
	@Autowired
	public ShoppingCartRepository cartRep;
	@Autowired
	public BookRepository bookRep;
	
	/**
	 * This method is method to get shopping cart.
	 * @param cartId represents id for cart that we want to  get.
	 * @return shopping cart.
	 */
	
	@Override
	public ShoppingCart getCart(Long cartId) {	
		return cartRep.findById(cartId).get();
	}
	
	/**
	 * This method is method to get shopping cart items that person wants to buy.
	 * @param cartId represents id for cart that we want to get it's items.
	 * @return list of shopping cart items.
	 */
	
	@Override
	public List<CartItem> getCartItems(Long cartId) {
		return  cartRep.findById(cartId).get().getItemList();
		
	}
	
	/**
	 * This method is method to add item to shopping cart.
	 * @param cartId represents shopping cart in which person wants to add item.
	 * @param quantity represents number of copies of book that person wants to add to shopping cart.
	 * @param bookId represents id of the book that person wants to add to shopping cart.
	 * @return true value when cart item don't exist or false when cart item already exists.
	 * 
	 */
	
	@Override
	public boolean addItem(Long cartId, int quantity, Long bookId) {
		ShoppingCart cart = cartRep.getOne(cartId);
		
		if(cart.getItemList().size() != 0) {
			if(cart.checkBook(bookId)) {
				return false;
			}
		}
		
		Book b = bookRep.getOne(bookId);
		CartItem item = new CartItem(b, quantity);
		cartItemRep.save(item);
		cart.getItemList().add(item);
		cartRep.save(cart);
		return true;
	}
	
	/**
	 * This method is method to change quantity of the book.
	 * @param quantity represents number of copies of book that person wants to add to shopping cart.
	 * @param itemId represents id of the item that person wants to add to shopping cart.
	 * @return true value.
	 * 
	 */

	@Override
	public boolean changeQuantity(int quantity, Long itemId) {
		CartItem item = cartItemRep.getOne(itemId);
		item.setQuantity(quantity);
		item.setTotal(quantity * item.getBook().getPrice());
		cartItemRep.save(item);
		return true;
	}
	
	/**
	 * This method is method to empty shopping cart.
	 * @param cartId represents id of the shopping cart that person wants to empty.
	 * @return true value.
	 * 
	 */

	@Override
	public boolean emptyCart(Long cartId) {
		
		ShoppingCart cart = cartRep.getOne(cartId);
		for(CartItem item : cart.getItemList()) {
			cartItemRep.delete(item);
		}
		cart.getItemList().clear();
		cartRep.save(cart);
		
		return true;
	}
	
	/**
	 * This method is method to remove one item from shopping cart.
	 * @param cartId represents id of the shopping cart.
	 * @param cartItemId represents id of cart item that person wants to remove from shopping cart.
	 * @return true value.
	 * 
	 */

	@Override
	public boolean removeItem(Long cartId, Long cartItemId) {
		ShoppingCart cart = cartRep.getOne(cartId);
		cart.removeItemById(cartItemId);
		cartItemRep.deleteById(cartItemId);
		return true;
	}

	
}
