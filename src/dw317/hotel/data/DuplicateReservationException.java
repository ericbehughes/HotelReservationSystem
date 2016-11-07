package dw317.hotel.data;

import dw317.hotel.business.interfaces.Reservation;

public class DuplicateReservationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6709528290139333009L;
	//this will be thrown if a room is being reserved at a time that overlaps with an existing reservation.
	public DuplicateReservationException(){
		super("The provided Customer or Room already is registered.");
	}
//this will be thrown if a room is being reserved at a time that overlaps with an existing reservation.
	public DuplicateReservationException(String message) {
		super(message);
	}
	
	public DuplicateReservationException(String message, Reservation rv) {
		super(message);
		System.out.println("Customer: "+rv.getCustomer().toString()+
							"\n Email: "+rv.getCustomer().getEmail().toString()+
							"\n Room: "+rv.getRoom().toString());
	}
}
