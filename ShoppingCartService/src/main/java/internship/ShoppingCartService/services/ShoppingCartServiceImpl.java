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

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	public CartItemRepository cartItemRep;
	@Autowired
	public ShoppingCartRepository cartRep;
	@Autowired
	public BookRepository bookRep;
	
	@Override
	public ShoppingCart getCart(Long cartId) {	
		return cartRep.findById(cartId).get();
	}
	
	@Override
	public List<CartItem> getCartItems(Long cartId) {
		return  cartRep.findById(cartId).get().getItemList();
		
	}
	
	@Override
	public boolean addItem(Long cartId, int quantity, Long bookId) {
		ShoppingCart cart = cartRep.findById(cartId).get();
		if(cart.checkBook(bookId)) {
			return false;
		}
		Book b = bookRep.getOne(bookId);
		CartItem item = new CartItem(b, quantity);
		cartItemRep.save(item);
		cart.getItemList().add(item);
		cartRep.save(cart);
		return true;
	}

	@Override
	public boolean changeQuantity(int quantity, Long itemId) {
		CartItem item = cartItemRep.findById(itemId).get();
		item.setQuantity(quantity);
		item.setTotal(quantity * item.getBook().getPrice());
		cartItemRep.save(item);
		return true;
	}

	@Override
	public boolean emptyCart(Long cartId) {
		
		ShoppingCart cart = cartRep.findById(cartId).get();
		for(CartItem item : cart.getItemList()) {
			cartItemRep.delete(item);
		}
		cart.getItemList().clear();
		cartRep.save(cart);
		
		return true;
	}

	@Override
	public boolean removeItem(Long cartId, Long cartItemId) {
		ShoppingCart cart = cartRep.findById(cartId).get();
		cart.removeItemById(cartItemId);
		cartItemRep.deleteById(cartItemId);
		return true;
	}

	
}
