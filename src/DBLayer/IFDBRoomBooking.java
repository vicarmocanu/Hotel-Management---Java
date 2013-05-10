package DBLayer;

import java.util.ArrayList;

import Model.RoomBooking;

public interface IFDBRoomBooking {
	//find a room booking by its id
	public RoomBooking findRoomBooking(int id, boolean retrieveAssociation);
	//find all bookings by guests arrival date
	public ArrayList<RoomBooking> findRoomBookingsByArrival(String arrival, boolean retrieveAssociation);
	//find all bookings by guests departure date
	public ArrayList<RoomBooking> findRoomBookingsByDeparture(String departure, boolean retrieveAssociation);
	//find all bookings with a status
	public ArrayList<RoomBooking> findRoomBookingsByStatus(String status, boolean retrieveAssociation);
	//find the room bookings that a guest made
	public ArrayList<RoomBooking> findRoomBookingsForGuest(int guestId, boolean retrieveAssociation);
	//find all bookings for a room
	public ArrayList<RoomBooking> findRoomBookingsForRoom(int roomNo, boolean retrieveAssociation);
	
	//insert room booking 
	public int insertRoomBooking(RoomBooking rb) throws Exception;
	//update room booking
	public int updateRoomBooking(RoomBooking rb);
	//delete room booking
	public int deleteRoomBooking(int roomBookingId);
}
