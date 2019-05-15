	package internship.ShoppingCartService.services;

import java.util.List;
import java.util.Optional;

import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart getCart(Optional<Long> cartId);
	List<CartItem> getCartItems(Long cartId);
	boolean addItem(Optional<Long> cartId, int quantity, Long bookId);
	boolean changeQuantity(Optional<Long> cartId, int quantity, Long itemId);
	boolean emptyCart(Optional<Long> cartId);
	boolean removeItem(Optional<Long> cartId, Long cartItemId);
	
	
	
	
	
}
