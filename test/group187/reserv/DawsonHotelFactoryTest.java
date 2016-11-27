/*
 * 
 */
package group187.reserv;

import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonHotelFactory;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;

// TODO: Auto-generated Javadoc
/**
 * The Class DawsonHotelFactoryTest.
 */
public class DawsonHotelFactoryTest {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		try {

			DawsonHotelFactory dhf = DawsonHotelFactory.DAWSON;
			Email email1 = new Email("zhu@abc.com");
			Name name = new Name("eric", "Hughes");
			DawsonRoom room1 = new DawsonRoom(101, RoomType.NORMAL);
			CreditCard cardTest1 = new Amex("374616906032009");
			DawsonCustomer customer1 = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1);
			customer1.setCreditCard(Optional.ofNullable(cardTest1));
			DawsonReservation reservation1 = new DawsonReservation(customer1, room1, 2016, 8, 21, 2016, 8, 25);
			dhf.getReservationInstance(customer1, room1, 2016, 8, 21, 2016, 8, 25);

			System.out.println(dhf.getCustomerInstance(name.getFirstName(), name.getLastName(), email1));

		
			System.out.println(dhf.getRoomInstance(room1.getRoomNumber(), room1.getRoomType().toString()));

			System.out.println(dhf.getReservationInstance(reservation1));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
