package dw317.lib.creditcard;

import java.util.Optional;

import dw317.lib.Email;
import dw317.lib.Name;
import group187.hotel.business.DawsonCustomer;

public class DawsonCustomerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Email email1 = new Email("zhu@-abc.com");
		Name name = new Name("eric","hughes");
		DawsonCustomer customer = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1);
		Optional <CreditCard> amex = Optional.of(new Amex("374616906032009"));
		customer.setCreditCard(null);
		System.out.println(customer.toString());
	}

}
