package group187.hotel.business;

import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.data.ReservationListDB;
import group187.hotel.data.SequentialTextFileList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class DawsonHotelAllocationPolicyTest {
	public static void main(String[] args) {
		// Set file paths
		String roomFilename = "datafiles/database/rooms.txt";
		String customerFilename = "datafiles/database/customers.txt";
		String reservationFilename = "datafiles/database/reservations.txt";
		
		// Create database and factory object
		SequentialTextFileList obj = new SequentialTextFileList(roomFilename, customerFilename, reservationFilename);
		DawsonHotelFactory factory = DawsonHotelFactory.DAWSON;
		ReservationListDB reservdb = new ReservationListDB(obj);
		
		// Create the customer and reservation objects
		List<Room> freeRooms = new ArrayList<>();
		DawsonRoom rm1 = new DawsonRoom(101, RoomType.NORMAL);
		Email email1 = new Email("zhu@abcc.com");
		Name name = new Name("test", "hughes");
		CreditCard cardTest1 = new Amex("374616906032009");
		DawsonCustomer customer1 = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1);
		DawsonReservation r1 = new DawsonReservation(customer1, rm1, 2015, 9, 1, 2016, 9, 9);
		
		// Get checkin and checkout dates
		LocalDate checkin = r1.getCheckInDate();
		LocalDate checkout = r1.getCheckOutDate();
		
		// Get all free rooms within checkin and checkout date for the given room type
		freeRooms = reservdb.getFreeRooms(checkin, checkout, RoomType.NORMAL);
		
		// Begin test
		DawsonHotelAllocationPolicy allocationPolicy = new DawsonHotelAllocationPolicy(reservdb);
		Optional<Room> freeRoom = allocationPolicy.getAvailableRoom(checkin, checkout, RoomType.NORMAL);
		System.out.println(freeRoom.toString());
		
	}

}
