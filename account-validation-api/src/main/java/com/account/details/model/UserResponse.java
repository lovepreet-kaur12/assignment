package com.account.details.model;

import org.springframework.web.bind.annotation.ResponseBody;


public class UserResponse {

	private String name;
	private boolean status;
	private String maskedPan;
	
	public UserResponse(String name,boolean status,String maskedPan){
		
			this.name=name;
			this.status=status;
			this.maskedPan=maskedPan;
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
	public String getMaskedPan() {
		return maskedPan;
	}
	public void setMaskedPAN(String maskedPan) {
		this.maskedPan = maskedPan;
	}
	
	
	

}
