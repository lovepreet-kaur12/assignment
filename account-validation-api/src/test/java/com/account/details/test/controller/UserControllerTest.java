package com.account.details.test.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.account.details.controller.UserController;
import com.account.details.kaur.AccountValidationApi;
import com.account.details.model.UserResponse;
import com.account.details.model.User;
import com.account.details.service.UserService;

@SpringBootTest(classes=com.account.details.kaur.AccountValidationApi.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	
	@Before
	    public void init() {
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
			
			assertEquals("LoveUser",responseEntity.getBody().getName());
		
			
			
		}
		  
	    @Test
	    public void isPanMasked(){
	    	
	    	User user = new User();
	        user.setName("LoveUser");
			user.setAccNo(87654321);
			user.setSrcCode(121212);
			user.setPanNumber("7458934736219876");
			
			UserResponse res=new UserResponse();
			res.setName("LoveKaur");
			res.setStatus(true);
			res.setMaskedPAN("7458XXXXXXXX9876");
			
			when(userService.addUser(user)).thenReturn(res);
			
			ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
			
			assertEquals(res.getMaskedPan(),responseEntity.getBody().getMaskedPan());
			assertEquals(res.getName(),responseEntity.getBody().getName());
			assertEquals(res.isStatus(),responseEntity.getBody().isStatus());
			
	    	
	    }
	    
	@Test
	public void validateAccNoLength() {
		User user=new User("TestUser",8765432,121212,"7458934736219876");
		
		UserResponse res=new UserResponse("null",false,"null");
		when(userService.addUser(user)).thenReturn(res);
		
		ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
		
		assertEquals(res,responseEntity.getBody());
		
		
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
	}
	
	@Test
	public void isScrCodePositive()
	{
		User user=new User("TestUser",87654328,-1212127,"7458934736219876");
		
		UserResponse res=new UserResponse("null",false,"null");
		
		when(userService.addUser(user)).thenReturn(res);
		
		ResponseEntity<UserResponse> responseEntity=userController.validateDetails(user);
	
		assertEquals(res,responseEntity.getBody());
	}
}
