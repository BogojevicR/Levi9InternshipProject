package internship.AuthenticationService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.AuthenticationService.models.User;
import internship.AuthenticationService.models.User.Role;
import internship.AuthenticationService.repositories.UserRepository;

/**
 * This class represents service for user.
 * @author s.krasic
 *
 */

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	
	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}
	


	@Override
	public Role getRoleById(Long id) {
		return userRepository.getRole(id);
	}

	
	@Override
	public User logInUser(User u) {
		User userToLog = userRepository.login(u.getUsername(), u.getPassword());
		if (userToLog != null) {
			return userToLog;
		}
		
		return new User();
	}

	@Override
	public User save(User u) {	
		return userRepository.save(u);
	}

	
	
	
	
	
}
