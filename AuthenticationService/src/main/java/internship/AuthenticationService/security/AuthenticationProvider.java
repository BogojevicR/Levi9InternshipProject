package internship.AuthenticationService.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import internship.AuthenticationService.services.AuthenticationServiceImpl;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	 @Autowired
	 AuthenticationServiceImpl authService;

	 @Override
	 protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
	  //
	 }

	 @Override
	 protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {

	  Object token = usernamePasswordAuthenticationToken.getCredentials();
	  return new UserAccount(authService.findByToken(token.toString()));

	 }
}