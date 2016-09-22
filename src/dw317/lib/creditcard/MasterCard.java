package dw317.lib.creditcard;

public class MasterCard extends AbstractCreditCard{
	private static final long serializeVersionUID = 42031768871L;
	
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
		String checkNum = number.substring(0, 2);
		if (checkNum.equals("50") || checkNum.equals("51") || checkNum.equals("52") || checkNum.equals("53") || checkNum.equals("54") || checkNum.equals("55"))
			return true;
		return false;
	}
	
	/**
	 * Validates the number to check if it's a valid credit card number. The validation scheme
	 * is that the first number must be a 5, and then follow up with any number from 1 - 5, and the number
	 * must be 16 digits long.
	 * 
	 * @param number
	 * @return The same number value if it passes the validation
	 * @throws IllegalArgumentException
	 */
	private static String validateNumber(String number) throws IllegalArgumentException {
		if(number.length() == 16 && checkTwoFirstNumbers(number))
			return number;
		throw new IllegalArgumentException();
	}
	
}
