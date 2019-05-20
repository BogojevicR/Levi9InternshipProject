package internship.AuthenticationService;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import internship.AuthenticationService.models.User;
import internship.AuthenticationService.models.User.Role;
import internship.AuthenticationService.security.AuthenticationProvider;
import internship.AuthenticationService.services.AuthenticationServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc(secure = false)
public class TestAuthenticationController {
	
	@Autowired
	public MockMvc mockMvc;
	
	@MockBean
	public AuthenticationServiceImpl authService;
	
	@MockBean
	public AuthenticationProvider authProvider;
	
	@Test
	public void saveShouldReturnTrue() throws Exception {
		
		User u = new User("rale", "123", Role.ADMIN);

		Mockito.when(authService.save("rale", "123", Role.ADMIN.toString())).thenReturn(true);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/save").param("username", u.getUsername()).param("password", u.getPassword()).param("role", u.getRole().toString()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		Mockito.verify(authService).save("rale", "123", Role.ADMIN.toString());
	}
	
	@Test
	public void loginShouldReturnToken() throws Exception {
		User u = new User("rale", "123", Role.ADMIN);
		u.setToken("token");
		
		Mockito.when(authService.login(u.getUsername(), u.getPassword())).thenReturn(u.getToken());
	
		this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/login").param("username", u.getUsername()).param("password", u.getPassword()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(authService).login(u.getUsername(), u.getPassword());
		
		Mockito.when(authService.login(u.getUsername(), u.getPassword())).thenReturn("");
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/login").param("username", u.getUsername()).param("password", u.getPassword()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
			
	}
	
	@Test
	public void logoutShouldReturnBoolean() throws Exception {
		User u = new User("rale", "123", Role.ADMIN);
		u.setToken("token");
		
		Mockito.when(authService.logout(u.getUsername())).thenReturn(true);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/logout").param("username", u.getUsername()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk());

		Mockito.verify(authService).logout(u.getUsername());

		
		Mockito.when(authService.logout(u.getUsername())).thenReturn(false);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/logout").param("username", u.getUsername()).contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	@Test
	public void validateTokenShouldReturnBoolean() throws Exception {
		Optional<User> u = Optional.of(new User("rale", "123", Role.ADMIN));

		String token = "Bearer token";
		
		Mockito.when(authService.validation(token)).thenReturn(u);

		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/auth/validation")).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		
	
	}
	
}
