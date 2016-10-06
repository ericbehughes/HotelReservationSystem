/*
 * 
 */
package dw317.lib.test;

import dw317.lib.creditcard.MasterCard;

// TODO: Auto-generated Javadoc
/**
 * The Class MasterCardTest.
 */
public class MasterCardTest {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		//This should return false
		try {
			
			MasterCard mastercard1 = new MasterCard("5337813027531960");
			
			MasterCard mastercard2 = new MasterCard("5000000000000000");
			
			MasterCard mastercard3 = new MasterCard("542898495ffff7517541");
		} catch (IllegalArgumentException iae) {
			iae.getMessage();
		}
	
	}

	
	
}