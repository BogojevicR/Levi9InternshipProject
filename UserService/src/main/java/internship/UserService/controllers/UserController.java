package internship.UserService.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import internship.UserService.model.User;
import internship.UserService.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public  ResponseEntity<User>  save(@RequestBody User user) {
		userService.save(user);
		return new ResponseEntity<User> (user, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/getRole/{id}",  method = RequestMethod.GET)
	public  ResponseEntity<String>  getRole(@PathVariable Long id) {
		String role = userService.getRoleById(id);
		String rola;
		if (role.equals("0")) {
			rola = "ADMIN";
		}
		else {
			rola = "CUSTOMER";
		}
		return new ResponseEntity<String> (rola, HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Boolean> login(@RequestBody User u, HttpSession http){
		User userToLog = userService.logInUser(u);
		if (userToLog.getId() != null) {
			http.setAttribute("loged", userToLog);
			String role = userService.getRoleById(u.getId());
			http.setAttribute("role", role);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
	}
	
}