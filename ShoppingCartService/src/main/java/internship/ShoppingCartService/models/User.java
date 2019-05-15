package internship.ShoppingCartService.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * This class represents entity of user who is buying books.
 * @author s.krasic
 *
 */

@Entity
public class User implements Serializable {


	private static final long serialVersionUID = 1L;

	/** 
	 * 
	 * @author s.krasic
	 * Role - represents role of the user that can be ADMIN or CUSTOMER.
	 * id is generated value that is value to do identification of user.
	 * email represents email of the user.
	 * password represents password of the user.
	 * shoppingCart represents shopping cart for users shopping.
	 * receipts represents list of the receipts that user made by each purchase.
	 *
	 */
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	

	public enum Role { ADMIN, CUSTOMER }
	
	@Column (nullable = false, unique = true )
	private String username;
	
	@Column (nullable = false)
	private String password;
	
	@Column (nullable = false)
	private Role role;
	
	@OneToOne
	private ShoppingCart shoppingCart;
	
	@OneToOne
	private UserInfo userInfo;

	@ElementCollection
	private List<Purchase> purchases;

	public User () {}
	
	public User (Long id, String username, Role role, String password,UserInfo userInfo, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.username = username;
		this.userInfo = userInfo;
		this.role = role;
		this.password = password;
		this.shoppingCart = shoppingCart;
		
	}

	
	public User (Long id, String username, Role role, String password, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;
		this.shoppingCart = shoppingCart;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", shoppingCart=" + shoppingCart + ", userInfo=" + userInfo + ", purchases=" + purchases + "]";
	}

	



	
	
}
