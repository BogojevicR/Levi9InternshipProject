package internship.ShoppingCartService.controllers;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ShoppingCart sessionShoppingCart() {
	    return new ShoppingCart();
	}
	
	/**
	 * This method is method to get shopping cart.
	 * @param cartId represents id for cart that we want to  get.
	 * @return shopping cart.
	 */
	
	
	@GetMapping(value = { "getCart/{cartId}", "getCart" })
	public ShoppingCart getCart(@PathVariable Optional<Long> cartId) {
		return cartService.getCart(cartId);
		
	}
	
	 /** This method is method to get shopping cart items that person wants to buy.
	 * @param cartId represents id for cart that we want to get it's items.
	 * @return list of shopping cart items.
	 */
	
	@GetMapping(value = {"getCartItems/{cartId}", "getCartItems"})
	public List<CartItem> getCartItems(@PathVariable Optional<Long> cartId){
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
	
	@PostMapping(value = { "addItem/{cartId}/{quantity}/{bookId}", "addItem/{quantity}/{bookId}"})
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
	
	@DeleteMapping(value = { "removeItem/{cartId}/{bookORItemId}", "removeItem/{bookORItemId}" })
	public boolean removeItem(@PathVariable Optional<Long> cartId, @PathVariable Long bookORItemId) {
		return cartService.removeItem(cartId, bookORItemId);
	}

	/**
	 * This method is method to change quantity of the book.
	 * @param quantity represents number of copies of book that person wants to add to shopping cart.
	 * @param itemId represents id of the item that person wants to add to shopping cart.
	 * @return true value.
	 * 
	 */
	
	@PutMapping(value = { "changeQuantity/{cartId}/{quantity}/{bookORItemId}", "changeQuantity/{quantity}/{bookORItemId}"})
	public boolean changeQuantity(@PathVariable Optional<Long> cartId,@PathVariable int quantity, @PathVariable Long bookORItemId) {
		return cartService.changeQuantity(cartId,quantity, bookORItemId);
	}
	
	/**
	 * This method is method to empty shopping cart.
	 * @param cartId represents id of the shopping cart that person wants to empty.
	 * @return true value.
	 * 
	 */
	
	@DeleteMapping(value = { "emptyCart/{cartId}", "emptyCart" })
	public boolean emptyCart(@PathVariable Optional<Long> cartId) {
		return cartService.emptyCart(cartId);
	}
	
	

}
