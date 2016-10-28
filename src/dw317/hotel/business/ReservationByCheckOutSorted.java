package dw317.hotel.business;

import java.util.Comparator;

import dw317.hotel.business.interfaces.Reservation;

public class ReservationByCheckOutSorted implements Comparator<Reservation>{
	@Override 
	//This method will compare two reservation based on their checkout dates
	public int compare(Reservation r1, Reservation r2){
		int result = 0;
		//If Two Reservation have the same checkout dates
		//The default comparison is used
		if(r1.getCheckOutDate().isEqual(r2.getCheckOutDate())){
			 result = (r1.compareTo(r2));
		}
		if(r1.getCheckOutDate().isBefore(r2.getCheckOutDate())){
			result = 1;
		}
		if(r1.getCheckOutDate().isAfter(r2.getCheckOutDate())) {
			result = -1;
		}
		return result;
	}

}
