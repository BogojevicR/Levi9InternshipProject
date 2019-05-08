package internship.UserService.DTOmodels;

public class UserToLogDTO {

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
