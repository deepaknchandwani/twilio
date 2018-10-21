package com.deepakchandwani.twilio;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.*;
import java.util.Random;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AES { 
	
	
	
	private static String secretKey ;
	private static String salt = "deepaknchandwani";
	 
	public static String encrypt(String strToEncrypt, String secret)
	{
		secretKey=secret;
	    try
	    {
	        IvParameterSpec ivspec = getIV();
	         
	        SecretKeySpec secretKey = getSecterKey();
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
	        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	    }
	    catch (Exception e)
	    {
	        System.out.println("Error while encrypting: " + e.toString());
	    }
	    return null;
	}



	private static SecretKeySpec getSecterKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
		SecretKey tmp = factory.generateSecret(spec);
		SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
		return secretKey;
	}



	private static IvParameterSpec getIV() {
		byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 };
		IvParameterSpec ivspec = new IvParameterSpec(iv);
		return ivspec;
	}
	
	
	
	public static String decrypt(String strToDecrypt, String secret) {
		
		secretKey=secret;
	    try
	    {
	        IvParameterSpec ivspec = getIV();
	         
	        SecretKeySpec secretKey = getSecterKey();
	         
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
	        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	    }
	    catch (Exception e) {
	        System.out.println("Error while decrypting: " + e.toString());
	    }
	    return null;
	}
	
	
	public static  String ACCOUNT_SID = "AC4cef2103@786aad22fe1addb156c03ba29b2"; 
	public static  String AUTH_TOKEN = "c71e4333bf42770f47d3ec90dedf7a2@786c"; 
	public static void main(String[] args)
	{
		
		
		 
		 
		 
//	    String originalString = "Deepak N Chandwani";
//	    System.out.println(originalString);
//
//	     
//	    String encryptedString = AES.encrypt(originalString, "09051936121119431702196806041972") ;
//	    System.out.println(encryptedString);


	    
//	    String decryptedString = AES.decrypt("5uoYyv8Swjmq1+09o0jqvibrl3ISbwzHjikRBWkGK4A=", "09051936121119431702196806041972") ;	      
//	    System.out.println(decryptedString);
		 
		 
	}
	
}