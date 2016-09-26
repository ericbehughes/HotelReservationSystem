package group187.hotel.business;
import dw317.hotel.business.interfaces.Room;

public class DawsonRoom implements Room {
	private static final long serialVersionUID = 42031768871L;
	private final int roomNumber;
	private final Room roomType; 
	
	public DawsonRoom(int roomNumber,Room e){
		this.roomNumber = roomNumber; 
		this.roomType = e; 
	}
	
	public boolean isValid(int roomNumber) throws IllegalArgumentException{
		//change later on
		return false;
	}
	
	public int getFloor(int floorNumber){
		return ((floorNumber / 100) % 10);
	}
	
	public int getNumber(int roomNumber){
		return (roomNumber % 10);
	}
	
	@Override
	public String toString() {
		return (this.roomNumber + "*" + this.roomType);
	}

	@Override
	public int compareTo(Room o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
