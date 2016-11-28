package group187.hotel.data;

import java.io.IOException;

import group187.util.Utilities;

public class SerializedFileLoaderApp {
	
	public static void main (String[] args) throws IOException {
		SequentialTextFileList sequentialFile = 
			new SequentialTextFileList("datafiles/database/rooms.txt",
									   "datafiles/database/customers.txt", 
									   "datafiles/database/reservations.txt");
		
		String roomFile = "datafiles/database/rooms.ser";
		String customerFile = "datafiles/database/customers.ser";
		String reservFile = "datafiles/database/reservations.ser" ;
		
		saveRoomObject(sequentialFile, roomFile);
		saveCustObject(sequentialFile, customerFile);
		saveReservObject(sequentialFile, reservFile);
		
	}
	
	private static void saveRoomObject(SequentialTextFileList sequentialFile, String path){
		
		try{
			Utilities.serializeObject(sequentialFile.getRoomDatabase(),path);
		}catch(IOException ioe){
			System.out.println("IOE Exception has been thrown: " + ioe.getMessage());
		}
	}
	
	private static void saveCustObject(SequentialTextFileList sequentialFile, String path){
		
		try{
			Utilities.serializeObject(sequentialFile.getCustomerDatabase(),path);
		}catch(IOException ioe){
			System.out.println("IOE Exception has been thrown: " + ioe.getMessage());
		}
	}
	
	private static void saveReservObject(SequentialTextFileList sequentialFile, String path){
		
		try{
			Utilities.serializeObject(sequentialFile.getReservationDatabase(),path);
		}catch(IOException ioe){
			System.out.println("IOE Exception has been thrown: " + ioe.getMessage());
		}
	}
}
	