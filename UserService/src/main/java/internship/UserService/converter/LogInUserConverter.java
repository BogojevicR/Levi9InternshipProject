package internship.UserService.converter;

import internship.UserService.model.User;
import internship.UserService.modelsDTO.UserToLogDTO;

/**
 * This class represents abstract class for converting users.
 * @author s.krasic
 *
 */

public abstract class LogInUserConverter extends AbstractConverter {
	
	/**
	 * This method is method to convert original entity User to DTO entity of user.
	 * @param e that we need to be converted.
	 * @return DTO entity (newUserToLogDTO).
	 */

	public static UserToLogDTO fromEntity (User e) {
		UserToLogDTO newUserToLogDTO = new UserToLogDTO();
		newUserToLogDTO.setUsername(e.getUsername());
		newUserToLogDTO.setPassword(e.getPassword());
		return newUserToLogDTO;
	}
	
	/**
	 * This method is method to convert DTO entity of user to original entity.
	 * @param d DTO that we need to be converted.
	 * @return original entity (user).
	 */

	
	public static User toEntity (UserToLogDTO d) {
		User user = new User(); 
		user.setUsername(d.getUsername());
		user.setPassword(d.getPassword());
		return user;
		}

	private LogInUserConverter() {
		
	}
	
}
