package internship.UserService.modelsDTO;

/**
 * This class represents entity of user who is login to his profile.
 * 
 * @author s.krasic
 *
 */

public class UserToLogDTO {

	/**
	 * 
	 * @author s.krasic email represents email of the user. password represents
	 *         password of the user.
	 *
	 */

	private String username;
	private String password;

	public UserToLogDTO() {
	}

	public UserToLogDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
