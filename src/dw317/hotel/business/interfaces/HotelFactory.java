package dw317.hotel.business.interfaces;
import java.io.Serializable;

import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

public interface HotelFactory extends Serializable {
	//interface
	Customer getCustomerInstance(String firstName, String lastName, Email email);
	CreditCard getCard(String cardtype, String number);
	Room getRoomInstance(int roomNumber, String roomtype);
	Reservation getReservationInstance
	(Customer aCustomer,Room aRoom, int inYear, int inMonth, int inDay, int outYear, int outMonth, int outDay);
}
