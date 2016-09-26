package group187.hotel.business;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

public class DawsonCustomer implements Customer {
	private static final long serialVersionUID = 42031768871L;
	private final String firstName,lastName; 
	private final String email;
	protected final CreditCard card=null;
	
	@Override
	public String toString() {
		return email + "*" + firstName + "*" + lastName + "*" + card.getType() + "*" + card.getNumber();
	}

	public DawsonCustomer(String firstN, String lastN, String email){
		this.firstName = firstN;
		this.lastName = lastN;
		this.email = email;
	}

	@Override
	public int compareTo(Customer o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Email getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CreditCard> getCreditCard() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
