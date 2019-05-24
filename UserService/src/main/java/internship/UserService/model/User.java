package internship.UserService.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents entity of user who is buying books.
 * 
 * @author s.krasic
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @author s.krasic Role - represents role of the user that can be ADMIN or
	 *         CUSTOMER. id is generated value that is value to do identification of
	 *         user. email represents email of the user. password represents
	 *         password of the user. shoppingCart represents shopping cart for users
	 *         shopping. receipts represents list of the receipts that user made by
	 *         each purchase.
	 *
	 */

	public enum Role {
		ADMIN, CUSTOMER
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Role role;

	@OneToOne
	private ShoppingCart shoppingCart;

	@OneToOne
	private UserInfo userInfo;

	@ElementCollection
	private List<Purchase> purchases;


	public User(Long id, String username, Role role, String password, UserInfo userInfo, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.username = username;
		this.userInfo = userInfo;
		this.role = role;
		this.password = password;
		this.shoppingCart = shoppingCart;

	}

	public User(Long id, String username, Role role, String password, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;
		this.shoppingCart = shoppingCart;

	}

	public User(Long id, String username, Role role, String password) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;

	}

}
