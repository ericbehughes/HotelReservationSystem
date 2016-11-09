package group187.hotel.data;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonCustomer;
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

			customer1.setCreditCard(cardTest1);

			Email email2 = new Email("fhse@zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz.com");
			Name name2 = new Name("something", "something");
			Optional<CreditCard> cardTest2 = Optional.of(new Amex("374616906032009"));
			DawsonCustomer customer2 = new DawsonCustomer(name2.getFirstName(), name2.getLastName(), email2, cardTest2);
			customer2.setCreditCard(cardTest2);

			System.out.println("Running binary search");
			int index = binarySearch(customer2);
			System.out.println("Previous email: " + database.get(index - 1).getEmail().toString());
			System.out.println("Current email: " + customer2.getEmail().toString());
			System.out.println("Next email: " + database.get(index + 1).getEmail().toString());
	}
	
	@SuppressWarnings("rawtypes")
	public static int binarySearch(Customer customer){
		listPersistenceObject = new SequentialTextFileList("datafiles\\database\\rooms.txt" , "datafiles\\database\\customers.txt", "datafiles\\database\\reservations.txt");
		database = listPersistenceObject.getCustomerDatabase();
		database.toArray(new Comparable[database.size()]);
		ListUtilities.sort(database.toArray(new Comparable[database.size()]));

		Customer custObj; // Customer var to hold objects from database
		Email email = customer.getEmail(), // Email from customer passed through parameter
			  emailObj; // Email var to hold objects from database
		int index = -1, // Index where to add new customer, -1 if duplicate
			startIndex = 0, // Start index where to start searching
		    endIndex = database.size(); // End index where to stop searching
			while (endIndex >= startIndex){
				int  midIndex = (endIndex+startIndex) / 2;
				Email temp = database.get(midIndex).getEmail();
				if (temp.compareTo(email) < 0){			
					startIndex = midIndex+1;
				}
	
				else if (temp.compareTo(email) > 0){
					endIndex = midIndex -1;
				}
				
//				if ((database.get(midIndex - 1).getEmail().compareTo(email) < 0) && (database.get(midIndex + 1).getEmail().compareTo(email) > 0))
//					System.out.println("The index where the customer should go is: " + midIndex);
				
			}
			return startIndex;

	}
}
			
			/*private int binarySearch(List<Integer> list, Integer value) {
		        int low = 0;
		        int high = list.size() - 1;

		        while (low <= high) {
		            int mid = (low + high) / 2;
		            Integer midVal = list.get(mid);
		            int cmp = midVal.compareTo(value);

		            if (cmp < 0)
		                low = mid + 1;
		            else if (cmp > 0)
		                high = mid - 1;
		            else
		                return mid;
		        }
		        return low;
		    }
		    **/
		
			
 

