package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

import Model.ActivityBooking;
import Model.Guest;
import Model.Team;

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
		
		IFDBTeam dbTeam = new DBTeam();
		Team teamObj = new Team();
		
		
		
		try
		{
			activityBookingObj.setId(results.getInt("id"));
			
			int guestId = results.getInt("guestId");
			guestObj = dbGuest.searchGuestById(guestId, false);
			if(guestObj != null)
			{
				activityBookingObj.setGuest(guestObj);
			}
			
			int teamId = results.getInt("teamId");
			teamObj = dbTeam.getTeamById(teamId, true);
			if(teamObj != null)
			{
				activityBookingObj.setTeam(teamObj);
			}
			
			activityBookingObj.setDate(results.getString("date"));
			activityBookingObj.setStatus(results.getString("status"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the activity booking object: " + e);
		}
		
		try
		{
			activityBookingObj.setTeam(dbTeam.getTeamById(results.getInt("teamId"), false));
		}
		catch(Exception e)
		{
			System.out.println("Exceptin in setting the team for the activity booking: " + e);
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
			{//the guest and the team are selected as well
				IFDBGuest dbGuest = new DBGuest();
				Guest guestObj=dbGuest.searchGuestById(activityBookingObj.getGuest().getId(), false);
				System.out.println("Guest is selected.");
				activityBookingObj.setGuest(guestObj);
				
				IFDBTeam dbTeam = new DBTeam();
				Team teamObj = dbTeam.getTeamById(activityBookingObj.getTeam().getId(), false);
				if(teamObj != null)
				{
					System.out.println("Team is selected.");
					activityBookingObj.setTeam(teamObj);
				}
				System.out.println("No team selection.");
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
	
	private LinkedList<ActivityBooking> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<ActivityBooking> activityBookingList = new LinkedList<ActivityBooking>();
		String query =  buildQuery(wClause);
		
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
			{//the guest and the team are selected as well
				IFDBGuest dbGuest= new DBGuest();
				IFDBTeam dbTeam = new DBTeam();
				
				for(ActivityBooking activityBookingObj : activityBookingList)
				{
					Guest guestObj=dbGuest.searchGuestById(activityBookingObj.getGuest().getId(), false);
					System.out.println("Guest is selected.");
					activityBookingObj.setGuest(guestObj);
					
					Team teamObj = dbTeam.getTeamById(activityBookingObj.getTeam().getId(), false);
					if(teamObj != null)
					{
						System.out.println("Team is selected.");
						activityBookingObj.setTeam(teamObj);
					}
					else
					{
						System.out.println("No team selection.");
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
		String wClause = "  id= '" + id + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public LinkedList<ActivityBooking> getActivityBookingsByDate(Date date,	boolean retrieveAssociation)
	{
		String wClause = "  date= '" + date + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

	@Override
	public LinkedList<ActivityBooking> getActivityBookingsForGuest(int guestId, boolean retrieveAssociation)
	{
		String wClause = "  guestId= '" + guestId + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

	@Override
	public int insertActivityBookig(ActivityBooking activityBooking) throws Exception
	{
		ActivityBooking acticityBookingObj = activityBooking;
		Team teamObj = acticityBookingObj.getTeam();		
		int result = -1;
		
		String query = new String();
		if(teamObj !=null )
		{
			query = "INSERT INTO Team(guestId, teamId, date, status) VALUES ('" +
					acticityBookingObj.getGuest().getId() + "','" + 
					teamObj.getId() + "','" + 
					acticityBookingObj.getStringDate() +"','" + 
					acticityBookingObj.getStatus() +"')";
		}
		else
		{
			query = "INSERT INTO Team(id, guestId, teamId, date, status) VALUES ('" +
				acticityBookingObj.getGuest().getId() + "', 'NULL', '" + 
				acticityBookingObj.getStringDate() +"','" + 
				acticityBookingObj.getStatus() +"')";
		}
		
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
	    	System.out.println("Insertio exception: " + e);
	    }
	    
	    return(result);
	}

	@Override
	public int updateActivityBooking(ActivityBooking activityBooking)
	{
		ActivityBooking activityBookingObj = activityBooking;
		Team teamObj = activityBookingObj.getTeam();
		String query = new String();
		
		if(teamObj !=null)
		{
			query="UPDATE ActivityBooking SET " + 
					" teamId= '" + activityBookingObj.getTeam().getId() + "', " + 
		" date= '" + activityBookingObj.getDate() + "', " + 
					" status= '" + activityBookingObj.getStatus() + "' " + 
		"WHERE id= '" + activityBookingObj.getId() + "'";
		}
		else
		{
			query="UPDATE ActivityBooking SET " + 
		" teamId= 'NULL'" + ", " + 
								" date= '" + activityBookingObj.getDate() + "', " + 
					" status= '" + activityBookingObj.getStatus() + "' " +
			"WHERE id= '" + activityBookingObj.getId() + "'";
		}
		
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
}
