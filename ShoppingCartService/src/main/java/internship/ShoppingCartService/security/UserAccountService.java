package internship.ShoppingCartService.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.repositories.UserRepository;

@Service
public class UserAccountService implements org.springframework.security.core.userdetails.UserDetailsService {

	private UserRepository userRep;
		
	public UserAccountService(UserRepository userRep) {
		this.userRep = userRep;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRep.findByUsername(username);
		if(user == null)
			return null;
		UserAccount userAcc = new UserAccount(user);
		
		return userAcc;
	}

}
