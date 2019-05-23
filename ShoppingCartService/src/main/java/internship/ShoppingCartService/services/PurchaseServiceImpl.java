package internship.ShoppingCartService.services;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.ShoppingCartService.DTO.UserInfoDTO;
import internship.ShoppingCartService.converters.AdressConverter;
import internship.ShoppingCartService.converters.UserInfoConverter;
import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Purchase;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.User.Role;
import internship.ShoppingCartService.repositories.AdressRepository;
import internship.ShoppingCartService.repositories.BookRepository;
import internship.ShoppingCartService.repositories.CartItemRepository;
import internship.ShoppingCartService.repositories.PurchaseRepository;
import internship.ShoppingCartService.repositories.ShoppingCartRepository;
import internship.ShoppingCartService.repositories.UserInfoRepository;
import internship.ShoppingCartService.repositories.UserRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	PurchaseRepository purchaseRep;
	@Autowired
	BookRepository bookRep;
	@Autowired
	CartItemRepository cartItemRep;
	@Autowired
	ShoppingCartRepository shoppingCartRep;
	@Autowired
	UserRepository userRep;
	@Autowired
	AdressRepository adressRep;
	@Autowired
	UserInfoRepository userInfoRep;

	@Resource(name = "sessionShoppingCart")
	ShoppingCart sessionShoppingCart;

	@Override
	public Purchase buyNow(Long userId, int quantity, Long bookId) {
		User user = userRep.getOne(userId);
		Book book = bookRep.getOne(bookId);
		Purchase purchase = null;
		if (book.getQuantity() >= quantity) {

			book.payBook(quantity);
			bookRep.save(book);

			CartItem item = new CartItem(book, quantity);
			cartItemRep.save(item);

			purchase = new Purchase();
			purchase.setUserInfo(user.getUserInfo());
			purchase.getItemList().add(item);
			purchase.calculateTotalPrice();
			purchaseRep.save(purchase);

			user.getPurchases().add(purchase);
			userRep.save(user);

			return purchase;
		}

		return purchase;
	}

	@Override
	public Purchase buyCart(Long userId) {
		User user = userRep.getOne(userId);
		ShoppingCart cart = user.getShoppingCart();
		Purchase purchase = new Purchase();
		if (cart.getItemList().isEmpty()) {

			return null;
		}
		for (CartItem cItem : cart.getItemList()) {
			if (cItem.getBook().getQuantity() < cItem.getQuantity()) {

				return null;
			}
		}

		for (CartItem item : cart.getItemList()) {
			item.getBook().payBook(item.getQuantity());
			bookRep.save(item.getBook());
			purchase.getItemList().add(item);
		}

		cart.getItemList().clear();
		shoppingCartRep.save(cart);

		purchase.calculateTotalPrice();
		purchase.setUserInfo(user.getUserInfo());
		purchaseRep.save(purchase);

		user.getPurchases().add(purchase);
		userRep.save(user);

		return purchase;
	}

	public Purchase buyCartUnauth(UserInfoDTO userInfo) {
		if (sessionShoppingCart.getItemList().isEmpty()) {

			return null;
		}

		for (CartItem item : sessionShoppingCart.getItemList()) {
			if (item.getBook().getQuantity() < item.getQuantity()) {

				return null;
			}
		}

		Purchase purchase = new Purchase();
		adressRep.save(AdressConverter.toEnity(userInfo.getAdress()));
		userInfoRep.save(UserInfoConverter.toEntity(userInfo));

		for (CartItem cItem : sessionShoppingCart.getItemList()) {
			cItem.getBook().payBook(cItem.getQuantity());
			bookRep.save(cItem.getBook());

			purchase.getItemList().add(cItem);
			CartItem item = cartItemRep.save(cItem);
			cItem.setId(item.getId());
		}
		sessionShoppingCart.getItemList().clear();
		purchase.calculateTotalPrice();
		purchase.setUserInfo(UserInfoConverter.toEntity(userInfo));
		purchaseRep.save(purchase);

		return purchase;
	}

	public Purchase buyNowUnauth(int quantity, Long bookId, UserInfoDTO userInfo) {

		Book b = bookRep.getOne(bookId);
		if (b.getQuantity() >= quantity) {
			b.payBook(quantity);
			bookRep.save(b);

			CartItem item = new CartItem(b, quantity);
			cartItemRep.save(item);

			adressRep.save(AdressConverter.toEnity(userInfo.getAdress()));
			userInfoRep.save(UserInfoConverter.toEntity(userInfo));

			Purchase purchase = new Purchase();
			purchase.setUserInfo(UserInfoConverter.toEntity(userInfo));
			purchase.getItemList().add(item);
			purchase.calculateTotalPrice();
			purchaseRep.save(purchase);

			return purchase;
		}

		return null;
	}

}
