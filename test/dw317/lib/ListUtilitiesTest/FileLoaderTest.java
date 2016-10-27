package dw317.lib.ListUtilitiesTest;
import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.HotelFileLoader;
import group187.util.ListUtilities;

public class FileLoaderTest {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		
			String customerFilePath;
			try {
				File newCustomers = new File("datafiles/unsorted/customers/NewCustomers.txt");
				newCustomers.createNewFile();
				// save customer records to big customer file
				for (int count = 2; count <=  10; count++){
					
					customerFilePath = "datafiles/unsorted/customers/customer" + count + ".txt";
					Customer[] customerArray = HotelFileLoader.getCustomerListFromSequentialFile(customerFilePath);
					ListUtilities.sort(customerArray);
					ListUtilities.saveListToTextFile(customerArray, newCustomers);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			String reservationFilePath;
			try{
				for (int count = 1; count <= 10; count++){
					reservationFilePath = "datafiles/unsorted/customers/reservation" + count + ".txt";
					Room[] roomArray = HotelFileLoader.getRoomListFromSequentialFile(reservationFilePath);
					ListUtilities.sort(roomArray);
				}
		
			}catch (IOException var) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

	}
}
