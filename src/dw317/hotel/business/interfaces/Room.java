package dw317.hotel.business.interfaces;
import java.io.Serializable;
import group187.hotel.business.Room.RoomType;

public interface Room extends Comparable<Room>, Serializable {
	
	public static RoomType getRoomType(){
		//change later on 
		return null;
	}
	
	public static int getRoomNumber(){
		//change later on
		return 0;
	}
	
	public static int getFloor(){
		//change later on 
		return 0;
	}
	
	public static int getNumber(){
		//change later on
		return 0;
	}

}
