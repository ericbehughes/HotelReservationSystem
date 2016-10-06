/*
 * 
 */
package dw317.lib.test;

import dw317.lib.creditcard.Visa;

// TODO: Auto-generated Javadoc
/**
 * The Class VisaTestClass.
 */
public class VisaTestClass {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
try {
			
			Visa visa1 = new Visa("4485268144950929");
			
			Visa visa2 = new Visa("5000000000000000");
			
			Visa visa3 = new Visa("4485268144950929fff");
		} catch (IllegalArgumentException iae) {
			iae.getMessage();
		}
	}

	
	
}