package internship.ShoppingCartService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import internship.ShoppingCartService.models.Purchase;
import internship.ShoppingCartService.models.UserInfo;
import internship.ShoppingCartService.services.PurchaseServiceImpl;
/**
 * REST Controller for shopping cart application.
 * @author r.bogojevic
 *
 */

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

	@Autowired
	public PurchaseServiceImpl purchaseService;
	/**
	 * Instant buying of selected book in chosen quantity.
	 * @param userId id of user who is buying book.
	 * @param quantity number of books he is buying.
	 * @param bookId id of the book which is he buying.
	 * @return receipt that is created, together with HTTP status.
	 */
	@RequestMapping(value = "/buyNow/{userId}/{quantity}/{bookId}", method = RequestMethod.POST)
	public ResponseEntity<Purchase> buyNow(@PathVariable Long userId, @PathVariable int quantity, @PathVariable Long bookId) {
		Purchase response = purchaseService.buyNow(userId, quantity, bookId);
		if(response != null)
			return new ResponseEntity<Purchase>(response, HttpStatus.OK);
		return new ResponseEntity<Purchase>(response, HttpStatus.BAD_REQUEST);
	}
	/**
	 * Buys current cart of selected user.
	 * @param userId id of user who is buying.
	 * @return receipt that is created, together with HTTP status.
	 */
	@RequestMapping(value = "/buyCart/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Purchase> buyCart( @PathVariable Long userId) {
		Purchase response = purchaseService.buyCart(userId);
		if(response != null)
			return new ResponseEntity<Purchase>(response, HttpStatus.OK);
		return new ResponseEntity<Purchase>(response, HttpStatus.BAD_REQUEST);
		
	
	}
	

	@RequestMapping(value = "/buyCart", method = RequestMethod.POST)
	public ResponseEntity<Purchase> buyCart( @RequestBody UserInfo userInfo) {
		Purchase response = purchaseService.buyCartUnauth(userInfo);
		if(response != null)
			return new ResponseEntity<Purchase>(response, HttpStatus.OK);
		return new ResponseEntity<Purchase>(response, HttpStatus.BAD_REQUEST);
		
	
	}
	
	@RequestMapping(value = "/buyNow/{quantity}/{bookId}", method = RequestMethod.POST)
	public ResponseEntity<Purchase> buyNow(@PathVariable int quantity, @PathVariable Long bookId, @RequestBody UserInfo userInfo) {
		Purchase response = purchaseService.buyNowUnauth(quantity,bookId,userInfo);
		if(response != null)
			return new ResponseEntity<Purchase>(response, HttpStatus.OK);
		return new ResponseEntity<Purchase>(response, HttpStatus.BAD_REQUEST);
		
	
	}
	

}
