package internship.ShoppingCartService.services;

import java.beans.Visibility;
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
	
	@Resource(name = "sessionScopedBean")
	ShoppingCart sessionScopedBean;
	//TODO: Obrisi counter i zameni ga sa Book IDem
	static int counter = 0;
	
	/**
	 * This method is method to get shopping cart.
	 * @param cartId represents id for cart that we want to  get.
	 * @return shopping cart.
	 */
	
	@Override
	public ShoppingCart getCart(Optional<Long> cartId) {
		if(!cartId.isPresent()){
		//	System.out.println(sessionScopedBean.toString());
			return new ShoppingCart(sessionScopedBean);
		}
		return cartRep.findById(cartId.get()).get();
	}
	
	/**
	 * This method is method to get shopping cart items that person wants to buy.
	 * @param cartId represents id for cart that we want to get it's items.
	 * @return list of shopping cart items.
	 */
	
	@Override
	public List<CartItem> getCartItems(Optional<Long> cartId) {
	
		if(!cartId.isPresent()){
			List<CartItem> retList = sessionScopedBean.getItemList();
			System.out.println("LISTA ITEMA NEAUTORIZOVANIH " + retList);
			return retList;
		}
		
		List<CartItem> retList = cartRep.getOne(cartId.get()).getItemList();
		System.out.println("LISTA ITEMA AUTORIZOVANIH " + retList);
		return  retList;

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
	public boolean addItem(Optional<Long> cartId, int quantity, Long bookId) {
		
		Book b = bookRep.getOne(bookId);
		if(!cartId.isPresent()){
			if(sessionScopedBean.getItemList().size() != 0) {
				if(sessionScopedBean.checkBook(bookId)) {
					return false;
				}
			}
			sessionScopedBean.getItemList().add(new CartItem(new Long(counter),b, quantity));
			counter++;
			
			//TODO: zasto ovo ne radi kad se skloni println ???
		//	sessionScopedBean.getItemList().toString();
			return true;
		}
		ShoppingCart cart = cartRep.getOne(cartId.get());
		if(cart.getItemList().size() != 0) {
			if(cart.checkBook(bookId)) {
				return false;
			}
		}

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
	public boolean changeQuantity(Optional<Long> cartId,int quantity, Long itemId) {
		if(!cartId.isPresent()) {
			for(CartItem i : sessionScopedBean.getItemList()) {
				if(i.getId().longValue() == itemId.longValue()) {
					i.setQuantity(quantity);
					return true;
				}
			}
			return false;
		}
		
		CartItem item = cartItemRep.getOne(itemId);
		item.setQuantity(quantity);
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
	public boolean emptyCart(Optional<Long> cartId) {
		if(!cartId.isPresent()) {
			sessionScopedBean.getItemList().clear();
			return true;
		}
		
		ShoppingCart cart = cartRep.getOne(cartId.get());
		for(CartItem i : cart.getItemList()) {
			cartItemRep.deleteById(i.getId());
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
	public boolean removeItem(Optional<Long> cartId, Long cartItemId) {
		if(!cartId.isPresent()) {
			for(CartItem i : sessionScopedBean.getItemList()) {
				if(i.getId().longValue() == cartItemId.longValue()) {
					sessionScopedBean.getItemList().remove(i);
					return true;
				}
			}
			return false;
		}
		
		ShoppingCart cart = cartRep.getOne(cartId.get());
		cart.removeItemById(cartItemId);
		cartItemRep.deleteById(cartItemId);
		cartRep.save(cart);
		return true;
	}

	
}
