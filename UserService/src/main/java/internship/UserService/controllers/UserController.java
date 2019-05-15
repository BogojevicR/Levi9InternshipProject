package internship.UserService.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import internship.UserService.model.User;
import internship.UserService.model.User.Role;
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
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public  ResponseEntity<User>  save(@RequestBody User user) {
		userService.save(user);
		return new ResponseEntity<User> (user, HttpStatus.OK); 
	}
	
	/**
	 * This method is method to get all users.
	 * @return list of all users.
	 */
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAll(){
		return new ResponseEntity<List<User>> (userService.findAll(),HttpStatus.OK);
	}
	
	/**
	 * This method is method to get role of the user.
	 * @param id represents id of the user which role we want to get.
	 * @return string of the role.
	 * 
	 */
	
	@RequestMapping(value = "/getRole/{id}",  method = RequestMethod.GET)
	public  ResponseEntity<String>  getRole(@PathVariable Long id) {
		Role role = userService.getRoleById(id);
		String rola;
		User u = userService.getById(id);
		System.out.println(u.getRole().toString());
		if (u.getRole().toString().equals("ADMIN")) {
			rola = "ADMIN";
		}
		else {
			rola = "CUSTOMER";
		}
		return new ResponseEntity<String> (rola, HttpStatus.OK); 
	}
	
	/*@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Boolean> login(HttpServletRequest request, @RequestBody UserToLogDTO uDTO){
		HttpSession http = request.getSession();
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
	}*/
	
	
}
