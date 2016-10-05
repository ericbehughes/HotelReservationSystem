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
	protected Optional<CreditCard> card;
	
	public DawsonCustomer(String firstN, String lastN, Email email, Optional<CreditCard> card){
		this.email = new Email(email);//
		this.name = new Name(firstN, lastN);
		this.card = card;
	}
	public DawsonCustomer(DawsonCustomer e){
		this.email = new Email(e.email);//
		this.name = new Name(e.getName().getFirstName(), e.getName().getLastName());
	}
	
	@Override
	public String toString() {
			if (card.isPresent()) //
			return  email + "*" + this.name.toString() +"*"+ this.card.get().toString();
		return  email + "*" + this.name.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DawsonCustomer))
			return false;
		DawsonCustomer other = (DawsonCustomer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;

		return true;
	}

	
	@Override
	public int compareTo(Customer o) {
		if (o == null)
			throw new NullPointerException("The customer object in the parameter list is null");
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
	// returns deep copy with new keyword
	@Override
	public Name getName() {		
		return new Name(this.name.getFirstName(),this.name.getLastName());
		
	}


	@Override
	public Optional<CreditCard> getCreditCard() {
		return this.card;
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		 this.card = card;
		
		
	}


	
	
}
