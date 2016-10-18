/*
 * 
 */
package dw317.hotel.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonRoom;

/**
 * The Class HotelFileLoade
 */
public class HotelFileLoader {
	
	
	//The Room array returned by the above method must be an array whose size is equal to
	//its capacity (i.e. the array must be full to capacity).
	 public static  Room[] getRoomListFromSequentialFile(String filename) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String str;

		List<Room> list = new ArrayList<Room>();
		while ((str = in.readLine()) != null) {
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
			String[] array = str.split("\\*");
			for (int i = 0; i < array.length - 4; i += 4) {
				Email email = new Email(array[i]);
				Name name = new Name(array[i + 1], array[i + 2]);
				Optional<CreditCard> card = Optional.of(CreditCard.getInstance(
						CardType.valueOf(array[i+3].toUpperCase()), array[i + 4]));
				DawsonCustomer customer = new DawsonCustomer(name.getFirstName(), name.getLastName(), email, card);
				list.add(customer);
			}
		}

		Customer[] customers = list.toArray(new Customer[0]);
		in.close();
		return customers;
	 }
	 
	 

	
	
	
}
