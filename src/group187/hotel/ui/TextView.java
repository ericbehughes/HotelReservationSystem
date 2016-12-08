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
			Optional<Reservation> reservation = ((Optional<Reservation>)arg);
			if (reservation.isPresent()){
				System.out.println("Your reservation is for:");
				System.out.println("\t"+reservation.get().getCustomer().getName().toString());
				System.out.println("\tat email: " + reservation.get().getCustomer().getEmail().toString());
				System.out.println(("\tcheck in at: " + reservation.get().getCheckInDate()));
				System.out.println(("\tcheck out at: "+ reservation.get().getCheckOutDate()));
				System.out.println(("\tyour room: " + reservation.get().getRoom().getRoomNumber() +
						" is a " + reservation.get().getRoom().getRoomType() + " on the " + 
						reservation.get().getRoom().getFloor() + " floor"));
			}
		}
		
		else if (arg instanceof Customer){
			System.out.println("Customer information: ");
			System.out.println("Name: " + ((Customer)arg).getName().toString());
			System.out.println(("Email: " + ((Customer)arg).getEmail().toString()));
			Optional<CreditCard> card = ((Customer)arg).getCreditCard();
			if (!card.isPresent())
				System.out.println(("No credit card on file."));
			else
				System.out.println(("Credit card: " + card.get().getType() + " " + card.get().getNumber()));
		}
		
		else if (arg instanceof List<?>){
			List<Reservation> reservations = (List<Reservation>)arg;
			if (reservations.size() > 0){
				Customer customer = reservations.get(0).getCustomer();
				update(o, customer);
				System.out.println();
				System.out.println("You have " + reservations.size() + " reservation(s) on file: ");
				for (Reservation reservation : reservations){
					System.out.println("Room: " + reservation.getRoom().getRoomNumber());
					System.out.println("Room type: " + reservation.getRoom().getRoomType());
					System.out.println("Check in year: " + reservation.getCheckInDate().getYear());
					System.out.println("Check in month: " + reservation.getCheckInDate().getMonthValue());
					System.out.println("Check in day: " + reservation.getCheckInDate().getDayOfMonth());
					System.out.println("Check out year: " + reservation.getCheckOutDate().getYear());
					System.out.println("Check out month: " + reservation.getCheckOutDate().getMonthValue());
					System.out.println("Check out day: " + reservation.getCheckOutDate().getDayOfMonth());
				}
			}
			else{
				System.out.println();
				System.out.println("You have " + reservations.size() + " reservation(s) on file: ");
			}
	    }
				
		}
		
}

