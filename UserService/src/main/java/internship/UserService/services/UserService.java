package internship.UserService.services;

import java.util.List;

import internship.UserService.model.User;
import internship.UserService.model.User.Role;
import internship.UserService.modelsDTO.UserToLogDTO;

public interface UserService {

	List<User> findAll();
	boolean save(User u);
	Role getRoleById(Long id);
	User logInUser(UserToLogDTO u);
	User getById(long id);
	
}
