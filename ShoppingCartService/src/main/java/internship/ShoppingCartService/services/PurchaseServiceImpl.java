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

	private Purchase purchase;

	@Override
	public Purchase buyNow(Long userId, int quantity, Long bookId) {
		User user = userRep.getOne(userId);
		Book book = bookRep.getOne(bookId);
		purchase = new Purchase();
		if (book.getQuantity() >= quantity) {

			book.payBook(quantity);
			bookRep.save(book);

			CartItem item = new CartItem(book, quantity);
			cartItemRep.save(item);

			purchase.setPurchase(user.getUserInfo(), item);
			purchaseRep.save(purchase);

			user.getPurchases().add(purchase);
			userRep.save(user);
		}

		return purchase;
	}

	@Override
	public Purchase buyCart(Long userId) {
		User user = userRep.getOne(userId);
		ShoppingCart cart = user.getShoppingCart();
		purchase = new Purchase();
		if (!cart.getItemList().isEmpty() && cart.checkStock()) {

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
		}
		return purchase;
	}

	public Purchase buyCartUnauth(UserInfoDTO userInfo) {
		purchase = new Purchase();
		if (!sessionShoppingCart.getItemList().isEmpty() && sessionShoppingCart.checkStock()) {

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
		}
		return purchase;
	}

	public Purchase buyNowUnauth(int quantity, Long bookId, UserInfoDTO userInfo) {
		purchase = new Purchase();
		Book b = bookRep.getOne(bookId);
		if (b.getQuantity() >= quantity) {
			b.payBook(quantity);
			bookRep.save(b);

			CartItem item = new CartItem(b, quantity);
			cartItemRep.save(item);

			adressRep.save(AdressConverter.toEnity(userInfo.getAdress()));
			userInfoRep.save(UserInfoConverter.toEntity(userInfo));

			purchase.setPurchase(UserInfoConverter.toEntity(userInfo), item);
			purchaseRep.save(purchase);

		}

		return purchase;
	}

}
