package internship.ShoppingCartService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import internship.ShoppingCartService.models.Receipt;
import internship.ShoppingCartService.services.ReceiptServiceImpl;
/**
 * REST Controller for shopping cart application.
 * @author r.bogojevic
 *
 */

@RestController
@RequestMapping("/api/reciept")
public class RecieptController {

	@Autowired
	public ReceiptServiceImpl receiptService;
	/**
	 * Instant buying of selected book in chosen quantity.
	 * @param userId id of user who is buying book.
	 * @param quantity number of books he is buying.
	 * @param bookId id of the book which is he buying.
	 * @return receipt that is created, together with HTTP status.
	 */
	@RequestMapping(value = "buyNow/{userId}/{quantity}/{bookId}", method = RequestMethod.POST)
	public ResponseEntity<Receipt> buyNow(@PathVariable Long userId, @PathVariable int quantity, @PathVariable Long bookId) {
		Receipt response = receiptService.buyNow(userId, quantity, bookId);
		if(response != null)
			return new ResponseEntity<Receipt>(response, HttpStatus.OK);
		return new ResponseEntity<Receipt>(response, HttpStatus.BAD_REQUEST);
	}
	/**
	 * Buys current cart of selected user.
	 * @param userId id of user who is buying.
	 * @return receipt that is created, together with HTTP status.
	 */
	@RequestMapping(value = "buyCart/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Receipt> buyCart( @PathVariable Long userId) {
		Receipt response = receiptService.buyCart(userId);
		if(response != null)
			return new ResponseEntity<Receipt>(response, HttpStatus.OK);
		return new ResponseEntity<Receipt>(response, HttpStatus.BAD_REQUEST);
		
	
	}

}
