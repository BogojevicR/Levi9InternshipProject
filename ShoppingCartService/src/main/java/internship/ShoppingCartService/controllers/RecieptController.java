package internship.ShoppingCartService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Receipt buyNow(@PathVariable Long userId, @PathVariable int quantity, @PathVariable Long bookId) {
		return receiptService.buyNow(userId, quantity, bookId);
	}
	
	@RequestMapping(value = "buyCart/{userId}", method = RequestMethod.POST)
	public Receipt buyNow( @PathVariable Long userId) {
		return receiptService.buyCart(userId);
	}

}
