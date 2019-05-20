package internship.AuthenticationService.services;

import java.util.List;

import internship.AuthenticationService.models.User;
import internship.AuthenticationService.models.User.Role;

public interface UserService {

	List<User> findAll();
	boolean save(User u);
	Role getRoleById(Long id);
	User logInUser(User u);

	
}
