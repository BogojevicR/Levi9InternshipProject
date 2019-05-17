package internship.UserService.controllers;

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
import org.springframework.web.bind.annotation.RequestMethod;

import internship.UserService.converter.UserConverter;
import internship.UserService.model.User;
import internship.UserService.model.User.Role;
import internship.UserService.modelsDTO.UserDTO;
import internship.UserService.services.UserService;

/**
 * This class represents controller for user.
 * @author s.krasic
 *
 */

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * This method is method to save current user.
	 * @param user represents user who we want to save.
	 * @return saved user.
	 * 
	 */
	
	@PostMapping(value = "/save")
	public  ResponseEntity<UserDTO>  save(@RequestBody UserDTO userDTO) {
		User u = UserConverter.toEntity(userDTO); 
		userService.save(u);
		UserDTO user1DTO = UserConverter.fromEntity(u);
		return new ResponseEntity<> (user1DTO, HttpStatus.OK); 
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
	
	@RequestMapping(value = "/getRole/{id}", method = RequestMethod.GET)
	public  ResponseEntity<Role>  getRole(@PathVariable Long id) {
		//String rola;
		//User u = userService.getById(id);
		Role rola = userService.getRoleById(id);
		
		/*if (u.getRole().toString().equals("ADMIN")) {
			rola = "ADMIN";
		}
		else {
			rola = "CUSTOMER";
		}*/
		return new ResponseEntity<> (rola, HttpStatus.OK); 
	}

	
	
}
