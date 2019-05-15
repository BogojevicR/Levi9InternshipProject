package internship.ShoppingCartService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import internship.ShoppingCartService.models.Order;
import internship.ShoppingCartService.services.OrderServiceImpl;
/**
 * REST Controller for shopping cart application.
 * @author r.bogojevic
 *
 */

@RestController
@RequestMapping("/api/reciept")
public class OrderController {

	@Autowired
	public OrderServiceImpl receiptService;
	/**
	 * Instant buying of selected book in chosen quantity.
	 * @param userId id of user who is buying book.
	 * @param quantity number of books he is buying.
	 * @param bookId id of the book which is he buying.
	 * @return receipt that is created, together with HTTP status.
	 */
	@RequestMapping(value = "buyNow/{userId}/{quantity}/{bookId}", method = RequestMethod.POST)
	public ResponseEntity<Order> buyNow(@PathVariable Long userId, @PathVariable int quantity, @PathVariable Long bookId) {
		Order response = receiptService.buyNow(userId, quantity, bookId);
		if(response != null)
			return new ResponseEntity<Order>(response, HttpStatus.OK);
		return new ResponseEntity<Order>(response, HttpStatus.BAD_REQUEST);
	}
	/**
	 * Buys current cart of selected user.
	 * @param userId id of user who is buying.
	 * @return receipt that is created, together with HTTP status.
	 */
	@RequestMapping(value = "buyCart/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Order> buyCart( @PathVariable Long userId) {
		Order response = receiptService.buyCart(userId);
		if(response != null)
			return new ResponseEntity<Order>(response, HttpStatus.OK);
		return new ResponseEntity<Order>(response, HttpStatus.BAD_REQUEST);
		
	
	}

}
