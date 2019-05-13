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
	 * @param Role - represents role of the user that can be ADMIN or CUSTOMER.
	 * @param id is generated value that is value to do identification of user.
	 * @param name represents name of the user.
	 * @param surname represents surname of the user.
	 * @param email represents email of the user.
	 * @param password represents password of the user.
	 * @param shoppingCart represents shopping cart for users shopping.
	 * @param receipts represents list of the receipts that user made by each purchase.
	 *
	 */
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	

	public enum Role { ADMIN, CUSTOMER }
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column (nullable = false, unique = true )
	private String email;
	
	@Column (nullable = false)
	private Role role;
	
	@Column (nullable = false)
	private String password;
	
	@OneToOne
	private ShoppingCart shoppingCart;
	
	@ElementCollection
	private List<Receipt> receipts;
	
	public User () {}
	
	public User (Long id, String name, String surname, String email, Role role, String password, ShoppingCart shoppingCart) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.role = role;
		this.password = password;
		this.shoppingCart = shoppingCart;
		
	}

	
	public User (Long id, String name, String surname, String email, Role role, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.role = role;
		this.password = password;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", role=" + role
				+ "]";
	}
	
	
	
	
	
}
