package group187.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.lib.Email;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;

public class ReservationListDB implements ReservationDAO {
	
	private List<Reservation> database;
	private List<Room> allRooms;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
	public ReservationListDB (ListPersistenceObject listPersistenceObject)
	{
		this.listPersistenceObject = listPersistenceObject;
		database = listPersistenceObject.getReservationDatabase();
		allRooms = listPersistenceObject.getRoomDatabase();
		factory = null;
	}
	public ReservationListDB (ListPersistenceObject listPersistenceObject,
	HotelFactory factory)
	{
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		database = listPersistenceObject.getReservationDatabase();
		allRooms = listPersistenceObject.getRoomDatabase();
	}


	@Override
	public void add(Reservation reserv) throws DuplicateReservationException {
		for (int i = 0; i < database.size(); i++)
			if (reserv.equals(database.get(i)))
				throw new DuplicateReservationException("The reservation: " + reserv.toString() + " is already in the list");
		if (factory == null)
			throw new IllegalArgumentException("ReservationLisrtDB - factory cannot be null");
		int index = binarySearch(reserv); // Find the index for the object to keep order
		Reservation reservationObj = factory.getReservationInstance(reserv); // Create a copy of the object
		database.add(index, reservationObj); // Add the copy
		}

	@Override
	public void disconnect() throws IOException {
		listPersistenceObject.saveReservationDatabase(database); // Save the database to the disk
		database = null; // Make the database null
	}
		
	@Override
	public List<Reservation> getReservations(Customer cust) {
		List<Reservation> reservList = new ArrayList<>(); // Create the list of reservations
		for (int i = 0; i < database.size(); i++){
			DawsonReservation tempReserv = (DawsonReservation)database.get(i); // Get the reservation at index i
			if (tempReserv.getCustomer().equals(cust)) // Check to see if the customer matches the parameter
				reservList.add(tempReserv);	 // Add reservation to list
		}
		return reservList;
	}
	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		DawsonReservation reservCopy = (DawsonReservation)factory.getReservationInstance(reserv); // Create a copy of the reservation to be deleted
		for (int i = 0; i < database.size(); i++){
			DawsonReservation tempReserv = (DawsonReservation)database.get(i); // Get the reservation at index i
			if (tempReserv.equals(reservCopy)) // Check to see if the reservation matches the one to be deleted
				database.remove(i);	 // Delete the reservation from the database
		}
	}
	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
		List<Room> reservedRooms = new ArrayList<>();
		if (checkin.isAfter(checkout))
			return reservedRooms;
		for (int i = 0; i < allRooms.size(); i++){
			DawsonReservation reservTemp = (DawsonReservation)database.get(i);
			Room roomTemp = reservTemp.getRoom();
			LocalDate tempCheckIn = reservTemp.getCheckInDate(),
					  tempCheckOut = reservTemp.getCheckOutDate();
			
			if (tempCheckIn.equals(checkin) && tempCheckOut.equals(checkout))
				reservedRooms.add(roomTemp); 
		}
		return reservedRooms;
	}
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		LocalDate[] dates;
		List<Room> reservedRooms = getReservedRooms(checkin, checkout),
				   freeRooms = new ArrayList<>();
		for (int i = 0; i < reservedRooms.size(); i++){
			for (int j = 0; j < allRooms.size(); j++){
				DawsonRoom roomTemp = (DawsonRoom)allRooms.get(j);
				dates = getCheckInCheckOutForRoom(roomTemp);
				LocalDate tempCheckIn = dates[0],
						  tempCheckOut = dates[1];
			}
		}
		return null;
	}
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void clearAllPast() {
		// TODO Auto-generated method stub
		
	}
	
	private <T> int binarySearch(T o){
		DawsonReservation reservObj = null;
		DawsonRoom roomObj = null;
		if (o instanceof Reservation)
			reservObj = (DawsonReservation)o;
		else
			throw new IllegalArgumentException("ReservationListDB - binarySearch(T o) - The object in the parameter must be a Reservation");
		
		int startIndex = 0, // Start index where to start searching
		endIndex = database.size(); // End index where to stop searching
		while (endIndex >= startIndex){
			int  midIndex = (endIndex+startIndex) / 2;
			DawsonReservation temp = (DawsonReservation)database.get(midIndex);
			if (temp.compareTo(reservObj) < 0)			
				startIndex = midIndex+1;
				
		else if (temp.compareTo(reservObj) > 0)
				endIndex = midIndex -1;

			else if (temp.equals(reservObj))
				return midIndex;
		}
		return startIndex;
	}		
	
	private LocalDate[] getCheckInCheckOutForRoom(Room room){
		LocalDate[] dates = new LocalDate[2];
		DawsonReservation reservObj;
		DawsonRoom roomObj = (DawsonRoom)room;
		for (int i = 0; i < database.size(); i++){
			reservObj = (DawsonReservation)database.get(i);
			for (int j = 0; j < allRooms.size(); j++){
				roomObj = (DawsonRoom)allRooms.get(j);
				if (roomObj.equals(reservObj.getRoom())){
					dates[0] = reservObj.getCheckInDate();
					dates[1] = reservObj.getCheckOutDate();
					return dates;
				}
			}
		}
		return null;
	}	
	
}
