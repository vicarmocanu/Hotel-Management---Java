package Controller;

import java.util.ArrayList;
import java.util.LinkedList;

import DBLayer.DBConnection1;
import DBLayer.DBGuest;
import DBLayer.DBRoom;
import DBLayer.DBRoomBooking;
import DBLayer.DBRoomLine;
import DBLayer.GetMax;
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
	
	public ArrayList<RoomBooking> findRoomBookingByArrival(int arrival)
	{
		IFDBRoomBooking dbrb = new DBRoomBooking();
		ArrayList<RoomBooking> rb = dbrb.findRoomBookingsByArrival(arrival, false);
		return rb;
	}
	
	public ArrayList<RoomBooking> findRoomBookingByDeparture(int departure)
	{
		IFDBRoomBooking dbrb = new DBRoomBooking();
		ArrayList<RoomBooking> rb = dbrb.findRoomBookingsByDeparture(departure, false);
		return rb;
	}
	
	public ArrayList<RoomLine> findRoomLinesForBooking(int bookingId)
	{
		IFDBRoomLine dbrl = new DBRoomLine();
		ArrayList<RoomLine> roomLines= dbrl.findRoomLinesForBooking(bookingId, true);
		return roomLines;
	}
	
	public LinkedList<Room> findAvailableRooms(int arrival, int departure)
	{
		IFDBRoomBooking dbrb=new DBRoomBooking();
		ArrayList<RoomBooking> rbl=dbrb.findRoomBookingsWithDates(arrival, departure, false);
		IFDBRoom dbroom = new DBRoom();
		
		IFDBRoomLine dbrl = new DBRoomLine();
		ArrayList<RoomLine> rll = new ArrayList<>();
		
		LinkedList<Room> rooms = new LinkedList<>();
		
		if(rbl.isEmpty())
		{
			rooms = dbroom.findAllRooms(true);;
		}
		else
		{
			for(RoomBooking rb : rbl)
			{
				rll.addAll(dbrl.findRoomLinesForBooking(rb.getId(), true));				
			}
			for(RoomLine rl:rll)
			{				
				rooms.addAll(dbroom.findDifferentRooms(rl.getRoom().getNumber(), true));
				
				for(int i=0; i<rooms.size();i++)
				{
					if(rl.getRoom().getNumber()==rooms.get(i).getNumber())
					{
						rooms.remove(i);
					}
				}
			}
		}
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
	
	public int createNewBooking(int arrival, int departure, String status, int children)
	{
		int nextId = GetMax.getMaxId("Select max(id) from RoomBooking");
        nextId = nextId + 1;
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
		return nextId;
	}
	
	public int updateBooking(int id, int arrival, int departure, String status, int children)
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
