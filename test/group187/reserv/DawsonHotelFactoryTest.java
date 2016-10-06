package group187.reserv;



import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.MasterCard;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonHotelFactory;
import group187.hotel.business.DawsonReservation;

public class DawsonHotelFactoryTest {

	public static void main(String[] args) {
		DawsonHotelFactory dhf = DawsonHotelFactory.DAWSON;
		CreditCard amex = new Amex("374616906032009");
		Email email1 = new Email("zhu@-abc.com");
		Name name = new Name("eric","Hughes");
		DawsonCustomer aCustomer = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1, amex);
		DawsonReservation reservation1 = new DawsonReservation(customer, room, inYear, inMonth, inDay, outYear, outMonth, outDay);
		dhf.getReservationInstance(aCustomer, aRoom, inYear, inMonth, inDay, outYear, outMonth, outDay);

	}

}
