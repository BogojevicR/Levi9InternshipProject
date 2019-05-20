package internship.AuthenticationService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import internship.AuthenticationService.services.AuthenticationServiceImpl;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
    private AuthenticationServiceImpl authService;

	@PostMapping(value = "/save/{username}/{password}/{role}")
	public ResponseEntity<Boolean> save(@PathVariable String username, @PathVariable String password, @PathVariable String role){
		authService.save(username, password, role);
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> getToken(@RequestParam("username") final String username, @RequestParam("password") final String password){
       String token = authService.login(username, password);
       if(token == ""){
    	   return new ResponseEntity<String>(token,HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<String>(token,HttpStatus.OK);
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Boolean> logout(@RequestParam("username") final String username, @RequestParam("password") final String password){
       boolean token = authService.logout(username, password);
       if(token)
    	   return new ResponseEntity<Boolean>(token,HttpStatus.OK);
       return new ResponseEntity<Boolean>(token,HttpStatus.NOT_FOUND);
       
    }
}
	
	
	

