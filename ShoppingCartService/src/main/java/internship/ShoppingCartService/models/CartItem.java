package internship.ShoppingCartService.models;

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
 * Represents entity of item in shopping cart.
 * 
 * @author r.bogojevic
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CartItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3721540758013955780L;

	/**
	 * Auto-generated,unique key for each Cart Item.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * Book which will be bought.
	 */
	@ManyToOne
	private Book book;

	/**
	 * Number of books for purchase.
	 */
	@Column(nullable = false)
	private int quantity;

	/**
	 * Total price for current cart item. Quantity * Book price.
	 */
	@Column(nullable = false)
	private double total;
	

	public CartItem(Book book, int quantity) {
		super();
		this.book = book;
		this.quantity = quantity;
		this.total = book.getPrice() * quantity;
	}

	public CartItem(Long long1, Book book1, int i) {
		this.id = long1;
		this.quantity = i;
		this.book = book1;
		this.total = book.getPrice() * quantity;
	}

}
