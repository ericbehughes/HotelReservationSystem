package group187.hotel.data;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.HotelFileLoader;
import group187.util.ListUtilities;

public class SortMergeApp {

	public static void main(String[] args) {
		Customer[] customerArray = null;
		Reservation[] reservationArrray = null;
		// TEST getCustomerListFromSequentialFile
					try {
						File allCustomers = new File("datafiles/unsorted/customers/AllCustomers.txt");
						allCustomers.createNewFile();
						// save customer records to big customer file
						for (int count = 1; count <= 10; count++){
							try{
								//create list per customer text file
									customerArray = HotelFileLoader.getCustomerListFromSequentialFile(
											new File("datafiles/unsorted/customers/customers" + count + ".txt"));
									//save list to big AllCustomers.txt file
									ListUtilities.saveListToTextFile(customerArray, allCustomers);
									
								}catch (IllegalArgumentException iae)
									{
									System.out.println(iae.getMessage());
									}
								}
						}catch (IOException ie) {
							// TODO Auto-generated catch block
							ie.printStackTrace();
					
					}
					System.out.println("record count for customers " + ListUtilities.recordCount);
					ListUtilities.recordCount = 0;
				System.out.println("customers done");
				
				
				
			
				try {
					//create giant list with all customers
					Customer[] allCustomerObjects = HotelFileLoader.getCustomerListFromSequentialFile(
							new File("datafiles/unsorted/customers/AllCustomers.txt"));
					
					// create roomsList
					File roomsFile = new File("datafiles/rooms.txt");
					roomsFile.createNewFile();
					Room[] allRooms = HotelFileLoader.getRoomListFromSequentialFile(roomsFile);
					System.out.println("rooms done");
					// create a file from customer and rooms list
					File allReservations = new File("datafiles/unsorted/reservations/AllReservations.txt");
					allReservations.createNewFile();
					// save reservation records to big reservation file
					for (int count = 1; count <= 10; count++) {
						try {
							//create reservations array for each reservation file while using BIG customerArray and roomArray
							Reservation[] reservationsArray = HotelFileLoader.getReservationListFromSequentialFile(
									new File("datafiles/unsorted/reservations/reservations" + count + ".txt"), allCustomerObjects, allRooms);
							//save reservation to BIG reservation file
							
							ListUtilities.saveListToTextFile(reservationsArray,allReservations );
							
						} catch (IllegalArgumentException iae) {
							iae.printStackTrace();
							System.out.println(iae.getMessage());
						}
						catch (NullPointerException npe) {
							npe.printStackTrace();
							System.out.println(npe.getMessage());
						}
					
					}
				} catch (IOException ie) {
					// TODO Auto-generated catch block
				
				}
				System.out.println("record count for reservations " + ListUtilities.recordCount);
				
				
				ListUtilities.sort(customerArray);
				
				
				File duplicates = new File("datafiles/duplicates/duplicates.txt");
				try {
					duplicates.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Customer[] array1  = HotelFileLoader.getCustomerListFromSequentialFile(
							new File("datafiles/unsorted/customers/customers1.txt"));
					Customer[] array2  = HotelFileLoader.getCustomerListFromSequentialFile(
							new File("datafiles/unsorted/customers/customers2.txt"));
					System.out.println("non sorted array 1\n");
					for (Customer a1: array1){
						System.out.println(a1.toString());
					}
					ListUtilities.sort(array1);
					System.out.println("sorted array 1\n");
					for (Customer a1: array1){
						System.out.println(a1.toString());
					}
						
					ListUtilities.sort(array2);
					Customer[] array = (Customer[]) ListUtilities.merge(array1, array2, duplicates);
					System.out.println("Merged array\n");
					for (Customer arr : array){
						
						System.out.println(arr.toString());
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
			

				
				}		

			}

	}


