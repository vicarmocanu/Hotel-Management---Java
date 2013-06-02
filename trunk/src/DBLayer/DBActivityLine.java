package DBLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import Model.ActivityBooking;
import Model.ActivityLine;
import Model.ActivityType;
import Model.Facility;
import Model.Instructor;
import Model.Team;

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
		IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
		IFDBFacility dbFacility = new DBFacility();		
		IFDBInstructor dbInstructor = new DBInstructor();
		IFDBTeam dbTeam = new DBTeam();
		
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
			activityLineObj.setEndHour(results.getString("endHour"));
			
			Facility facilityObj = new Facility();
			facilityObj = dbFacility.getFacilityById(results.getInt("facilityId"), true);
			activityLineObj.setFacility(facilityObj);
			
			Instructor instructorObj =new Instructor();
			instructorObj = dbInstructor.getInstructorById(results.getInt("instructorId"), true);
			if(instructorObj != null)
			{
				activityLineObj.setInstructor(instructorObj);
			}
			
			Team teamObj = new Team();
			teamObj = dbTeam.getTeamById(results.getInt("teamId"), true);
			if(teamObj != null)
			{
				activityLineObj.setTeam(teamObj);
			}
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
			{//activity, activity booking, facility, instructor, team reference
				IFDBActivityType dbActivityType = new DBActivityType();
				ActivityType activityTypeObj = new ActivityType();
				activityTypeObj = dbActivityType.getActivityTypeByID(activityLineObj.getActivity().getID(), false);
				if(activityTypeObj != null)
				{
					activityLineObj.setActivity(activityTypeObj);
					System.out.println("Activity type is selected.");
				}
				
				IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
				ActivityBooking activityBookingObj = new ActivityBooking();
				activityBookingObj = dbActivityBooking.getActivityBookingById(activityLineObj.getActivityBooking().getId(), true);
				if(activityBookingObj != null)
				{
					activityLineObj.setActivityBooking(activityBookingObj);
					System.out.println("Activity booking is selected.");
				}
				
				IFDBFacility dbFacility = new DBFacility();
				Facility facilityObj = new Facility();
				facilityObj = dbFacility.getFacilityById(activityLineObj.getFacility().getId(), true);
				if(facilityObj != null)
				{
					activityLineObj.setFacility(facilityObj);
					System.out.println("Facility is selected.");
				}
				
				IFDBInstructor dbInstructor = new DBInstructor();
				Instructor instructorObj = new Instructor();
				instructorObj = dbInstructor.getInstructorById(activityLineObj.getInstructor().getId(), true);
				if(instructorObj != null)
				{
					activityLineObj.setInstructor(instructorObj);
					System.out.println("Instructor is selected.");
				}
				else
				{
					System.out.println("No instructor selection.");
				}
				
				IFDBTeam dbTeam = new DBTeam();
				Team teamObj = new Team();
				teamObj = dbTeam.getTeamById(activityLineObj.getTeam().getId(), true);
				if(teamObj != null)
				{
					activityLineObj.setTeam(teamObj);
					System.out.println("Team is selected.");
				}
				else
				{
					System.out.println("No team selection.");
				}
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
			{//activity, activity booking, facility, instructor, team reference
				IFDBActivityType dbActivityType = new DBActivityType();
				IFDBInstructor dbInstructor = new DBInstructor();
				IFDBFacility dbFacility = new DBFacility();
				IFDBActivityBooking dbActivityBooking = new DBActivityBooking();
				IFDBTeam dbTeam = new DBTeam();
				for(ActivityLine activityLineObj : activityLineList)
				{
					ActivityType activityTypeObj = new ActivityType();
					activityTypeObj = dbActivityType.getActivityTypeByID(activityLineObj.getActivity().getID(), false);
					if(activityTypeObj != null)
					{
						activityLineObj.setActivity(activityTypeObj);
						System.out.println("Activity type is selected.");
					}
					
					ActivityBooking activityBookingObj = new ActivityBooking();
					activityBookingObj = dbActivityBooking.getActivityBookingById(activityLineObj.getActivityBooking().getId(), true);
					if(activityBookingObj != null)
					{
						activityLineObj.setActivityBooking(activityBookingObj);
						System.out.println("Activity booking is selected.");
					}
					
					Facility facilityObj = new Facility();
					facilityObj = dbFacility.getFacilityById(activityLineObj.getFacility().getId(), true);
					if(facilityObj != null)
					{
						activityLineObj.setFacility(facilityObj);
						System.out.println("Facility is selected.");
					}
					
					Instructor instructorObj = new Instructor();
					instructorObj = dbInstructor.getInstructorById(activityLineObj.getInstructor().getId(), true);
					if(instructorObj != null)
					{
						activityLineObj.setInstructor(instructorObj);
						System.out.println("Instructor is selected.");
					}
					else
					{
						System.out.println("No instructor selection.");
					}
					
					Team teamObj = new Team();
					teamObj = dbTeam.getTeamById(activityLineObj.getTeam().getId(), true);
					if(teamObj != null)
					{
						activityLineObj.setTeam(teamObj);
						System.out.println("Team is selected.");
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
		
		return activityLineList;
	}
	
	@Override
	public ActivityLine getActivityLine(int bookingId, String startHour, boolean retrieveAssociation)
	{
		String wClause = " bookingId= '" + bookingId + "'" + " AND startHour= '" + startHour +"'";
		return singleWhere(wClause, retrieveAssociation);
	}

	@Override
	public LinkedList<ActivityLine> getActivityLinesForActivityBooking(int bookingId, boolean retrieveAssociation)
	{
		String wClause = " bookingId= '" + bookingId + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

	@Override
	public int insertActivityLine(ActivityLine activityLine) throws Exception
	{
		int result = -1;
		String query = new String();
		
		ActivityType activityTypeObj = activityLine.getActivity();
		ActivityBooking activityBookingObj = activityLine.getActivityBooking();
		Facility facilityObj = activityLine.getFacility();		
		Instructor instructorObj = activityLine.getInstructor();
		Team teamObj = activityLine.getTeam();
		
		if(instructorObj == null && teamObj == null)
		{
			query = "INSERT INTO ActivityLine(activityId, bookingId, date, startHour, endHour, facilityId) VALUES ('" +
					activityTypeObj.getID() + "','" + 
					activityBookingObj.getId() + "','" +
					activityLine.getDate() + "','" + 
					activityLine.getStartHour() + "','" +
					activityLine.getEndHour() + "','" +
					facilityObj.getId() + "')";
			
			System.out.println("Insertion query: " + query);
		}
		
		if(instructorObj != null)
		{
			query = "INSERT INTO ActivityLine(activityId, bookingId, date, startHour, endHour, facilityId, instructorId) VALUES ('" +
					activityTypeObj.getID() + "','" + 
					activityBookingObj.getId() + "','" +
					activityLine.getDate() + "','" + 
					activityLine.getStartHour() + "','" +
					activityLine.getEndHour() + "','" +
					facilityObj.getId() + "','" +
					instructorObj.getId() + "')";
			
			System.out.println("Insertion query: " + query);
		}
		
		if(teamObj !=null)
		{
			query = "INSERT INTO ActivityLine(activityId, bookingId, date, startHour, endHour, facilityId, teamId) VALUES ('" +
					activityTypeObj.getID() + "','" + 
					activityBookingObj.getId() + "','" +
					activityLine.getDate() + "','" + 
					activityLine.getStartHour() + "','" +
					activityLine.getEndHour() + "','" +
					facilityObj.getId() + "','" +
					teamObj.getId() + "')";
			
			System.out.println("Insertion query: " + query);
		}
		
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

	@Override
	public int updateActivityLine(ActivityLine activityLine)
	{
		ActivityLine activityLineObj = activityLine;
		String query = new String();
		
		ActivityType activityTypeObj = activityLine.getActivity();
		ActivityBooking activityBookingObj = activityLine.getActivityBooking();
		Facility facilityObj = activityLine.getFacility();		
		Instructor instructorObj = activityLine.getInstructor();
		Team teamObj = activityLine.getTeam();
		
		if(instructorObj == null && teamObj == null)
		{
			query = "UPDATE ActivityLine SET " +
		" activityId= '" + activityTypeObj.getID() + "', " + 
					" date= '" + activityLineObj.getDate() + "', " + 
		" startHour= '" + activityLineObj.getStartHour() + "', " +
					" endHour= '" + activityLineObj.getEndHour() + "', " +
		" facilityId= '" + facilityObj.getId() + "' " + 
					"WHERE bookingId = '" + activityBookingObj.getId() + "'";
		}
		if(instructorObj !=null)
		{
			query = "UPDATE ActivityLine SET " +
		" activityId= '" + activityTypeObj.getID() + "', " + 
					" date= '" + activityLineObj.getDate() + "', " + 
		" startHour= '" + activityLineObj.getStartHour() + "', " +
								" endHour= '" + activityLineObj.getEndHour() + "', " +
		" facilityId= '" + facilityObj.getId() + "', " +
								" instructorId= '" + instructorObj.getId() + "' " +
		"WHERE bookingId = '" + activityBookingObj.getId() + "'";
		}
		if(teamObj != null)
		{
			query = "UPDATE ActivityLine SET " +
					" activityId= '" + activityTypeObj.getID() + "', " + 
								" date= '" + activityLineObj.getDate() + "', " + 
					" startHour= '" + activityLineObj.getStartHour() + "', " +
											" endHour= '" + activityLineObj.getEndHour() + "', " +
					" facilityId= '" + facilityObj.getId() + "', " +
											" teamId= '" + teamObj.getId() + "' " +
					"WHERE bookingId = '" + activityBookingObj.getId() + "'";
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
	public  int deleteActivityLine(int activityId, int bookingId, String date, String startHour)
	{
		int result=-1;
		  
	  	String query="DELETE FROM ActivityLine WHERE activityId= '" + activityId + 
	  			"' AND bookingId= '" + bookingId + 
	  			"' AND date= '" + date + 
	  			"' AND startHour= '" + startHour + "'";
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
	public int deleteActivityLinesFromActivityBooking(int bookingId)
	{
		int result=-1;
		  
	  	String query="DELETE FROM ActivityLine WHERE bookingId= '" + bookingId + "'";
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
	public int getActivityLineInstances1(int activityId, int bookingId, String date, String startHour, int facilityId)
	{
		int instances = 0;		
		ResultSet results;
		String query = "SELECT COUNT(activityId, bookingId, date, startHour, facilityId) AS activityLineInstances1 FROM ActivityLine " + 
		"WHERE activityId= '" + activityId + "' AND bookingId= '" +  bookingId + "' AND date = '" + date + "' AND startHour= '" + startHour + "' AND facilityId= '" + facilityId + "'";
		;		
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);			
			instances = results.getInt("activityLineInstances1");
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity line instance count: " + e);
		}
		return instances;		
	}
	
	@Override
	public int getActivityLineInstances2(String date, String startHour, int instructorId)
	{
		int instances = 0;		
		ResultSet results;
		String query = "SELECT COUNT(date, startHour, instructorId) AS activityLineInstances2 FROM ActivityLine " + 
		" WHERE date='" + date + "' AND startHour= '" +  startHour + "' AND instructorId= '" + instructorId + "'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);			
			instances = results.getInt("activityLineInstances2");
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity line instance count: " + e);
		}
		return instances;		
	}	

	@Override
	public int getNumberOfActivityLinesInActivityBooking(int bookingId,	String date)
	{
		int numbers = 0;		
		ResultSet results;
		String query = "SELECT COUNT(bookingId, date) AS activityLineNumbers FROM ActivityLine " + 
		" WHERE bookingId='" + bookingId + "' AND date= '" +  date + "'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);			
			numbers = results.getInt("activityLineNumbers");
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity line number count: " + e);
		}
		return numbers;		
	}

	@Override
	public LinkedList<ActivityLine> getDateActivityLines(String date,boolean retrieveAssociation) 
	{
		String wClause = " date= '" + date + "'";
		return miscWhere(wClause, retrieveAssociation);
	}

}