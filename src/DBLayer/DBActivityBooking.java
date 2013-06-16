package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import Model.ActivityBooking;
import Model.Guest;

public class DBActivityBooking implements IFDBActivityBooking 
{
	private Connection con;
	final static String DATE_FORMAT = "MM-dd-yyyy";
	
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
			guestObj = dbGuest.searchGuestById(guestId);
			activityBookingObj.setGuest(guestObj);
			
			Date activityBookingDate = results.getDate("date");
			String stringActivityBookingDate = new String();
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			stringActivityBookingDate = df.format(activityBookingDate);
			activityBookingObj.setDate(stringActivityBookingDate);
			
			activityBookingObj.setStatus(results.getString("status"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the activity booking object: " + e);
		}
		
		return activityBookingObj;
	}
	
	private ActivityBooking singleWhere(String wClause)
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
			else
			{
				activityBookingObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		return activityBookingObj;
	}
	
	private LinkedList<ActivityBooking> miscWhere(String wClause)
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
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return activityBookingList;
	}

	@Override
	public LinkedList<ActivityBooking> getAllActivityBookings()
	{
		return miscWhere("");
	}

	@Override
	public ActivityBooking getActivityBookingById(int id)
	{
		String wClause = " id= '" + id + "'";
		return singleWhere(wClause);
	}	

	@Override
	public ActivityBooking getActivityBookingForDate(int guestId, String date, String status)
	{
		String wClause = " guestId= '" + guestId + "' AND date= '" + date + "' AND status= '" + status + "'";
		return singleWhere(wClause);
	}
	
	@Override
	public LinkedList<ActivityBooking> getActivityBookingsForGuest(int guestId)
	{
		String wClause = " guestId= '" + guestId + "'";
		return miscWhere(wClause);
	}
	
	@Override
	public int insertActivityBooking(ActivityBooking activityBooking) throws Exception
	{
		ActivityBooking acticityBookingObj = activityBooking;
		//call to get the next activity booking id
		int nextActivityBookingId = GetMax.getMaxId("SELECT MAX(id) from ActivityBooking");
		nextActivityBookingId = nextActivityBookingId + 1;
		System.out.println("Next activity booking id = " + nextActivityBookingId);
		
		int result = -1;
		
		String query = "INSERT INTO ActivityBooking(id, guestId, date, status) VALUES ('" +
		nextActivityBookingId + "','" +
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
		
		String query = "SELECT COUNT(*) AS activityBokingInstances FROM ActivityBooking " + 
		" WHERE date='" +  date + "' AND guestId='" + guestId + "' AND status<>'Canceled'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				instances = results.getInt("activityBokingInstances");
				System.out.println("Activity booking instances: " + instances);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity booking instance count: " + e);
		}
		return instances;
	}
}