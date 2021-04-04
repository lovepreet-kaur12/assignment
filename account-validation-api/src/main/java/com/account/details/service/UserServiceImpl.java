package com.account.details.service;

import org.springframework.stereotype.Service;

import com.account.details.masking.DataValidation;
import com.account.details.masking.EncryptedPAN;
import com.account.details.model.UserResponse;
import com.account.details.model.User;

@Service
public class UserServiceImpl implements UserService{

	
	private static User list=new User();
	
	@Override
	public UserResponse addUser(User user) {
		
		
		list.getUserList().add(user); 
		
		boolean status =DataValidation.validateSensitiveData(user.getAccNo(),user.getSrcCode());
		
		String panNumber=user.getPanNumber();
		
		String maskedPAN=EncryptedPAN.maskPanNumber(user.getPanNumber());
		
		if(status==false)
			return new UserResponse();
		
		return new UserResponse(user.getName(),status,maskedPAN);
		
	}
	

}
