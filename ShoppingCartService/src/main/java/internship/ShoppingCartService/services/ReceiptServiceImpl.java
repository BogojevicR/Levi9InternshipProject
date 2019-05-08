package internship.ShoppingCartService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.ShoppingCartService.models.Book;
import internship.ShoppingCartService.models.CartItem;
import internship.ShoppingCartService.models.Receipt;
import internship.ShoppingCartService.models.ReceiptItem;
import internship.ShoppingCartService.models.ShoppingCart;
import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.repositories.BookRepository;
import internship.ShoppingCartService.repositories.ReceiptItemRepository;
import internship.ShoppingCartService.repositories.ReceiptRepository;
import internship.ShoppingCartService.repositories.ShoppingCartRepository;
import internship.ShoppingCartService.repositories.UserRepository;
@Service
public class ReceiptServiceImpl implements ReceiptService {
	
	@Autowired
	ReceiptItemRepository receiptItemRep;
	@Autowired
	ReceiptRepository receiptRep;
	@Autowired
	BookRepository bookRep;
	@Autowired
	ShoppingCartRepository shoppingCartRep;
	@Autowired
	UserRepository userRep;

	@Override
	public Receipt buyNow(Long userId,int quantity, Long bookId) {
		User u = userRep.getOne(userId);
		
		Book b = bookRep.findById(bookId).get();
		if(b.getQuantity() >= quantity) {
			b.payBook(quantity);
			bookRep.save(b);
			ReceiptItem item = new ReceiptItem(b, quantity);
			receiptItemRep.save(item);
			Receipt r = new Receipt();
			r.getItemList().add(item);
			receiptRep.save(r);
			u.getReceipts().add(r);
			userRep.save(u);

			return r;
		}

		return null;
	}

	@Override
	public Receipt buyCart(Long userId) {
		User u = userRep.getOne(userId);

		ShoppingCart s = u.getShoppingCart();
		Receipt r = new Receipt();
		
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
		receiptRep.save(r);
		u.getReceipts().add(r);
		userRep.save(u);
		
		return r;
	}

}
