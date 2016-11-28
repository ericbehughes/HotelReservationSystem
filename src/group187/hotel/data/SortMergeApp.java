package group187.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.HotelFileLoader;
import group187.util.ListUtilities;

public class SortMergeApp {

	public static void main(String[] args) {
		//Customer[] customerArray = null;
		List<Customer> custList = new ArrayList<>();
		Customer[] allCusts = null;
		Reservation[] reservationArrray = null;
		
		// TEST getCustomerListFromSequentialFile
					try {
						File allCustomers = new File("datafiles/unsorted/customers/AllCustomers.txt");
						allCustomers.createNewFile();
						// save customer records to big customer file
						for (int count = 1; count <= 10; count++){
							try{
								//create list per customer text file
									custList.addAll(HotelFileLoader.getCustomerListFromSequentialFile(
											new File("datafiles/unsorted/customers/customers" + count + ".txt")));
									//save list to big AllCustomers.txt file
									//ListUtilities.saveListToTextFile(customerArray, allCustomers);
								}catch (IllegalArgumentException iae)
									{
									System.out.println(iae.getMessage());
									}
								}
						allCusts = new Customer[custList.size()];
						ListUtilities.saveListToTextFile(custList.toArray(allCusts), allCustomers);
						}catch (IOException ie) {
							// TODO Auto-generated catch block
							ie.printStackTrace();
					
					}
					System.out.println("record count for customers " + ListUtilities.recordCount);
					ListUtilities.recordCount = 0;
				System.out.println("customers done");
				
				
				
			
				try {
					//create giant list with all customers
					List<Customer> allCustomerObjects = new ArrayList<>();
					allCustomerObjects = HotelFileLoader.getCustomerListFromSequentialFile(
							new File("datafiles/unsorted/customers/AllCustomers.txt"));
					
					// create roomsList
					File roomsFile = new File("datafiles/rooms.txt");
					roomsFile.createNewFile();
					List<Room> allRooms = HotelFileLoader.getRoomListFromSequentialFile(roomsFile);
					//List<Room> roomList = new ArrayList<>();
					System.out.println("rooms done");
					// create a file from customer and rooms list
					File allReservations = new File("datafiles/unsorted/reservations/AllReservations.txt");
					allReservations.createNewFile();
					// save reservation records to big reservation file
					List<Reservation> allReservs = new ArrayList<>();
							          //tempReservs = new ArrayList<>();
					for (int count = 1; count <= 10; count++) {
						try {
							Customer[] custArr = new Customer[allCustomerObjects.size()];
							Room[] roomArr = new Room[allRooms.size()];
							//create reservations array for each reservation file while using BIG customerArray and roomArray
							allReservs.addAll(HotelFileLoader.getReservationListFromSequentialFile(
									new File("datafiles/unsorted/reservations/reservations" + count + ".txt"), allCustomerObjects.toArray(custArr), allRooms.toArray(roomArr)));
							//save reservation to BIG reservation file
							
							//ListUtilities.saveListToTextFile(reservationsArray,allReservations );
							
						} catch (IllegalArgumentException iae) {
							iae.printStackTrace();
							System.out.println(iae.getMessage());
						}
						catch (NullPointerException npe) {
							npe.printStackTrace();
							System.out.println(npe.getMessage());
						}
					
					}
					Reservation[] reservArray = new Reservation[allReservs.size()];
					ListUtilities.saveListToTextFile(allReservs.toArray(reservArray),allReservations );
				}
				catch (IOException ie) {
					// TODO Auto-generated catch block
				
				}
				System.out.println("record count for reservations " + ListUtilities.recordCount);
				
				
				ListUtilities.sort(allCusts);
				System.out.println("All custs");
				System.out.println(allCusts.toString());
				
				File customerDuplicatesFile = new File("datafiles/duplicates/customerDuplicates.txt");
				File reservationDuplicatesFile = new File("datafiles/duplicates/reservationDuplicates.txt");
				try {
					customerDuplicatesFile.createNewFile();
					reservationDuplicatesFile.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					List<Customer> array1  = HotelFileLoader.getCustomerListFromSequentialFile(
							new File("datafiles/unsorted/customers/customers1.txt"));
					List<Customer> array2  = HotelFileLoader.getCustomerListFromSequentialFile(
							new File("datafiles/unsorted/customers/customers2.txt"));
					System.out.println("non sorted array 1\n");
					for (Customer a1: array1){
						System.out.println(a1.toString());
					}
					Customer[] custArr1 = new Customer[array1.size()];
					ListUtilities.sort(array1.toArray(custArr1));
					System.out.println("sorted array 1\n");
					for (Customer a1: array1){
						System.out.println(a1.toString());
					}
					Customer[] custArr2 = new Customer[array2.size()];
					ListUtilities.sort(array2.toArray(custArr2));
					Customer[] arr1 = new Customer[array1.size()],
							   arr2 = new Customer[array2.size()];
					Customer[] array = (Customer[]) ListUtilities.merge(array1.toArray(arr1), array2.toArray(arr2), customerDuplicatesFile);
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


