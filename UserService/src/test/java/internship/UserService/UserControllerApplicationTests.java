package internship.UserService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import com.google.gson.Gson;

import internship.UserService.controllers.UserController;
import internship.UserService.converter.UserConverter;
import internship.UserService.model.User;
import internship.UserService.modelsDTO.UserDTO;
import internship.UserService.security.UserAccountService;
import internship.UserService.services.UserService;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@WebAppConfiguration
public class UserControllerApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private UserService userService;
    
    @MockBean
	public UserAccountService userAccountService;
    

    @WithMockUser(username="sara95krasic", authorities="ADMIN", password="saki")
    @Test
    public void saveTest() throws Exception {
    	
    	User user1 = new User(new Long(17),"sara", User.Role.ADMIN, "saki",null);
    	UserDTO user1DTO = UserConverter.fromEntity(user1);
    	
       Mockito.when(userService.save(user1)).thenReturn(true);
    	
       /*this.mockMvc.perform(MockMvcRequestBuilders.post("/user/save").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(user1DTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
       .andExpect(MockMvcResultMatchers.content().json(new Gson().toJson(user1DTO)));
       */
       this.mockMvc.perform(MockMvcRequestBuilders.post("/user/save").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(user1DTO))).andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
       
       Mockito.verify(userService).save(user1);
       


    }
    
    @Test
    @WithMockUser(username="sara95krasic", authorities="ADMIN", password="saki")
    public void getAllTest() throws Exception {
    	
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
		
		String jsonUsers = new Gson().toJson(users);
		
		Mockito.when(userService.findAll()).thenReturn(users);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonUsers));
		
		 Mockito.verify(userService).findAll();
		
    }
    
    
    @Test
    @WithMockUser(username="sara95krasic", authorities="ADMIN", password="saki")
    public void getRoleTest() throws Exception {
    	
    	User user = new User(new Long(17),"sara", User.Role.CUSTOMER, "saki");
    	
    	String jsonUserRole = new Gson().toJson(user.getRole());
    	
    	Mockito.when(userService.getRoleById(user.getId())).thenReturn(user.getRole());
    	
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getRole/{id}", user.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonUserRole));
    	
    	Mockito.verify(userService).getRoleById(user.getId());
    	
    }
    
    @Test
    @WithMockUser(username="sara95krasic", authorities="ADMIN", password="saki")
    public void getUserTest() throws Exception {
    	
    	User user = new User(new Long(17),"sara", User.Role.CUSTOMER, "saki");
    	
    	String jsonUserRole = new Gson().toJson(user);
    	
    	Mockito.when(userService.getById(user.getId())).thenReturn(user);
    	
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser/{id}", user.getId())).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonUserRole));
    	
    	Mockito.verify(userService).getById(user.getId());
    	
    }
    
  /*  @Test
    public void loginUserTestWhenExists() throws Exception {
    	
    	  User user = new User(new Long(17),"Sara","Krasic","krasicsara1@gmail.com", User.Role.ADMIN, "saki");
    	  UserToLogDTO userDTO = new UserToLogDTO("krasicsara1@gmail.com", "saki"); 

    	  Mockito.when(userService.logInUser(userDTO)).thenReturn(user);
    	  
    	  this.mockMvc.perform(MockMvcRequestBuilders.post("/user/login").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(userDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("true"));
    	
    }*/
    
   /* @Test
    public void loginUserTestWhenNotExists() throws Exception {
    	
    	//System.out.println("*************************************");
    	
    	  UserToLogDTO userDTO = new UserToLogDTO("k@gmail.com","kk");
    	  
    	  Mockito.when(userService.logInUser(userDTO)).thenReturn(new User());
    	  
    	//  System.out.println("//////////////////////////////////" + userDTO.getEmail() + userDTO.getPassword());
    	  
    	  this.mockMvc.perform(MockMvcRequestBuilders.post("/user/login").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(userDTO))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.content().string("false"));
    	
    }
    */
    
    
}
