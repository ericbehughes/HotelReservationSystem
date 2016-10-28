/*
 * 
 */
package dw317.lib.test;

import dw317.lib.Email;

public class EmailTest {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		Email email1 = new Email("zhu@-abc.com");
		Email email2 = new Email("ahu@abc.com");

		String email1234 = "jaya.haha.gotcha*Jaya*Thebest**";
		String email21234 = "jaya@ha@gotcha*Jaya*Theworst**";

		email1 = new Email("zhu@abc.com");
		email2 = new Email("A@bc.com");

		//result = email1.compareTo(email2);

		email1 = new Email("zhu@ba.com");
		email2 = new Email("A@bc.com");

		//result = email1.compareTo(email2);

	}
}
