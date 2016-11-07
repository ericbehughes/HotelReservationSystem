package dw317.hotel.data;

import dw317.lib.Email;

public class NonExistingCustomerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 158840072470333095L;
	private String message = null;
	public NonExistingCustomerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NonExistingCustomerException(String message, Email email) {
		super(message);
		System.out.println("Email: " + email.toString());
		
		
	}



}
