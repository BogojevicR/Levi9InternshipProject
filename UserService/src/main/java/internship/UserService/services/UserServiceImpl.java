package internship.UserService.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.UserService.DTOmodels.UserToLogDTO;
import internship.UserService.model.Receipt;
import internship.UserService.model.ShoppingCart;
import internship.UserService.model.User;
import internship.UserService.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShoppingCartRepository cartRepository;
	
	@Override
	public List<User> findAll() {
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}

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

	@Override
	public String getRoleById(Long id) {
		return userRepository.getRole(id);
	}

	@Override
	public User logInUser(UserToLogDTO u) {
		User userToLog = userRepository.logInUser(u.getEmail(), u.getPassword());
		if (userToLog != null) {
			return userToLog;
		}
		
		return new User();
	}

	
	
	
	
	
}
