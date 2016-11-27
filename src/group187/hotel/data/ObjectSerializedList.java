package group187.hotel.data;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;

public class ObjectSerializedList implements ListPersistenceObject{

	@Override
	public List<Room> getRoomDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomerDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> getReservationDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}
