package group187.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonHotelFactory;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;

public class ReservationListDBTest {
	public static List<Reservation> database;
	public static ListPersistenceObject listPersistenceObject;
	
	public static void main(String[] args) {
			System.out.println("ReservationListDbTest");
			Email email1 = new Email("zhu@abcc.com");
			Name name = new Name("test", "hughes");
			Optional<CreditCard> cardTest1 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer1 = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1, cardTest1);

			customer1.setCreditCard(cardTest1);

			Email email2 = new Email("fhse@abc.com");
			Name name2 = new Name("test", "Depaz");
			Optional<CreditCard> cardTest2 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer2 = new DawsonCustomer(name2.getFirstName(), name2.getLastName(), email2, cardTest2);
			customer2.setCreditCard(cardTest2);
			
			
			String roomFilename = "datafiles/database/rooms.txt";
			String customerFilename = "datafiles/database/customers.txt";
			String reservationFilename = "datafiles/database/reservations.txt";
			
			// Create needed objects for first reservation obj
			DawsonRoom rm1 = new DawsonRoom(101, RoomType.NORMAL);
			DawsonRoom rm2 = new DawsonRoom(302, RoomType.NORMAL);
			//2017*12*28*2018*1*3*101
			DawsonReservation r1 = new DawsonReservation(customer1, rm1, 2015, 9, 1, 2016, 9, 9);
			DawsonReservation r2 = new DawsonReservation(customer2, rm2, 2015, 9, 1, 2016, 9, 9);

			SequentialTextFileList obj = new SequentialTextFileList(roomFilename, customerFilename, reservationFilename);
			DawsonHotelFactory factory = DawsonHotelFactory.DAWSON;
			CustomerListDB cDB = new CustomerListDB(obj, factory);
			ReservationListDB reservdb = new ReservationListDB(obj);
			try {
				cDB.add(customer1);
				cDB.add(customer2);
				
				reservdb.add(r1);
				reservdb.add(r2);
				
				LocalDate checkin = LocalDate.of(2016, 9, 10);
				LocalDate checkout = LocalDate.of(2016, 10, 31);
				
				List<Room> freeRooms = reservdb.getFreeRooms(checkin, checkout, RoomType.NORMAL);
				List<Room> reservedRooms = reservdb.getReservedRooms(checkin, checkout);
				System.out.println(reservdb.toString());
				//cDB.toString();
				
				System.out.println("");
				System.out.println("--------------------------------");
				System.out.println("Free rooms:");
				for (Room room : freeRooms)
					System.out.println(room.toString());
				System.out.println("");
				System.out.println("--------------------------------");
				/**
				System.out.println("");
				System.out.println("Reserved Rooms");
				for (Room room : reservedRooms)
					System.out.println(room.toString());
				System.out.println("");
				System.out.println("--------------------------------");
				System.out.println("");
				System.out.println("Reservations");
				System.out.print(reservdb.toString());
				System.out.println("--------------------------------");
				*/
				reservdb.disconnect();
				
			} catch (DuplicateReservationException | DuplicateCustomerException | IOException e) {
				
				e.printStackTrace();
			}
	}
	
}
	



	
			
			
		
		
			
 

