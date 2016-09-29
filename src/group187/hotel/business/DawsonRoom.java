package group187.hotel.business;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;

public class DawsonRoom implements Room {
	private static final long serialVersionUID = 42031768871L;
	private final int roomNumber;
	private final RoomType roomType; 
	
	public DawsonRoom(int roomNumber,RoomType roomType2){
		this.roomNumber = roomNumber; 
		this.roomType = roomType2; 
	}
	
	public boolean isValid(int roomNumber) throws IllegalArgumentException{
		try{
			int floor = -1;
			int[] roomArr = new int[3];
			// Converts the int to an array for easier digit extraction
			for (int i = 2; i >= 0; i--){
				roomArr[i] = roomNumber % 10;
				roomNumber = roomNumber / 10;
			}
			floor = roomArr[0];
			if (floor < 1 || floor > 8)
				throw new IllegalArgumentException("The floor can only be between 1 and 8");
			if (roomArr[1] != 0)
				throw new IllegalArgumentException("The room number can only start with 0");
			if (roomArr[2] < 1 || roomArr[2] > 8)
				throw new IllegalArgumentException("The room number can only end with 1 - 8");
		}
		
		catch(IllegalArgumentException e){
			System.out.print(e.getMessage());
		}
		
		return true;
		
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
		// TODO Auto-generated method 
		return 0;
	}

	@Override
	public RoomType getRoomType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRoomNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFloor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
}
