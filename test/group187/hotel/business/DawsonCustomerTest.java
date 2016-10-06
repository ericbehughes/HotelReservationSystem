package group187.hotel.business;

import java.util.Optional;

import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonCustomer;

public class DawsonCustomerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Email email1 = new Email("zhu@-abc.com");
		Name name = new Name("eric","hughes");
		Optional <CreditCard> cardTest1 = Optional.of(new Amex("374616906032009"));
		DawsonCustomer customer1 = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1,cardTest1);
		
		customer1.setCreditCard(cardTest1);
		System.out.println(customer1.toString());
		customer1.setCreditCard(null);
		customer1.getCreditCard();
		
		
		Email email2 = new Email("--fhse@.....com");
		Name name2 = new Name(",,","something");
		Optional <CreditCard> cardTest2 = Optional.of(new Amex("374616906032009"));
		DawsonCustomer customer2 = new DawsonCustomer(name2.getFirstName(), name2.getLastName(), email2,cardTest2);
		customer2.setCreditCard(cardTest2);
		System.out.println(customer2.toString());
		
	}

}
