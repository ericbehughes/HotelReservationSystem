package dw317.hotel.business.interfaces;
import java.io.Serializable;
import dw317.hotel.business.RoomType;

public interface Room extends Comparable<Room>, Serializable {
	
	public RoomType getRoomType();

	public int getRoomNumber();
	
	public int getFloor();
	
	public int getNumber();

}
