package dw317.lib.FileLoaderTest;
import java.io.IOException;

import dw317.hotel.data.HotelFileLoader;

public class FileLoaderTest {

	public static void main(String[] args) {
		
		String roomList = "C:\\Users\\ehugh\\git\\HotelReservationSystem\\datafiles\\rooms.txt";
		String customerList = "C:\\Users\\ehugh\\git\\HotelReservationSystem\\datafiles\\customer187.txt";
		
		try {
			HotelFileLoader.getRoomListFromSequentialFile(roomList);
			HotelFileLoader.getCustomerListFromSequentialFile(customerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
