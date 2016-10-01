package group187.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.*;
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
	/*
	 * str1 = "zbc"
	 * str2 = "bbc"
	 * 
	 * str1.compareToingnorecase(str2) 43
	 * 
	 * */
	@Override
	public int compareTo(Customer o) {
		if (o == null)
			throw new NullPointerException();
		// handle if same host id
		if (this.email.getHost().equalsIgnoreCase(o.getEmail().getHost())){
				if (this.email.getUserId().equalsIgnoreCase(o.getEmail().getUserId())){
					return 0; //same everything
				// same host but different userID
				}
				else if (this.email.getUserId().compareToIgnoreCase(o.getEmail().getUserId()) > 0)
					return -1;
				else if (this.email.getUserId().compareToIgnoreCase(o.getEmail().getUserId()) < 0)
					return 1;
		}
		//handle for different hosts
		else if (this.email.getHost().compareToIgnoreCase(o.getEmail().getHost())> 0)
			return -1;
		return 1;
	}

	// does not return deep copy as address is immutable
	@Override
	public Email getEmail() {
		return this.email;
	}

	@Override
	public Optional<CreditCard> getCreditCard() {
		// TODO Auto-generated method stub
		return null;
	}

	// returns deep copy with new keyword
	@Override
	public Name getName() {		
		return new Name(this.firstName,this.lastName);
		
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		// TODO Auto-generated method stub
		
	}


	
	
}
