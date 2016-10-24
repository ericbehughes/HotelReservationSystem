package dw317.lib.FileLoaderTest;
import java.io.IOException;

import dw317.hotel.data.HotelFileLoader;

public class FileLoaderTest {

	public static void main(String[] args) {
		
		String roomList = "C:\\Users\\ehugh\\git\\HotelReservationSystem\\datafiles\\rooms.txt",
			   customerList = "C:\\Users\\ehugh\\git\\HotelReservationSystem\\datafiles\\customer187.txt",
			   fileName;
		try {
			HotelFileLoader.getRoomListFromSequentialFile(roomList);
			for (int count = 2; count <= 10; count++){
				fileName = "h:\\git\\hotelreservationsystem\\datafiles\\unsorted\\customers\\customer" + count + ".txt";
				HotelFileLoader.getCustomerListFromSequentialFile(customerList);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
