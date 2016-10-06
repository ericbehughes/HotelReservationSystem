/*
 * 
 */
package group187.hotel.business;

import dw317.hotel.business.RoomType;
import group187.hotel.business.DawsonRoom;

// TODO: Auto-generated Javadoc
/**
 * The Class DawsonRoomTest.
 */
public class DawsonRoomTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int roomTest1 = 402;
			DawsonRoom room1 = new DawsonRoom(roomTest1, RoomType.NORMAL);
			/*
			 * Notice that in Dawson Hotel, floors 1 to 5 have 8 normal rooms available, floor 6 and 7 have 4 suites each, 
			 * and floor 8 has 1 penthouse. Other hotels may have different configurations.
			 * */
			
			// returns 
			
			
			int roomTest2 = 502;
			DawsonRoom room2 = new DawsonRoom(roomTest2, RoomType.NORMAL);
			
			int roomTest3 = 704;
			DawsonRoom room3 = new DawsonRoom(roomTest3, RoomType.SUITE);
			
			int roomTest4 = 801;
			DawsonRoom room4 = new DawsonRoom(roomTest4, RoomType.PENTHOUSE);
			
			int result;
			
			result =room1.compareTo(room1);
			
			result =room1.compareTo(room2);
			
			boolean equals = room1.equals(room1);
			
			equals =room4.equals(room3);
			
			
			
			
			
			
			
	}

}
