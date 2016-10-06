package dw317.hotel.business.interfaces;
import java.io.Serializable;
import dw317.hotel.business.RoomType;

// TODO: Auto-generated Javadoc
/**
 * The Interface Room.
 */
public interface Room extends Comparable<Room>, Serializable {
	
	/**
	 * Gets the room type.
	 *
	 * @return the room type
	 */
	public RoomType getRoomType();

	/**
	 * Gets the room number.
	 *
	 * @return the room number
	 */
	public int getRoomNumber();
	
	/**
	 * Gets the floor.
	 *
	 * @return the floor
	 */
	public int getFloor();
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public int getNumber();

}
