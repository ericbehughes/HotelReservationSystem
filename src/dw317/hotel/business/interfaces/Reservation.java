package dw317.hotel.business.interfaces;
import java.io.Serializable;
import java.time.LocalDate;

public interface Reservation extends Comparable<Reservation>, Serializable {

	public static Customer getCustomer(){
		//change later on 
		return null;
	}
	public static Room getRoom(){
		//change later on 
		return null;
	}
	public static LocalDate getCheckInDate(){
		//change later onn
		return null;
	}
	public static LocalDate getCheckOutDate(){
		//change later on 
		return null; 
	}
	public static int getNumberDays(){
		//change later on 
		return 0;
	}
}
