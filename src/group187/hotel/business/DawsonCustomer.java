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
	
	@Override
	public String toString() {
			if (card.isPresent()) 
			return  email + "*" + this.name.getFirstName() + "*" + this.name.getLastName() + "*" + card.get().getType() + "*" + card.get().getNumber();
		return  email + "*" + this.name.getFirstName() + "*" + this.name.getLastName() + "*";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((card == null) ? 0 : card.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (card == null) {
			if (other.card != null)
				return false;
		} else if (!card.equals(other.card))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
