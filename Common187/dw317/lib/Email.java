package dw317.lib;

import java.io.Reader;
import java.io.Serializable;

public class Email implements Serializable, Comparable<Email> {
	private static final long serialVersionUID = 42031768871L;
	private final String  address;
	
	public Email(String address){
		this.address = address;
	}
	
	
	
	public String getAddress() {
		return address;
	}
	
	public String getUserId() {
		return getAddress().split("@")[0];
	}
	
	public String getHost() {
		return getAddress().split("@")[1];
	}
	
	@Override
	public boolean equals(Object object) {
		if(this == object)
			return true;
		return false;
	}
	
	/**
	@Override
	public String getNumber() {
		
		return null;
	}
	
    @Override
  	public Scheme getType()
	{}
	*/
	
	@Override
	public String toString() {
		return getAddress();
	}
	
	public boolean validateEmail(String email) throws IllegalArgumentException{
		//dot cannot be first or last character of userid and no consecutive dots
		//can't have hyphen as first or last either
		String[] hosts = hostSplit(getHost());
		if(checkLength(getUserId()))
			return false;
		if(!Name.isValidString(getUserId(), 1));
			return false;
		for(int i = 0 ; i < hosts.length ; i++){
			if(checkLength(hosts[i]))
				return false;
			if(!Name.isValidString(hosts[i], 1))
				return false;
		}
		
		
	
	}
	
	private static String[] hostSplit(String host){
		return host.split(".");
	}
	
	private static boolean checkLength(String input) {
		if(input.length() > 1 || input.length() < 32)
			return false;
		return true;
	}
	
}