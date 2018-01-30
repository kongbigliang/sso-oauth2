package com.kongbig.util;

public class Test {
	
	public static void main(String[] args) {
		String str1 = "http://127.0.0.1:8080/sso-client/OAuthController/hasLegalToken.do";
		
		String str2 = Base64.encodeObject(str1);
		System.out.println(str2);
		
		String str3 = (String) Base64.decodeToObject(str2);
		System.out.println(str3);
		
		String str4 = (String) Base64.decodeToObject("rO0ABXQAQWh0dHA6Ly8xMjcuMC4wLjE6ODA4MC9zc28tY2xpZW50L09BdXRoQ29udHJvbGxlci9oYXNMZWdhbFRva2VuLmRv");
		System.out.println(str4);
	}

}
