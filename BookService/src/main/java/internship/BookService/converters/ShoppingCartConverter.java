package internship.BookService.converters;

import internship.BookService.DTO.ShoppingCartDTO;
import internship.BookService.models.ShoppingCart;

public class ShoppingCartConverter extends AbstractConverter{

	public static ShoppingCartDTO fromEntity(ShoppingCart e) {
		if (e != null) {
			ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
			shoppingCartDTO.setId(e.getId());
			shoppingCartDTO.setItemList(e.getItemList());
			
			return shoppingCartDTO;	
		}else {
			
			return null;
		}
	}
	
	public static ShoppingCart toEntity(ShoppingCartDTO d) {
		if (d != null) {
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setId(d.getId());
			shoppingCart.setItemList(d.getItemList());
			
			return shoppingCart;
			
		}else {
			
			return null;
		}
	}
	
	private ShoppingCartConverter() {
		
	}
	
}
