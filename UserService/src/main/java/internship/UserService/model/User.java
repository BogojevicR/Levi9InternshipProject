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

/**
 * This class represents entity of user who is buying books.
 * 
 * @author s.krasic
 *
 */
@Entity
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

	public User() {
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((purchases == null) ? 0 : purchases.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((shoppingCart == null) ? 0 : shoppingCart.hashCode());
		result = prime * result + ((userInfo == null) ? 0 : userInfo.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (purchases == null) {
			if (other.purchases != null)
				return false;
		} else if (!purchases.equals(other.purchases))
			return false;
		if (role != other.role)
			return false;
		if (shoppingCart == null) {
			if (other.shoppingCart != null)
				return false;
		} else if (!shoppingCart.equals(other.shoppingCart))
			return false;
		if (userInfo == null) {
			if (other.userInfo != null)
				return false;
		} else if (!userInfo.equals(other.userInfo))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
