package internship.AuthenticationService.DTO;

import internship.AuthenticationService.models.User;
import internship.AuthenticationService.models.User.Role;

public class UserDTO {

	private Long id;
	private String username;
	private Role role;
	private String password;
	private String token = "";

	
	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String username, Role role, String password, String token) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;
		this.token = token;
	}

	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.role = user.getRole();
		this.password = user.getPassword();
		this.token = user.getToken();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", role=" + role + ", password=" + password + ", token="
				+ token + "]";
	}

}
