/*
 * 
 */
package group187.hotel.business;

import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating DawsonHotel objects.
 */
public enum DawsonHotelFactory implements HotelFactory {

	/** The dawson hotel. */
	DAWSON;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dw317.hotel.business.interfaces.HotelFactory#getCustomerInstance(java.
	 * lang.String, java.lang.String, dw317.lib.Email, java.util.Optional)
	 */
	@Override
	public Customer getCustomerInstance(String firstName, String lastName, Email email, Optional<CreditCard> card) {
		DawsonCustomer cust = new DawsonCustomer(firstName, lastName, email);
		cust.setCreditCard(card);
		return cust;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dw317.hotel.business.interfaces.HotelFactory#getCard(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public CreditCard getCard(String cardtype, String number) {
		return CreditCard.getInstance(CreditCard.CardType.valueOf(cardtype.toUpperCase()), number);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dw317.hotel.business.interfaces.HotelFactory#getRoomInstance(int,
	 * java.lang.String)
	 */
	@Override
	public Room getRoomInstance(int roomNumber, String roomtype) {
		return new DawsonRoom(roomNumber, RoomType.valueOf(roomtype.toUpperCase()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dw317.hotel.business.interfaces.HotelFactory#getReservationInstance(dw317
	 * .hotel.business.interfaces.Customer,
	 * dw317.hotel.business.interfaces.Room, int, int, int, int, int, int)
	 */
	@Override
	public Reservation getReservationInstance(Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay,
			int outYear, int outMonth, int outDay) {
		return new DawsonReservation(aCustomer, aRoom, inYear, inMonth, inDay, outYear, outMonth, outDay);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dw317.hotel.business.interfaces.HotelFactory#getReservationInstance(dw317
	 * .hotel.business.interfaces.Reservation)
	 */
	@Override
	public Reservation getReservationInstance(Reservation toCopy) {
		return new DawsonReservation(toCopy.getCustomer(), toCopy.getRoom(), toCopy.getCheckInDate().getYear(),
				toCopy.getCheckInDate().getMonthValue(), toCopy.getCheckInDate().getDayOfMonth(),
				toCopy.getCheckOutDate().getYear(), toCopy.getCheckOutDate().getMonthValue(),
				toCopy.getCheckOutDate().getDayOfMonth());
	}

	@Override
	public AllocationPolicy getAllocationPolicy(ReservationDAO reservations) {
		return new DawsonHotelAllocationPolicy(reservations);
	}

}
