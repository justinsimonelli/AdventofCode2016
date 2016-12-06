package com.sims.advent.of.code.day5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class PasswordCrack {
	public static void main(String[] args) {
		String password = "ffykfhsq";
		StringBuilder pwd = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			int i = 0;
			System.out.println("Running...");

			while (true) {
				if (pwd.toString().length() == 8) {
					break;
				}

				String tmpPassword = password + i;
				
				md.update(tmpPassword.getBytes());
				byte[] digest = md.digest();
				String hash = DatatypeConverter.printHexBinary(digest);
				if( hash.startsWith("00000") ){
					pwd.append(hash.charAt(5));
				}
				i++;

			}
			
			System.out.println("Password is " + pwd.toString().toLowerCase());

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
