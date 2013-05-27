package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

import Model.ActivityLine;

public class DBActivityLine implements IFDBActivityLine
{
	private Connection con;
	
	public DBActivityLine()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM ActivityLine";
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		return query;
	}
	
	private ActivityLine buildActivityLine(ResultSet results)
	{
		ActivityLine activityLineObj=new ActivityLine();
		
		IFDBActivityType dbActivityType=new DBActivityType();
		//IFDBInstructor dbInstructo = new DBInstructor();
		IFDBFacility dbFacility = new DBFacility();
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		
		try
		{
			
		}
		catch(Exception e)
		{
			System.out.println("Error in building the team type object.");
		}
		return activityLineObj;
	}
	
	private ActivityLine singleWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		ActivityLine activityLineObj = new ActivityLine();
		
		String query = buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				activityLineObj = buildActivityLine(results);
				stmt.close();
			}
			if(retrieveAssociation)
			{//activity booking, instructor, facility and activity type reference
			}
			else
			{
				activityLineObj=null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		return activityLineObj;
	}
	
	private LinkedList<ActivityLine> miscWhere(String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<ActivityLine> activityLineList=new LinkedList<ActivityLine>();
		String query =  buildQuery(wClause);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				ActivityLine activityLineObj = new ActivityLine();
				activityLineObj = buildActivityLine(results);
				activityLineList.add(activityLineObj);
			}
			stmt.close();
			if(retrieveAssociation)
			{//activity booking, instructor, facility and activity type reference
				IFDBActivityType dbActivityType = new DBActivityType();
				//IFDBInstructor dbInstructor = new DBInstructor();
				IFDBFacility dbFacility = new DBFacility();
				IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
				for(ActivityLine activityLine : activityLineList)
				{
					
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return activityLineList;
	}
	
	
	@Override
	public ActivityLine getActivityLineById(int activityId,	int activityBookingId, boolean retrieveAssociation)
	{
		String wClause = " activityId= '" + activityId + "'" + " AND activityBookingId= '" + activityBookingId + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public LinkedList<ActivityLine> getActivityLineForActivityBooking(int activityBookingId, boolean retrieveAssociation)
	{
		String wClause = " activityBookingId= '" + activityBookingId + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

	@Override
	public int insertActivityLine(ActivityLine activityLine) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO ActivityLine(activityId, instructorId, date, startHour, endHour, facilityId, bookingId) VALUES ('" +
				activityLine.getActivity().getID() + "','" + 
				activityLine.getInstructor().getId() + "','" + 
				activityLine.getDate() + "','" + 
				activityLine.getStartHour() + "','" +
				activityLine.getEndHour() + "','" +
		activityLine.getFacility().getId() + "','" +
				activityLine.getActivityBooking().getId() + "')";
		
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
	    	System.out.println("Activity line has not been inserted correctly. Exception: " + e);
	    }
	    return(result);
	}

	@Override
	public int updateActivityLine(ActivityLine activityLine)
	{
		ActivityLine activityLineObj = activityLine;
		
		String query="UPDATE ActivityLine SET " + 
		" activityId= '" + activityLineObj.getActivity().getID() + "'," +
		" instructorId= '" + activityLineObj.getInstructor().getId() + "'," + 
		" date= '" + activityLineObj.getDate() + "'," + 
		" startHour= '" + activityLineObj.getStartHour() + "'," +
		" endHour= '" + activityLineObj.getEndHour() + "'," +
		" facilityId= '" + activityLineObj.getFacility().getId() + "'," +
		" bookingId= '" + activityLineObj.getActivityBooking().getId() + "'";
		
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
			System.out.println("Activity line has not been updated correctly. Exception: " + e);
		}
		
		return(result);
	}

	@Override
	public int deleteActivityLine(int activityId, int activityBookingId, Date startHour)
	{
		int result=-1;
		  
	  	String query="DELETE ActivityLine WHERE activityId= '" + activityId + "' " + 
	  			" AND activityBookingId= '" + activityBookingId + " AND startHour= '" + startHour + "'";
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
	  		System.out.println("Team has not been deleted successfully. Exception: " + e);
	  	}
	  	
	  	return(result);
	}

}
