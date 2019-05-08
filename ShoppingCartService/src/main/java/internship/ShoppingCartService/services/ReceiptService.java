package internship.ShoppingCartService.services;

import internship.ShoppingCartService.models.Receipt;

public interface ReceiptService {
	
	Receipt buyNow(Long userId,int quantity, Long bookId);
	Receipt buyCart(Long cartId);

}
