/*
 * 
 */
package group187.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.Name;

// TODO: Auto-generated Javadoc
/**
 * The Class DawsonCustomer.
 */
public class DawsonCustomer implements Customer {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 42031768871L;
	
	/** The name.. */
	private final Name name; 
	
	/** The email. */
	private final Email email;
	
	/** The card. */
	protected Optional<CreditCard> card;
	
	/**
	 * Instantiates a new dawson customer.
	 *
	 * @param firstN the first N
	 * @param lastN the last N
	 * @param email the email
	 * @param card the card
	 */
	public DawsonCustomer(String firstN, String lastN, Email email, Optional<CreditCard> card){
		this.email = new Email(email);//
		this.name = new Name(firstN, lastN);
		this.card = card;
	}
	
	/**
	 * Instantiates a new dawson customer.
	 *
	 * @param e the e
	 */
	public DawsonCustomer(DawsonCustomer e){
		this.email = new Email(e.email);//
		this.name = new Name(e.getName().getFirstName(), e.getName().getLastName());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
			if (card.isPresent()) //
			return  email.toString() + "*" + this.name.toString() +"*"+ this.card.get().toString();
		return  email + "*" + this.name.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
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
	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Customer#getEmail()
	 */
	@Override
	public Email getEmail() {
		return this.email;
	}
	// returns deep copy with new keyword
	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Customer#getName()
	 */
	@Override
	public Name getName() {		
		return new Name(this.name.getFirstName(),this.name.getLastName());
		
	}


	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Customer#getCreditCard()
	 */
	@Override
	public Optional<CreditCard> getCreditCard() {
		return this.card;
	}

	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Customer#setCreditCard(java.util.Optional)
	 */
	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		 this.card = card;
		
		
	}


	
	
}
