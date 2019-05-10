package internship.UserService;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import internship.UserService.DTOmodels.UserToLogDTO;
import internship.UserService.model.User;
import internship.UserService.model.User.Role;
import internship.UserService.repositories.UserRepository;
import internship.UserService.services.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

	
    @InjectMocks
	UserServiceImpl userService;
    
    @Mock
    UserRepository userRepository;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void getAllUsersTest() {
		
		List<User> users = new ArrayList<User>();
		
		User userOne = new User(new Long(17),"Sara","Krasic","krasicsara1@gmail.com", User.Role.ADMIN, "saki");
		User userTwo = new User(new Long(19),"Rada","Radic","radic@gmail.com", User.Role.CUSTOMER, "rada");
		User userThree = new User(new Long(21),"Pera","Peric","peric@gmail.com", User.Role.CUSTOMER, "pera");
		User userFour = new User(new Long(23),"Mika","Mikic","mikic@gmail.com", User.Role.CUSTOMER, "mika");
		User userFive = new User(new Long(25),"Jova","Jovic","jovic@gmail.com", User.Role.CUSTOMER, "jova");
		
		users.add(userOne);
		users.add(userTwo);
		users.add(userThree);
		users.add(userFour);
		users.add(userFive);
		
		Mockito.when(userRepository.findAll()).thenReturn(users);
		
		List<User> usersServ = userService.findAll();
        
        assertEquals(5, usersServ.size());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
  
	}
	
	  @Test
	  public void saveUserTest() {
	      
		  User user = new User(new Long(17),"Sara","Krasic","krasicsara1@gmail.com", User.Role.ADMIN, "saki");
	         
	      userService.save(user);
	         
	      Mockito.verify(userRepository, Mockito.times(1)).save(user);
	  }
	  
	  @Test
	  public void loginUserTest() {
		  
		  User user = new User(new Long(17),"Sara","Krasic","krasicsara1@gmail.com", User.Role.ADMIN, "saki");
		  UserToLogDTO userDTO = new UserToLogDTO("krasicsara1@gmail.com", "saki");
		  
		  Mockito.when(userRepository.logInUser("krasicsara1@gmail.com", "saki")).thenReturn(user);
		  
		  User logged = userService.logInUser(userDTO);
		  System.out.println(logged.getEmail() + logged.getName() + logged.getSurname() + logged.getPassword());
		  
		  assertEquals(user, logged);
		  
		 
	  }
	  
	  @Test
	  public void getRoleByIdTest() {
		  
		  User user = new User(new Long(17),"Sara","Krasic","krasicsara1@gmail.com", User.Role.ADMIN, "saki");
		  
		  Mockito.when(userRepository.getRole(new Long(user.getId()))).thenReturn(user.getRole());
		  
		  Role role = userService.getRoleById(user.getId());
		  
		  assertEquals(role, user.getRole());
	  }
	

}
