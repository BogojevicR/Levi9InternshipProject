package internship.AuthenticationService.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import internship.AuthenticationService.DTO.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents entity of user who is buying books.
 * 
 * @author s.krasic
 *
 */

@Entity @Getter @Setter @ToString @NoArgsConstructor
public class User implements Serializable {

	public enum Role {
		ADMIN, CUSTOMER
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6655785789440219176L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private Role role;
	@Column(nullable = false)
	private String password;
	@Column
	private String token = "";


	public User(String username, String password, Role role) {
		super();
		this.username = username;
		this.role = role;
		this.password = password;
		this.token = "";
	}

	public User(Long id, String username, Role role, String password, String token) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password;
		this.token = token;
	}

	public User(UserDTO dto) {
		this.id = dto.getId();
		this.username = dto.getUsername();
		this.role = dto.getRole();
		this.password = dto.getPassword();
		this.token = dto.getToken();
	}

}
