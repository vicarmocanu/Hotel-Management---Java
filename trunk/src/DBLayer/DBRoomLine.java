package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Guest;
import Model.Room;
import Model.RoomBooking;
import Model.RoomLine;

public class DBRoomLine implements IFDBRoomLine {
	private Connection con;
	
	public DBRoomLine()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	public RoomLine findRoomLine(int guestId, int roomNo, int booking, boolean retrieveAssociation)
	{
		return singleWhere("guestId="+guestId+" AND roomNo="+roomNo+
				"AND booking="+booking, retrieveAssociation);
	}
	
	
	public ArrayList<RoomLine> findRoomLinesForBooking(int bookingId,
			boolean retrieveAssociation) {
		return miscWhere("booking="+bookingId, retrieveAssociation);
	}
	
	//insert
	public int insertRoomLine(RoomLine roomLine) throws Exception
	{
		String query = "INSERT INTO RoomLine(guestId, roomNo, booking) VALUES('" +
				roomLine.getGuest().getId() +"', '"+
				roomLine.getRoom().getNumber()+"', '"+
				roomLine.getBooking().getId() + "')";
		
		int rc = -1;
		System.out.println("insert: " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("RoomLine is not inserted");
	        throw new Exception ("RoomLine is not inserted correct");
		}
		
		return rc;
	}
	
	//delete
	public int deleteRoomLine(int guestId, int roomNo, int booking) {
		int rc=-1;
		  
	  	String query="DELETE FROM RoomLine WHERE guestId=" +
				guestId + " AND roomNo="+roomNo+" AND booking="+booking;
                System.out.println(query);
	  	try{ 
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}
   	        catch(Exception ex){
	 	  	System.out.println("Delete exception in room line db: "+ex);
   	        }
		return(rc);
	}	
	
//private methods
	private ArrayList<RoomLine> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		ArrayList<RoomLine> list = new ArrayList<RoomLine>();
		
		String query=buildQuery(wClause);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while(results.next())
			{
				RoomLine roomLine = new RoomLine();
				roomLine = buildRoomLine(results);
				list.add(roomLine);
			}
			stmt.close();
			if(retrieveAssociation)
			{
				for(RoomLine roomLine : list)
				{
					//Make association with the guest
					IFDBGuest dbguestObj = new DBGuest();
					Guest guestObj = dbguestObj.searchGuestById(roomLine.getGuest().getId(), true);
					System.out.println("Guest is found!");
					roomLine.setGuest(guestObj);
					//Make association with the room
					IFDBRoom dbroomObj = new DBRoom();
					Room roomObj = dbroomObj.findRoom(roomLine.getRoom().getNumber(), true);
					System.out.println("Room is found!");
					roomLine.setRoom(roomObj);
					//Make association with the room booking
					IFDBRoomBooking dbroomBookingObj = new DBRoomBooking();
					RoomBooking roomBookingObj = dbroomBookingObj.findRoomBooking(roomLine.getBooking().getId(), false); 
					System.out.println("Booking is found!");
					roomLine.setBooking(roomBookingObj);
				}
			}
		} catch (Exception e) {
			System.out.println("Query exception - select: "+e);
			e.printStackTrace();
		}
		
		return list;
	}
	
	//SingleWhere is used when we only select one employee 	
	private RoomLine singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		RoomLine roomLine = new RoomLine();
		
		String query = buildQuery(wClause);
		System.out.println(query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if(results.next())
			{
				roomLine=buildRoomLine(results);
				stmt.close();
				if (retrieveAssociation) {
					//Make association with the guest
					IFDBGuest dbguestObj = new DBGuest();
					Guest guestObj = dbguestObj.searchGuestById(roomLine.getGuest().getId(), true);
					System.out.println("Guest is found!");
					roomLine.setGuest(guestObj);
					//Make association with the room
					IFDBRoom dbroomObj = new DBRoom();
					Room roomObj = dbroomObj.findRoom(roomLine.getRoom().getNumber(), true);
					System.out.println("Room is found!");
					roomLine.setRoom(roomObj);
					//Make association with the room booking
					IFDBRoomBooking dbroomBookingObj = new DBRoomBooking();
					RoomBooking roomBookingObj = dbroomBookingObj.findRoomBooking(roomLine.getBooking().getId(), false); 
					System.out.println("Booking is found!");
					roomLine.setBooking(roomBookingObj);
				}
			}
		} catch (Exception e) {
			System.out.println("Query exception: "+e);
		}
		
		return roomLine;
	}
	
	//method to build the query
	private String buildQuery(String wClause)
	{
		String query = "SELECT guestId, roomNo, booking FROM RoomLine";
		if (wClause.length()>0) {
			query = query + " WHERE " + wClause;
		}
		
		return query;
	}
	
	//method to build the object
	private RoomLine buildRoomLine(ResultSet results)
	{
		RoomLine roomLine = new RoomLine();
		try {
			roomLine.setGuest(new Guest(results.getInt("guestId")));
			roomLine.setRoom(new Room(results.getInt("roomNo")));
			roomLine.setBooking(new RoomBooking(results.getInt("booking")));
		} catch (Exception e) {
			System.out.println("error in building the RoomLine object");
		}
		return roomLine;
	}
	
}
