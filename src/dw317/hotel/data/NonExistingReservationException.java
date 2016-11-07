package dw317.hotel.data;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;


public class NonExistingReservationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2659460084493752828L;
	
	public NonExistingReservationException() {
		super();
		
	}
	public NonExistingReservationException(String message, Customer dc, Room room) {
		super(message);
		System.out.println("Customer: "+dc.toString()+"\n Room: "+room.toString());
		
	}




}
