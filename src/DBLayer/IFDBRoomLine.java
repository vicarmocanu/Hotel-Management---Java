package DBLayer;

import java.util.ArrayList;

import Model.RoomLine;

public interface IFDBRoomLine {

	public RoomLine findRoomLine(int guestId, int roomNo, int booking, boolean retrieveAssociation);
	public ArrayList<RoomLine> findRoomLinesForBooking(int bookingId, boolean retrieveAssociation);
	
	public int insertRoomLine(RoomLine roomLine) throws Exception;
	public int deleteRoomLine(int guestId, int roomNo, int booking);
}
