package dw317.lib.creditcard;

public class Amex extends AbstractCreditCard {
	private static final long serializeVersionUID = 42031768871L;
	
	public Amex(String number) throws IllegalArgumentException {
		super(cardtype.AMEX, validateNumber(number));
	}
	
	private static boolean checkTwoFirstNumbers(String number){
		String checkNum = number.substring(0, 2);
		if (checkNum.equals("34") || checkNum.equals("37"))
			return true;
		return false;
	}
	
	/**
	 * Validate the number to see if it's a valid American Express number. The validation scheme is that 
	 * the first two numbers must be a 34 or 37, and the card must have 15 digits.
	 * 
	 * @param number
	 * @return The same number if it passes validation
	 * @throws IllegalArgumentException
	 */
	private static String validateNumber(String number){
		if(number.length() == 15 && checkTwoFirstNumbers(number))
			return number;
		throw new IllegalArgumentException();
	}
}
