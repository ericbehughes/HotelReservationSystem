package group187.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
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
		
		int index = binarySearch(reserv); // Find the index for the object to keep order
		Reservation reservationObj = factory.getReservationInstance(reserv); // Create a copy of the object
		database.add(index, reservationObj); // Add the copy
		}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Reservation> getReservations(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void cancel(Reservation reserv) throws NonExistingReservationException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
		// TODO Auto-generated method stub
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
	
}
