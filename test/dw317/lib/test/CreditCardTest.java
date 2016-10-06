package dw317.lib.test;

import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.MasterCard;
import dw317.lib.creditcard.Visa;

public class CreditCardTest {

	public static void main(String[] args) {

	try {	
			//Create a valid Amex Object
			CreditCard amex = new Amex("374616906032009");
			System.out.println("You've entered an " + amex.getType() + " card");
			CreditCard amex2 = CreditCard.getInstance(amex.getType(), amex.getNumber());
			System.out.println("This is an instance of Amex: " + (amex2 instanceof Amex));
			//Create an invalid Amex Object
			CreditCard amex3 = new Amex("374616906032009");
		}
	catch (IllegalArgumentException e){
			System.out.println("The card type entered is not of specified type");
			System.out.println("");
	}
	
	try {
			//Create a valid Visa Object
			CreditCard visa = new Visa("4538018341243017");
			System.out.println("You've entered an " + visa.getType() + " card");
			CreditCard visa2 = CreditCard.getInstance(visa.getType(), visa.getNumber());
			System.out.println("This is an instance of Visa: " + (visa2 instanceof Visa));
			//Create an invalid Visa object
			CreditCard visa3 = new Visa("4538018341243017");
		}
	catch (IllegalArgumentException e){
			System.out.println("The card type entered is not of specified type");
			System.out.println("");
	}
	
	try {
			//Create a valid MasterCard Object
			CreditCard mastercard = new MasterCard("5524890008827073");
			System.out.println("You've entered an " + mastercard.getType() + " card");
			CreditCard mastercard2 = CreditCard.getInstance(mastercard.getType(), mastercard.getNumber());
			System.out.println("This is an instance of MasterCard: " + (mastercard2 instanceof MasterCard));
			//Create an invalid MasterCard Object 
			CreditCard mastercard3 = new MasterCard("5424890008gg827073");
		}
	catch (IllegalArgumentException e){
			System.out.println("The card type entered is not of specified type");
			System.out.println("");
	}
	
	
	

	}

}
