package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Model.ActivityBooking;
import Model.Guest;

public class DBActivityBooking implements IFDBActivityBooking 
{
	private Connection con;
	
	public DBActivityBooking()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM ActivityBooking";
		
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		return query;
	}
	
	private ActivityBooking buildActivityBooking(ResultSet results)
	{
		ActivityBooking activityBookingObj = new ActivityBooking();
		
		IFDBGuest dbGuest = new DBGuest();
		Guest guestObj = new Guest();
		
		try
		{
			activityBookingObj.setId(results.getInt("id"));
			
			int guestId = results.getInt("guestId");
			guestObj = dbGuest.searchGuestById(guestId, false);
			activityBookingObj.setGuest(guestObj);
			
			activityBookingObj.setDate(results.getString("date"));
			
			activityBookingObj.setStatus(results.getString("status"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the activity booking object: " + e);
		}
		
		return activityBookingObj;
	}
	
	private ActivityBooking singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		ActivityBooking activityBookingObj=new ActivityBooking();		
		String query = buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				activityBookingObj = buildActivityBooking(results);
				stmt.close();
			}
			if(retrieveAssociation)
			{//guest reference
				IFDBGuest dbGuest = new DBGuest();
				Guest guestObj=dbGuest.searchGuestById(activityBookingObj.getGuest().getId(), false);
				if(guestObj != null)
				{
					System.out.println("Guest is selected.");
					activityBookingObj.setGuest(guestObj);
				}
			}
			else
			{
				activityBookingObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
			e.printStackTrace();
		}
		return activityBookingObj;
	}
	
	private LinkedList<ActivityBooking> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
		String query =  buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				ActivityBooking activityBookingObj = new ActivityBooking();
				activityBookingObj = buildActivityBooking(results);
				activityBookingList.add(activityBookingObj);
			}
			stmt.close();
			if(retrieveAssociation)
			{//guest reference
				IFDBGuest dbGuest= new DBGuest();
				
				for(ActivityBooking activityBookingObj : activityBookingList)
				{
					Guest guestObj=dbGuest.searchGuestById(activityBookingObj.getGuest().getId(), false);
					if(guestObj != null)
					{
						System.out.println("Guest is selected.");
						activityBookingObj.setGuest(guestObj);
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return activityBookingList;
	}

	@Override
	public LinkedList<ActivityBooking> getAllActivityBookings(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}

	@Override
	public ActivityBooking getActivityBookingById(int id, boolean retrieveAssociation)
	{
		String wClause = " id= '" + id + "'";
		return singleWhere(wClause, retrieveAssociation);
	}	

	@Override
	public ActivityBooking getActivityBookingForDate(int guestId, String date, boolean retrieveAssociation)
	{
		String wClause = " guestId= '" + guestId + "' AND date= '" + date + "'";
		return singleWhere(wClause, retrieveAssociation);
	}
	
	@Override
	public LinkedList<ActivityBooking> getActivityBookingsForGuest(int guestId,	boolean retrieveAssociation)
	{
		String wClause = " guestId= '" + guestId + "'";
		return miscWhere(wClause, retrieveAssociation);
	}
	
	@Override
	public int insertActivityBooking(ActivityBooking activityBooking) throws Exception
	{
		ActivityBooking acticityBookingObj = activityBooking;		
		int result = -1;
		
		String query = "INSERT INTO ActivityBooking(guestId, date, status) VALUES ('" +
					acticityBookingObj.getGuest().getId() + "','" + 
					acticityBookingObj.getDate() + "','" + 
					acticityBookingObj.getStatus() +"')";
		
		System.out.println("Insertion query: " + query);
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			result = stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Insertion exception: " + e);
		}
		return(result);
	}
	
	@Override
	public int updateActivityBookingStatus(ActivityBooking activityBooking)
	{
		ActivityBooking activityBookingObj = activityBooking;
		String query = "UPDATE ActivityBooking SET " + 
		" status= '" + activityBookingObj.getStatus() + "' " +
				"WHERE id= '" + activityBookingObj.getId() + "'";
		
		int result=-1;
		System.out.println("Update query: " + query);
		
		try
		{
			Statement stmt = con.createStatement();		
	 		stmt.setQueryTimeout(5);
	 	 	result = stmt.executeUpdate(query);
	 	 	stmt.close();
		}
		catch(SQLException e)
		{
			System.out.println("Update exception: " + e);
		}
		return(result);
	}

	@Override
	public int deleteActivityBooking(int id)
	{
		int result=-1;
		
		String query="DELETE FROM ActivityBooking WHERE id= '" + id + "'";
		System.out.println("Delete query: " + query);
		try
	  	{
	  		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	result = stmt.executeUpdate(query);
	 	  	stmt.close();
	 	}
		catch(SQLException e)
		{
			System.out.println("Delete exception: " + e);
		}
		return(result);
	}
	
	@Override
	public int deleteActivityBookingForDate(int guestId, String date)
	{
		int result=-1;
		
		String query="DELETE FROM ActivityBooking WHERE guestId= '" + guestId + "' AND date= '" + date + "'";
		System.out.println("Delete query: " + query);
		try
	  	{
	  		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	  	result = stmt.executeUpdate(query);
	 	  	stmt.close();
	 	}
		catch(SQLException e)
		{
			System.out.println("Delete exception: " + e);
		}
		return(result);
	}

	@Override
	public int getActivityBookingInstances(String date, int guestId)
	{
		int instances = 0;		
		ResultSet results;
		String query = "SELECT COUNT(guestId, date) AS activityBokingInstances FROM ActivityBooking " + 
		" WHERE date='" +  date + "' AND guestId='" + guestId + "'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);			
			instances = results.getInt("activityBokingInstances");
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity booking instance count: " + e);
		}
		return instances;
	}
}