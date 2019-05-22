package internship.AuthenticationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import internship.AuthenticationService.models.User;
import internship.AuthenticationService.models.User.Role;
import internship.AuthenticationService.repositories.UserRepository;
import internship.AuthenticationService.services.AuthenticationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestAuthenticationService {
	
	@InjectMocks
	AuthenticationServiceImpl authService;
	
	@Mock
	UserRepository userRep;

	
	@Test
	public void saveTest() {
		User u = new User("rale", "123", Role.ADMIN);
		
		when(userRep.findByUsername("rale")).thenReturn(null);
		assertEquals(true, authService.save(u.getUsername(), u.getPassword(), u.getRole().toString()));
		
		u.setRole(Role.CUSTOMER);
		
		when(userRep.findByUsername("rale")).thenReturn(null);
		assertEquals(true, authService.save(u.getUsername(), u.getPassword(), u.getRole().toString()));
		
		when(userRep.findByUsername("rale")).thenReturn(u);
		assertEquals(false, authService.save(u.getUsername(), u.getPassword(), u.getRole().toString()));
		
	}
	
	@Test
	public void loginTest() {
		User u = new User("rale", "123", Role.ADMIN);
		u.setToken("token");
		when(userRep.login(u.getUsername(), u.getPassword())).thenReturn(u);
		assertEquals("token", authService.login(u.getUsername(), u.getPassword()));
		
		u.setToken("");
		
		when(userRep.login(u.getUsername(), u.getPassword())).thenReturn(u);
		authService.login(u.getUsername(), u.getPassword());
		assertNotNull(u.getToken());
		
		when(userRep.login(u.getUsername(), u.getPassword())).thenReturn(null);
		assertEquals("", authService.login(u.getUsername(), u.getPassword()));

	}
	
	@Test
	public void findByTokenTest() {
		Optional<User> u = Optional.of(new User("rale", "123", Role.ADMIN));
		u.get().setToken("token");
		when(userRep.findByToken(u.get().getToken())).thenReturn(u);
		assertNotNull(authService.findByToken(u.get().getToken()));
		
		when(userRep.findByToken(u.get().getToken())).thenReturn(Optional.empty());
		assertNull(authService.findByToken(u.get().getToken()));
			
	}
	
	@Test
	public void logoutTest() {
		User u = new User("rale", "123", Role.ADMIN);

		when(userRep.findByUsername(u.getUsername())).thenReturn(u);
		assertEquals(true, authService.logout(u.getUsername()));
		
		when(userRep.findByUsername(u.getUsername())).thenReturn(null);
		assertEquals(false, authService.logout(u.getUsername()));
	
	}
	
	@Test
	public void validationTest() {
		Optional<User> u = Optional.of(new User("rale", "123", Role.ADMIN));

		when(userRep.findByToken(u.get().getToken())).thenReturn(u);
		
		assertNotNull(authService.validation(u.get().getToken()));
	}
	
}

