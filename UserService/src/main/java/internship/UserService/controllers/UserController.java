package internship.UserService.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import internship.UserService.converter.UserConverter;
import internship.UserService.helpers.Requests;
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
	 * @throws IOException 
	 * 
	 */
	
	@PostMapping(value = "/save")
	public  ResponseEntity<UserDTO>  save(@RequestBody UserDTO userDTO) throws IOException {
		User u = UserConverter.toEntity(userDTO); 

		if(userService.save(u)) {
			Map<String, String> map= new HashMap<String,String>();
			map.put("username", userDTO.getUsername());
			map.put("password", userDTO.getPassword());
			map.put("role", userDTO.getRole().toString());

			new Requests().makePostRequest("http://localhost:8085/auth/save",map);
			
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		}
		return new ResponseEntity<> (userDTO, HttpStatus.OK); 
	}
	
	/**
	 * This method is method to get all users.
	 * @return list of all users.
	 * @throws IOException 
	 */
	
	@GetMapping(value = "/getAll")
	public ResponseEntity<List<User>> getAll(HttpServletRequest request) throws IOException{
		try {
			new Requests().makeTokenCheck(new Requests().getCookie(request));
		}catch (IOException e) {
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<> (userService.findAll(),HttpStatus.OK);	
	}
	
	/**
	 * This method is method to get role of the user.
	 * @param id represents id of the user which role we want to get.
	 * @return string of the role.
	 * 
	 */
	
	@GetMapping(value = "/getRole/{id}")
	public  ResponseEntity<Role>  getRole(@PathVariable Long id, HttpServletRequest request) {
		try {
			new Requests().makeTokenCheck(new Requests().getCookie(request));
		}catch (IOException e) {
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}	
		
		Role rola = userService.getRoleById(id);
		return new ResponseEntity<> (rola, HttpStatus.OK); 
	}
	
	
	@GetMapping(value = "/getUser/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id, HttpServletRequest request){
		try {
			new Requests().makeTokenCheck(new Requests().getCookie(request));
		}catch (IOException e) {
			return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
		}
		User user = userService.getById(id);
		UserDTO userDTO = UserConverter.fromEntity(user);
		return new ResponseEntity<> (userDTO,HttpStatus.OK);
	}

	@GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam("username") final String username, @RequestParam("password") final String password, HttpServletResponse resp) throws IOException{
		User u = userService.login(username,password);
		if(u != null) {
			Map<String, String> map= new HashMap<String,String>();
			map.put("username", username);
			map.put("password", password);

			String response = new Requests().makePostRequest("http://localhost:8085/auth/login",map);
			Cookie cookie = new Cookie("token" , response);
			cookie.setValue(response);
			cookie.setPath("/");
			resp.addCookie(cookie);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
       return new ResponseEntity<>(username, HttpStatus.NOT_FOUND);
    }
	
	@GetMapping(value = "/logout")
    public ResponseEntity<String> logout(@RequestParam("username") final String username, HttpServletResponse resp) throws IOException{

			Map<String, String> map= new HashMap<String,String>();
			map.put("username", username);

			String response = new Requests().makePostRequest("http://localhost:8085/auth/logout",map);
			Cookie cookie = new Cookie("token" ,null);
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
    } 
	
}
