package internship.AuthenticationService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import internship.AuthenticationService.services.AuthenticationServiceImpl;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
    private AuthenticationServiceImpl authService;

	@PostMapping(value = "/save")
	public ResponseEntity<Boolean> save(@RequestParam String username, @RequestParam String password, @RequestParam String role){
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
    public ResponseEntity<Boolean> logout(@RequestParam("username") final String username){
       boolean token = authService.logout(username);
       if(token)
    	   return new ResponseEntity<>(token,HttpStatus.OK);
       return new ResponseEntity<>(token,HttpStatus.NOT_FOUND);
       
    }
	
	@PostMapping(value ="/validation")
	public ResponseEntity<Boolean> tokenValidation(@RequestHeader HttpHeaders headers){
		
		String token = headers.get("authorization").get(0);

		if(token.equals("Bearer"))
			return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
		token = token.replaceAll("]","");
		token = token.split("\\s+")[1];

		if(authService.validation(token).isPresent()) 
			return new ResponseEntity<>(true,HttpStatus.OK);
		
		return new ResponseEntity<>(false,HttpStatus.UNAUTHORIZED);
		
	}
}	
	
	
	

