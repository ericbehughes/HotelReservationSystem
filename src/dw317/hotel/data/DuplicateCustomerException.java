package dw317.hotel.data;

import dw317.lib.Email;

/**
 * This exception signals that the provided customer email exists already.
 */
public class DuplicateCustomerException extends Exception {
	private static final long  serialVersionUID = 42031768871L;
			
	public DuplicateCustomerException(String s){
		super(s);
	}

	public DuplicateCustomerException(String message, Email email) {
		super(message);
		System.out.println("Email: " + email.toString());
	}
}
