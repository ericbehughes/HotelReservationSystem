/*
 * 
 */
package dw317.hotel.data;
import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;

/**
 * The Class HotelFileLoade
 */
public class HotelFileLoader {
	
	//The private constructor prevents any form of instantionation
	private HotelFileLoader(){}
	
	//The Room array returned by the above method must be an array whose size is equal to
	//its capacity (i.e. the array must be full to capacity).
	 public static  Room[] getRoomListFromSequentialFile(String filePath) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(filePath));
		String str;
	
		List<Room> list = new ArrayList<Room>();
		while ((str = in.readLine()) != null) {
			if (str.isEmpty())
				continue;
			String[] array = str.split("\\*");
			for (int i = 0; i < array.length-1; i+=2) {
				String roomNumber = array[i];
				RoomType roomType = RoomType.valueOf(array[i+1].toUpperCase());
				DawsonRoom room = new DawsonRoom(Integer.parseInt(roomNumber), roomType);
				list.add(room);
			}
		}

		Room[] rooms = list.toArray(new Room[0]);
		in.close();
		return rooms;

	}
			
	 //The Customer array returned by the above method must be an array whose size is equal
	 //to its capacity (i.e. the array must be full to capacity).
	// Email*First name*Last name*Card type*Card number \\
	public static Customer[] getCustomerListFromSequentialFile(String filename) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String str;
		List<Customer> list = new ArrayList<Customer>();
		while ((str = in.readLine()) != null) {
			if (str.isEmpty())
				continue;
			String[] array = str.split("\\*");
			Optional<CreditCard> card;
			int i = 0; 
				try {
					 card = Optional.of(CreditCard.getInstance(
								CardType.valueOf(array[i+3].toUpperCase()), array[i + 4]));
					Email email = new Email(array[i]);
					Name name = new Name(array[i + 1], array[i + 2]);
					DawsonCustomer customer = new DawsonCustomer(name.getFirstName(), name.getLastName(), email, card);
					list.add(customer);
				} catch (IllegalArgumentException iae) {
					
				}
				
				catch (IndexOutOfBoundsException iob)
				{
					card = null;
				}
		}

		Customer[] customers = list.toArray(new Customer[0]);
		in.close();
		return customers;
	 }
	
//	public static Reservation[] getReservationListFromSequentialFile(String filename,Customer[] customerList,Room[] roomList)
//	throws IOException, IllegalArgumentException
//	{
//		
//	}

	 
	public static Reservation[] getReservationListFromSequentialFile (String filename, Customer[] customerList, Room[] roomList)
    throws IOException, IllegalArgumentException {
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String str;
		int inYear; int inMonth; int inDay;
		int outYear; int outMonth; int outDay;
		List<Reservation> list = new ArrayList<Reservation>();
		while ((str = in.readLine()) != null) {
			String [] array = str.split("\\*");
			for (int i =0; i < array.length - 7; i+=8) {
				Email email = new Email(array[i]);
				Customer customer = search(customerList, email);
				if (customer == null) {
					throw new IllegalArgumentException("The customer cannot be found");
				}
				inYear = Integer.parseInt(array[i+1]);
				inMonth = Integer.parseInt(array[i+2]);
				inDay = Integer.parseInt(array[i+3]);
				outYear = Integer.parseInt(array[i+4]);
				outMonth = Integer.parseInt(array[i+5]);
				outDay = Integer.parseInt(array[i+6]);
				Room room = search(roomList,Integer.parseInt(array[i+7]));
				if (room == null) {
					throw new IllegalArgumentException("The room cannot be found");
				}
				DawsonReservation reservation = new DawsonReservation(customer,room,inYear,inMonth,inDay,outYear,outMonth,outDay);
				list.add(reservation);
			}
		}
		Reservation [] reservation = list.toArray(new Reservation[0]);
		in.close();
		return reservation;
	}
	 
	private static Customer search(Customer[] customerList, Email email) {
		// This method will use the email field in order to find the associated customer
		// If the customer can not be found, an Illegal Argument Exception will be throwned 
		DawsonCustomer customer = null;
		Optional<CreditCard> card = null;
		for(int i =0; i < customerList.length; i++){
			if(customerList[i].equals(email)){
				Name name = new Name(customerList[i+1].toString(), customerList[i+2].toString());
				//This checks to see if the value at the next index is information related to a credit card or not
				switch(customerList[i+3].toString().toUpperCase()){
				case "VISA":
				case "MASTERCARD":
				case "AMEX":
				card = Optional.of(CreditCard.getInstance(CardType.valueOf(customerList[i+3].toString()), customerList[i+4].toString()));
				break;
				default: 
					card = null;
				}
				customer = new DawsonCustomer(name.getFirstName(),name.getLastName(), email, card);
				break;
			}
		}
		return customer;
	}

	private static Room search(Room[] roomList, int room) {
		// This method will use the room number in order to find the associated room
		// If the room can not be found, an Illegal Argument Exception will be throwned
		DawsonRoom roomMatch= null;
		for(int i =0; i < roomList.length; i++ ){ 
			if (roomList[i].equals(room)){
				roomMatch = new DawsonRoom(roomList[i].getRoomNumber(),roomList[i+1].getRoomType());
				break;
			}
		}
		return roomMatch;
	}


	}

	
	
	

