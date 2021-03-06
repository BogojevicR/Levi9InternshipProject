package internship.UserService.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Book is main entity in BookService. It is used for representation of the
 * book.
 * 
 * @author r.bogojevic
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Book implements Serializable {

	private static final long serialVersionUID = 753402533479256954L;

	/**
	 * Represents state of the book, whether its active for buying or not.
	 * 
	 * @author r.bogojevic
	 *
	 */
	public enum State {
		ACTIVE, DELETED
	}

	/**
	 * Auto-generated, unique key for a book.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * Title of the book in text value.
	 */
	@Column(nullable = false)
	private String title;
	/**
	 * Author of the book in text value.
	 */
	@Column(nullable = false)
	private String author;
	/**
	 * Cathegory of the book. It contains unique id and name.
	 */
	@ManyToOne
	private Category category;
	/**
	 * RPrice of the book in numerical value.
	 */
	@Column(nullable = false)
	private double price;
	/**
	 * Current state of the book.
	 */
	@Column(nullable = false)
	private State state;
	/**
	 * How many books are on stock in numeric value.
	 */
	@Column(nullable = false)
	private int quantity;
	/**
	 * Total number of sold books in numeric value.
	 */
	@Column(nullable = false)
	private int soldAmount;


	public Book(String title, String author, Category category, double price, State state, int quantity) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.state = state;
		this.quantity = quantity;
		this.soldAmount = 0;
	}

	public Book(Long id, String title, String author, Category category, double price, int quantity, int soldAmount) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.state = State.ACTIVE;
		this.quantity = quantity;
		this.soldAmount = soldAmount;
	}

	public Book(Long id, String title, String author, Category category, double price, State state, int quantity) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.state = state;
		this.quantity = quantity;
		this.soldAmount = 0;
	}

	/**
	 * Edits current book by selected one.
	 * 
	 * @param book contains values for change.
	 */
	public void edit(Book book) {
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.category = book.getCategory();
		this.price = book.getPrice();
		this.state = book.getState();
		this.quantity = book.getQuantity();
		this.soldAmount = book.getSoldAmount();
	}

	/**
	 * Simulate payment for books. Removes bought quantity from stock and adds it to
	 * sold amount.
	 * 
	 * @param quantity quantity of book
	 */
	public void payBook(int quantity) {
		this.quantity -= quantity;
		this.soldAmount += quantity;
	}

}
