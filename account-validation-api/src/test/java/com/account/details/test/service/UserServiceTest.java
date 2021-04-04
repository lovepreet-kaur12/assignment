package com.account.details.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.account.details.masking.DataValidation;
import com.account.details.masking.EncryptedPAN;
import com.account.details.model.User;
import com.account.details.model.UserResponse;
import com.account.details.service.UserService;

@SpringBootTest
public class UserServiceTest {
	
	@Mock
	UserService userService;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void successTest() {
		
		User user=new User();
		user.setName("LoveUser");
		user.setAccNo(87654321);
		user.setSrcCode(121212);
		user.setPanNumber("7458934736219876");
		
		boolean expectedStatus=DataValidation.validateSensitiveData(user.getAccNo(),user.getSrcCode());
		
		String expectedMaskedPan=EncryptedPAN.maskPanNumber(user.getPanNumber());
		
		UserResponse res=new UserResponse("LoveUser",expectedStatus,expectedMaskedPan);
		
		when(userService.addUser(user)).thenReturn(res);
		
		UserResponse result=userService.addUser(user);
		
		assertEquals("LoveUser",result.getName());
		assertEquals(res.isStatus(),result.isStatus());
		assertEquals(res.getMaskedPan(),result.getMaskedPan());
	
		
		
	}
	
	@Test
	public void isUserAccNoValid(){
		
		User user=new User();
		user.setName("LoveUser");
		user.setAccNo(8765432);
		user.setSrcCode(121212);
		user.setPanNumber("7458934736219876");
		
		boolean expectedStatus=DataValidation.validateSensitiveData(user.getAccNo(),user.getSrcCode());
		
		String expectedMaskedPan=EncryptedPAN.maskPanNumber(user.getPanNumber());
		
		UserResponse res=new UserResponse(user.getName(),expectedStatus,expectedMaskedPan);
		
		when(userService.addUser(user)).thenReturn(res);
		
		UserResponse result=userService.addUser(user);
		
		assertEquals("LoveUser",result.getName());
		assertEquals(res.isStatus(),result.isStatus());
		assertEquals(res.getMaskedPan(),result.getMaskedPan());
	}

}

