package group187.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.Name;

public class DawsonCustomer implements Customer {
	private static final long serialVersionUID = 42031768871L;
	private final String firstName,lastName; 
	private final Email email;
	protected final CreditCard card=null;
	
	@Override
	public String toString() {
		return email + "*" + firstName + "*" + lastName + "*" + card.getType() + "*" + card.getNumber();
	}

	public DawsonCustomer(String firstN, String lastN, Email email){
		this.email = new Email(email);//
		Name name = new Name(firstN, lastN);
		this.firstName = name.getFirstName();
		this.lastName = name.getLastName();//
	}

	@Override
	public int compareTo(Customer o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Email getEmail() {
		
		Email email = new Email(this.email);
		
		return email;
	}

	@Override
	public Optional<CreditCard> getCreditCard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Name getName() {		
		return name.
		return null;
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		// TODO Auto-generated method stub
		
	}
	
	
}
