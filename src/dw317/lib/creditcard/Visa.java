package dw317.lib.creditcard;

public class Visa extends AbstractCreditCard {
	private static final long serializeVersionUID = 42031768871L;
	
	public Visa(String number) throws IllegalArgumentException {
		super(CardType.VISA, validateNumber(number));
	}
	
	/**
	 * Validate the number to see if it's a valid Visa number. The validation scheme is that 
	 * the first number must be a 4, and the card must have 16 or 13 digits.
	 * 
	 * @param number
	 * @return The same number if it passes validation
	 * @throws IllegalArgumentException
	 */
	private static String validateNumber(String number) throws IllegalArgumentException {
		if(number.length() == 16 || number.length() == 13 && number.substring(0,1).equals("4"))
			return number;
		throw new IllegalArgumentException();
	}
}