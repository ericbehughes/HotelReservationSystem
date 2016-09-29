package dw317.lib.creditcard;

import dw317.lib.Email;

public class EmailTestClass {

	public static void main(String[] args) {
		
		
		Email email1 = new Email("zhu@abc.com");
		Email email2 = new Email("ahu@abc.com");
		
		int result = email1.compareTo(email2);
		
		email1 = new Email("zhu@abc.com");
		email2 = new Email("A@bc.com");
		
		result = email1.compareTo(email2);
		
		email1 = new Email("zhu@ba.com");
		email2 = new Email("A@bc.com");
		
		result = email1.compareTo(email2);
		
	}
}
