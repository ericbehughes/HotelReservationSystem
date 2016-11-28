package group187.hotel.data;

import java.io.IOException;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import group187.util.Utilities;

public class SerializedFileLoadeApp {

	public static void main(String[] args) {
		String rooms = "datafiles//database//rooms.txt",
			   custs = "datafiles//database//customers.txt",
			   reservs = "datafiles//database//reservations.txt",
			   roomSer = "datafiles//database//rooms.ser",
			   custSer = "datafiles//database//customers.ser",
			   reservSer = "datafiles//database//reservations.ser";
		
		List<Customer> custDatabase;
		List<Reservation> reservDatabase;
		List<Room> roomDatabase;
		
		SequentialTextFileList sObj = new SequentialTextFileList(rooms, custs, reservs);
		// Get the database
		custDatabase = sObj.getCustomerDatabase();
		reservDatabase = sObj.getReservationDatabase();
		roomDatabase = sObj.getRoomDatabase();
		System.out.println("First entry: " + custDatabase.get(0) + " database size: " + custDatabase.size());
		System.out.println("First entry: " + reservDatabase.get(0) + " database size: " + reservDatabase.size());
		System.out.println("First entry: " + roomDatabase.get(0) + "database size: " + roomDatabase.size());

		ObjectSerializedList serObj = new ObjectSerializedList(roomSer, custSer, reservSer);
		// Serialize database
		try {
			Utilities.serializeObject(custDatabase, custSer);
			Utilities.serializeObject(reservDatabase, reservSer);
			Utilities.serializeObject(roomDatabase, roomSer);
			
			// Deserialize 
			custDatabase = null;
			reservDatabase = null;
			roomDatabase = null;
			
			custDatabase = serObj.getCustomerDatabase();
			reservDatabase = serObj.getReservationDatabase();
			roomDatabase = serObj.getRoomDatabase();
			
			System.out.println("First entry: " + custDatabase.get(0) + " database size: " + custDatabase.size());
			System.out.println("First entry: " + reservDatabase.get(0) + " database size: " + reservDatabase.size());
			System.out.println("First entry: " + roomDatabase.get(0) + "database size: " + roomDatabase.size());
		} catch (IOException e) {
			e.getMessage();
		}
		


	}

}
