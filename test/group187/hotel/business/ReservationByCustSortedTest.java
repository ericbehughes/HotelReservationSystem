package group187.hotel.business;
import java.util.Comparator;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.Email;
import group187.hotel.business.ReservationByCustSorted;

public class ReservationByCustSortedTest{
	
	public static void main(String[] args) {
		// Create needed objects for first reservation obj
		DawsonRoom rm1 = new DawsonRoom(201, RoomType.NORMAL);
		Email e1 = new Email("customer1@hotmail.com");
		DawsonCustomer c1 = new DawsonCustomer("CustomerOne", "LastOne", e1, null);
		DawsonReservation r1 = new DawsonReservation(c1, rm1, 2015, 12, 1, 2016, 12, 5);
		
		
		// Create needed objects for second reservation obj
		DawsonRoom rm2 = new DawsonRoom(207, RoomType.NORMAL);
		Email e2 = new Email("customer2@hotmail.com");
		DawsonCustomer c2 = new DawsonCustomer("CustomerTwo", "LastTwo", e2, null);
		DawsonReservation r2 = new DawsonReservation(c2, rm2, 2015, 9, 1, 2016, 9, 9);
		
		ReservationByCustSorted resTest = new ReservationByCustSorted();
		System.out.println(resTest.compare(r1, r2));
		System.out.println(resTest.compare(r1, r1));
		
	}

}
