package group187.hotel.business;
import dw317.hotel.business.interfaces.*;

public class DawsonReservation implements Reservation {
	private static final long serialVersionUID = 42031768871L;
	private final Customer customer; 
	private final Room roomNumber;
	private int inYear, inMonth, inDay;
	private int outYear, outMonth, outDay;
	
	
	public DawsonReservation(Customer customer, Room roomNumber, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay) {
		super();
		this.customer = customer;
		this.roomNumber = roomNumber;
		this.inYear = inYear;
		this.inMonth = inMonth;
		this.inDay = inDay;
		this.outYear = outYear;
		this.outMonth = outMonth;
		this.outDay = outDay;
	}


	@Override
	public int compareTo(Reservation o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
