package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Model.Room;
import Model.RoomType;

public class DBRoom implements IFDBRoom {
	private Connection con;
	
	public DBRoom()
	{
		con = DBConnection1.getInstance().getDBcon();
	}

	//Interface methods
	public Room findRoom(int number, boolean retrieveAssociation) {
		return singleWhere("number='"+number+"'",retrieveAssociation);
	}
	
	public LinkedList<Room> findRoomsFromType(String type, boolean retrieveAssociation)
	{
		return miscWhere("roomType='"+type+"'", retrieveAssociation);
	}

/*	public ArrayList<Room> findAvailableRooms(int arrival, int departure,
			String type, boolean retrieveAssociation) {
		return miscWhere("(SELECT roomNo FROM RoomLine WHERE booking=(SELECT id FROM RoomBooking WHERE " +
				"arrivalDate>='"+arrival+"' AND departureDate<='"+departure+"'))<>number AND roomType='"+type+"'", retrieveAssociation);
	}*/
	
	public LinkedList<Room> findDifferentRooms(int roomNo,
			boolean retrieveAssociation) {
		LinkedList<Room> rooms = new LinkedList<>();
		
		rooms.addAll(miscWhere("number<>"+roomNo, retrieveAssociation));
		return rooms;
	}
	
	public LinkedList<Room> findAllRooms(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}
	
	
	public int insertRoom(Room room) throws Exception
	{
		String query = "INSERT INTO Room(number, roomType)" +
				" VALUES('" +
				room.getNumber()+"','"+
				room.getRoomType().getCategory()+ "')";
		
		int rc = -1;
		System.out.println("insert: " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("Room is not inserted");
	        throw new Exception ("Room is not inserted correctly!");
		}
		
		return rc;
	}
	
	public int deleteRoom(int number)
	{
		int rc=-1;
		  
	  	String query="DELETE FROM Room WHERE number=" +	number;
                System.out.println(query);
	  	try{ 
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}
   	        catch(Exception ex){
	 	  	System.out.println("Delete exception in room db: "+ex);
   	        }
		return(rc);
	}
	
	public int updateRoom(Room room)
	{
		Room r = room;
		int rc = -1;
		
		String query = "UPDATE Room SET " +
				"roomType='" + r.getRoomType().getCategory()+"' "+
						"WHERE number='" + r.getNumber()+"'";
		System.out.println("Update query: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in Room: " + e);
		}		
		return rc;
	}
	//end interface methods
	
	private Room buildRoom(ResultSet result)
	{
		Room roomObj = new Room();
		RoomType roomTypeObj = new RoomType();
		roomObj.setRoomType(roomTypeObj);
		try {
			roomObj.setNumber(result.getInt(1));
			roomTypeObj.setCategory(result.getString(2));
            System.out.println("build Room" + roomObj.getNumber());
		} catch (Exception e) {
			System.out.println("Error in building the Room object!");
		}
		return roomObj;
	}
	
	private String buildQuery(String wClause)
	{
		String query = "SELECT DISTINCT * FROM Room";
		
		if(wClause.length()>0)
		{
			query = query + " WHERE " + wClause;
		}
		
		return query;
	}
	
	private Room singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		Room roomObj = new Room();
		String query = buildQuery(wClause);
		System.out.println("DBRoom Query: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next()) {
				roomObj = buildRoom(results);
				stmt.close();
				if(retrieveAssociation)
				{//Build RoomType as well
					IFDBRoomType dbRoomType = new DBRoomType();
					RoomType roomTypeObj = dbRoomType.findRoomTypeByCategory(roomObj.getRoomType().getCategory(),false);
					System.out.println("Room type is selected.");
					roomObj.setRoomType(roomTypeObj);
				}
			}
			else
			{
				roomObj = null;
			}
		} catch (Exception e) {
			System.out.println("Query exception - select Room : "+e);
			e.printStackTrace();
		}
		
		return roomObj;
	}
	
	public LinkedList<Room> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<Room> roomList = new LinkedList<Room>();
		String query = buildQuery(wClause);
		System.out.println("DBRoom Query: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while (results.next()) {
				Room roomObj = new Room();
				roomObj = buildRoom(results);
				roomList.add(roomObj);
			}
			stmt.close();
			if(retrieveAssociation)
			{//Build RoomType as well
				IFDBRoomType dbRoomType = new DBRoomType();
				for(Room roomObj : roomList)
				{					
					RoomType roomTypeObj = dbRoomType.findRoomTypeByCategory(roomObj.getRoomType().getCategory(),false);
					System.out.println("Room type is selected.");
					roomObj.setRoomType(roomTypeObj);
				}
			}			
		} catch (Exception e) {
			System.out.println("Query exception - select Room : "+e);
			e.printStackTrace();
		}
		
		return roomList;
	}
}
