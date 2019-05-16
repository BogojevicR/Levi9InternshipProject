package internship.ShoppingCartService.services;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.ShoppingCartService.models.Adress;
import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Purchase;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.User.Role;
import internship.ShoppingCartService.models.UserInfo;
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
	
	
	@Resource(name = "sessionScopedBean")
	ShoppingCart sessionScopedBean;

	@Override
	public Purchase buyNow(Long userId,int quantity, Long bookId) {
		User u = userRep.getOne(userId);
		if(u.getRole() == Role.ADMIN)
			return null;
		Book b = bookRep.getOne(bookId);
		if(b.getQuantity() >= quantity) {
			b.payBook(quantity);
			bookRep.save(b);
			CartItem item = new CartItem(b, quantity);
			cartItemRep.save(item);
			Purchase purchase = new Purchase();
			purchase.setUserInfo(u.getUserInfo());
			purchase.getItemList().add(item);
			purchase.calculateTotalPrice();
			purchaseRep.save(purchase);
			u.getPurchases().add(purchase);
			userRep.save(u);

			return purchase;
		}

		return null;
	}

	@Override
	public Purchase buyCart(Long userId) {
		User u = userRep.getOne(userId);
		if(u.getRole() == Role.ADMIN)
			return null;
		ShoppingCart s = u.getShoppingCart();
		Purchase purchase = new Purchase();
		if(s.getItemList().size() == 0) {
			return null;
		}
		for(CartItem i : s.getItemList()) {
			if(i.getBook().getQuantity() < i.getQuantity()) {
				return null;	
			}
		}
		
		for(CartItem i : s.getItemList()) {
			i.getBook().payBook(i.getQuantity());
			bookRep.save(i.getBook());
			purchase.getItemList().add(i);
		}
		s.getItemList().clear();
		shoppingCartRep.save(s);
		purchase.calculateTotalPrice();
		purchase.setUserInfo(u.getUserInfo());
		purchaseRep.save(purchase);
		u.getPurchases().add(purchase);
		userRep.save(u);
		
		return purchase;
	}


	public Purchase buyCartUnauth(UserInfo userInfo) {
		
		if(sessionScopedBean.getItemList().size() == 0) {
			return null;
		}
		
		for(CartItem i : sessionScopedBean.getItemList()) {
			if(i.getBook().getQuantity() < i.getQuantity()) {
				return null;	
			}
		}
		
		Purchase purchase = new Purchase();
		adressRep.save(userInfo.getAdress());
		userInfoRep.save(userInfo);
		
		for(CartItem i : sessionScopedBean.getItemList()) {
			i.getBook().payBook(i.getQuantity());
			bookRep.save(i.getBook());
			purchase.getItemList().add(i);
			CartItem item = cartItemRep.save(i);
			i.setId(item.getId());
		}
		sessionScopedBean.getItemList().clear();
		purchase.calculateTotalPrice();
		purchase.setUserInfo(userInfo);
		purchaseRep.save(purchase);

		return purchase;
	}

	public Purchase buyNowUnauth(int quantity, Long bookId, UserInfo userInfo) {

		Book b = bookRep.getOne(bookId);
		if(b.getQuantity() >= quantity) {
			b.payBook(quantity);
			bookRep.save(b);
			CartItem item = new CartItem(b, quantity);
			cartItemRep.save(item);
			Purchase purchase = new Purchase();
			adressRep.save(userInfo.getAdress());
			userInfoRep.save(userInfo);
			purchase.setUserInfo(userInfo);
			purchase.getItemList().add(item);
			purchase.calculateTotalPrice();
			purchaseRep.save(purchase);

			return purchase;
		}

		return null;
	}

}
