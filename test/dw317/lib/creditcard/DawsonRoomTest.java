package dw317.lib.creditcard;

import dw317.hotel.business.RoomType;
import group187.hotel.business.DawsonRoom;

public class DawsonRoomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			DawsonRoom room1 = new DawsonRoom(302, RoomType.NORMAL);
			
			boolean bool = room1.isValid(room1.getRoomNumber());
			
			
	}

}
