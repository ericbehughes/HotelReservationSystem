/*
 * 
 */
package dw317.lib.creditcard;

// TODO: Auto-generated Javadoc
/**
 * The Class Visa.
 */
public class Visa extends AbstractCreditCard {
	
	/** The Constant serializeVersionUID. */
	private static final long serializeVersionUID = 42031768871L;
	
	/**
	 * Instantiates a new visa.
	 *
	 * @param number the number
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public Visa(String number) throws IllegalArgumentException {
		super(CardType.VISA, validateNumber(number));
	}
	
	/**
	 * Validate the number to see if it's a valid Visa number. The validation scheme is that 
	 * the first number must be a 4, and the card must have 16 or 13 digits.
	 *
	 * @param number the number
	 * @return The same number if it passes validation
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	private static String validateNumber(String number) throws IllegalArgumentException {
		if(number.indexOf("4") == 0 && (number.length() == 16 || number.length() == 13) )
			return number;
		throw new IllegalArgumentException();
	}
}