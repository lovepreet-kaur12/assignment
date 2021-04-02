package com.account.details.masking;

public class DataValidation {

	public static boolean validateSensitiveData(int accNo,int srcCode){
		
		String accNoConverted=String.valueOf(accNo);
		if(accNoConverted.length()==8 && accNoConverted.matches("^[8][0-9]{7}$") && String.valueOf(srcCode).length()==6)
			return true;
			
		return false;
	}
}
