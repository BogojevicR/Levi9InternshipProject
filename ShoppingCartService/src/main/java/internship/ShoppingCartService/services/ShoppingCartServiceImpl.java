package internship.ShoppingCartService.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

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
 * 
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

	@Resource(name = "sessionShoppingCart")
	ShoppingCart sessionShoppingCart;
	
	/**
	 * This method is method to get shopping cart.
	 * 
	 * @param cartId represents id for cart that we want to get.
	 * @return shopping cart.
	 */
	@Override
	public ShoppingCart getCart(Optional<Long> cartId) {
		if (!cartId.isPresent()) {

			return new ShoppingCart(sessionShoppingCart);
		}
		Optional<ShoppingCart> cart = cartRep.findById(cartId.get());
		if (cart.isPresent()) {

			return cart.get();
		}

		return null;
	}

	/**
	 * This method is method to get shopping cart items that person wants to buy.
	 * 
	 * @param cartId represents id for cart that we want to get it's items.
	 * @return list of shopping cart items.
	 */
	@Override
	public List<CartItem> getCartItems(Optional<Long> cartId) {

		if (!cartId.isPresent()) {

			return sessionShoppingCart.getItemList();
		}

		return cartRep.getOne(cartId.get()).getItemList();
	}

	/**
	 * This method is method to add item to shopping cart.
	 * 
	 * @param cartId   represents shopping cart in which person wants to add item.
	 * @param quantity represents number of copies of book that person wants to add
	 *                 to shopping cart.
	 * @param bookId   represents id of the book that person wants to add to
	 *                 shopping cart.
	 * @return true value when cart item don't exist or false when cart item already
	 *         exists.
	 * 
	 */

	@Override
	public boolean addItem(Optional<Long> cartId, int quantity, Long bookId) {

		Book b = bookRep.getOne(bookId);
		if (!cartId.isPresent()) {
			if (!sessionShoppingCart.getItemList().isEmpty() && sessionShoppingCart.checkBook(bookId)) {

				return false;
			}
			sessionShoppingCart.getItemList().add(new CartItem((long) 0, b, quantity));

			return true;
		}
		ShoppingCart cart = cartRep.getOne(cartId.get());
		if (!cart.getItemList().isEmpty() && cart.checkBook(bookId)) {

			return false;
		}
		CartItem item = new CartItem(b, quantity);
		cartItemRep.save(item);

		cart.getItemList().add(item);
		cartRep.save(cart);

		return true;
	}

	/**
	 * This method is method to change quantity of the book.
	 * 
	 * @param quantity represents number of copies of book that person wants to add
	 *                 to shopping cart.
	 * @param itemId   represents id of the item that person wants to add to
	 *                 shopping cart.
	 * @return true value.
	 * 
	 */
	@Override
	public boolean changeQuantity(Optional<Long> cartId, int quantity, Long bookORItemId) {
		if (!cartId.isPresent()) {
			for (CartItem i : sessionShoppingCart.getItemList()) {
				if (i.getBook().getId().longValue() == bookORItemId.longValue()) {
					i.setQuantity(quantity);

					return true;
				}
			}

			return false;
		}

		CartItem item = cartItemRep.getOne(bookORItemId);
		item.setQuantity(quantity);
		cartItemRep.save(item);

		return true;
	}

	/**
	 * This method is method to empty shopping cart.
	 * 
	 * @param cartId represents id of the shopping cart that person wants to empty.
	 * @return true value.
	 * 
	 */
	@Override
	public ShoppingCart emptyCart(Optional<Long> cartId) {
		if (!cartId.isPresent()) {
			sessionShoppingCart.getItemList().clear();

			return new ShoppingCart(sessionShoppingCart);
		}

		ShoppingCart cart = cartRep.getOne(cartId.get());
		if (cart != null) {

			for (CartItem i : cart.getItemList()) {
				cartItemRep.deleteById(i.getId());
			}

			cart.getItemList().clear();
			cartRep.save(cart);

			return cart;
		}

		return null;
	}

	/**
	 * This method is method to remove one item from shopping cart.
	 * 
	 * @param cartId     represents id of the shopping cart.
	 * @param cartItemId represents id of cart item that person wants to remove from
	 *                   shopping cart.
	 * @return true value.
	 * 
	 */
	@Override
	public boolean removeItem(Optional<Long> cartId, Long bookORItemId) {
		if (!cartId.isPresent()) {
			for (CartItem i : sessionShoppingCart.getItemList()) {
				if (i.getBook().getId().longValue() == bookORItemId.longValue()) {
					sessionShoppingCart.getItemList().remove(i);

					return true;
				}
			}

			return false;
		}

		ShoppingCart cart = cartRep.getOne(cartId.get());
		cart.removeItemById(bookORItemId);
		cartItemRep.deleteById(bookORItemId);
		cartRep.save(cart);

		return true;
	}

}
