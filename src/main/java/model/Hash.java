package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

	public static String getHash(String myString) throws HashingException {
		try {
			
			 MessageDigest md = MessageDigest.getInstance("MD5");
	            //Add password bytes to digest
	            md.update(myString.getBytes());
	            //Get the hash's bytes
	            byte[] bytes = md.digest();
	            //This bytes[] has bytes in decimal format;
	            //Convert it to hexadecimal format
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            //Get complete hashed password in hex format
	            String generatedPassword = sb.toString();
		//return thedigest.toString();
		
		return generatedPassword;
	
		}catch (NoSuchAlgorithmException e) {
			throw new HashingException(e);
		}

	}
}
