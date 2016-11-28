package group187.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.HotelFileLoader;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import group187.util.ListUtilities;

public class SequentialTextFileList implements ListPersistenceObject{
	
	private final String roomFilename;
	private final String customerFilename;
	private final String reservationFilename;

	/**
	 * Constructor requires the filenames of the files containing the sorted
	 * string representations of the Rooms, Customers, and Reservations.
	 * @param roomFilename Text file with sorted Rooms
	 * @param customerFilename Text file with sorted Customers
	 * @param reservationFilename Text file with sorted Reservations
	 */
	public SequentialTextFileList (String roomFilename,
			String customerFilename, String reservationFilename) {
		this.roomFilename = roomFilename;
		this.customerFilename = customerFilename;
		this.reservationFilename = reservationFilename;
	}

	/** 
	 * Returns a reference to an arraylist containing the rooms. If an
	 * IOException occurs an ArrayList of size zero will be returned.
	 */
	@Override
	public List<Room> getRoomDatabase() {
		List<Room> rooms;
		try{
			
			File roomFile = new File(roomFilename);
			roomFile.createNewFile();
			rooms = 
			HotelFileLoader.getRoomListFromSequentialFile(roomFile);
		}
		catch (IOException e) {
			return new ArrayList<Room>();
		}

		// Create the adapter object that will be used as an argument to 
		// instantiate an ArrayList instance.

		List<Room> listAdapter = rooms;

		// return a reference to an ArrayList instance.
		return listAdapter;
	}

	/** 
	 * Returns a reference to an arraylist containing the customers. If an
	 * IOException occurs an ArrayList of size zero will be returned.
	 */
	@Override
	public List<Customer> getCustomerDatabase() {
		List<Customer> custs;
		try{
			File customerFile = new File(customerFilename);
			customerFile.createNewFile();
			custs = 
			HotelFileLoader.getCustomerListFromSequentialFile(customerFile);
		}
		catch (IOException e) {
			return new ArrayList<Customer>();
		}

		// Create the adapter object that will be used as an argument to 
		// instantiate an ArrayList instance.
		Customer[] custArr = new Customer[custs.size()];
		ListUtilities.sort(custs.toArray(custArr));
		System.out.println(custs.toString());
		List<Customer> listAdapter = Arrays.asList(custArr);

		// return a reference to an ArrayList instance.
		return listAdapter;
	}
	
	/** 
	 * Returns a reference to an arraylist containing the reservations. If an
	 * IOException occurs an ArrayList of size zero will be returned.
	 */
	@Override
	public List<Reservation> getReservationDatabase() {
		List<Room> rooms = new ArrayList<>();
		List<Customer> custs = new ArrayList<>();
		List<Reservation> reservs = new ArrayList<>();

		try{
			// Create the room file
			File roomFile = new File(roomFilename);
			roomFile.createNewFile();
			rooms = HotelFileLoader.getRoomListFromSequentialFile(roomFile);
			// Create the customer file
			File customerFile = new File(customerFilename);
			customerFile.createNewFile();
			custs = 
			HotelFileLoader.getCustomerListFromSequentialFile(customerFile);
			// Create the reservation file
			File reservationFile = new File(reservationFilename);
			reservationFile.createNewFile();
			reservs =
			HotelFileLoader.getReservationListFromSequentialFile(reservationFile, (Customer[])custs.toArray(),(Room[])rooms.toArray());					
		}
		catch (IOException e) {
			return new ArrayList<Reservation>();
		}
		// Create the adapter object that will be used as an argument to 
		// instantiate an ArrayList instance.
		Reservation[] reservArr = new Reservation[reservs.size()];
		ListUtilities.sort(reservs.toArray(reservArr));
		List<Reservation> listAdapter = Arrays.asList(reservArr);

		// return a reference to an ArrayList instance.
		return listAdapter;
	}

	/**
	 * Saves the list of customers to the text file
	 */
	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		//For now we�ll use the existing saveListToTextFile utility method.
		Customer[] custArray = 
				custs.toArray(new Customer[custs.size()]);
		// Create the customer file
		File customerFile = new File(customerFilename);
		customerFile.createNewFile();
		ListUtilities.saveListToTextFile(custArray, customerFile);
	}

	/**
	 * Saves the list of reservations to the text file
	 */
	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException {
		//For now we�ll use the existing saveListToTextFile utility method.
		Reservation[] resArray = 
				reservs.toArray(new Reservation[reservs.size()]);
		// Create the reservation file
		File reservationFile = new File(reservationFilename);
		reservationFile.createNewFile();
		//ListUtilities.sort(resArray);
		ListUtilities.saveListToTextFile(resArray, reservationFile);	
	}
}
