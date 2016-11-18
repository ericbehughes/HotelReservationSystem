package group187.hotel.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;

public class DawsonHotelAllocationPolicy implements AllocationPolicy {
	private static final long serialVersionUID = 4203176887L;
	private ReservationDAO reservationDAO;

	public DawsonHotelAllocationPolicy(ReservationDAO reservationDAO) {
		this.reservationDAO = reservationDAO;
	}

	@Override
	public Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType roomtype) {
		/** Var explanations
		 *  freeRooms: A list of all the rooms available for reservation
		 *  
		 *  numFreeRooms: This array indicates how many free rooms per floor there are where index + 1 = floor (0 = floor 1). 
		 *  			  The value at index 0 will indicate the # of free rooms on floor 1.
		 *  
		 *  floorNum: Temporary var used to hold a floor number.
		 *  
		 *  highestNum: Used when iterating through the numFreeRooms array to find the highest number.
		 *  
		 *  floor: Which Floor to use when choosing the room to reserve.
		 *  
		 *  selectedRoom: The room to give to the customer.
		 *  
		 *  start: The floor to start searching on, based on the roomtype
		 *  
		 *  end: Thw floor to stop searching on, based on the roomtype
		 */
		List<Room> freeRooms = reservationDAO.getFreeRooms(checkin, checkout, roomtype); 
		int[] numFreeRooms = { 0, 0, 0, 0, 0, 0, 0, 0 }; 
		int floorNum, highestNum = numFreeRooms[0], floor = 0, selectedRoom = 0, start = 0, end = 0;
		
		// Find the total number of free rooms per floor, read explanation on numFreeRooms above.
		for (int i = 0; i < freeRooms.size(); i++) {
			DawsonRoom temp = (DawsonRoom)freeRooms.get(i); // Get the room object at index
			floorNum = temp.getFloor(); // Get the floor number
			numFreeRooms[floorNum-1]++;
		}
		
		if (roomtype.equals(RoomType.NORMAL)){
			start = 0;
			end = 5;
		}
		
		else if (roomtype.equals(RoomType.SUITE)){
			start = 5;
			end = 7;
		}
		
		else if (roomtype.equals(RoomType.PENTHOUSE)){
			start = 7;
			end = 8;
		}
		/**
		 * Find the floor with the highest number of free rooms
		 */
		for (; start < end; start++) {
			if (numFreeRooms[start] > highestNum){
				highestNum = numFreeRooms[start];
				floor = start + 1;
			}
		}
		
		selectedRoom = pickRandomRoom(floor, freeRooms, roomtype);
		DawsonRoom chosenRoom = new DawsonRoom(selectedRoom, roomtype);
		return Optional.of((Room)chosenRoom);
	}
	
	/**
	 * Randomly chooses one of the free rooms on the floor given in the parameter list
	 * If the randomly chosen room is not available for reservation, the method will call itself
	 * and repeat until it finds a room to use.
	 * 
	 * @param floor: The floor to chose the room from.
	 * @param freeRooms: The list containing all the free rooms.
	 * @param roomtype: The room type for the room.
	 * @return room: The room number.
	 */
	private int pickRandomRoom(int floor, List<Room> freeRooms, RoomType roomtype){
		/** Var explanation
		 * random: Create a random object to generate a number between 1 and 8.
		 * room: The randomly chosen room.
		 * temp: A temporary object referencing DawsonRoom so that we can properly set a room number
		 * checkRoom: A cast of DawsonRoom to Room so that we can check the list of free rooms
		 * 			  for the same room.
		 */
		
		Random random = new Random();
		int room = random.nextInt((8 - 1) + 1) + 1;
		DawsonRoom temp = new DawsonRoom((floor*100) + room, roomtype);
		Room checkRoom = (Room)temp;
		if (freeRooms.contains(checkRoom))
			room = (floor*100) + room;
			
		else 
			pickRandomRoom(floor, freeRooms, roomtype);
		
		return room;
	}
	
	

}
