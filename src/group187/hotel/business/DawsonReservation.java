package group187.hotel.business;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import dw317.hotel.business.interfaces.*;

public class DawsonReservation implements Reservation {
	private static final long serialVersionUID = 42031768871L;
	private final DawsonCustomer customer; 
	private final Room roomNumber;
	private int inYear, inMonth, inDay;
	private int outYear, outMonth, outDay;
	
	//constructor
	public DawsonReservation(Customer customer, Room roomNumber, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay) {
		super();
		this.customer = (DawsonCustomer)customer;
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


	@Override
	public Customer getCustomer() {
		// TODO Auto-generated method stub
		return new DawsonCustomer(this.customer);
	}


	@Override
	public Room getRoom() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LocalDate getCheckInDate() throws DateTimeException {
		LocalDate checkIn= LocalDate.of(inYear, inMonth, inDay);
		return checkIn;
	}


	@Override
	public LocalDate getCheckOutDate() {
		LocalDate checkOut = LocalDate.of(outYear, outMonth, outDay);
		return checkOut;
	}


	@Override
	public int getNumberDays() {
		// TODO Auto-generated method stub
		//to be continued
		LocalDate checkIn = this.getCheckInDate();
		LocalDate checkOut = this.getCheckOutDate();
		if (checkIn.isAfter(checkOut)){
			throw new IllegalArgumentException("The check in date is after the check out date");
		}
		long days = checkIn.until(checkOut, ChronoUnit.DAYS);
		return (int)days;

	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + inDay;
		result = prime * result + inMonth;
		result = prime * result + inYear;
		result = prime * result + outDay;
		result = prime * result + outMonth;
		result = prime * result + outYear;
		result = prime * result + ((roomNumber == null) ? 0 : roomNumber.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DawsonReservation)) {
			return false;
		}
		DawsonReservation other = (DawsonReservation) obj;
		if (customer == null) {
			if (other.customer != null) {
				return false;
			}
		} else if (!customer.equals(other.customer)) {
			return false;
		}
		if (inDay != other.inDay) {
			return false;
		}
		if (inMonth != other.inMonth) {
			return false;
		}
		if (inYear != other.inYear) {
			return false;
		}
		if (outDay != other.outDay) {
			return false;
		}
		if (outMonth != other.outMonth) {
			return false;
		}
		if (outYear != other.outYear) {
			return false;
		}
		if (roomNumber == null) {
			if (other.roomNumber != null) {
				return false;
			}
		} else if (!roomNumber.equals(other.roomNumber)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "DawsonReservation [email=" + customer.getEmail() + ", inYear=" + inYear + ", inMonth=" + inMonth
				+ ", inDay=" + inDay + ", outYear=" + outYear + ", outMonth=" + outMonth + ", outDay=" + outDay
				+ ", Room Number=" + roomNumber + "]";
	}
	
}
