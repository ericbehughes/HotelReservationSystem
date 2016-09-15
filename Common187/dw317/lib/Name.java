package dw317.lib;

/*
 * The Name class must validate the first and last names 
 * (refer to Lab 2 where you validated the Address class).
 *  Both first and last names must each contain at least 2
 *   letters. In addition to letters, the characters 
 *   � (apostrophe), - (hyphen) and space between two letters are 
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
		if(isValidString(fN, 2))
			this.firstName = fN;
		if(isValidString(lN, 2))
			this.lastName = lN;
	}
	
	// both first and last name must be >= 2
	// characters, apostrophe hyphen and space between two letters are valid
	
	/**
	 * 
	 * @param name
	 * @param type 0 equates to a name, 1 equates to being an email address to validate
	 * @return
	 */
	public static boolean isValidString(String string, int minLength)
	{
		
		if (string.length() < minLength)
			return false;
		if (minLength == 1)
			if (string.length() > 32)
				return false;
		
		// cant have spaces on ends
		// must have 2 letters minimum 
		int space = string.indexOf(" ");
		int apostrophe = string.indexOf("'");
		int hyphen = string.indexOf('-');
		int length = string.length()-1;
		if (space == 0 || space == length || apostrophe == 0 || apostrophe == length || hyphen == 0 || hyphen == length)
			return false;
		
		return true;
	}
			
		
		
	}

