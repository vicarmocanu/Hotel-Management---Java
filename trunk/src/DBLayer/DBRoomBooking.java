package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.RoomBooking;

public class DBRoomBooking implements IFDBRoomBooking {
	private Connection con;
	
	public DBRoomBooking()
	{
		con = DBConnection1.getInstance().getDBcon();
	}
	
	
	public RoomBooking findRoomBooking(int id, boolean retrieveAssociation) {
		return singleWhere("id='"+id+"'", retrieveAssociation);
	}

	
	public ArrayList<RoomBooking> findRoomBookingsByArrival(String arrival,
			boolean retrieveAssociation) {
		return miscWhere("arrivalDate='"+arrival+"'", retrieveAssociation);
	}

	
	public ArrayList<RoomBooking> findRoomBookingsByDeparture(String departure,
			boolean retrieveAssociation) {
		return miscWhere("departureDate='"+departure+"'", retrieveAssociation);
	}

	
	public ArrayList<RoomBooking> findRoomBookingsByStatus(String status,
			boolean retrieveAssociation) {
		return miscWhere("status='"+status+"'", retrieveAssociation);
	}

	
	public ArrayList<RoomBooking> findRoomBookingsForRoom(int roomNo,
			boolean retrieveAssociation) {
		return miscWhere("id=(SELECT booking FROM Booking_Guest_Room WHERE roomNo='"+roomNo+"'",
				retrieveAssociation);
	}
	
	
	public ArrayList<RoomBooking> findRoomBookingsForGuest(int guestId,
			boolean retrieveAssociation) {
		return miscWhere("id=(SELECT booking FROM Booking_Guest_Room WHERE guestId='"
			+guestId+"'", retrieveAssociation);
	}


	
	public int insertRoomBooking(RoomBooking rb) throws Exception {
		String query = "INSERT INTO RoomBooking(arrivalDate, departureDate, status, numberOfChildren)" +
				" VALUES('" +
				rb.getArrivalDate()+"','"+
				rb.getDepartureDate()+"','"+
				rb.getStatus()+"','"+
				rb.getNumberOfChildren()+ "')";
		
		int rc = -1;
		System.out.println("insert: " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException ex) {
			System.out.println("SalesOrder is not inserted");
	        throw new Exception ("SalesOrder is not inserted correctly!");
		}
		
		return rc;
	}

	
	public int updateRoomBooking(RoomBooking rb) {
		RoomBooking roomBooking = rb;
		int rc = -1;
		
		String query = "UPDATE RoomBooking SET " +
				"arrivalDate='" + roomBooking.getArrivalDate() +"', "+
				"departureDate='"+roomBooking.getDepartureDate()+"', "+
				"status='"+roomBooking.getStatus()+"', "+
				"numberOfChildren='"+roomBooking.getNumberOfChildren()+"' "+
						"WHERE id='" + roomBooking.getId()+"'";
		System.out.println("Update query: " + query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc=stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			System.out.println("Update exception in RoomBooking: " + e);
		}
		
		return rc;

	}

	
	public int deleteRoomBooking(int roomBookingId) {
		int rc=-1;
		  
	  	String query="DELETE FROM RoomBooking WHERE id = '" +
				roomBookingId + "'";
                System.out.println(query);
	  	try{ 
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	rc = stmt.executeUpdate(query);
	 	  	stmt.close();
  		}
   	    catch(Exception ex){
	 	  	System.out.println("Delete exception in RoomBooking: "+ex);
   	    }
		return(rc);
	}
	
	
	private String buildQuery(String wClause)
	{
		String query = "SELECT * FROM RoomBooking";
		
		if(wClause.length()>0)
		{
			query = query + " WHERE " + wClause;
		}
		
		return query;
	}
	
	private RoomBooking buildRoomBooking(ResultSet results)
	{
		RoomBooking rbObj = new RoomBooking();		
		
		try {
			rbObj.setId(results.getInt("id"));
			rbObj.setArrivalDate(results.getString("arrivalDate"));
			rbObj.setDepartureDate(results.getString("departureDate"));
			rbObj.setStatus(results.getString("status"));
			rbObj.setNumberOfChildren(results.getInt("numberOfChildren"));
		} catch (Exception e) {
			System.out.println("Error in building the RoomBooking object!");
		}
		
		return rbObj;
	}
	
	private RoomBooking singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		RoomBooking rbObj = new RoomBooking();
		String query = buildQuery(wClause);
		System.out.println("Query: "+query);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if (results.next()) {
				rbObj = buildRoomBooking(results);
				System.out.println("RoomBooking build successfully!");
				stmt.close();
			}
			else
			{
				rbObj = null;
			}			
		} catch (Exception e) {
			System.out.println("Query exception - select RoomBooking : "+e);
			e.printStackTrace();
		}
		
		return rbObj;
	}
	
	private ArrayList<RoomBooking> miscWhere(String wClause, boolean retrieveAssiciation)
	{
		ResultSet results;
		ArrayList<RoomBooking> list = new ArrayList<RoomBooking>();
		String query = buildQuery(wClause);
		
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			while(results.next())
			{
				RoomBooking rbObj = new RoomBooking();
				rbObj = buildRoomBooking(results);
				list.add(rbObj);
			}
			stmt.close();
		} catch (Exception e) {
			System.out.println("Query exception - select RoomBooking : "+e);
			e.printStackTrace();
		}
		return list;
	}
}
