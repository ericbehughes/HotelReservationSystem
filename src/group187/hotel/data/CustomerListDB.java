package group187.hotel.data;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;

public class CustomerListDB implements CustomerDAO{
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
	
	public CustomerListDB (ListPersistenceObject listPersistenceObject){
		// Assign both vars
		this.listPersistenceObject = listPersistenceObject;
		this.factory = null;
		// Fill the database
		database = listPersistenceObject.getCustomerDatabase();
	}
	
	public CustomerListDB (ListPersistenceObject listPersistenceObject, HotelFactory factory){
		// Assign the vars
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		// Fill the database
		database = listPersistenceObject.getCustomerDatabase();
	}
	
	@Override
	public void add(Customer cust) throws DuplicateCustomerException {
		// Create a copy of the customer and add to the database
		Customer custCopy = cust;
		database.add(custCopy);
		
	}
	@Override
	public void disconnect() throws IOException {
		//
		
	}
	@Override
	public Customer getCustomer(Email email) throws NonExistingCustomerException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(Email email, CreditCard card) throws NonExistingCustomerException {
		// TODO Auto-generated method stub
		
	}
	
	private int binarySearch(Customer customer){
		Customer custObj; // Customer var to hold objects from database
		Email email = customer.getEmail(), // Email from customer passed through parameter
			  emailObj; // Email var to hold objects from database
		int index = -1, // Index where to add new customer, -1 if duplicate
			startIndex = 0, // Start index where to start searching
		    endIndex = database.size(); // End index where to stop searching
		Iterator iterator = database.iterator(); // Iterator object to iterate through list
		while (iterator.hasNext()){
			custObj = (Customer)iterator.next();
			emailObj = custObj.getEmail();
			while (endIndex >= startIndex){
				int  midIndex = (endIndex+startIndex) / 2;
				
				if (database.get(midIndex).getEmail().equals(email))
					return index;
				
				else if (database.get(midIndex).getEmail().compareTo(email) < 0)
					endIndex = midIndex;
				
				else if (database.get(midIndex).getEmail().compareTo(email) > 0)
					startIndex = midIndex;
				
				
			}
			
 		}
		return -1;
		
	}
}
