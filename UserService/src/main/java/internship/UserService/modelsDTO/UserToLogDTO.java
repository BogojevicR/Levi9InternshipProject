package internship.UserService.modelsDTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents entity of user who is login to his profile.
 * 
 * @author s.krasic
 *
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserToLogDTO {

	/**
	 * 
	 * @author s.krasic email represents email of the user. password represents
	 *         password of the user.
	 *
	 */

	private String username;
	private String password;


	public UserToLogDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
