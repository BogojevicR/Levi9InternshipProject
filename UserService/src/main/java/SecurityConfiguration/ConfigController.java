package SecurityConfiguration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import internship.UserService.model.User;

@RestController
public class ConfigController {

	   @GetMapping("/users")
	   public User getUser(){
	       return new User(new Long(17), "Mira", "Miric", "m@gmail.com", User.Role.ADMIN,"mira");
	   }
}
	

