package internship.AuthenticationService.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import internship.AuthenticationService.DTO.UserDTO;
import internship.AuthenticationService.models.User;
import internship.AuthenticationService.models.User.Role;
import internship.AuthenticationService.services.UserServiceImpl;

/**
 * This class represents controller for user.
 * @author s.krasic
 *
 */

@Controller
@RequestMapping("api/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	/**
	 * This method is method to save current user.
	 * @param user represents user who we want to save.
	 * @return saved user.
	 * 
	 */
	
	@PostMapping(value = "/save")
	public  ResponseEntity<User>  save(@RequestBody UserDTO user) {
		User u = userService.save(new User(user));
		return new ResponseEntity<> (u, HttpStatus.OK); 
	}
	
	/**
	 * This method is method to get all users.
	 * @return list of all users.
	 */
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<> (userService.findAll(),HttpStatus.OK);
	}
	
	/**
	 * This method is method to get role of the user.
	 * @param id represents id of the user which role we want to get.
	 * @return string of the role.
	 * 
	 */
	
	@GetMapping(value = "/getRole/{id}")
	public  ResponseEntity<String>  getRole(@PathVariable Long id) {
		Role role = userService.getRoleById(id);
		String rola;
		if (role.toString().equals("ADMIN")) {
			rola = "ADMIN";
		}
		else {
			rola = "CUSTOMER";
		}
		return new ResponseEntity<> (rola, HttpStatus.OK); 
	}

	
	
}
