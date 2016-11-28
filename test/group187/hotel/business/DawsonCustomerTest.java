/*
 * 
 */
package group187.hotel.business;

import java.util.Optional;

import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonCustomer;

// TODO: Auto-generated Javadoc
/**
 * The Class DawsonCustomerTest.
 */
public class DawsonCustomerTest {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("run");
		try {
			System.out.println("entered try");
			Email email1 = new Email("zhu@abc.com");
			System.out.println("created email");
			Name name = new Name("eric", "hughes");
			System.out.println("created name");
			CreditCard cardTest1 = new Amex("374616906032009");
			System.out.println("created card");
			DawsonCustomer customer1 = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1);
			System.out.println("created cust");
			customer1.setCreditCard(Optional.ofNullable(cardTest1));
			System.out.println("set card");
			System.out.println(customer1.getCreditCard().get().toString());

			System.out.println(customer1.toString());
			//customer1.setCreditCard(null);
			//customer1.getCreditCard();

			Email email2 = new Email("fhse@asdfscom");
			Name name2 = new Name("something", "something");
			CreditCard cardTest2 = new Amex("374616906032009");
			DawsonCustomer customer2 = new DawsonCustomer(name2.getFirstName(), name2.getLastName(), email2);
			
			customer2.setCreditCard(Optional.ofNullable(cardTest2));
			System.out.println(customer2.toString());
			customer2.compareTo(customer1);

		} catch (IllegalArgumentException iae) {
			iae.getMessage();
		}

	}

}
