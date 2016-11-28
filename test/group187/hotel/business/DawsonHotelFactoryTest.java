package group187.hotel.business;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

public class DawsonHotelFactoryTest {

	public static void main(String[] args) {
		DawsonHotelFactory dHF = DawsonHotelFactory.DAWSON;
		try{
			
			Customer cust = new DawsonCustomer("Jokes", "Caterpilla", new Email("JokesOnYou@gmail.com"));
			Room room = new DawsonRoom(101, RoomType.NORMAL);

			System.out.println("Valid Data: \n" + "First Name: " + cust.getName().getFirstName() + " \tLast Name: " + cust.getName().getLastName() +
								"\tEmail: " + cust.getEmail().getAddress());
		
		
		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			
		}
	
		try{
			Room room = new DawsonRoom(1071, RoomType.NORMAL);
			System.out.println("Valid Data: \n" + "Room Number (3 digits): " + room.getRoomNumber() + " \tRoom Type: " + room.getRoomType());
		
		
		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			
		}

		try{
			Customer cust = new DawsonCustomer("suhh", "dudeee", new Email("suhdudee@gmail.com"));
			Room aRoom = new DawsonRoom(501, RoomType.NORMAL);
			Reservation reserve = dHF.getReservationInstance(cust, aRoom, 2015, 10, 5, 2015, 12, 5);

			
		}
			
		catch (IllegalArgumentException iae1){
			System.out.print("\n\t"+ iae1.getMessage());
		
		}
	
		
		try{
			Customer cust = new DawsonCustomer("suhh", "dudeee", new Email("suhdudee@gmail.com"));
			Room aRoom = new DawsonRoom(501, RoomType.NORMAL);
		
			Reservation toCopy = dHF.getReservationInstance(cust, aRoom, 2015, 10, 5, 2015, 12, 5);
			dHF.getReservationInstance(toCopy);

		}
		catch (IllegalArgumentException iae){
			System.out.print("\n\t"+ iae.getMessage());
			
		}
		
	}
}
