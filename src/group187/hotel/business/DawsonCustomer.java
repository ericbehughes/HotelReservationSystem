package group187.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;


// TODO: Auto-generated Javadoc
/**
 * The Class DawsonCustomer.
 */
public class DawsonCustomer implements Customer {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 42031768871L;
	
	/** The name. */
	private final Name name; 
	
	/** The email. */
	private final Email email;
	
	/** The card. */
	private CreditCard card;
	
	/**
	 * Instantiates a new dawson customer.
	 *
	 * @param firstN the first name
	 * @param lastN the last name
	 * @param email the email
	 * @param card the card
	 */
	public DawsonCustomer(String firstN, String lastN, Email email){
		this.email = new Email(email);
		this.name = new Name(firstN, lastN);
		
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
			if (card != null) //
			return  email.toString() + "*" + this.name.toString() +"*"+ this.card.toString();
		return  email.toString() + "*" + this.name.toString();
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
		return this.getEmail().compareTo(o.getEmail());
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
		return Optional.ofNullable(card);
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		if (card.isPresent())
			this.card = card.get();
		else
			this.card = card.orElse(null);
	}


}
