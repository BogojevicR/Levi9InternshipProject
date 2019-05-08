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
	
	@RequestMapping(value = "buyNow/{quantity}/{bookId}", method = RequestMethod.POST)
	public Receipt buyNow(@PathVariable int quantity, @PathVariable Long bookId) {
		return receiptService.buyNow(quantity, bookId);
	}
	
	@RequestMapping(value = "buyCart/{cartId}", method = RequestMethod.POST)
	public Receipt buyNow( @PathVariable Long cartId) {
		return receiptService.buyCart(cartId);
	}

}
