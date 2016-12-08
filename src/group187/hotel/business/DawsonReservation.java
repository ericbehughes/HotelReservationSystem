/*
 * 
 */
package group187.hotel.business;
import java.time.LocalDate;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.temporal.ChronoUnit;
import dw317.hotel.business.interfaces.*;

// TODO: Auto-generated Javadoc
/**
 * The Class DawsonReservation.
 */
public class DawsonReservation implements Reservation, Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 42031768871L;
	
	/** The customer. */
	private final Customer customer; 
	
	/** The room. */
	private final Room room;
	
	/** The check in and checkout dates. */
	private LocalDate checkIn,checkOut; 
	
	/**
	 * Instantiates a new dawson reservation.
	 *
	 * @param customer the customer
	 * @param room the room
	 * @param inYear the in year
	 * @param inMonth the in month
	 * @param inDay the in day
	 * @param outYear the out year
	 * @param outMonth the out month
	 * @param outDay the out day
	 */
	//constructor
	public DawsonReservation(Customer customer, Room room, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay) {	
		super();
		if (customer != null && room != null){
			this.customer = customer;
			this.room = room;
		}
		else
			throw new IllegalArgumentException("customer or room is null");
			
		if (validateDateFormat(inYear,inMonth,inDay) && validateDateFormat(outYear,outMonth,outDay)){
			if (LocalDate.of(inYear, inMonth, inDay).isBefore(LocalDate.of(outYear, outMonth, outDay))){
				this.checkIn = LocalDate.of(inYear, inMonth, inDay);
				this.checkOut = LocalDate.of(outYear, outMonth, outDay);
			}else
				throw new IllegalArgumentException("The time(s) is/are in the wrong format");
		}
		else
			throw new IllegalArgumentException("The time(s) is/are in the wrong format");
			
		
		
		
	}

	/**
	 * Validate date format.
	 *
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @return true, if successful
	 */
	private boolean validateDateFormat(int year, int month, int day) {
		
		if (year > 100 && year < 2050)
			if (month > 0 && month < 13)
				if (day > 0 && day < 32)
					return true;
		return false;
	}


	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Reservation#getCustomer()
	 */
	@Override
	public Customer getCustomer() {
		return (DawsonCustomer)this.customer;
	}


	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Reservation#getRoom()
	 */
	@Override
	public Room getRoom() {
		return this.room;
	}

	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Reservation#getCheckInDate()
	 */
	public LocalDate getCheckInDate() throws DateTimeException {
		return checkIn;
	}

	
	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Reservation#getCheckOutDate()
	 */
	public LocalDate getCheckOutDate() throws DateTimeException {
		return checkOut;
	}


	/* (non-Javadoc)
	 * @see dw317.hotel.business.interfaces.Reservation#getNumberDays()
	 */
	@Override
	public int getNumberDays() {
		LocalDate checkIn = this.getCheckInDate();
		LocalDate checkOut = this.getCheckOutDate();
		if (checkIn.isAfter(checkOut)){
			throw new IllegalArgumentException("The check in date is after the check out date");
		}
		long days = checkIn.until(checkOut, ChronoUnit.DAYS);
		return (int)days;

	}

//email*checkinYr*checkinMonth*checkinDay* checkoutYr*checkoutMonth*checkoutDay*roomnumber

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
 	 */
	@Override
	public String toString() {
		return customer.getEmail().toString() + "*" + this.getCheckInDate().getYear() + "*" + this.getCheckInDate().getMonthValue() + "*" +  this.getCheckInDate().getDayOfMonth()+ "*" + 
	this.getCheckOutDate().getYear() + "*" + this.getCheckOutDate().getMonthValue() + "*" + this.getCheckOutDate().getDayOfMonth() + "*" + room.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((checkIn == null) ? 0 : checkIn.hashCode());
		result = prime * result + ((checkOut == null) ? 0 : checkOut.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DawsonReservation))
			return false;
		DawsonReservation other = (DawsonReservation) obj;
		
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (checkIn == null) {
			if (other.checkIn != null)
				return false;
		} else if (!checkIn.equals(other.checkIn))
			return false;
		if (checkOut == null) {
			if (other.checkOut != null)
				return false;
		} else if (!checkOut.equals(other.checkOut))
			return false;
	
		return true;
	}





	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	//Fixed the compareTo method
	public int compareTo(Reservation o) {
				if(o == null){
					throw new NullPointerException();
				}
				if(this.room.compareTo(o.getRoom()) > 0)
					return 1;
				if(this.room.compareTo(o.getRoom()) == 0){
					if(this.getCheckInDate().isBefore(o.getCheckInDate()))
						return 1;
					if(this.getCheckInDate() == o.getCheckInDate())
						return 0;
				}
			return -1;	
			}



}
