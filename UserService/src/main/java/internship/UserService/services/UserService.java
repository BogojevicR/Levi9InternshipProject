package internship.UserService.services;

import java.util.List;

import internship.UserService.DTOmodels.UserToLogDTO;
import internship.UserService.model.User;

public interface UserService {

	List<User> findAll();
	boolean save(User u);
	String getRoleById(Long id);
	User logInUser(UserToLogDTO u);
	
}
