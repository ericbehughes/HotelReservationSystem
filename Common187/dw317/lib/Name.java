package dw317.lib;

/*
 * The Name class must validate the first and last names 
 * (refer to Lab 2 where you validated the Address class).
 *  Both first and last names must each contain at least 2
 *   letters. In addition to letters, the characters 
 *   ‘ (apostrophe), - (hyphen) and space between two letters are 
 *   valid. All validation is case-insensitive.
 * 
 * */

public class Name 
{
	private static final long serialVersionUID = 42031768871L;
	private String firstName = "Bob";
	private String lastName= "6-";
	
	public Name(String fN, String lN)
	{
		this.firstName = fN;
		this.lastName = lN;
	}
	
	// both first and last name must be >= 2
	// characters, apostrophe hyphen and space between two letters are valid
	
	public boolean validateName(String firstName, String lastName)
	{
		if (firstName.length() < 2 || lastName.length() < 2)
			return false;
		
		// cant have spaces on ends
		// must have 2 letters minimum 
		int space = firstName.indexOf(" ");
		int apostrophe = firstName.indexOf("'");
		int hyphen = firstName.indexOf('-');
		int length = firstName.length();
		if (space == 0 || space == length || apostrophe == 0 || apostrophe == length || hyphen == 0 || hyphen == length)
			return false;
		
		space = lastName.indexOf(" ");
		apostrophe = lastName.indexOf("'");
		hyphen = lastName.indexOf('-');
		length = lastName.length();
		if (space == 0 || space == length || apostrophe == 0 || apostrophe == length || hyphen == 0 || hyphen == length){
			return false;
		}
		
	}
			
		
		
	}

