	package internship.ShoppingCartService.services;

import java.util.List;
import java.util.Optional;

import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.ShoppingCart;

public interface ShoppingCartService {

	ShoppingCart getCart(Long cartId);
	List<CartItem> getCartItems(Optional <Long> cartId);
	boolean addItem(Optional<Long> cartId, int quantity, Long bookId);
	boolean changeQuantity(int quantity, Long itemId);
	boolean emptyCart(Long cartId);
	boolean removeItem(Long cartId, Long cartItemId);
	
}
