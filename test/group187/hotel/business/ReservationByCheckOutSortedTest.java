package group187.hotel.business;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.lib.Email;
public class ReservationByCheckOutSortedTest {

	public static void main(String[] args) {
		// Create needed objects for first reservation obj
				DawsonRoom rm1 = new DawsonRoom(201, RoomType.NORMAL);
				Email e1 = new Email("customer1@hotmail.com");
				DawsonCustomer c1 = new DawsonCustomer("CustomerOne", "LastOne", e1);
				c1.setCreditCard(Optional.ofNullable(null));
				DawsonReservation r1 = new DawsonReservation(c1, rm1, 2015, 12, 1, 2016, 12, 5);
				
				
				// Create needed objects for second reservation obj
				DawsonRoom rm2 = new DawsonRoom(207, RoomType.NORMAL);
				Email e2 = new Email("customer2@hotmail.com");
				DawsonCustomer c2 = new DawsonCustomer("CustomerTwo", "LastTwo", e2);
				c2.setCreditCard(Optional.ofNullable(null));
				DawsonReservation r2 = new DawsonReservation(c2, rm2, 2015, 9, 1, 2016, 9, 9);
				//
				ReservationByCheckOutSorted resTest = new ReservationByCheckOutSorted();
				System.out.println(resTest.compare(r1, r2));
				System.out.println(resTest.compare(r2, r1));
		
	}

}
