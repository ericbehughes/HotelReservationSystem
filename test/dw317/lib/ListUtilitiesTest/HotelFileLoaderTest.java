package dw317.lib.ListUtilitiesTest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.HotelFileLoader;
import group187.util.ListUtilities;

public class HotelFileLoaderTest {

	
	public static void main(String[] args) {
			
		// TEST getCustomerListFromSequentialFile
			try {
				File allCustomers = new File("datafiles/unsorted/customers/AllCustomers.txt");
				allCustomers.createNewFile();
				// save customer records to big customer file
				for (int count = 1; count <= 10; count++){
					try{
						//create list per customer text file
							Customer[] customerArray = (Customer[]) HotelFileLoader.getCustomerListFromSequentialFile(
									new File("datafiles/unsorted/customers/customers" + count + ".txt")).toArray();
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
			ListUtilities.recordCount = 0;
		System.out.println("customers done");
	
		try {
			//create giant list with all customers
			List<Customer> allCustomerObjects = HotelFileLoader.getCustomerListFromSequentialFile(
					new File("datafiles/unsorted/customers/AllCustomers.txt"));
			
			// create roomsList
			File roomsFile = new File("datafiles/rooms.txt");
			roomsFile.createNewFile();
			Room[] allRooms = (Room[]) HotelFileLoader.getRoomListFromSequentialFile(roomsFile).toArray();
			System.out.println("rooms done");
			// create a file from customer and rooms list
			File allReservations = new File("datafiles/unsorted/reservations/AllReservations.txt");
			allReservations.createNewFile();
			// save reservation records to big reservation file
			for (int count = 1; count <= 10; count++) {
				try {
					//create reservations array for each reservation file while using BIG customerArray and roomArray
					Customer[] allCustsArr = (Customer[]) allCustomerObjects.toArray();
					Reservation[] reservationsArray = (Reservation[]) HotelFileLoader.getReservationListFromSequentialFile(
							new File("datafiles/unsorted/reservations/reservations" + count + ".txt"), allCustsArr, allRooms).toArray();
					//save reservation to BIG reservation file
					
					ListUtilities.saveListToTextFile(reservationsArray,allReservations );
					
				} catch (IllegalArgumentException iae) {
					iae.printStackTrace();
					System.out.println(iae.getMessage());
				}
				catch (NullPointerException npe) {
					npe.printStackTrace();
					System.out.println(npe.getMessage());
				}
			
			}
		} catch (IOException ie) {
			// TODO Auto-generated catch block
		
		}
		System.out.println("record count for reservations " + ListUtilities.recordCount);

		
	

	}
	
}
