package internship.UserService.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import internship.UserService.DTOmodels.UserToLogDTO;
import internship.UserService.model.Receipt;
import internship.UserService.model.ShoppingCart;
import internship.UserService.model.User;
import internship.UserService.model.User.Role;
import internship.UserService.repositories.ShoppingCartRepository;
import internship.UserService.repositories.UserRepository;

/**
 * This class represents service for user.
 * @author s.krasic
 *
 */

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShoppingCartRepository cartRepository;
	
	/**
	 * This method is method to get all users.
	 * @return list of all users.
	 */
	
	@Override
	public List<User> findAll() {
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}
	
	/**
	 * This method is method to save current user.
	 * @param u represents user who we want to save.
	 * @return true value when user don't exist or false when user that we want to save already exists.
	 * 
	 */

	@Override
	public boolean save(User u) {
		if (userRepository.findByEmail(u.getEmail()) == null){
			if(u.getRole() == User.Role.CUSTOMER) {
				ShoppingCart s = new ShoppingCart();
				cartRepository.save(s);
				u.setShoppingCart(s);	
				u.setReceipts( new ArrayList<Receipt>());
			}	
			userRepository.save(u);
			return true;
		}
		return false;
	}

	/**
	 * This method is method to get role of the user.
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
	 * @param u represents user that is loging in.
	 * @return loged user.
	 * 
	 */
	
	@Override
	public User logInUser(UserToLogDTO u) {
		User userToLog = userRepository.logInUser(u.getEmail(), u.getPassword());
		if (userToLog != null) {
			return userToLog;
		}
		
		return new User();
	}

	@Override
	public User getById(long id) {
		User u = userRepository.getOne(id);
		return u;
	}

	
	
	
	
	
}
