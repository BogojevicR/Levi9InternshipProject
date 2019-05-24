package internship.UserService.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents entity of receipt.
 * 
 * @author s.krasic
 *
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Purchase implements Serializable {

	private static final long serialVersionUID = 3914793453491104987L;

	/**
	 * 
	 * @author s.krasic id is generated value that is value to do identification of
	 *         receipt. itemList represents list of items (book and number of copies
	 *         of book and price) that person wants to buy. totalPrice represents
	 *         final amount on receipt that person must pay.
	 *
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ElementCollection
	private List<CartItem> itemList = new ArrayList<>();
	private double totalPrice;
	@ManyToOne
	private UserInfo userInfo;

	public Purchase() {
		super();
		this.totalPrice = 0;
	}

	public Purchase(List<CartItem> itemList, UserInfo userInfo) {
		super();
		this.itemList = itemList;
		this.totalPrice = 0;
		this.userInfo = userInfo;
	}


	public void calculateTotalPrice() {
		for (CartItem i : this.itemList) {
			totalPrice += i.getTotal();
		}
	}

}
