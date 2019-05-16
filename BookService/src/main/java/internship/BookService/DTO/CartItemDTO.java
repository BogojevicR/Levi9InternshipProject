package internship.BookService.DTO;

import internship.BookService.converters.BookConverter;
import internship.BookService.models.CartItem;

public class CartItemDTO {

	private Long id;
	private BookDTO book;
	private int quantity;
	private double total;
	
	public CartItemDTO(CartItem cI) {
		this.id = cI.getId();
		this.book = BookConverter.fromEntity(cI.getBook());
		this.quantity = cI.getQuantity();
		this.total = cI.getTotal();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public BookDTO getBook() {
		return book;
	}
	
	public void setBook(BookDTO book) {
		this.book = book;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "CartItemDTO [id=" + id + ", book=" + book + ", quantity=" + quantity + ", total=" + total + "]";
	}
	
	
	
	
}
