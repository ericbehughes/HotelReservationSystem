/*
 * 
 */
package dw317.lib.creditcard;
import java.io.Serializable;
// TODO: Auto-generated Javadoc

/**
 * The Interface CreditCard.
 */
public interface CreditCard extends Serializable {
       
       /**
        * Gets the number.
        *
        * @return the number
        */
       String getNumber();
       
       /**
        * Gets the type.
        *
        * @return the type
        */
       CardType getType();
       
       /**
        * Gets the single instance of CreditCard.
        *
        * @param type the type
        * @param number the number
        * @return single instance of CreditCard
        */
       //Credit card factory method based on the type
       public static CreditCard getInstance(CardType type,String number) {
               CreditCard card = null;
               switch (type) {
               case MASTERCARD:
                      card =  new MasterCard(number);
                      break;
               case VISA:
                      card =  new Visa(number);
                      break;
               case AMEX:
                   card = new Amex(number);
                   break;
               }
               return card;
       }
       
       /**
        * The Enum CardType.
        */
       public enum CardType {
               
               /** The mastercard. */
               MASTERCARD, 
 /** The visa. */
 VISA, 
 /** The amex. */
 AMEX;
    	   		
               /* (non-Javadoc)
                * @see java.lang.Enum#toString()
                */
               public String toString() {
                      return this.name().toLowerCase();
               }
       }
}
