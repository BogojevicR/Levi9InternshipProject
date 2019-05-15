package internship.ShoppingCartService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Order;
import internship.ShoppingCartService.models.ReceiptItem;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.User.Role;
import internship.ShoppingCartService.repositories.BookRepository;
import internship.ShoppingCartService.repositories.ReceiptItemRepository;
import internship.ShoppingCartService.repositories.OrderRepository;
import internship.ShoppingCartService.repositories.ShoppingCartRepository;
import internship.ShoppingCartService.repositories.UserRepository;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	ReceiptItemRepository receiptItemRep;
	@Autowired
	OrderRepository receiptRep;
	@Autowired
	BookRepository bookRep;
	@Autowired
	ShoppingCartRepository shoppingCartRep;
	@Autowired
	UserRepository userRep;

	@Override
	public Order buyNow(Long userId,int quantity, Long bookId) {
		User u = userRep.getOne(userId);
		if(u.getRole() == Role.ADMIN)
			return null;
		Book b = bookRep.getOne(bookId);
		if(b.getQuantity() >= quantity) {
			b.payBook(quantity);
			bookRep.save(b);
			ReceiptItem item = new ReceiptItem(b, quantity);
			receiptItemRep.save(item);
			Order r = new Order();
			r.getItemList().add(item);
			r.calculateTotalPrice();
			receiptRep.save(r);
			u.getReceipts().add(r);
			userRep.save(u);

			return r;
		}

		return null;
	}

	@Override
	public Order buyCart(Long userId) {
		User u = userRep.getOne(userId);
		if(u.getRole() == Role.ADMIN)
			return null;
		ShoppingCart s = u.getShoppingCart();
		Order r = new Order();
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
			ReceiptItem ri = new ReceiptItem(i);
			receiptItemRep.save(ri);
			r.getItemList().add(ri);
		}
		s.getItemList().clear();
		shoppingCartRep.save(s);
		r.calculateTotalPrice();
		receiptRep.save(r);
		u.getReceipts().add(r);
		userRep.save(u);
		
		return r;
	}

}
