package group187.hotel.data;
 
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
 
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import dw317.lib.Email;
import group187.hotel.business.DawsonHotelFactory;
import group187.hotel.business.DawsonReservation;
import group187.hotel.business.DawsonRoom;
import group187.util.ListUtilities;
 
public class ReservationListDB implements ReservationDAO {
   
    private List<Reservation> database;
    private List<Room> allRooms;
    private final ListPersistenceObject listPersistenceObject;
    private final HotelFactory factory;
    public ReservationListDB (ListPersistenceObject listPersistenceObject)
    {
        if (listPersistenceObject == null)
            throw new IllegalArgumentException("CustomerListDB - The parameters cannot be null");
        this.listPersistenceObject = listPersistenceObject;
        database = listPersistenceObject.getReservationDatabase();
        allRooms = listPersistenceObject.getRoomDatabase();
        factory = DawsonHotelFactory.DAWSON;
    }
    public ReservationListDB (ListPersistenceObject listPersistenceObject,
    HotelFactory factory)
    {
        if (listPersistenceObject == null || factory == null)
            throw new IllegalArgumentException("CustomerListDB - The parameters cannot be null");
        this.listPersistenceObject = listPersistenceObject;
        this.factory = factory;
        database = listPersistenceObject.getReservationDatabase();
        allRooms = listPersistenceObject.getRoomDatabase();
    }
 
 
    @Override
    public void add(Reservation reserv) throws DuplicateReservationException {
        for (int i = 0; i < database.size(); i++)
            if (reserv.equals(database.get(i)))
                throw new DuplicateReservationException("The reservation: " + reserv.toString() + " is already in the list");
        if (factory == null)
            throw new IllegalArgumentException("ReservationListDB - factory cannot be null");
        int index = binarySearch(reserv); // Find the index for the object to keep order
        Reservation reservationObj = factory.getReservationInstance(reserv); // Create a copy of the object
        database.add(index, reservationObj); // Add the copy
		try {
			listPersistenceObject.saveReservationDatabase(database);
		} catch (IOException e) {
			System.out.println("Cannot save the reservation databse to the file");
		}
        }
 
    @Override
    public void disconnect() throws IOException {
        listPersistenceObject.saveReservationDatabase(database); // Save the database to the disk
        database = null; // Make the database null
    }
       
    @Override
    public List<Reservation> getReservations(Customer cust) {
        List<Reservation> reservList = new ArrayList<>(); // Create the list of reservations
        for (int i = 0; i < database.size(); i++){
            DawsonReservation tempReserv = (DawsonReservation)database.get(i); // Get the reservation at index i
            if (tempReserv.getCustomer().equals(cust)) // Check to see if the customer matches the parameter
                reservList.add(tempReserv);  // Add reservation to list
        }
        return reservList;
    }
    @Override
    public void cancel(Reservation reserv) throws NonExistingReservationException {
        DawsonReservation reservCopy = (DawsonReservation)factory.getReservationInstance(reserv); // Create a copy of the reservation to be deleted
        for (int i = 0; i < database.size(); i++){
            DawsonReservation tempReserv = (DawsonReservation)database.get(i); // Get the reservation at index i
            if (tempReserv.equals(reservCopy)) // Check to see if the reservation matches the one to be deleted
                database.remove(i);  // Delete the reservation from the database
           
            else if (!tempReserv.equals(reservCopy))
                throw new NonExistingReservationException("ReservationListDB - Cancel - The reservation does not exist");
        }
    }
    
    @Override
    public List<Room> getReservedRooms(LocalDate checkin, LocalDate checkout) {
        List<Room> reservedRooms = new ArrayList<>();
        if (checkin.isAfter(checkout))
            return reservedRooms;
        
        for (int i = 0; i < database.size(); i++){
            DawsonReservation reservTemp = (DawsonReservation)database.get(i);
            Room roomTemp = reservTemp.getRoom();
            LocalDate roomCheckIn = reservTemp.getCheckInDate(),
                      roomCheckOut = reservTemp.getCheckOutDate();
            if ((roomCheckIn.isBefore(checkout) || roomCheckOut.isAfter(checkin)) &&
                    !(roomCheckIn.isAfter(checkout)|| roomCheckOut.isBefore(checkin))){
            			if (!reservedRooms.contains(roomTemp))
                        reservedRooms.add(roomTemp);
                        
                    }
        }
        System.out.println("-----------------------------------------------");
 
        return reservedRooms;
    }
    
