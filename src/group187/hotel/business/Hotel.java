package group187.hotel.business;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import dw317.hotel.business.RoomType;
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
import dw317.lib.creditcard.CreditCard.CardType;

public class Hotel extends Observable implements HotelManager {
	private final HotelFactory factory;
	private final CustomerDAO customers;
	private final ReservationDAO reservations;
	private final RoomDAO rooms;
	private static final long serialVersionUID = 42031768871L;
	
	public Hotel (HotelFactory factory, RoomDAO rooms, CustomerDAO customers, ReservationDAO reservations){
		this.factory = factory;
		this.customers = customers;
		this.reservations = reservations;
		this.rooms = rooms;
	}

	@Override
	public void cancelReservation(Reservation reservation) throws NonExistingReservationException {
		reservations.cancel(reservation);
		
	}

	@Override
	public void closeHotel() throws IOException {
		// TODO Auto-generated method stub
		customers.disconnect();
		reservations.disconnect();
		
	}

	@Override
	public Optional<Reservation> createReservation(Customer customer, LocalDate checkin, LocalDate checkout,
			RoomType roomType) {
		DawsonHotelAllocationPolicy allocationPolicy = new DawsonHotelAllocationPolicy(reservations);
		Optional<Room> room = allocationPolicy.getAvailableRoom(checkin, checkout, roomType);
		Reservation reservation =  new DawsonReservation(customer, room.get(), checkin.getYear(), checkin.getMonthValue(),
				checkin.getDayOfMonth(), checkout.getYear(), checkout.getMonthValue(), checkout.getDayOfMonth());
		try {
		reservations.add(reservation);
		}
		catch (DuplicateReservationException dre){
			System.out.println(dre.getMessage());
		}
		return Optional.of(reservation);
	}

	@Override
	public Customer findCustomer(String email) throws NonExistingCustomerException {
		Email tempEmail = new Email(email);
		Customer customer = customers.getCustomer(tempEmail);
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
		Customer tempCustomer = new DawsonCustomer(firstName, lastName, tempEmail, null);
		customers.add(tempCustomer);
		return tempCustomer;
	}

	@Override
	public Customer updateCreditCard(String email, String cardType, String cardnumber)
			throws NonExistingCustomerException {
		String cardTypeTemp = cardType.toLowerCase();
		
		if (cardTypeTemp != "amex" && cardTypeTemp != "mastercard" && cardTypeTemp != "visa")
			throw new IllegalArgumentException("The card type has to be either visa, mastercard or amex");
		CreditCard card = null;
		Email emailOBJ = new Email(email);
		

		if (cardTypeTemp.equals("amex"))
			card = new Amex(cardnumber);
		
		else if (cardTypeTemp.equals("mastercard"))
			card = new MasterCard(cardnumber);

		
		else if (cardTypeTemp.equals("visa"))
			card = new Visa(cardnumber);
		
		
		customers.update(emailOBJ, card);
		return customers.getCustomer(emailOBJ);
	}
}
