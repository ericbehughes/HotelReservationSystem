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
			Optional<CreditCard> cardTest1 = Optional.of(new Amex("374616906032009"));
			Email email1 = new Email("zhu@abc.com");
			Name name = new Name("eric", "Hughes");
			DawsonRoom room1 = new DawsonRoom(101, RoomType.NORMAL);
			DawsonCustomer aCustomer = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1, cardTest1);
			DawsonReservation reservation1 = new DawsonReservation(aCustomer, room1, 2016, 8, 21, 2016, 8, 25);
			dhf.getReservationInstance(aCustomer, room1, 2016, 8, 21, 2016, 8, 25);

			System.out.println(dhf.getCustomerInstance(name.getFirstName(), name.getLastName(), email1, cardTest1));

			System.out
					.println(dhf.getCard(cardTest1.get().getType().toString(), cardTest1.get().getNumber().toString()));

			System.out.println(dhf.getRoomInstance(room1.getRoomNumber(), room1.getRoomType().toString()));

			System.out.println(dhf.getReservationInstance(reservation1));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
