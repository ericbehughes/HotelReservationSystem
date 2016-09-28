package dw317.hotel.business;

public enum RoomType {
	NORMAL, SUITE, PENTHOUSE;
	
	@Override
	public String toString(){
		return this.name().toLowerCase();
	}
}
