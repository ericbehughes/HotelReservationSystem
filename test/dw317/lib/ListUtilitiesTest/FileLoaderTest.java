package dw317.lib.ListUtilitiesTest;
import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.HotelFileLoader;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;
import group187.util.ListUtilities;

public class FileLoaderTest {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
			String currentCustomerFilePath;
			try {
				File allCustomers = new File("datafiles/unsorted/customers/AllCustomers.txt");
				allCustomers.createNewFile();
				// save customer records to big customer file
				for (int count = 1; count <= 10; count++){
					currentCustomerFilePath = "datafiles/unsorted/customers/customers" + count + ".txt";
					try{
							Customer[] customerArray = HotelFileLoader.getCustomerListFromSequentialFile(currentCustomerFilePath);
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

			System.out.println("customers done");
			String currentRoomFilePath;
			try {
				File reservations = new File("datafiles/unsorted/customers/AllReservations.txt");
				reservations.createNewFile();
				// save reservation records to big reservation file
				for (int count = 1; count <=  10; count++){
					currentRoomFilePath = "datafiles/unsorted/reservations/reservations" + count + ".txt";
					DawsonRoom[] roomArray = HotelFileLoader.getRoomListFromSequentialFile(currentRoomFilePath)
					ListUtilities.saveListToTextFile(customerArray, reservations);
				}
			} catch (IOException ie) {
				// TODO Auto-generated catch block
				ie.printStackTrace();
		
		}
			
			System.out.println("rooms done");

	
	}
	
}
