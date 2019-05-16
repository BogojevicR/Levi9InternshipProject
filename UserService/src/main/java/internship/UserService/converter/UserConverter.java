package internship.UserService.converter;

import internship.UserService.model.ShoppingCart;
import internship.UserService.model.User;
import internship.UserService.model.UserInfo;
import internship.UserService.modelsDTO.ShoppingCartDTO;
import internship.UserService.modelsDTO.UserDTO;
import internship.UserService.modelsDTO.UserInfoDTO;


public class UserConverter {
	
	public static UserDTO fromEntity(User e) {
		if (e != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(e.getId());
			userDTO.setUsername(e.getUsername());
			UserInfoDTO userInfoDTO = UserInfoConverter.fromEntity(e.getUserInfo());
			userDTO.setUserInfo(userInfoDTO);
			userDTO.setPassword(e.getPassword());
			userDTO.setRole(e.getRole());
			ShoppingCartDTO shoppingCartDTO = ShoppingCartConverter.fromEntity(e.getShoppingCart());
			userDTO.setShoppingCart(shoppingCartDTO);
			userDTO.setPurchases(e.getPurchases());
			
			return userDTO;
			
		}else {
			
			return null;
		}
	}
	
	public static User toEntity(UserDTO d) {
		if (d != null) {
			User user = new User();
			user.setId(d.getId());
			user.setUsername(d.getUsername());
			UserInfo userInfo = UserInfoConverter.toEntity(d.getUserInfo());
			user.setUserInfo(userInfo);
			user.setPassword(d.getPassword());
			user.setRole(d.getRole());
			ShoppingCart shoppingCart = ShoppingCartConverter.toEntity(d.getShoppingCart());
			user.setShoppingCart(shoppingCart);
			user.setPurchases(d.getPurchases());
			
			return user;
			
		}else {
			
			return null;
		}
	}
	
	private UserConverter(){
	}

}
