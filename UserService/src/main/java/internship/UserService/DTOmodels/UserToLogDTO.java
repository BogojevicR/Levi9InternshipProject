package internship.UserService.DTOmodels;

/**
 * This class represents entity of user who is login to his profile.
 * @author s.krasic
 *
 */

public class UserToLogDTO {

	/** 
	 * 
	 * @author s.krasic
	 * email represents email of the user.
	 * password represents password of the user.
	 *
	 */
	
	private String email;
	private String password;

	public UserToLogDTO(){}
	
	public UserToLogDTO (String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
