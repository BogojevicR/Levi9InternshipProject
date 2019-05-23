package internship.UserService.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.UserService.model.Adress;
import internship.UserService.model.CartItem;
import internship.UserService.model.Purchase;
import internship.UserService.model.ShoppingCart;
import internship.UserService.model.User;
import internship.UserService.model.User.Role;
import internship.UserService.model.UserInfo;
import internship.UserService.repositories.AdressRepository;
import internship.UserService.repositories.ShoppingCartRepository;
import internship.UserService.repositories.UserInfoRepository;
import internship.UserService.repositories.UserRepository;

/**
 * This class represents service for user.
 * 
 * @author s.krasic
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShoppingCartRepository cartRepository;
	@Autowired
	private AdressRepository adressRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;

	/**
	 * This method is method to get all users.
	 * 
	 * @return list of all users.
	 */
	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	/**
	 * This method is method to save current user.
	 * 
	 * @param u represents user who we want to save.
	 * @return true value when user don't exist or false when user that we want to
	 *         save already exists.
	 * 
	 */
	@Override
	public boolean save(User u) {
		if (userRepository.findByUsername(u.getUsername()) == null) {
			if (u.getRole() == User.Role.CUSTOMER) {
				ShoppingCart s = new ShoppingCart();
				s.setItemList(new ArrayList<CartItem>());
				cartRepository.save(s);
				u.setShoppingCart(s);
				u.setPurchases(new ArrayList<Purchase>());

				UserInfo ui = u.getUserInfo();
				Adress a = ui.getAdress();
				adressRepository.save(a);
				userInfoRepository.save(ui);

			}
			userRepository.save(u);
			return true;
		}
		return false;
	}

	/**
	 * This method is method to get role of the user.
	 * 
	 * @param id represents id of the user which role we want to get.
	 * @return role of the user.
	 * 
	 */
	@Override
	public Role getRoleById(Long id) {
		return userRepository.getRole(id);
	}

	/**
	 * This method is method for user login.
	 * 
	 * @param u represents user that is loging in.
	 * @return loged user.
	 * 
	 */
	@Override
	public User getById(Long id) {
		return userRepository.getOne(id);
	}

	@Override
	public User login(String username, String password) {
		return userRepository.logInUser(username, password);
	}

}
