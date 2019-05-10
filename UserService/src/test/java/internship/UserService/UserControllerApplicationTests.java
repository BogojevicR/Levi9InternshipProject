package internship.UserService;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import internship.UserService.controllers.UserController;
import internship.UserService.model.User;
import internship.UserService.services.UserService;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private UserService userService;

    @Test
    public void getAllTest() throws Exception {
    	
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
		
		String jsonUsers = new Gson().toJson(users);
		
		Mockito.when(userService.findAll()).thenReturn(users);
		
		this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getAll")).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonUsers));
		
    }
    
}
