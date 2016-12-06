package com.sims.advent.of.code.day5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class PasswordCrack {

	
	public static void main(String[] args) {
		System.out.println("Running...");
		String password = null;
		String moviePassword = null;
		try {
			password = easyBruteForce("ffykfhsq");
			System.out.println("Easy brute password: " + password);
		
			
			moviePassword = movieH4x1ng("ffykfhsq");
			System.out.println("Movie password: " + moviePassword);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("passwords cracked");
	}
	
	public static String easyBruteForce(String inputKey) throws NoSuchAlgorithmException{
		StringBuilder pwd = new StringBuilder();
		MessageDigest md = MessageDigest.getInstance("MD5");
		int i = 0;

		while (true) {
			if (pwd.toString().length() == 8) {
				break;
			}

			String tmpPassword = inputKey + i;
			
			md.update(tmpPassword.getBytes());
			byte[] digest = md.digest();
			String hash = DatatypeConverter.printHexBinary(digest);
			if( hash.startsWith("00000") ){
				pwd.append(hash.charAt(5));
			}
			i++;

		}
		
		return pwd.toString().toLowerCase();

	}
	
	public static String movieH4x1ng(String inputKey) throws NoSuchAlgorithmException{
		Character[] password = new Character[8];
		MessageDigest md = MessageDigest.getInstance("MD5");
		StringBuilder builder = new StringBuilder();
		int i = 0;
		int numberOfKeysFound = 0;
		
		while(true){

			if(numberOfKeysFound == 8){
				break;
			}
			
			String tmpPassword = inputKey + i;
			md.update(tmpPassword.getBytes());
			byte[] digest = md.digest();
			String hash = DatatypeConverter.printHexBinary(digest);
			
			if( hash.startsWith("00000") ){
				String position  = String.valueOf(hash.charAt(5));
				Character key = hash.charAt(6);
				
				if( position.matches("[0-7]") ){
					int pos = Integer.parseInt(position);
					
					if( password[pos] == null ){
						password[pos] = key;
						numberOfKeysFound++;
					}
				}
			}
			
			i++;
			
		}
		
		for( Character c : password ){
			builder.append(c);
		}
		
		return builder.toString().toLowerCase();
	}
}
