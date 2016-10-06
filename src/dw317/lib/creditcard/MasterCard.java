/*
 * 
 */
package dw317.lib.creditcard;

// TODO: Auto-generated Javadoc
/**
 * The Class MasterCard.
 */
public class MasterCard extends AbstractCreditCard{
	
	/** The Constant serializeVersionUID. */
	private static final long serializeVersionUID = 42031768871L;
	
	/**
	 * Instantiates a new master card.
	 *
	 * @param number the number
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public MasterCard(String number) throws IllegalArgumentException {
		super(CardType.MASTERCARD, validateNumber(number));
	}
	
	/**
	 * This method evaluates the two first numbers of the entered credit card, to see if it complies
	 * with a valid MasterCard. This is the first check to see if the card works.
	 * 
	 * @param number - The number of the credit card
	 * @return boolean - The value that determines if the two starting digits of the card comply with
	 * the norms of MasterCard
	 */
	private static boolean checkTwoFirstNumbers(String number){
		return number.indexOf("50") == 0|| number.indexOf("51",0) == 0 || number.indexOf("52",0) == 0 || 
			   number.indexOf("53",0) == 0 || number.indexOf("54",0) == 0 || number.indexOf("55",0) == 0;
	}
	
	/**
	 * Validates the number to check if it's a valid credit card number. The validation scheme
	 * is that the first number must be a 5, and then follow up with any number from 1 - 5, and the number
	 * must be 16 digits long.
	 *
	 * @param number the number
	 * @return The same number value if it passes the validation
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	private static String validateNumber(String number) throws IllegalArgumentException {
		if(number.length() == 16 && checkTwoFirstNumbers(number))
			return number;
		throw new IllegalArgumentException();
	}
	
}
