package internship.ShoppingCartService.controllers;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.services.ShoppingCartServiceImpl;

/**
 * This class represents controller for shopping cart.
 * @author s.krasic
 *
 */

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
	
	
	@Autowired
	public ShoppingCartServiceImpl cartService;
	
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public ShoppingCart sessionScopedBean() {
	    return new ShoppingCart();
	}
	
	/**
	 * This method is method to get shopping cart.
	 * @param cartId represents id for cart that we want to  get.
	 * @return shopping cart.
	 */
	
	
	@RequestMapping(value = "getCart/{cartId}", method = RequestMethod.GET)
	public ShoppingCart getCart(@PathVariable Long cartId) {
		return cartService.getCart(cartId);
		
	}
	
	 /** This method is method to get shopping cart items that person wants to buy.
	 * @param cartId represents id for cart that we want to get it's items.
	 * @return list of shopping cart items.
	 */
	
	@RequestMapping(value = "getCartItems/{cartId}", method = RequestMethod.GET)
	public List<CartItem> getCartItems(@PathVariable Long cartId){
		return cartService.getCartItems(cartId);
	}
	
	/**
	 * This method is method to add item to shopping cart.
	 * @param cartId represents shopping cart in which person wants to add item.
	 * @param quantity represents number of copies of book that person wants to add to shopping cart.
	 * @param bookId represents id of the book that person wants to add to shopping cart.
	 * @return true value when cart item don't exist or false when cart item already exists.
	 * 
	 */
	
	@RequestMapping(value = { "addItem/{cartId}/{quantity}/{bookId}", "addItem/{quantity}/{bookId}"}, method = RequestMethod.GET)
	public boolean addItem(@PathVariable Optional<Long> cartId, @PathVariable int quantity, @PathVariable Long bookId) {

		return cartService.addItem(cartId, quantity, bookId);
	}
	
	/**
	 * This method is method to remove one item from shopping cart.
	 * @param cartId represents id of the shopping cart.
	 * @param cartItemId represents id of cart item that person wants to remove from shopping cart.
	 * @return true value.
	 * 
	 */
	
	@RequestMapping(value = "removeItem/{cartId}/{cartItemId}", method = RequestMethod.DELETE)
	public boolean removeItem(@PathVariable Long cartId, @PathVariable Long cartItemId) {
		return cartService.removeItem(cartId, cartItemId);
	}

	/**
	 * This method is method to change quantity of the book.
	 * @param quantity represents number of copies of book that person wants to add to shopping cart.
	 * @param itemId represents id of the item that person wants to add to shopping cart.
	 * @return true value.
	 * 
	 */
	
	@RequestMapping(value = "changeQuantity/{quantity}/{itemId}", method = RequestMethod.PUT)
	public boolean changeQuantity(@PathVariable int quantity, @PathVariable Long itemId) {
		return cartService.changeQuantity(quantity, itemId);
	}
	
	/**
	 * This method is method to empty shopping cart.
	 * @param cartId represents id of the shopping cart that person wants to empty.
	 * @return true value.
	 * 
	 */
	
	@RequestMapping(value = "emptyCart/{cartId}", method = RequestMethod.DELETE)
	public boolean emptyCart(@PathVariable Long cartId) {
		return cartService.emptyCart(cartId);
	}
	
	

}
