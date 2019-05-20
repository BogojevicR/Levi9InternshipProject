package internship.AuthenticationService.services;

import internship.AuthenticationService.models.User;


public interface AuthenticationService {

	boolean save(String username, String password, String role);
	String login(String username, String password);
	User findByToken(String token);
	boolean logout(String username, String password);
	
	
}
