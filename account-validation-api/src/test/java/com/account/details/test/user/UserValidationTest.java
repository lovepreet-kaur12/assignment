package com.account.details.test.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.account.details.kaur.AccountValidationApi;
import com.account.details.controller.UserController;
import com.account.details.model.UserResponse;
import com.account.details.model.User;
import com.account.details.service.UserService;

@SpringBootTest
public class UserValidationTest {
	
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	@Test
	public void contextLoads()
	{
		 
		
	}
	
	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}
		
	@Test
	public void successTest() 
	{

		User user=new User();
		user.setName("LoveUser");
		user.setAccNo(87654321);
		user.setSrcCode(121212);
		user.setPanNumber("7458934736219876");
		
		UserResponse res=new UserResponse("LoveUser",true,"7458XXXXXXXX9876");
		
		when(userService.addUser(user)).thenReturn(res);
		
		ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
		
		assertEquals(res.getMaskedPAN(),responseEntity.getBody().getMaskedPAN());
		assertEquals(res.getName(),responseEntity.getBody().getName());
		assertEquals(res.isStatus(),responseEntity.getBody().isStatus());
		assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
	
		
		
	}
	  
    @Test
    public void isPanMasked(){
    	
    	User user = new User();
        user.setName("LoveUser");
		user.setAccNo(87654321);
		user.setSrcCode(121212);
		user.setPanNumber("7458934736219876");
		
		UserResponse res=new UserResponse("LoveKaur",true,"7458XXXXXXXX9876");
		
		when(userService.addUser(user)).thenReturn(res);
		
		ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
		
		assertEquals(res.getMaskedPAN(),responseEntity.getBody().getMaskedPAN());
		assertEquals(res.getName(),responseEntity.getBody().getName());
		assertEquals(res.isStatus(),responseEntity.getBody().isStatus());
		assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
		
    	
    }
    
	@Test
	public void validateAccNoLength() {
		User user=new User("TestUser",8765432,121212,"7458934736219876");
		
		UserResponse res=new UserResponse("null",false,"null");
		when(userService.addUser(user)).thenReturn(res);
		
		ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
		
		assertEquals(res,responseEntity.getBody());
		assertEquals(res.getMaskedPAN(),responseEntity.getBody().getMaskedPAN());
		assertEquals(res.getName(),responseEntity.getBody().getName());
		assertEquals(res.isStatus(),responseEntity.getBody().isStatus());
		assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
		
		
	}
	
	
	@Test
	public void validateSrcCodeLength() {
		
		User user=new User("TestUser",87654328,1212126,"7458934736219876");
		
		UserResponse res=new UserResponse("null",false,"null");
		
		when(userService.addUser(user)).thenReturn(res);
		
		ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
	
		assertEquals(res,responseEntity.getBody());
		assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
		
	}
	
	
	@Test
	public void isAccNoPositive()
	{
		User user=new User("TestUser",-87654328,1212127,"7458934736219876");
		
		UserResponse res=new UserResponse("null",false,"null");
		
		when(userService.addUser(user)).thenReturn(res);
		
		ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
	
		assertEquals(res,responseEntity.getBody());
		assertEquals(res.getMaskedPAN(),responseEntity.getBody().getMaskedPAN());
		assertEquals(res.getName(),responseEntity.getBody().getName());
		assertEquals(res.isStatus(),responseEntity.getBody().isStatus());
		assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
	}
	
	@Test
	public void isScrCodePositive()
	{
		User user=new User("TestUser",87654328,-1212127,"7458934736219876");
		
		UserResponse res=new UserResponse("null",false,"null");
		
		when(userService.addUser(user)).thenReturn(res);
		
		ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
	
		assertEquals(res,responseEntity.getBody());
		assertEquals(res.getMaskedPAN(),responseEntity.getBody().getMaskedPAN());
		assertEquals(res.getName(),responseEntity.getBody().getName());
		assertEquals(res.isStatus(),responseEntity.getBody().isStatus());
		assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
		
	}
	
	
	
}
