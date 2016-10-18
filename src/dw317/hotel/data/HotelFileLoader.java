/*
 * 
 */
package dw317.hotel.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import group187.hotel.business.DawsonRoom;

/**
 * The Class HotelFileLoade
 */
public class HotelFileLoader {
	
	
	//The Room array returned by the above method must be an array whose size is equal to
	//its capacity (i.e. the array must be full to capacity).
	 public static  Room[] getRoomListFromSequentialFile(String filename) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String str;

		List<Room> list = new ArrayList<Room>();
		while ((str = in.readLine()) != null) {
			String[] array = str.split("\\*");
			for (int i = 0; i < array.length-1; i+=2) {
				String roomNumber = array[i];
				RoomType roomType = RoomType.checkRoomType(array[i + 1]);
				DawsonRoom room = new DawsonRoom(Integer.parseInt(roomNumber), roomType);
				list.add(room);
			}
		}

		Room[] rooms = list.toArray(new Room[0]);

		return rooms;

	}
			
			
			
			
			
			/*
			 * Notice that in Dawson Hotel, floors 1 to 5 have 8 normal rooms
			 * available, floor 6 and 7 have 4 suites each, and floor 8 has 1
			 * penthouse. Other hotels may have different configurations.
			 */

//			Boolean reservation = false, customer = false;
//					BufferedReader br = new BufferedReader(new FileReader(reservationFile));
//					String line = Files.readAllLines(Paths.get(reservationFile)).get(lineNumber);
//					customerInfo = line.split("\\*");
			
	 
	 //The Customer array returned by the above method must be an array whose size is equal
	 //to its capacity (i.e. the array must be full to capacity).

	 public static Customer[] getCustomerListFromSequentialFile(String filename) throws IOException
	 {
		 return null;
	 }

	
	
	
}
