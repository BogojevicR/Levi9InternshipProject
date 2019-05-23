package internship.BookService.converters;

import internship.BookService.DTO.BookDTO;
import internship.BookService.models.Book;

public abstract class BookConverter extends AbstractConverter {

	public static BookDTO fromEntity (Book b) {
		return new BookDTO(b);
	}
	
	public static Book toEntity (BookDTO b) {
		Book book = new Book();
		book.setId(b.getId());
		book.setTitle(b.getTitle());
		book.setAuthor(b.getAuthor());
		book.setCategory(CategoryConverter.toEntity(b.getCategory()));
		book.setPrice(b.getPrice());
		book.setState(b.getState());
		book.setQuantity(b.getQuantity());
		book.setSoldAmount(b.getSoldAmount());
		
		return book;
	}
	
}
