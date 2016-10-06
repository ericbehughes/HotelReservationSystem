package dw317.lib.test;

import dw317.lib.Name;

/**
 * @author Jonathan Depaz (Jepaz - Github)
 *
 */
public class NameTest {

	public static void main(String[] args) {
		String firstName, lastName;
		Name name;
		
		// Initialize variables (variables are correct format)
		firstName = "Joe";
		lastName = "Smith";

		// Create object to test
		name = new Name(firstName, lastName);
		
		// Check if object is correct
		System.out.println("-----------Correct variables-----------");
		System.out.println("First name:\t" + name.getFirstName());
		System.out.println("Last name:\t" + name.getLastName());
		System.out.println("Full name:\t" + name.toString());

		// Initialize variables (variables are incorrect format)
		firstName = "L";
		lastName = "1-*&^#36734";
		
		// Create object to test bad var's 
		try{
			name = new Name(firstName, lastName);
			System.out.println("-----------Incorrect variables-----------");
			System.out.println("First name:\t" + name.getFirstName());
			System.out.println("Last name:\t" + name.getLastName());
			System.out.println("Full name:\t" + name.toString());
		}
		catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}

}
