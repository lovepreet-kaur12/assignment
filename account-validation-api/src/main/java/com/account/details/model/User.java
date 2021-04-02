package com.account.details.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import org.springframework.stereotype.Component;
@Component
public class User {

	@NotNull(message ="Name should not be null")
	private String name;
	
//	@Length(min=8,max=8)
	private int accNo;
	
//	@Length(min=6,max=6)
	private int srcCode;
	
	@Pattern(regexp="[0-9]{16}" ,message ="PAN must consist of all digits and should be of length 16")
	private String panNumber;
	
	public User()
	{
		
	}
	
	public User(String name,int accNo,int srcCode,String panNumber )
	{
		this.name=name;
		this.accNo=accNo;
		this.srcCode=srcCode;
		this.panNumber=panNumber;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public int getSrcCode() {
		return srcCode;
	}
	
	public void setSrcCode(int srcCode) {
		this.srcCode = srcCode;
	}
	
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	
	
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
