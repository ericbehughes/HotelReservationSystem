	package group187.hotel.data;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.business.DawsonHotelFactory;
import group187.util.ListUtilities;

public class CustomerListDB implements CustomerDAO{
	

	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
	
	public CustomerListDB (ListPersistenceObject listPersistenceObject){
		if (listPersistenceObject == null)
			throw new IllegalArgumentException("CustomerListDB - The parameters cannot be null");
		// Assign both vars
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
		// Fill the database+
		this.database = listPersistenceObject.getCustomerDatabase();
	}
	
	public CustomerListDB (ListPersistenceObject listPersistenceObject, HotelFactory factory){
		if (listPersistenceObject == null || factory == null)
			throw new IllegalArgumentException("CustomerListDB - The parameters cannot be null");
		// Assign the vars
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		// Fill the database
		database = listPersistenceObject.getCustomerDatabase();
	}
	/*
	Number of customers in database: 8
	raj@king.ru*Raj*Wong*visa*4556737586899855
	joe.mancini@mail.me*Joe*Mancini**
	*/
	@Override
	public String toString() {
		System.out.println("Number of customers in database: " + database.size() + "\n");
				for (Customer arr: database)
					System.out.println(arr.toString());
		return "";
		
		
	}
	
	@Override
	public void add(Customer cust) throws DuplicateCustomerException {
		for (int i = 0; i < database.size(); i++)
			if (cust.equals(database.get(i)))
				throw new DuplicateCustomerException("CustomerListDB - add - The customer: " + cust.toString() + " is already in the list");
		// Create a copy of the customer and add to the database
		Customer custCopy = factory.getCustomerInstance(cust.getName().getFirstName(), cust.getName().getLastName()
				, cust.getEmail());
		custCopy.setCreditCard(custCopy.getCreditCard());
		// Use bianry search to find proper index
		int index = binarySearch(custCopy);
		// Add customer
		if (index >= database.size())
			database.add(custCopy);
		else
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
		if (customerIndex == 0 && (!(database.get(customerIndex).getEmail().equals(email))))
			throw new NonExistingCustomerException("The customer does not exist for the email: ",email );
		Customer cust = database.get(customerIndex);// Get the customer
		if (!cust.getEmail().equals(email))
			throw new NonExistingCustomerException("The customer does not exist for the email: ",email );
		// Use factory to make a copy of the customer
		Customer custObjCopy = factory.getCustomerInstance(cust.getName().getFirstName(), cust.getName().getLastName()
				, cust.getEmail());
		custObjCopy.setCreditCard(cust.getCreditCard());
		return custObjCopy;
	}
	@Override
	public void update(Email email, CreditCard card) throws NonExistingCustomerException {
		int index = binarySearch(email); // Search for existing customer or find index to keep order
		if (index == 0) // 0 also gets returned if no customer is found in the database
			if (!database.get(index).getEmail().equals(email)) // Check to see if the customer at index 0 is the intended one
				throw new NonExistingCustomerException("The customer was not found in the database", email);
		Customer temp = database.get(index); // Create a temp object of the customer we want to update
		System.out.println("Current Card: " + temp.getCreditCard().toString());
		temp.setCreditCard(Optional.ofNullable(card)); // Sets the credit card
		System.out.println("New card: " + temp.getCreditCard().toString());
		database.add(index, temp); // Updates the customer at index
		database.remove(index+1); // Delete the old reference
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
		while (endIndex > startIndex){
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
