package internship.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;

import internship.UserService.controllers.UserController;
import internship.UserService.converter.UserConverter;
import internship.UserService.helpers.Requests;
import internship.UserService.model.User;
import internship.UserService.modelsDTO.UserDTO;
import internship.UserService.security.UserAccountService;
import internship.UserService.services.UserService;

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
    
    @MockBean
	public Requests requestSrvice;
    

    @WithMockUser(username="sara95krasic", authorities="ADMIN", password="saki")
    @Test
    public void saveTest() throws Exception {
    	
    	User user1 = new User(new Long(17),"sara", User.Role.ADMIN, "saki",null);
    	UserDTO user1DTO = UserConverter.fromEntity(user1);
    	
       Mockito.when(userService.save(user1)).thenReturn(true);
    	
       this.mockMvc.perform(MockMvcRequestBuilders.post("/user/save").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(user1DTO)))
		.andExpect(MockMvcResultMatchers.status().isOk());
       
       Mockito.verify(userService).save(user1);
       
       Mockito.when(userService.save(user1)).thenReturn(false);
       
       this.mockMvc.perform(MockMvcRequestBuilders.post("/user/save").contentType(MediaType.APPLICATION_JSON_UTF8).content(new Gson().toJson(user1DTO)))
		.andExpect(MockMvcResultMatchers.status().isOk());

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
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll")).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonUsers));
		
		 Mockito.verify(userService).findAll();
		
    }
    
    @Test
    @WithMockUser(username="Rale", authorities="ADMIN", password="rale")
    public void getAllShouldThrowException() throws Exception {
    	
    	List<User> users = new ArrayList<User>();
		
    	User userOne = new User(new Long(1),"Rale", User.Role.ADMIN, "rale");
		User userTwo = new User(new Long(2),"Pera", User.Role.CUSTOMER, "pera");
		User userThree = new User(new Long(3),"Marko", User.Role.CUSTOMER, "marko");

		
		users.add(userOne);
		users.add(userTwo);
		users.add(userThree);
				
		Mockito.when(userService.findAll()).thenReturn(users);
		Mockito.when(requestSrvice.makeTokenCheck(Mockito.any())).thenThrow(new IOException());
		this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll"))
		.andExpect(MockMvcResultMatchers.status().isUnauthorized());
				
    	Mockito.verify(requestSrvice).makeTokenCheck(Mockito.any());

    }
    
    
    @Test
    @WithMockUser(username="sara95krasic", authorities="ADMIN", password="saki")
    public void getRoleTest() throws Exception {
    	
    	User user = new User(new Long(17),"sara", User.Role.CUSTOMER, "saki");
    	
    	String jsonUserRole = new Gson().toJson(user.getRole());
    	
    	Mockito.when(userService.getRoleById(user.getId())).thenReturn(user.getRole());
    	
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getRole/{id}", user.getId())).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonUserRole));
    	
    	Mockito.verify(userService).getRoleById(user.getId());
    	
    }
    
    @Test
    @WithMockUser(username="Rale", authorities="ADMIN", password="rale")
    public void getRoleShouldThrowException() throws Exception {
    	
    	User user = new User(new Long(1),"Rale", User.Role.CUSTOMER, "rale");
    	    	
    	Mockito.when(userService.getRoleById(user.getId())).thenReturn(user.getRole());
    	Mockito.when(requestSrvice.makeTokenCheck(Mockito.any())).thenThrow(new IOException());
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getRole/{id}", user.getId()))
    	.andExpect(MockMvcResultMatchers.status().isUnauthorized());

    	
    	Mockito.verify(requestSrvice).makeTokenCheck(Mockito.any());
    	
    }
    
    @Test
    @WithMockUser(username="sara95krasic", authorities="ADMIN", password="saki")
    public void getUserTest() throws Exception {
    	
    	User user = new User(new Long(17),"sara", User.Role.CUSTOMER, "saki");
    	
    	String jsonUserRole = new Gson().toJson(user);
    	
    	Mockito.when(userService.getById(user.getId())).thenReturn(user);
    	
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser/{id}", user.getId())).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonUserRole));
    	
    	Mockito.verify(userService).getById(user.getId());
    	
    }
    
    @Test
    @WithMockUser(username="Rale", authorities="ADMIN", password="rale")
    public void getUserShouldThrowException() throws Exception {
    	
    	User user = new User(new Long(1),"Rale", User.Role.CUSTOMER, "rale");
    	
    	
    	Mockito.when(userService.getById(user.getId())).thenReturn(user);
    	Mockito.when(requestSrvice.makeTokenCheck(Mockito.any())).thenThrow(new IOException());

    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser/{id}", user.getId()))
    	.andExpect(MockMvcResultMatchers.status().isUnauthorized());

    	Mockito.verify(requestSrvice).makeTokenCheck(Mockito.any());
    	
    }
    
    @Test
    @WithMockUser(username="admin", authorities="ADMIN", password="123")
    public void loginTest() throws Exception {
    	
    	User user = new User(new Long(1),"Rale", User.Role.CUSTOMER, "rale");
    	
    	Mockito.when(userService.login(user.getUsername(), user.getPassword())).thenReturn(user);
    	Map<String, String> map= new HashMap<String,String>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
    	
    	Mockito.when(requestSrvice.makePostRequest("http://localhost:8085/auth/login",map)).thenReturn("cookie");
    	
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/login").param("username", user.getUsername()).param("password", user.getPassword())).andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("cookie"));
    	
    	Mockito.when(userService.login(user.getUsername(), user.getPassword())).thenReturn(null);
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/login").param("username", user.getUsername()).param("password", user.getPassword())).andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
  
    @Test
    @WithMockUser(username="admin", authorities="ADMIN", password="123")
    public void logoutTest() throws Exception {
    	
    	User user = new User(new Long(1),"Rale", User.Role.CUSTOMER, "rale");

    	Map<String, String> map= new HashMap<String,String>();
		map.put("username", user.getUsername());
    	
    	Mockito.when(requestSrvice.makePostRequest("http://localhost:8085/auth/logout",map)).thenReturn("cookie");
    	
    	this.mockMvc.perform(MockMvcRequestBuilders.get("/user/logout").param("username", user.getUsername())).andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isOk());

    }
    
    
}
