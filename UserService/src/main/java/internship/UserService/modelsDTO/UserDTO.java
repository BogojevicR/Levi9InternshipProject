package internship.UserService.modelsDTO;

import java.util.List;

import internship.UserService.model.Purchase;
import internship.UserService.model.User.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {

	private Long id;
	private String username;
	private String password;
	private Role role;
	private ShoppingCartDTO shoppingCart;
	private UserInfoDTO userInfo;
	private List<Purchase> purchases;

	
	public UserDTO(Long id, String username, String password, Role role, ShoppingCartDTO shoppingCart,
			UserInfoDTO userInfo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.shoppingCart = shoppingCart;
		this.userInfo = userInfo;
	}

}
