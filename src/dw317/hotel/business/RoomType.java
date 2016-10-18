/*
 * 
 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
	
	public static RoomType checkRoomType(String inputFromFile)
	{
		RoomType roomType = RoomType.NORMAL;
		if (inputFromFile.equalsIgnoreCase(RoomType.NORMAL.toString()))
			roomType=RoomType.NORMAL;
		else if (inputFromFile.equalsIgnoreCase(RoomType.SUITE.toString()))
			roomType=RoomType.SUITE;
		else if (inputFromFile.equalsIgnoreCase(RoomType.PENTHOUSE.toString()))
			roomType=RoomType.PENTHOUSE;
		
		return roomType;
	}
}
