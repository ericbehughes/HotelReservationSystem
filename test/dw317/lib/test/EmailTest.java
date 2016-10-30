/*
 * 
 */
package dw317.lib.test;

import dw317.lib.Email;
import dw317.lib.Name;

public class EmailTest {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		Email email1 = new Email("zhu@abc.com");
		Email email2 = new Email("ahu@abc.com");
		System.out.println(Name.isValidString("syndicatdesetudiants@dawsoncollege.qc.ca", 1));
		System.out.println(Name.isValidString("ahu@abc.com", 1));
		System.out.println(Name.isValidString("jaya.h@@aha.gotcha*Jaya*Thebest**", 1));
		System.out.println(Name.isValidString("jaya@ha@gotcha*Jaya*Theworst**", 1));

	
		System.out.println(email1.compareTo(email2));

		email1 = new Email("jaya.haha.gotcha*Jaya*Thebest**");
		email2 = new Email("jaya@ha@gotcha*Jaya*Theworst**");
		
		//result = email1.compareTo(email2);

	}
}
