package internship.UserService.modelsDTO;

import java.util.List;

import internship.UserService.model.Purchase;
import internship.UserService.model.User.Role;

public class UserDTO {

	
	private Long id;
	private String username;
	private String password;
	private Role role;
	private ShoppingCartDTO shoppingCart;
	private UserInfoDTO userInfo;
	private List<Purchase> purchases;
	
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(Long id, String username, String password, Role role, ShoppingCartDTO shoppingCart, UserInfoDTO userInfo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.shoppingCart = shoppingCart;
		this.userInfo = userInfo;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ShoppingCartDTO getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCartDTO shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public UserInfoDTO getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDTO userInfo) {
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
		return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", shoppingCart=" + shoppingCart + ", userInfo=" + userInfo + ", purchases=" + purchases + "]";
	}
	
	
	
}
