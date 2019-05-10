package internship.UserService.controllers;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import internship.UserService.DTOmodels.UserToLogDTO;
import internship.UserService.model.User;
import internship.UserService.model.User.Role;
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
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<List<User>> (userService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getRole/{id}",  method = RequestMethod.GET)
	public  ResponseEntity<String>  getRole(@PathVariable Long id) {
		Role role = userService.getRoleById(id);
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
	public ResponseEntity<Boolean> login(@RequestBody UserToLogDTO uDTO, HttpSession http){
		User userToLog = userService.logInUser(uDTO);
		if (userToLog.getId() != null) {
			http.setAttribute("loged", userToLog);
			Role role = userService.getRoleById(userToLog.getId());
			http.setAttribute("role", role);
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseEntity<Boolean> logOut(HttpSession http) {
		http.removeAttribute("logged");
		http.removeAttribute("role");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
}
