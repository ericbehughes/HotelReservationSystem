package dw317.hotel.business.interfaces;
import java.io.Serializable;
import java.util.Optional;

import com.sun.xml.internal.bind.v2.runtime.Name;

import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;

public interface Customer extends Comparable<Customer>, Serializable {
	
	public Name getName(); // deep copy
	
	public Email getEmail(); // not deep copy as emails are not immutable
	
	public Optional<CreditCard> getCreditCard();
	
	public void setCreditCard(Optional<CreditCard> card);
}
