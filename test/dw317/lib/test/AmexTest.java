package dw317.lib.test;

import dw317.lib.creditcard.Amex;


public class AmexTest {

	public static void main(String[] args) {
		
		try {
			
			Amex amex1 = new Amex("378240964454959");
			
			Amex amex2 = new Amex("5000000000000000");
			
			Amex amex3 = new Amex("3749944502fsdfs29614");
		} catch (IllegalArgumentException iae) {
			iae.getMessage();
		}
}

}
