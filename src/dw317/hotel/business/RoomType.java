package dw317.hotel.business;

// TODO: Auto-generated Javadoc
/**
 * The Enum RoomType.
 */
public enum RoomType {
	
	/** The normal. */
	NORMAL, 
 /** The suite. */
 SUITE, 
 /** The penthouse. */
 PENTHOUSE;
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
}
