package internship.UserService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.UserService.model.User;
import internship.UserService.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}

	@Override
	public boolean save(User u) {
		if (userRepository.findByEmail(u.getEmail()) == null){
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
	public User logInUser(User u) {
		User userToLog = userRepository.logInUser(u.getEmail(), u.getPassword());
		if (userToLog != null) {
			return userToLog;
		}
		
		return new User();
	}
	
	
	
	
}
