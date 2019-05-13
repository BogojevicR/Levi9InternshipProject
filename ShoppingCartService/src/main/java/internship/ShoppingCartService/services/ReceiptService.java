package internship.ShoppingCartService.services;

import internship.ShoppingCartService.models.Receipt;
/**
 * Interface for ReceiptService.
 * @author r.bogojevic
 *
 */
public interface ReceiptService {
	/**
	 * Instant buying of selected book in chosen quantity.
	 * @param userId id of user who is buying book.
	 * @param quantity number of books he is buying.
	 * @param bookId id of the book which is he buying.
	 * @return receipt that is created.
	 */
	Receipt buyNow(Long userId,int quantity, Long bookId);
	/**
	 * Buys current cart of selected user.
	 * @param userId id of user who is buying.
	 * @return receipt that is created.
	 */
	Receipt buyCart(Long cartId);

}
