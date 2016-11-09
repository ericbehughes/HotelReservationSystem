package group187.hotel.data;

import java.io.IOException;
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
}
