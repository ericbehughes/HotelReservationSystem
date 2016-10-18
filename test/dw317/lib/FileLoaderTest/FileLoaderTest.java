package dw317.lib.FileLoaderTest;
import java.io.File;
import java.io.IOException;

import dw317.hotel.data.HotelFileLoader;

public class FileLoaderTest {

	public static void main(String[] args) {
		
		String roomList = "C:\\Users\\ehugh\\git\\HotelReservationSystem\\datafiles\\rooms.txt";
		
		try {
			HotelFileLoader.getRoomListFromSequentialFile(roomList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
