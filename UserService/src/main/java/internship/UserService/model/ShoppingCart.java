package internship.UserService.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the entity of the users shopping cart.
 * 
 * @author r.bogojevic
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 4119611172148464783L;

	/**
	 * Auto-generated, unique key for a shopping cart.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/**
	 * List of current items in shopping cart.
	 */
	@ElementCollection
	private List<CartItem> itemList;


	public ShoppingCart(List<CartItem> itemList) {
		super();
		this.itemList = itemList;
	}

	public ShoppingCart(Long long1, List<CartItem> listOfItems) {
		super();
		this.id = long1;
		this.itemList = listOfItems;
	}

	public ShoppingCart(ShoppingCart sc) {
		super();
		this.itemList = sc.getItemList();
		this.id = sc.getId();
	}


	/**
	 * Checks if there is a certain book in shopping cart.
	 * 
	 * @param id2 id of the selected book
	 * @return boolean value wheather book allready is in shopping cart
	 */
	public boolean checkBook(Long id2) {
		for (CartItem i : this.itemList) {
			if (i.getBook().getId().equals(id2))
				return true;
		}
		return false;
	}

	/**
	 * Returns CartItem object by book id.
	 * 
	 * @param id2 id of the selected book.
	 * @return CartItem or null if there isn't CartItem with chosen id.
	 */
	public CartItem getItemByBookId(Long id2) {
		for (CartItem i : this.itemList) {
			if (i.getBook().getId().equals(id2))
				return i;
		}
		return null;
	}

	/**
	 * Removes cart item from shopping cart if exists.
	 * 
	 * @param cartItemId id of
	 */
	public void removeItemById(Long cartItemId) {
		for (CartItem i : this.itemList) {
			if (i.getId().equals(cartItemId)) {
				this.itemList.remove(i);
				return;
			}

		}

	}

}
