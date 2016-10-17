/*
 * 
 */
package dw317.lib.creditcard;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractCreditCard.
 */
public abstract class AbstractCreditCard implements CreditCard {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 42031768871L;

	/** The cardtype. */
	private final CardType cardtype;

	/** The number. */
	private final String number;

	/**
	 * Instantiates a new abstract credit card.
	 *
	 * @param cardtype
	 *            the cardtype
	 * @param number
	 *            the number
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public AbstractCreditCard(CardType cardtype, String number) throws IllegalArgumentException {
		this.cardtype = cardtype;
		this.number = validateLuhnAlgorithm(number);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardtype == null) ? 0 : cardtype.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractCreditCard)) {
			return false;
		}
		AbstractCreditCard other = (AbstractCreditCard) obj;
		if (cardtype != other.cardtype) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the number.
	 *
	 * @return number - The numbers of the credit card
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Gets the type.
	 *
	 * @return cardtype - The type of credit card
	 */
	public CardType getType() {
		return cardtype;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getType() + "*" + this.getNumber();
	}

	/**
	 * Validates the credit card number that is passed as a parameter according
	 * to the luhn algorithm.
	 *
	 * @param cardNumber
	 *            the card number
	 * @return the string
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	private String validateLuhnAlgorithm(String cardNumber) throws IllegalArgumentException {
		int cardSize = cardNumber.length(), total = 0;
		long[] cardArr = new long[cardSize];
		// Parse the string into an int array for easy access to digits
		// Possibly able to cast instead of substring, ask prof
		try {
			// check if card size is appropriate
			if (cardSize < 15 || cardSize >= 18)
				throw new IllegalArgumentException("The card number is not the right size");
			// check if card number contains letters
			for (int i = 0; i < cardSize; i++)
				if (cardNumber.charAt(i) < 48 || cardNumber.charAt(i) > 57)
					throw new IllegalArgumentException("The card number cannot contain letters");
			long cardNum = Long.parseLong(cardNumber);
			// extract digits and make long array
			for (int i = cardSize - 1; i >= 0; i--) {
				cardArr[i] = cardNum % 10;
				cardNum = cardNum / 10;
			}
			// Doubles every second number and subtracts 9 if necessary
			for (int i = cardSize - 2; i >= 0; i = i - 2) {
				cardArr[i] = cardArr[i] * 2;
				if (cardArr[i] > 9)
					cardArr[i] = cardArr[i] - 9;
			}
			// Sums all the digits in the card number
			for (int i = 0; i < cardSize; i++)
				total += cardArr[i];

			if (total % 10 == 0)
				return cardNumber;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		throw new IllegalArgumentException("The card number is not valid");
	}
}
