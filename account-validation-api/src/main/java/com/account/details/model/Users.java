package com.account.details.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class Users {
	
	private List<User> userList;
	
	
	public List<User> getUserList(){
		
		if(userList==null){
			
			userList=new ArrayList();
		}
		return userList;
	}
	
	
	public void setUserList(List<User> userList){
		
		this.userList=userList;
		
	}

}


