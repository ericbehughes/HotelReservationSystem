package group187.hotel.business;
import java.util.Optional;

import com.sun.xml.internal.bind.v2.runtime.Name;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

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
		this.firstName = firstN;
		this.lastName = lastN;
		this.email = new Email(email);//
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		// TODO Auto-generated method stub
		
	}
	
	
}
