package group187.hotel.business;

import java.time.LocalDate;
import java.util.ArrayList;
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
		List<Room> freeRooms = reservationDAO.getFreeRooms(checkin, checkout, roomtype);
		List<Integer> multipleFloors = new ArrayList<>();
		int[] numFreeRooms = { 0, 0, 0, 0, 0, 0, 0, 0 };
		int floorNum, highestNum = numFreeRooms[0], floor = 0, selectedRoom = 0;
		// Find the total number of free rooms per floor
		for (int i = 0; i < freeRooms.size(); i++) {
			DawsonRoom temp = (DawsonRoom) freeRooms.get(i);
			floorNum = temp.getFloor();
			switch (floorNum) {
			case 1:
				numFreeRooms[0] = numFreeRooms[0] + 1;
				break;

			case 2:
				numFreeRooms[1] = numFreeRooms[1] + 1;
				break;

			case 3:
				numFreeRooms[2] = numFreeRooms[2] + 1;
				break;

			case 4:
				numFreeRooms[3] = numFreeRooms[3] + 1;
				break;

			case 5:
				numFreeRooms[4] = numFreeRooms[4] + 1;
				break;

			case 6:
				numFreeRooms[5] = numFreeRooms[5] + 1;
				break;

			case 7:
				numFreeRooms[6] = numFreeRooms[6] + 1;
				break;

			case 8:
				numFreeRooms[7] = numFreeRooms[7] + 1;
				break;

			default:
				break;
			}
		}

		// Find the highest number of free rooms in the array
		// and the floor containing them;
		for (int i = 0; i < numFreeRooms.length; i++) {
			if (numFreeRooms[i] > highestNum){
				highestNum = numFreeRooms[i];
				floor = i + 1;
			}
			// If two or more floors have the same # of free rooms
			// Add them both to a list to decide which one to choose
			// later on
			else if (numFreeRooms[i] == highestNum){
				multipleFloors.add(i, numFreeRooms[i]);
				multipleFloors.add(floor, highestNum);
				highestNum = numFreeRooms[i];
				floor = i + 1;
			}
		}		
		if (multipleFloors.size() <= 0)
			selectedRoom = pickRandomRoom(floor, freeRooms);
		
		else{
			for (int i = 0; i < multipleFloors.size(); i++){
				if (multipleFloors.get(i) > 0){
					floor = i + 1;
					break;
				}
			}
			selectedRoom = pickRandomRoom(floor, freeRooms);
		}
		
		DawsonRoom chosenRoom = new DawsonRoom(selectedRoom, roomtype);
		return Optional.of((Room)chosenRoom);
	}
	
	private int pickRandomRoom(int floor, List<Room> freeRooms){
		Random randomFloor = new Random();
		int room = randomFloor.nextInt((8 - 1) + 1) + 1;
		if (freeRooms.contains((floor*100) + room))
			room = (floor*100) + room;
			
		else 
			pickRandomRoom(floor, freeRooms);
		
		return room;
	}
	
	

}
