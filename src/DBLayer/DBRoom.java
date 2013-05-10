package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Room;
import Model.RoomType;

public class DBRoom implements IFDBRoom {
	private Connection con;
	
	public DBRoom()
	{
		con = DBConnection1.getInstance().getDBcon();
	}

	//Interface methods
	@Override
	public Room findRoom(int number, boolean retrieveAssociation) {
		return singleWhere("number='"+number+"'",retrieveAssociation);
	}

	@Override
	public ArrayList<Room> findAvailableRooms(String arrival, String departure,
			String type, boolean retrieveAssociation) {
		return miscWhere("(SELECT roomNo FROM Booking_Guest_Room WHERE booking=(SELECT id FROM RoomBooking WHERE " +
				"arrivalDate>='"+arrival+"' AND rb.departureDate<='"+departure+"'))<>number AND roomType='"+type+"'", retrieveAssociation);
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
			// TODO: handle exception
			System.out.println("Error in building the Room object!");
		}
		return roomObj;
	}
	
	private String buildQuery(String wClause)
	{
		String query = "SELECT * FROM Room";
		
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
					RoomType roomTypeObj = dbRoomType.findRoomTypeByCategory(roomObj.getRoomType().getCategory());
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
	
	public ArrayList<Room> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		ArrayList<Room> roomList = new ArrayList<>();
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
					RoomType roomTypeObj = dbRoomType.findRoomTypeByCategory(roomObj.getRoomType().getCategory());
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
