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
import group187.util.ListUtilities;

import java.io.File;
import java.io.IOException;
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

		// Get checkinArray and checkoutArray dates
		LocalDate checkin = r1.getCheckInDate();
		LocalDate checkout = r1.getCheckOutDate();

		// Get all free rooms within checkinArray and checkoutArray date for the given
		// room type
		freeRooms = reservdb.getFreeRooms(checkin, checkout, RoomType.NORMAL);

		// Begin test
		DawsonHotelAllocationPolicy allocationPolicy = new DawsonHotelAllocationPolicy(reservdb);
		Optional<Room> freeRoom = allocationPolicy.getAvailableRoom(checkin, checkout, RoomType.NORMAL);
		System.out.println(freeRoom.toString());
		
		String[] rooms = new String[10];
		rooms[0] = "304*normal";
		rooms[1] = "205*normal";
		rooms[2] = "407*normal";
		rooms[3] = "108*normal";
		rooms[4] = "801*penthouse";
		rooms[5] = "105*normal";
		rooms[6] = "302*normal";
		rooms[7] = "700*suite";

		String[] custs = new String[7];
		custs[0] = "people@gotaway.com*Edward*Lee*mastercard*5458325441641567";
		custs[1] = "alwayswantostay@humble.ca*Abel*Excelent*mastercard*5233382411178726";
		custs[2] = "igotallthese@lightbulbs.com*Robert*Moka*Visa*4485011762777210";
		custs[3] = "donttakeaway@rocking.com*Johny*Texting**";
		custs[4] = "youdonthaveto@energy.com*Missed*Tupac**";

		String[] reservs = new String[7];
		reservs[0] = "Adderall@idontuseit.com*2016*8*30*2016*12*25*206";
		reservs[1] = "Opus@istooexpensive.ca*2016*10*26*2016*12*30*401";
		reservs[2] = "WallMount@islyfe.com*2017*1*1*2018*1*1*704";
		reservs[3] = "somuchmelody@spotify.com*2016*9*20*2016*9*26*801";
		reservs[4] = "yyyy@yyyy*2017*5*5*2017*7*8*801";

		File dir = new File("testfiles");
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File testRoomsFile = new File("testfiles/testRooms.txt");
			File testCustomersFile = new File("testfiles/testCustomers.txt");
			File testReservationsFile = new File("testfiles/testReservations.txt");
			ListUtilities.saveListToTextFile(rooms, testRoomsFile);
			ListUtilities.saveListToTextFile(custs, testCustomersFile);
			ListUtilities.saveListToTextFile(reservs, testReservationsFile);
		} catch (IOException io) {
			System.out.println("Error creating file in setUp()");
		}
		

		String[] testcase = new String[8];
		LocalDate[] checkinArray = new LocalDate[8];
		LocalDate[] checkoutArray = new LocalDate[8];
		RoomType[] roomType = new RoomType[8];

		testcase[0] = "Case 1: Valid data -- No rooms available with given information";
		checkinArray[0] = LocalDate.of(2016, 10, 10);
		checkoutArray[0] = LocalDate.of(2016, 10, 20);
		roomType[0] = RoomType.PENTHOUSE;

		testcase[1] = "Case 2: Valid data -- One room available on one floor";
		checkinArray[1] = LocalDate.of(2016, 7, 11);
		checkoutArray[1] = LocalDate.of(2016, 7, 26);
		roomType[1] = RoomType.NORMAL;

		testcase[2] = "Case 3: Valid data -- Same amount of rooms available on two different floors";
		checkinArray[2] = LocalDate.of(2016, 10, 26);
		checkoutArray[2] = LocalDate.of(2016, 10, 31);
		roomType[2] = RoomType.NORMAL;

		testcase[3] = "Case 4: Valid data -- More than one room available on one floor";
		checkinArray[3] = LocalDate.of(2016, 10, 20);
		checkoutArray[3] = LocalDate.of(2016, 11, 26);
		roomType[3] = RoomType.NORMAL;

		
				SequentialTextFileList file = new SequentialTextFileList("testfiles/testRooms.txt",
						"testfiles/testCustomers.txt", "testfiles/testReservations.txt");

				ReservationListDB db = new ReservationListDB(file);
				DawsonHotelAllocationPolicy dhap = new DawsonHotelAllocationPolicy(db);
			for (int i = 0; i < testcase.length; i++) {
				
			
				try {

					Optional<Room> r = dhap.getAvailableRoom(checkinArray[i], checkoutArray[i], roomType[i]);
					System.out
							.println("\t List of Free Rooms: " + db.getFreeRooms(checkinArray[i], checkoutArray[i], roomType[i]));
					System.out.println("\t Chosen Free Room: " + r.get() + "\n");

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			
			}
		}
	}