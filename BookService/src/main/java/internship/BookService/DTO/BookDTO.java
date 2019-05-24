package internship.BookService.DTO;

import internship.BookService.converters.CategoryConverter;
import internship.BookService.models.Book;
import internship.BookService.models.Book.State;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class BookDTO {

	private Long id;
	private String title;
	private String author;
	private CategoryDTO category;
	private double price;
	private State state;
	private int quantity;
	private int soldAmount;

	
	public BookDTO(Book book) {
		this.id = book.getId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.category = CategoryConverter.fromEntity(book.getCategory());
		this.price = book.getPrice();
		this.state = book.getState();
		this.quantity = book.getQuantity();
		this.soldAmount = book.getSoldAmount();
	}

}
