package group187.hotel.data;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group187.util.Utilities;

public class ObjectSerializedList implements ListPersistenceObject{
	private String roomsFile, custFile, reservFile;
	public ObjectSerializedList(String roomsFile, String custFile, String reservFile){
		this.roomsFile = roomsFile;
		this.custFile = custFile;
		this.reservFile = reservFile;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRoomDatabase() {
		List<Room> roomDatabase;
		try{
			roomDatabase = (List<Room>) Utilities.deserializeObject(roomsFile);
			return roomDatabase;
		}
		catch (ClassNotFoundException | IOException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerDatabase() {
		List<Customer> custDatabase;
		try{
			custDatabase = (List<Customer>) Utilities.deserializeObject(custFile);
			return custDatabase;
		}
		catch (ClassNotFoundException | IOException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservation> getReservationDatabase() {
		List<Reservation> reservDatabase;
		try{
			reservDatabase = (List<Reservation>) Utilities.deserializeObject(reservFile);
			return reservDatabase;
		}
		catch (ClassNotFoundException | IOException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		Utilities.serializeObject(custs, custFile);
	}

	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException {
		Utilities.serializeObject(reservs, reservFile);
	}
	
}
