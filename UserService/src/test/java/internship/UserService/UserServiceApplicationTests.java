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

import internship.UserService.model.Adress;
import internship.UserService.model.CartItem;
import internship.UserService.model.ShoppingCart;
import internship.UserService.model.User;
import internship.UserService.model.UserInfo;
import internship.UserService.model.User.Role;
import internship.UserService.repositories.AdressRepository;
import internship.UserService.repositories.ShoppingCartRepository;
import internship.UserService.repositories.UserInfoRepository;
import internship.UserService.repositories.UserRepository;
import internship.UserService.services.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {

	
    @InjectMocks
	UserServiceImpl userService;
    
    @Mock
    UserRepository userRepository;
    
    @Mock
    ShoppingCartRepository cartRepository;
    
    @Mock
    AdressRepository adressRepository;
	
    @Mock
    UserInfoRepository userInfoRepository;
	
	@Test
	public void getAllUsersTest() {
		
		List<User> users = new ArrayList<User>();
		
		User userOne = new User(new Long(17),"sara", User.Role.ADMIN, "saki");
		User userTwo = new User(new Long(19),"radic@gmail.com", User.Role.CUSTOMER, "rada");
		User userThree = new User(new Long(21),"peric@gmail.com", User.Role.CUSTOMER, "pera");
		User userFour = new User(new Long(23),"mikic@gmail.com", User.Role.CUSTOMER, "mika");
		User userFive = new User(new Long(25),"jovic@gmail.com", User.Role.CUSTOMER, "jova");
		
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
	      
		  User user1 = new User(new Long(17),"krasicsara1@gmail.com", User.Role.ADMIN, "saki");
		  User user2 = new User(new Long(17),"pera", User.Role.CUSTOMER, "pera");
		  Adress adresa = new Adress(new Long(20), "Ruma", "Srbija", "Nikole tesle", "97");
 		  UserInfo userInfo =  new UserInfo(new Long(21), "Pera", "peric" , "pera@gmail.com", "065458595");
 		  userInfo.setAdress(adresa);
 		  user2.setUserInfo(userInfo);
	         
	      userService.save(user1);
	         
	      Mockito.when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);
	      
	      boolean retVal = userService.save(user1);
	      
	      assertEquals(retVal, false);
	      
	      Mockito.when(userRepository.findByUsername(user1.getUsername())).thenReturn(null);
	      Mockito.when(userRepository.getRole(user1.getId())).thenReturn(User.Role.ADMIN);
	      
	      boolean retVal1 = userService.save(user1);
	      
	      assertEquals(retVal1, true);
	      
	      Mockito.when(userRepository.findByUsername(user2.getUsername())).thenReturn(null);
	      Mockito.when(userRepository.getRole(user2.getId())).thenReturn(User.Role.CUSTOMER);
	      
		  List<CartItem> listOfItems = new ArrayList<CartItem>();		
		  ShoppingCart shopingCart = new ShoppingCart(new Long(2), listOfItems);
		  
		  Mockito.when(cartRepository.save(shopingCart)).thenReturn(shopingCart);
		  
		  ShoppingCart retVal2 = cartRepository.save(shopingCart);
		  
		  assertEquals(retVal2, shopingCart);
		  
		  UserInfo ui = user2.getUserInfo();
		  
		  Adress adress = ui.getAdress();
		  
		  Mockito.when(adressRepository.save(adress)).thenReturn(adress);
		  
		  Adress retVal3 = adressRepository.save(adress); 
		  
		  assertEquals(retVal3, adress);
		  
		  Mockito.when(userInfoRepository.save(ui)).thenReturn(ui);
		  
		  UserInfo retVal4 =  userInfoRepository.save(ui);
		  
		  assertEquals(retVal4, ui);
		  
		  boolean retVal5 = userService.save(user2);
		  
		  assertEquals(retVal5, true);

	  }
	  
	  @Test
	  public void getRoleByIdTest() {
		  
		  User user = new User(new Long(17),"krasicsara1@gmail.com", User.Role.ADMIN, "saki");
		  
		  Mockito.when(userRepository.getRole(new Long(user.getId()))).thenReturn(user.getRole());
		  
		  Role role = userService.getRoleById(user.getId());
		  
		  assertEquals(role, user.getRole());
	  }
	  
	  @Test
	  public void getUserTest() {
		  
		  User user = new User(new Long(17),"krasicsara1@gmail.com", User.Role.ADMIN, "saki");
		  
		  Mockito.when(userRepository.getOne(user.getId())).thenReturn(user);
		  
		  User userRet = userService.getById(user.getId());
		  
		  assertEquals(userRet, user);
		  
	  }
	

}
