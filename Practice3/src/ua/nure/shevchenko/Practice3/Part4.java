package ua.nure.shevchenko.Practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {
	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.update(input.getBytes());
		byte[] hash = digest.digest();
		String res = "";
		for (byte x : hash) {
			String temp = String.format("%8s", Integer.toHexString(x & 0xFF).toUpperCase());
			if (temp.length()==1)
			{
			res += "0" + temp + " ";
			}
			else{
				res += temp + " ";
			}
		}
		return res;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hash("password", "SHA-256"));
		System.out.println(hash("passwort", "SHA-256"));
	}
}
