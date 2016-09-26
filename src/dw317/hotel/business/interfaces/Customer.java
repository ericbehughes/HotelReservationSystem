package dw317.hotel.business.interfaces;
import java.io.Serializable;
import java.util.Optional;
import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;

public interface Customer extends Comparable<Customer>, Serializable {
	
	public static Name getName(){
		// return Name.Copy();
		return null;
	}
	public Email getEmail(){
		//return Emaill
	}
	public  Optional<CreditCard> getCreditCard(){
		//return CreditCard type 
	}
	public static void setCreditCard(Optional<CreditCard> card){
		
	}
}
