package dw317.lib.creditcard;

// TODO: Auto-generated Javadoc
/**
 * The Class Amex.
 */
public class Amex extends AbstractCreditCard {
	
	/** The Constant serializeVersionUID. */
	private static final long serializeVersionUID = 42031768871L;
	
	/**
	 * Instantiates a new amex.
	 *
	 * @param number the number
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public Amex(String number) throws IllegalArgumentException {
		super(CardType.AMEX, validateNumber(number));
	}
	
	/**
	 * Check two first numbers.
	 *
	 * @param number the number
	 * @return true, if successful
	 */
	private static boolean checkTwoFirstNumbers(String number){
		return number.indexOf("34") == 0 || number.indexOf("37") == 0;
	}
	
	/**
	 * Validate the number to see if it's a valid American Express number. The validation scheme is that 
	 * the first two numbers must be a 34 or 37, and the card must have 15 digits.
	 *
	 * @param number the number
	 * @return The same number if it passes validation
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	private static String validateNumber(String number){
		if(number.length() == 15 && checkTwoFirstNumbers(number))
			return number;
		throw new IllegalArgumentException();
	}
}