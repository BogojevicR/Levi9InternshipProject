package internship.AuthenticationService.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import internship.AuthenticationService.models.User;
import internship.AuthenticationService.models.User.Role;
import internship.AuthenticationService.repositories.UserRepository;
@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private UserRepository userRep;
	
	
	@Override
	public boolean save(String username, String password, String role) {
		if (userRep.findByUsername(username) == null){
			if(role.equals(Role.ADMIN.toString())) {
				userRep.save(new User(username,password,Role.ADMIN));
			}else {
				userRep.save(new User(username,password,Role.CUSTOMER));
			}
			
			return true;
		}
		return false;
	}
	@Override
	public String login(String username, String password) {
		User user = userRep.login(username, password);
        if(user != null){
        	if(!user.getToken().equals(""))
        		return user.getToken();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userRep.save(user);
            return token;
        }

        return "";
	}

	@Override
	public User findByToken(String token) {
		Optional<User> user= userRep.findByToken(token);
        if(user.isPresent()){
            return  user.get();
        }
        return  null;
	}

	@Override
	public boolean logout(String username) {
		User user = userRep.findByUsername(username);
		if(user != null) {
			user.setToken("");
			userRep.save(user);
			return true;
		}
			return false;
	}
	@Override
	public Optional<User> validation(String token) {
		
		
		return userRep.findByToken(token);
	}

	



}
