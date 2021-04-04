package com.account.details.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.details.model.UserResponse;
import com.account.details.model.User;
import com.account.details.service.UserService;
import com.account.details.masking.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value="/user",consumes ="application/json",produces="application/json")
	public ResponseEntity<UserResponse> validateDetails(@Valid @RequestBody User user)
	{
		UserResponse returnResponse=userService.addUser(user);
		
		if(returnResponse.isStatus()==false)
			return new ResponseEntity<UserResponse>(returnResponse,HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<UserResponse>(returnResponse,HttpStatus.CREATED);
		
	}
	
}
