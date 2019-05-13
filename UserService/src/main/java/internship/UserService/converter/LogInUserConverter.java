package internship.UserService.converter;

import internship.UserService.DTOmodels.UserToLogDTO;
import internship.UserService.model.User;

/**
 * This class represents abstract class for converting users.
 * @author s.krasic
 *
 */

public abstract class LogInUserConverter extends AbstractConverter {
	
	/**
	 * This method is method to convert original entity User to DTO entity of user.
	 * @param user that we need to be converted.
	 * @return DTO entity (newUserToLogDTO).
	 */

	public static UserToLogDTO fromEntity (User e) {
		UserToLogDTO newUserToLogDTO = new UserToLogDTO();
		newUserToLogDTO.setEmail(e.getEmail());
		newUserToLogDTO.setPassword(e.getPassword());
		return newUserToLogDTO;
	}
	
	/**
	 * This method is method to convert DTO entity of user to original entity.
	 * @param user DTO that we need to be converted.
	 * @return original entity (user).
	 */

	
	public static User toEntity (UserToLogDTO d) {
		User user = new User(); 
		user.setEmail(d.getEmail());
		user.setPassword(d.getPassword());
		return user;
		}

	
}
