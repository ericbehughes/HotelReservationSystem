/*
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;

import dw317.lib.*;
import java.util.Optional;
import dw317.lib.creditcard.CreditCard;

// TODO: Auto-generated Javadoc
/**
 * The Interface Customer.
 */
public interface Customer extends Comparable<Customer>, Serializable {

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public Name getName(); // deep copy

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public Email getEmail(); // not deep copy as emails are not immutable

	/**
	 * Gets the credit card.
	 *
	 * @return the credit card
	 */
	public Optional<CreditCard> getCreditCard();

	/**
	 * Sets the credit card.
	 *
	 * @param card
	 *            the new credit card
	 */
	public void setCreditCard(Optional<CreditCard> card);
}
