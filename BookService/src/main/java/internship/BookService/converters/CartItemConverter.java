package internship.BookService.converters;



import internship.BookService.DTO.CartItemDTO;
import internship.BookService.models.Book;
import internship.BookService.models.CartItem;

public class CartItemConverter {

	public static CartItemDTO fromEnity(CartItem e) {
		if (e != null) {
			CartItemDTO cartItemDTO = new CartItemDTO(e);
			return cartItemDTO;
		}else {
			return null;
		}
	}
	
	public static CartItem toEntity(CartItemDTO d) {
		if (d != null) {
			CartItem cI =  new CartItem();
			cI.setId(d.getId());
			Book book = BookConverter.toEntity(d.getBook());
			cI.setBook(book);
			cI.setQuantity(d.getQuantity());
			cI.setTotal(d.getTotal());
			return cI;
			
		}else {
			return null;
		}
	}
}
