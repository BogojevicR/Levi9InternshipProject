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


@RestController
@RequestMapping("/api/reciept")
public class RecieptController {
	
	@Autowired
	public ReceiptServiceImpl receiptService;
	
	@RequestMapping(value = "buyNow/{userId}/{quantity}/{bookId}", method = RequestMethod.POST)
	public ResponseEntity<Receipt> buyNow(@PathVariable Long userId, @PathVariable int quantity, @PathVariable Long bookId) {
		Receipt response = receiptService.buyNow(userId, quantity, bookId);
		if(response != null)
			return new ResponseEntity<Receipt>(response, HttpStatus.OK);
		return new ResponseEntity<Receipt>(response, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "buyCart/{userId}", method = RequestMethod.POST)
	public ResponseEntity<Receipt> buyCart( @PathVariable Long userId) {
		Receipt response = receiptService.buyCart(userId);
		if(response != null)
			return new ResponseEntity<Receipt>(response, HttpStatus.OK);
		return new ResponseEntity<Receipt>(response, HttpStatus.BAD_REQUEST);
		
	
	}

}
