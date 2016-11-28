package group187.hotel.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.HotelManager;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.hotel.data.interfaces.RoomDAO;
import dw317.lib.Email;
import dw317.lib.creditcard.*;

public class Hotel extends Observable implements HotelManager {
	private final HotelFactory factory;
	private final CustomerDAO customers;
	private final ReservationDAO reservations;
	private static final long serialVersionUID = 42031768871L;
	
	public Hotel (HotelFactory factory, RoomDAO rooms, CustomerDAO customers, ReservationDAO reservations){
		if (factory == null || rooms == null || customers == null || reservations == null)
			throw new IllegalArgumentException("The parameters cannot be null");
		this.factory = factory;
		this.customers = customers;
		this.reservations = reservations;
	}

	@Override
	public void cancelReservation(Reservation reservation) throws NonExistingReservationException {
		try{
		reservations.cancel(reservation);
		}
		catch (NonExistingReservationException e){
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void closeHotel() throws IOException {
		customers.disconnect();
		reservations.disconnect();

	}

	@Override
	public Optional<Reservation> createReservation(Customer customer, LocalDate checkin, LocalDate checkout,
			RoomType roomType) {
		AllocationPolicy allocationPolicy = factory.getAllocationPolicy(reservations);
		Optional<Room> room = allocationPolicy.getAvailableRoom(checkin, checkout, roomType);
		Reservation reservation =  new DawsonReservation(customer, room.get(), checkin.getYear(), checkin.getMonthValue(),
				checkin.getDayOfMonth(), checkout.getYear(), checkout.getMonthValue(), checkout.getDayOfMonth());
		try {
		reservations.add(reservation);
		}
		catch (DuplicateReservationException dre){
			System.out.println(dre.getMessage());
		}
		return Optional.ofNullable(reservation);
	}

	@Override
	public Customer findCustomer(String email) throws NonExistingCustomerException {
		Email tempEmail = new Email(email);
		Customer customer;
		try{
			customer = customers.getCustomer(tempEmail);
		}
		catch(NonExistingCustomerException e){
			throw new NonExistingCustomerException("The customer does not exist with that email");
		}
		return customer;
	}

	@Override
	public List<Reservation> findReservations(Customer customer) {
		return reservations.getReservations(customer);
	}

	@Override
	public Customer registerCustomer(String firstName, String lastName, String email)
			throws DuplicateCustomerException {
		Email tempEmail = new Email(email);
		Customer tempCustomer = new DawsonCustomer(firstName, lastName, tempEmail);
		tempCustomer.setCreditCard(null);
		try{
			customers.add(tempCustomer);
		}
		catch (DuplicateCustomerException e){
			throw new DuplicateCustomerException(e.getMessage());
		}
		return tempCustomer;
	}

	@Override
	public Customer updateCreditCard(String email, String cardType, String cardnumber)
			throws NonExistingCustomerException {
		CreditCard card = factory.getCard(cardType, cardnumber);
		Email emailOBJ = new Email(email);
		try{
			customers.update(emailOBJ, card);
		}
		catch(NonExistingCustomerException e){
			throw new NonExistingCustomerException(e.getMessage());
		}
		return customers.getCustomer(emailOBJ);
	}
}
