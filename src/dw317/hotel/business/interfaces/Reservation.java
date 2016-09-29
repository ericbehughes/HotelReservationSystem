package dw317.hotel.business.interfaces;
import java.io.Serializable;
import java.time.LocalDate;

public interface Reservation extends Comparable<Reservation>, Serializable {

	public Customer getCustomer(); // deep copy 
	
	public Room getRoom();
	
	public LocalDate getCheckInDate();
	
	public LocalDate getCheckOutDate();
	
	public int getNumberDays();
}
