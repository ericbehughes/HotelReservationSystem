/*
 * 
 */
package dw317.lib.test;

import dw317.lib.Name;

// TODO: Auto-generated Javadoc
/**
 * The Class NameTest. ======= import dw317.lib.Name;
 * 
 * /**
 * 
 * @author Jonathan Depaz (Jepaz - Github)
 *
 * 
 */
public class NameTest {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
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
		System.out.println("l"+Name.isValidString("l", 2));
		System.out.println("1-*&^#36734"+Name.isValidString("1-*&^#36734", 2));
		System.out.println(Name.isValidString("Dav1D", 2));
		System.out.println(Name.isValidString("Petawwwaaaa", 2));
		System.out.println(Name.isValidString("kdhjg2y3tr7i", 2));
		System.out.println(Name.isValidString("eric_joe?", 2));
		System.out.println(Name.isValidString("jOe", 2));
		// Create object to test bad var's
		try {
			name = new Name(firstName, lastName);
			System.out.println("-----------Incorrect variables-----------");
			System.out.println("First name:\t" + name.getFirstName());
			System.out.println("Last name:\t" + name.getLastName());
			System.out.println("Full name:\t" + name.toString());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

}
