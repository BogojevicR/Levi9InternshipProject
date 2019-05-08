package internship.UserService.converter;

import internship.UserService.DTOmodels.UserToLogDTO;
import internship.UserService.model.User;

public abstract class LogInUserConverter extends AbstractConverter {

	public static UserToLogDTO fromEntity (User e) {
		UserToLogDTO newUserToLogDTO = new UserToLogDTO();
		newUserToLogDTO.setEmail(e.getEmail());
		newUserToLogDTO.setPassword(e.getPassword());
		return newUserToLogDTO;
	}
	
	public static User toEntity (UserToLogDTO d) {
		User user = new User(); 
		user.setEmail(d.getEmail());
		user.setPassword(d.getPassword());
		return user;
		}

	
}
