/*
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.util.Optional;

import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Hotel objects.
 */
public interface HotelFactory extends Serializable {

	/**
	 * Gets the customer instance.
	 *
	 * @param firstName
	 *            the first name
	 * @param lastName
	 *            the last name
	 * @param email
	 *            the email
	 * @param card
	 *            the card
	 * @return the customer instance
	 */
	// interface
	Customer getCustomerInstance(String firstName, String lastName, Email email, Optional<CreditCard> card);

	/**
	 * Gets the card.
	 *
	 * @param cardtype
	 *            the cardtype
	 * @param number
	 *            the number
	 * @return the card
	 */
	CreditCard getCard(String cardtype, String number);

	/**
	 * Gets the room instance.
	 *
	 * @param roomNumber
	 *            the room number
	 * @param roomtype
	 *            the roomtype
	 * @return the room instance
	 */
	Room getRoomInstance(int roomNumber, String roomtype);

	/**
	 * Gets the reservation instance.
	 *
	 * @param aCustomer
	 *            the a customer
	 * @param aRoom
	 *            the a room
	 * @param inYear
	 *            the in year
	 * @param inMonth
	 *            the in month
	 * @param inDay
	 *            the in day
	 * @param outYear
	 *            the out year
	 * @param outMonth
	 *            the out month
	 * @param outDay
	 *            the out day
	 * @return the reservation instance
	 */
	Reservation getReservationInstance(Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay);

	/**
	 * Gets the reservation instance.
	 *
	 * @param toCopy
	 *            the to copy
	 * @return the reservation instance
	 */
	Reservation getReservationInstance(Reservation toCopy);
}
