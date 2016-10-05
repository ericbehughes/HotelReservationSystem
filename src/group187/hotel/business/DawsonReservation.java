package group187.hotel.business;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import dw317.hotel.business.interfaces.*;

public class DawsonReservation implements Reservation {
	private static final long serialVersionUID = 42031768871L;
	private final Customer customer; 
	private final Room room;
	private LocalDate checkIn,checkOut; 
	
	//constructor
	public DawsonReservation(Customer customer, Room room, int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay) {	
		super();
		this.customer = customer;
		this.room = room;
		this.checkIn= LocalDate.of(inYear, inMonth, inDay);
		this.checkOut = LocalDate.of(outYear, outMonth, outDay);
		if (!this.checkIn.isBefore(this.checkOut))
			throw new IllegalArgumentException();
	}


	@Override
	public Customer getCustomer() {
		return new DawsonCustomer((DawsonCustomer) this.customer);
	}


	@Override
	public Room getRoom() {
		return this.room;
	}

	public LocalDate getCheckInDate() throws DateTimeException {
		return checkIn;
	}

	
	public LocalDate getCheckOutDate() throws DateTimeException {
		return checkOut;
	}


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



	@Override
	public String toString() {
		return customer.getEmail() + "*" 
				+ "*" + room.getNumber();
	}

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





	@Override
	public int compareTo(Reservation o) {
		// check for null
		if (o == null)
			throw new NullPointerException("The reservation object in the parameter list is null");
		if (this == o)
			return 0;
		// check floor 
		if (this.room.getFloor() < o.getRoom().getFloor())
			return -1;
		else if (this.room.getFloor() > o.getRoom().getFloor())
			return 1;
		// if floors are same
		else if (this.room.getFloor() == o.getRoom().getFloor()){
			if (this.room.getNumber() < o.getRoom().getNumber())
				return -1;
			else if (this.room.getNumber() > o.getRoom().getNumber())
				return 1;
			// if room numbers are same
			else if (this.room.getNumber() == o.getRoom().getNumber())
				if (this.getCheckInDate().isBefore(o.getCheckInDate()) )
					return 1;
				else if (this.getCheckInDate().isAfter(o.getCheckInDate()))
					return -1;
				else 
					return 0;
		}
		
		return 0;

	}
	
}
