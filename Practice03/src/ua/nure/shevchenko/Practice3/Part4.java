package ua.nure.shevchenko.Practice3;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {
	
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	
	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(input.getBytes("CP1251"));
		byte[] hash = digest.digest();
		char[] hexChars = new char[hash.length * 2];
	    for ( int j = 0; j < hash.length; j++ ) {
	        int v = hash[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
		return new String(hexChars);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(hash("password", "SHA-256"));
		System.out.println(hash("passwort", "SHA-256"));
	}
}
