package Controller;

import java.util.ArrayList;

import DBLayer.DBConnection1;
import DBLayer.DBGuest;
import DBLayer.DBRoom;
import DBLayer.DBRoomBooking;
import DBLayer.DBRoomLine;
import DBLayer.IFDBGuest;
import DBLayer.IFDBRoom;
import DBLayer.IFDBRoomBooking;
import DBLayer.IFDBRoomLine;
import Model.Guest;
import Model.Room;
import Model.RoomBooking;
import Model.RoomLine;

public class RoomBookingCtr {
	
	public RoomBookingCtr()
	{
		
	}
	
	public RoomBooking findRoomBookingByID(int bookingId)
	{
		IFDBRoomBooking dbrb = new DBRoomBooking();
		RoomBooking rb = dbrb.findRoomBooking(bookingId, false);
		return rb;
	}
	
	public ArrayList<RoomLine> findRoomLinesForBooking(int bookingId)
	{
		IFDBRoomLine dbrl = new DBRoomLine();
		ArrayList<RoomLine> roomLines= dbrl.findRoomLinesForBooking(bookingId, true);
		return roomLines;
	}
	
	public ArrayList<Room> findAvailableRooms(String arrival, String departure, String type)
	{
		IFDBRoom dbroom = new DBRoom();
		ArrayList<Room> rooms = dbroom.findAvailableRooms(arrival, departure, type, true);
		return rooms;
	}
	
	public Guest findGuestInRoom(String date, int roomNo)
	{
		IFDBGuest dbguest = new DBGuest();
		Guest guest = dbguest.findGuestInRoom(date, roomNo, true);
		return guest;
	}
	
	public void createRoomLine(int guestId, int roomNo, int booking)
	{
		RoomLine rl = new RoomLine();
		
		rl.setGuest(new Guest(guestId));
		rl.setRoom(new Room(roomNo));
		rl.setBooking(new RoomBooking(booking));
		
		try {
			DBConnection1.startTransaction();
			IFDBRoomLine dbrl = new DBRoomLine();
			dbrl.insertRoomLine(rl);
			DBConnection1.commitTransaction();
		} catch (Exception e) {
			DBConnection1.rollbackTransaction();
		}
		System.out.println("Line created.");
	}
	
	public void createNewBooking(String arrival, String departure, String status, int children)
	{
		RoomBooking rb = new RoomBooking();
		
		rb.setArrivalDate(arrival);
		rb.setDepartureDate(departure);
		rb.setStatus(status);
		rb.setNumberOfChildren(children);
		
		try {
			DBConnection1.startTransaction();
			IFDBRoomBooking dbrb = new DBRoomBooking();
			dbrb.insertRoomBooking(rb);
			DBConnection1.commitTransaction();
		} catch (Exception e) {
			DBConnection1.rollbackTransaction();
		}
		System.out.println("Booking created: "+rb.getId());
	}
	
	public int updateBooking(int id, String arrival, String departure, String status, int children)
	{
		IFDBRoomBooking dbrb = new DBRoomBooking();
		RoomBooking rb = new RoomBooking();
		rb.setId(id);
		rb.setArrivalDate(arrival);
		rb.setDepartureDate(departure);
		rb.setStatus(status);
		rb.setNumberOfChildren(children);
		return dbrb.updateRoomBooking(rb);
	}
	
	public int deleteBooking(int id)
	{
		IFDBRoomBooking dbrb = new DBRoomBooking();
		return dbrb.deleteRoomBooking(id);
	}
}
