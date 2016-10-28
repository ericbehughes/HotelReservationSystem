package dw317.lib.ListUtilitiesTest;
import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.HotelFileLoader;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;
import group187.util.ListUtilities;

public class FileLoaderTest {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
			
		// TEST getCustomerListFromSequentialFile
			try {
				File allCustomers = new File("datafiles/unsorted/customers/AllCustomers.txt");
				allCustomers.createNewFile();
				// save customer records to big customer file
				for (int count = 1; count <= 10; count++){
					try{
						//create list per customer text file
							Customer[] customerArray = HotelFileLoader.getCustomerListFromSequentialFile(
									new File("datafiles/unsorted/customers/customers" + count + ".txt"));
							//save list to big AllCustomers.txt file
							ListUtilities.saveListToTextFile(customerArray, allCustomers);
							
						}catch (IllegalArgumentException iae)
							{
							System.out.println(iae.getMessage());
							}
						}
				}catch (IOException ie) {
					// TODO Auto-generated catch block
					ie.printStackTrace();
			
			}
			System.out.println("record count for customers " + ListUtilities.recordCount);

		System.out.println("customers done");
	
		try {
			//create giant list with all customers
			Customer[] allCustomerObjects = HotelFileLoader.getCustomerListFromSequentialFile(
					new File("datafiles/unsorted/customers/AllCustomers.txt"));
			
			// create roomsList
			File roomsFile = new File("datafiles/rooms.txt");
			roomsFile.createNewFile();
			Room[] allRooms = HotelFileLoader.getRoomListFromSequentialFile(roomsFile);
			// create a file from customer and rooms list
			File allReservations = new File("datafiles/unsorted/reservations/AllReservations.txt");
			allReservations.createNewFile();
			// save reservation records to big reservation file
			for (int count = 1; count <= 10; count++) {
				try {
					//create reservations array for each reservation file while using BIG customerArray and roomArray
					Reservation[] reservationsArray = HotelFileLoader.getReservationListFromSequentialFile(
							new File("datafiles/unsorted/reservations/reservations" + count + ".txt"), allCustomerObjects, allRooms);
					//save reservation to BIG reservation file
					ListUtilities.saveListToTextFile(reservationsArray,allReservations );
				} catch (IllegalArgumentException iae) {
					
				}
			
			}
		} catch (IOException ie) {
			// TODO Auto-generated catch block
		
		}
		System.out.println("record count for reservations " + ListUtilities.recordCount);

		System.out.println("rooms done");
	

	}
	
}
