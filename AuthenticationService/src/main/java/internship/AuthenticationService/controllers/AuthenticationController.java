package internship.AuthenticationService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return new ResponseEntity<>(true,HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/login")
    public ResponseEntity<String> getToken(@RequestParam("username") final String username, @RequestParam("password") final String password){
       String token = authService.login(username, password);
       if(token.equals("")){
    	   return new ResponseEntity<>(token,HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(token,HttpStatus.OK);
    }
	
	@PostMapping(value = "/logout")
    public ResponseEntity<Boolean> logout(@RequestParam("username") final String username, @RequestParam("password") final String password){
       boolean token = authService.logout(username, password);
       if(token)
    	   return new ResponseEntity<>(token,HttpStatus.OK);
       return new ResponseEntity<>(token,HttpStatus.NOT_FOUND);
       
    }
}
	
	
	

