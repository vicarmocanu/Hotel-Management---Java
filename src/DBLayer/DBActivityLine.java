package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;

import Model.ActivityBooking;
import Model.ActivityLine;
import Model.ActivityType;
import Model.Facility;
import Model.Instructor;

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
		IFDBInstructor dbInstructor = new DBInstructor();
		IFDBFacility dbFacility = new DBFacility();
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		
		try
		{
			ActivityType activityTypeObj = new ActivityType();
			activityTypeObj = dbActivityType.getActivityTypeByID(results.getInt("activityId"), false);
			activityLineObj.setActivity(activityTypeObj);
			
			ActivityBooking activityBookingObj = new ActivityBooking();
			activityBookingObj = dbActivityBooking.getActivityBookingById(results.getInt("bookingId"), true);
			activityLineObj.setActivityBooking(activityBookingObj);
			
			activityLineObj.setDate(results.getString("date"));
			activityLineObj.setStartHour(results.getString("startHour"));
			activityLineObj.setEndHour(results.getString("emdHour"));
			
			Facility facilityObj = new Facility();
			facilityObj = dbFacility.getFacilityById(results.getInt("facilityId"), true);
			activityLineObj.setFacility(facilityObj);
			
			Instructor instructorObj =new Instructor();
			instructorObj = dbInstructor.getInstructorById(results.getInt("instructorId"), true);
			activityLineObj.setInstructor(instructorObj);
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the activity line object: " + e);
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
				IFDBActivityType dbActivityType = new DBActivityType();
				ActivityType activityTypeObj = new ActivityType();
				activityTypeObj = dbActivityType.getActivityTypeByID(activityLineObj.getActivity().getID(), false);
				activityLineObj.setActivity(activityTypeObj);
				System.out.println("Activity type is selected.");
				
				IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
				ActivityBooking activityBookingObj = new ActivityBooking();
				activityBookingObj = dbActivityBooking.getActivityBookingById(activityLineObj.getActivityBooking().getId(), true);
				activityLineObj.setActivityBooking(activityBookingObj);
				System.out.println("Activity booking is selected.");
				
				IFDBFacility dbFacility = new DBFacility();
				Facility facilityObj = new Facility();
				facilityObj = dbFacility.getFacilityById(activityLineObj.getFacility().getId(), true);
				activityLineObj.setFacility(facilityObj);
				System.out.println("Facility is selected.");
				
				IFDBInstructor dbInstructor = new DBInstructor();
				Instructor instructorObj = new Instructor();
				instructorObj = dbInstructor.getInstructorById(activityLineObj.getInstructor().getId(), true);
				activityLineObj.setInstructor(instructorObj);
				System.out.println("Instructor is selected.");
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
		System.out.println(query);
		
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
				IFDBInstructor dbInstructor = new DBInstructor();
				IFDBFacility dbFacility = new DBFacility();
				IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
				for(ActivityLine activityLineObj : activityLineList)
				{
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = dbActivityType.getActivityTypeByID(activityLineObj.getActivity().getID(), false);
					activityLineObj.setActivity(activityTypeObj);
					System.out.println("Activity type is selected.");
					
					ActivityBooking activityBookingObj = new ActivityBooking();
					activityBookingObj = dbActivityBooking.getActivityBookingById(activityLineObj.getActivityBooking().getId(), true);
					activityLineObj.setActivityBooking(activityBookingObj);
					System.out.println("Activity booking is selected.");
					
					Facility facilityObj = new Facility();
					facilityObj = dbFacility.getFacilityById(activityLineObj.getFacility().getId(), true);
					activityLineObj.setFacility(facilityObj);
					System.out.println("Facility is selected.");
					
					Instructor instructorObj = new Instructor();
					instructorObj = dbInstructor.getInstructorById(activityLineObj.getInstructor().getId(), true);
					activityLineObj.setInstructor(instructorObj);
					System.out.println("Instructor is selected.");
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
	
	
	
	public ActivityLine getActivityLineById(int activityId,	int activityBookingId, boolean retrieveAssociation)
	{
		String wClause = " activityId= '" + activityId + "'" + " AND activityBookingId= '" + activityBookingId + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	
	public LinkedList<ActivityLine> getActivityLinesForActivityBooking(int activityBookingId, boolean retrieveAssociation)
	{
		String wClause = " activityBookingId= '" + activityBookingId + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

	
	public int insertActivityLine(ActivityLine activityLine) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO ActivityLine(activityId, bookingId, date, startHour, endHour, facilityId, instructorId) VALUES ('" +
				activityLine.getActivity().getID() + "','" + 
				activityLine.getActivityBooking().getId() + "','" +
				activityLine.getDate() + "','" + 
				activityLine.getStartHour() + "','" +
				activityLine.getEndHour() + "','" +
				activityLine.getFacility().getId() + "','" +
				activityLine.getInstructor().getId() + "')";
		
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
	    	System.out.println("Insert exception: " + e);
	    }
	    return(result);
	}

	
	public int updateActivityLine(ActivityLine activityLine)
	{
		ActivityLine activityLineObj = activityLine;
		
		String query="UPDATE ActivityLine SET " +
		" instructorId= '" + activityLineObj.getInstructor().getId() + "', " + 
		" date= '" + activityLineObj.getDate() + "', " + 
		" startHour= '" + activityLineObj.getStartHour() + "', " +
		" endHour= '" + activityLineObj.getEndHour() + "', " +
		" facilityId= '" + activityLineObj.getFacility().getId() + "', " +
		" bookingId= '" + activityLineObj.getActivityBooking().getId() + "', " +
		" WHERE activityId= '" + activityLineObj.getActivity().getID() + "'";
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
	  		System.out.println("Delete exception: " + e);
	  	}
	  	
	  	return(result);
	}
	
	
	public int getActivityLineInstances(int bookingId, String date, String startHour)
	{
		int instances = 0;		
		ResultSet results;
		String query = "SELECT COUNT(bookingId, date, startHour) AS activityLineInstances FROM ActivityLine " + 
		" WHERE bookingId='" + bookingId + "' AND date='" +  date + "' AND startHour='" + startHour + "'";		
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);			
			instances = results.getInt("activityLineInstances");
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity line instance count: " + e);
		}
		return instances;		
	}
	
	
	public int getNumberOfActivityLinesForBooking(int bookingId, String date)
	{
		int number = 0;
		ResultSet results;
		String query = "SELECT COUNT(bookingId, date) AS numberOfActivityLinesPerBooking FROM ActivityLine " + 
		" WHERE bookingId='" + bookingId + "' AND date='" +  date + "'";		
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);			
			number = results.getInt("numberOfActivityLinesPerBooking");
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity line instance count: " + e);
		}
		return number;	
	}

}
