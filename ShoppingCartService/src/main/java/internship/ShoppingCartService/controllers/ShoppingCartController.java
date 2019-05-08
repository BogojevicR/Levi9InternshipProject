package internship.ShoppingCartService.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.services.ShoppingCartServiceImpl;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
	
	@Autowired
	public ShoppingCartServiceImpl cartService;
	
	@RequestMapping(value = "getCart/{cartId}", method = RequestMethod.GET)
	public ShoppingCart getCart(@PathVariable Long cartId) {
		return cartService.getCart(cartId);
		
	}
	
	@RequestMapping(value = "getCartItems/{cartId}", method = RequestMethod.GET)
	public List<CartItem> getCartItems(@PathVariable Long cartId){
		return cartService.getCartItems(cartId);
	}
	
	@RequestMapping(value = "addItem/{cartId}/{quantity}/{bookId}", method = RequestMethod.POST)
	public boolean addItem(@PathVariable Long cartId, @PathVariable int quantity, @PathVariable Long bookId) {
		return cartService.addItem(cartId, quantity, bookId);
	}
	
	@RequestMapping(value = "removeItem/{cartId}/{cartItemId}", method = RequestMethod.DELETE)
	public boolean removeItem(@PathVariable Long cartId, @PathVariable Long cartItemId) {
		return cartService.removeItem(cartId, cartItemId);
	}
	
	
	@RequestMapping(value = "changeQuantity/{quantity}/{itemId}", method = RequestMethod.PUT)
	public boolean changeQuantity(@PathVariable int quantity, @PathVariable Long itemId) {
		return cartService.changeQuantity(quantity, itemId);
	}
	
	@RequestMapping(value = "emptyCart/{cartId}", method = RequestMethod.DELETE)
	public boolean emptyCart(@PathVariable Long cartId) {
		return cartService.emptyCart(cartId);
	}

}
