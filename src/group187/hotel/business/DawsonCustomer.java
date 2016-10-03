package group187.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.Name;

public class DawsonCustomer implements Customer {
	private static final long serialVersionUID = 42031768871L;
	private final Name name; 
	private final Email email;
	protected final CreditCard card=null;
	
	@Override
	public String toString() {
		return email + "*" + this.name.getFirstName() + "*" + this.name.getLastName() + "*" + card.getType() + "*" + card.getNumber();
	}

	public DawsonCustomer(String firstN, String lastN, Email email){
		this.email = new Email(email);//
		this.name = new Name(firstN, lastN);
	}
	public DawsonCustomer(DawsonCustomer e){
		this.email = new Email(e.email);//
		this.name = new Name(e.getName().getFirstName(), e.getName().getLastName());
	}
	
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
		return new Name(this.name.getFirstName(),this.name.getLastName());
		
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		// TODO Auto-generated method stub
		
	}


	
	
}
