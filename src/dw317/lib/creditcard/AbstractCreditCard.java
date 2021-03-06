package dw317.lib.creditcard;

public abstract class AbstractCreditCard implements CreditCard {
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
		
		return false;
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
}
