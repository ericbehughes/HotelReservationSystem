package dw317.lib.creditcard;

public abstract class AbstractCreditCard implements CreditCard{
	private static final long serialVersionUID = 42031768871L;
	private final CardType cardtype;
	private final String number;
	
	public AbstractCreditCard(CardType cardtype, String number) throws IllegalArgumentException{
		
	}
}
