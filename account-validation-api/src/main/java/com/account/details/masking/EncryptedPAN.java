package com.account.details.masking;

public class EncryptedPAN {
	
	
	//Masking of PAN number
	public static String maskPanNumber(String panNumber){
		
        int length = panNumber.length();
     
        int startlen=4,endlen = 4;
        int masklen = length-(startlen + endlen) ;
        StringBuffer maskedPanBuffer = new StringBuffer(panNumber.substring(0,startlen));
        
        for(int i=0;i<masklen;i++) {
        	maskedPanBuffer.append('X');
        }
        maskedPanBuffer.append(panNumber.substring(startlen+masklen, length));
        String maskedPanNumber = maskedPanBuffer.toString();


        return maskedPanNumber;
	}

}
