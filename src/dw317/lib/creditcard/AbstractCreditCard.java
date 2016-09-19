package dw317.lib.creditcard;

public abstract class AbstractCreditCard implements CreditCard{
	private static final long serialVersionUID = 42031768871L;
	private final CardType cardtype;
	private final String number;
	
	public AbstractCreditCard(CardType cardtype, String number) throws IllegalArgumentException{
		this.cardtype = cardtype;
		this.number = number;
		
	}
	
	/*
	 * Still needs to be worked on. Must check to see that two Cards are identical
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		return true; // changed line 19 to show you how to fetchsss
	}
	/**
	 * @return number - The numbers of the credit card
	 */
	public String getNumber(){
		return number;
	}
	
	/**
	 * @return cardtype - The type of credit card
	 */
	public CardType getType(){
		return cardtype;
	}
	
	/**
	 * @return Concatenated string of card type and numbers
	 */
	public String toString(){
		return this.getType() + "*" + this.getNumber();
	}
	
	private String validateLuhnAlgorithm(String cardNumber) throws IllegalArgumentException
	{
		int cardSize = cardNumber.length(), total = 0;
		int[] cardArr = new int[cardSize];
		String digit = new String();
		// Parse the string into an int array for easy access to digits
		// Possibly able to cast instead of substring, ask prof
		try {
			if (cardSize < 15 || cardSize >= 18)
				throw new IllegalArgumentException("The card number is not the right size")
		}
		
		catch (IllegalArgumentException iae){
			System.out.println(iae.getMessage());
		}
		try {
			for (int i = 0; i < cardSize; i++){
				if (cardNumber.charAt(i) < 48 || cardNumber.charAt(i) > 57)
					throw new IllegalArgumentException("The card number cannot contain letters");
				digit = cardNumber.substring(i,i+1);
				cardArr[i] = Integer.parseInt(digit);
			}
		}
		catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		// Doubles every second number and subtracts 9 if necessary
		for (int i = cardSize - 2; i >= 0; i = i - 2){
			cardArr[i] = cardArr[i]*2;
			if (cardArr[i] > 9)
				cardArr[i] = cardArr[i] - 9;
		}
		// Sums all the digits in the card number
		for (int i = 0; i < cardSize; i++)
			total += cardArr[i];
		
		if (total % 10 == 0)
			return cardNumber;
		throw new IllegalArgumentException("The card number is not valid");
	}
}
