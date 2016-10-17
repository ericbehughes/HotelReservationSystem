/*
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.time.LocalDate;

// TODO: Auto-generated Javadoc
/**
 * The Interface Reservation.
 */
public interface Reservation extends Comparable<Reservation>, Serializable {

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public Customer getCustomer(); // deep copy

	/**
	 * Gets the room.
	 *
	 * @return the room
	 */
	public Room getRoom();

	/**
	 * Gets the check in date.
	 *
	 * @return the check in date
	 */
	public LocalDate getCheckInDate();

	/**
	 * Gets the check out date.
	 *
	 * @return the check out date
	 */
	public LocalDate getCheckOutDate();

	/**
	 * Gets the number days.
	 *
	 * @return the number days
	 */
	public int getNumberDays();
}
