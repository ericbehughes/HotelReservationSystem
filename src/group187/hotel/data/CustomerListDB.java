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
import group187.util.ListUtilities;

public class CustomerListDB implements CustomerDAO{
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
	
	public CustomerListDB (ListPersistenceObject listPersistenceObject){
		// Assign both vars
		this.listPersistenceObject = listPersistenceObject;
		this.factory = null;
		// Fill the database+
		this.database = listPersistenceObject.getCustomerDatabase();
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
		// Use bianry search to find proper index
		int index = binarySearch(custCopy);
		// Add customer
		database.add(index, custCopy);
		
	}
	
	@Override
	public void disconnect() throws IOException {
		listPersistenceObject.saveCustomerDatabase(database);
		database = null;
	}
	
	@Override
	public Customer getCustomer(Email email) throws NonExistingCustomerException {

		int customerIndex = binarySearch(email);
		Customer custObj = database.get(customerIndex);
		Customer custObjCopy = custObj;
		return custObjCopy;
	}
	@Override
	public void update(Email email, CreditCard card) throws NonExistingCustomerException {
		// TODO Auto-generated method stub
		
	}
	
	private <T> int binarySearch(T o){
		Email emailObj = null;
		if (o instanceof Email)
			emailObj = (Email)o;
		else if (o instanceof Customer)
			emailObj = ((Customer) o).getEmail();
		else
			throw new IllegalArgumentException("CustomerListDB - binarySearch(T o) - The object in the parameter must be either an Email or a Customer");
		
		int startIndex = 0, // Start index where to start searching
		endIndex = database.size(); // End index where to stop searching
		while (endIndex >= startIndex){
			int  midIndex = (endIndex+startIndex) / 2;
			Email temp = database.get(midIndex).getEmail();
			if (temp.compareTo(emailObj) < 0)			
				startIndex = midIndex+1;
				
		else if (temp.compareTo(emailObj) > 0)
				endIndex = midIndex -1;

			else if (temp.equals(emailObj))
				return midIndex;
		}		
		return startIndex;
	}			
	
}
