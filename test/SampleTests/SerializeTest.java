package SampleTests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;

public class SerializeTest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 42031768871L;

	public static void main(String[] args) {
		System.out.println("Run");
		Email email = new Email("abc@test.com");
		Name name = new Name("John", "Smith");
		CreditCard amex = new Amex("374616906032009");
		DawsonCustomer cust = new DawsonCustomer(name.getFirstName(), name.getLastName(), email, Optional.of(amex));
		DawsonRoom room = new DawsonRoom(707, RoomType.SUITE);
		DawsonReservation reservation = new DawsonReservation(cust, room, 2016, 9, 1, 2016, 9, 10);
		DawsonCustomer c2 = null;
		//Begin serialization
		
		try {
			// Serialize
			FileOutputStream fOut = new FileOutputStream("obj.ser");
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(reservation);
			out.close();
			fOut.close();
			System.out.println("Done Serializing");
			// Deserialize
			ObjectInputStream objS = new ObjectInputStream(new FileInputStream("obj.ser"));
			DawsonReservation resObj = (DawsonReservation)objS.readObject();
			System.out.println("Done");
			System.out.println(resObj.toString());
			c2 = (DawsonCustomer)resObj.getCustomer();
			//System.out.println(resObj.getCustomer().getCreditCard().get().getClass());
			//System.out.println(resObj.getCustomer().getCreditCard());
			
		} catch (IOException  | ClassNotFoundException e) {
			System.out.println(e.getMessage() + " " + e.getCause());
		}
		
			System.out.println(c2.getCreditCard().isPresent());

	}

}
