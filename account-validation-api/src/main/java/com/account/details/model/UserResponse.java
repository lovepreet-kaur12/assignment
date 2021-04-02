package com.account.details.model;

import org.springframework.web.bind.annotation.ResponseBody;


public class UserResponse {

	private String name;
	private boolean status;
	private String maskedPAN;
	
	public UserResponse(String name,boolean status,String maskedPAN){
		
			this.name=name;
			this.status=status;
			this.maskedPAN=maskedPAN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserResponse() {
		// TODO Auto-generated constructor stub
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMaskedPAN() {
		return maskedPAN;
	}
	public void setMaskedPAN(String maskedPAN) {
		this.maskedPAN = maskedPAN;
	}
	
	
	

}
