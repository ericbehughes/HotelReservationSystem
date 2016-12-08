package group187.hotel.ui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.Hotel;

public class TextView implements Observer {
	public TextView(Observable model){
		model.addObserver(this);
	}
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Optional<?>){
			Reservation reservation = ((Optional<Reservation>)arg).get();
			System.out.println("Your reservation is for:");
			System.out.println("\t"+reservation.getCustomer().getName().toString());
			System.out.println("\tat email: " + reservation.getCustomer().getEmail().toString());
			System.out.println(("\tcheck in at: " + reservation.getCheckInDate()));
			System.out.println(("\tcheck out at: "+ reservation.getCheckOutDate()));
			System.out.println(("\tyour room: " + reservation.getRoom().getRoomNumber() +
					" is a " + reservation.getRoom().getRoomType() + " on the " + 
					reservation.getRoom().getFloor() + " floor"));
		}
		
		else if (arg instanceof Customer){
			System.out.println("Customer information: ");
			System.out.println("Name: " + ((Customer)arg).getName().toString());
			System.out.println(("Email: " + ((Customer)arg).getEmail().toString()));
			CreditCard card = ((Customer)arg).getCreditCard().get();
			if (card == null)
				System.out.println(("No credit card on file."));
			else
				System.out.println(("Credit card: " + card.getType() + " " + card.getNumber()));
		}
		
		else if (arg instanceof List<?>){
			List<Reservation> reservations = (List<Reservation>)arg;
			Customer customer = reservations.get(0).getCustomer();
			update(o, customer);
			System.out.println();
			System.out.println("You have " + reservations.size() + " reservation(s) on file: ");
			for (Reservation reservation : reservations)
				System.out.println(reservation.toString());
		}
		
	}

}
