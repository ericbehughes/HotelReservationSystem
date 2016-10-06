package dw317.lib.test;

import dw317.lib.Email;

public class EmailTestClass {

	public static void main(String[] args) {
		
		
		Email email1 = new Email("zhu@-abc.com");
		Email email2 = new Email("ahu@abc.com");
		
		String str1 = "zhu";
		String str2 = "abc";
		
		int result2 = str1.compareToIgnoreCase(str2);
		
		
		
		int result = email1.compareTo(email2);
		
		email1 = new Email("zhu@abc.com");
		email2 = new Email("A@bc.com");
		
		result = email1.compareTo(email2);
		
		email1 = new Email("zhu@ba.com");
		email2 = new Email("A@bc.com");
		
		result = email1.compareTo(email2);
		
	}
}
