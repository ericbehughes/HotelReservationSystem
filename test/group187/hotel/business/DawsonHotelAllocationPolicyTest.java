package group187.hotel.business;

import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import group187.hotel.data.ReservationListDB;
import group187.hotel.data.SequentialTextFileList;
import group187.util.ListUtilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DawsonHotelAllocationPolicyTest {
	public static void main(String[] args) {
		// Set file paths
		String roomFilename = "datafiles//database//rooms.txt";
		String customerFilename = "datafiles//database//customers.txt";
		String reservationFilename = "datafiles//database//reservations.txt";

		// Create database and factory object
		SequentialTextFileList obj = new SequentialTextFileList(roomFilename, customerFilename, reservationFilename);
		DawsonHotelFactory factory = DawsonHotelFactory.DAWSON;
		ReservationListDB reservdb = new ReservationListDB(obj);
		
		// Create the customer and reservation objects
		List<Room> freeRooms = new ArrayList<>();
		List<Room> reservedRooms = new ArrayList<>();
		
		// Get checkinArray and checkoutArray dates
		LocalDate checkin = LocalDate.of(2016, 9, 10);
		LocalDate checkout = LocalDate.of(2016, 10, 31);

		// Get all free rooms within checkinArray and checkoutArray date for the given
		// room type
		//freeRooms = reservdb.getFreeRooms(checkin, checkout, RoomType.PENTHOUSE);
		reservedRooms = reservdb.getReservedRooms(checkin, checkout);
		System.out.println(reservedRooms.toString());
		// Begin test
		DawsonHotelAllocationPolicy allocationPolicy = new DawsonHotelAllocationPolicy(reservdb);
		Optional<Room> freeRoom = allocationPolicy.getAvailableRoom(checkin, checkout, RoomType.NORMAL);
		System.out.println(freeRoom.toString());
		
		}
	}