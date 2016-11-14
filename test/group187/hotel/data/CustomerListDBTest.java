package group187.hotel.data;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonCustomer;
import group187.hotel.business.DawsonHotelFactory;
import group187.reserv.DawsonHotelFactoryTest;
import group187.util.ListUtilities;

public class CustomerListDBTest {
	public static List<Customer> database;
	public static ListPersistenceObject listPersistenceObject;
	
	public static void main(String[] args) {
		System.out.println("test");
			Email email1 = new Email("zhu@abc.com");
			Name name = new Name("eric", "hughes");
			Optional<CreditCard> cardTest1 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer1 = new DawsonCustomer(name.getFirstName(), name.getLastName(), email1, cardTest1);
			CreditCard cardTest3 = new Amex("349997755621171");
			customer1.setCreditCard(cardTest1);

			Email email2 = new Email("fhssdasdasdasse@zzzjjvtrcikjzuyyufzz.com");
			Name name2 = new Name("something", "something");
			Optional<CreditCard> cardTest2 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer2 = new DawsonCustomer(name2.getFirstName(), name2.getLastName(), email2, cardTest2);
			customer2.setCreditCard(cardTest2);
			String roomFilename = "datafiles/database/rooms.txt";
			String customerFilename = "datafiles/database/customers.txt";
			String reservationFilename = "datafiles/database/reservations.txt";
			
			SequentialTextFileList obj = new SequentialTextFileList(roomFilename, customerFilename, reservationFilename);
			DawsonHotelFactory factory = DawsonHotelFactory.DAWSON;
			CustomerListDB cDB = new CustomerListDB(obj, factory);
			
			try {
				cDB.add(customer2);
				cDB.update(email2, cardTest3);
				System.out.println(cDB.toString());
				
				cDB.disconnect();
			} catch (DuplicateCustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException | NonExistingCustomerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			
		
			
		
	}
	
	
}
			
		
		
			
 

