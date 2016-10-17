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
		try {
			Email email1 = new Email("zhu@-abc.com");
			Name name = new Name("eric", "hughes");
			Optional<CreditCard> cardTest1 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer1 = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1, cardTest1);

			customer1.setCreditCard(cardTest1);
			System.out.println(customer1.toString());
			customer1.setCreditCard(null);
			customer1.getCreditCard();

			Email email2 = new Email("fhse@asdfscom");
			Name name2 = new Name("something", "something");
			Optional<CreditCard> cardTest2 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer2 = new DawsonCustomer(name2.getFirstName(), name2.getLastName(), email2, cardTest2);
			customer2.setCreditCard(cardTest2);
			System.out.println(customer2.toString());
			customer2.compareTo(customer1);

			customer1.equals(customer1);
			customer1.equals(customer1);
		} catch (IllegalArgumentException iae) {
			iae.getMessage();
		}

	}

}