    @Override
    public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout) {
    	List<Room> reservedRooms = getReservedRooms(checkin, checkout);
    	List<Room> freeRooms = new ArrayList<>();
           if (checkin.isAfter(checkout))
               return freeRooms;
           if (reservedRooms.size() == 0)
           		return allRooms;
           System.out.println();
           
          int counter = 0;
        	   
        	   for (int j = 0; j < allRooms.size(); j++){
        		   DawsonRoom currentRoom = (DawsonRoom)allRooms.get(j);
        		   DawsonRoom reservedRoom = (DawsonRoom) reservedRooms.get(counter);
        	    	if(!(currentRoom.equals(reservedRoom))){
        	    			freeRooms.add(allRooms.get(j));
        	    			
        	    	}
        	    	else if (currentRoom.equals(reservedRoom) && counter < reservedRooms.size() - 1)
        	    		counter++;
        	    	else 
        	    		counter = reservedRooms.size() - 1;
                    
           
          }
         
          return freeRooms;
    }
   
    @Override
    public String toString() {
         System.out.println("Number of reservations in database: " + database.size() + "\n");
        for (Reservation arr: database)
            System.out.println(arr.toString());
            return "";
    }
   
    @Override
    public List<Room> getFreeRooms(LocalDate checkin, LocalDate checkout, RoomType roomType) {
    	//List<Room> reservedRooms = getReservedRooms(checkin, checkout);
    	List<Room> freeRooms = getFreeRooms(checkin, checkout);
    	List<Room> freeRoomType = new ArrayList<>();
    	for (Room room : freeRooms){
    		if (room.getRoomType().equals(roomType))
    			freeRoomType.add(room);
    	}
    	return freeRoomType;
    	/**
           if (checkin.isAfter(checkout))
               return freeRooms;
           System.out.println();
           
          int counter = 0;
        	   
        	   for (int j = 0; j < allRooms.size(); j++){
        		   DawsonRoom currentRoom = (DawsonRoom)allRooms.get(j);
        		   DawsonRoom reservedRoom = (DawsonRoom) reservedRooms.get(counter);
        	    	if(!(currentRoom.equals(reservedRoom)) && currentRoom.getRoomType().equals(roomType)){
        	    			freeRooms.add(allRooms.get(j));
        	    			
        	    	}
        	    	else if (currentRoom.equals(reservedRoom) && counter < reservedRooms.size() - 1)
        	    		counter++;
        	    	else 
        	    		counter = reservedRooms.size() - 1;
        	    	
          }
         */
    }
 
 
   
   
   
   
    @Override
    public void clearAllPast() {
        LocalDate date;
        date = LocalDate.now();
        for (int i = 0; i < database.size(); i++){
            if (database.get(i).getCheckOutDate().isBefore(date)){
                System.out.println("The checkout date: " +database.get(i).getCheckOutDate() + " is before the current date: " + date);
                database.remove(i);
            }
        }
       
    }
 
   
    private <T> int binarySearch(T o){
        DawsonReservation reservObj = null;
        if (o instanceof Reservation)
            reservObj = (DawsonReservation)o;
        else
            throw new IllegalArgumentException("ReservationListDB - binarySearch(T o) - The object in the parameter must be a Reservation");
       
        int startIndex = 0, // Start index where to start searching
        endIndex = database.size(); // End index where to stop searching
        while (endIndex > startIndex){
            int  midIndex = (endIndex+startIndex) / 2;
            DawsonReservation temp = (DawsonReservation)database.get(midIndex);
            if (temp.compareTo(reservObj) > 0)         
                startIndex = midIndex+1;
               
        else if (temp.compareTo(reservObj) < 0)
                endIndex = midIndex -1;
 
            else if (temp.equals(reservObj))
                return midIndex;
        }
        return startIndex;
    }
   
       
   
}