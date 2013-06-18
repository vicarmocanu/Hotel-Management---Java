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
			activityTypeObj = dbActivityType.getActivityTypeByID(results.getInt("activityId"));
			activityLineObj.setActivity(activityTypeObj);
			
			ActivityBooking activityBookingObj = new ActivityBooking();
			activityBookingObj = dbActivityBooking.getActivityBookingById(results.getInt("bookingId"));
			activityLineObj.setActivityBooking(activityBookingObj);
			
			activityLineObj.setDate(results.getString("date"));
			activityLineObj.setStartHour(results.getString("startHour"));
			activityLineObj.setEndHour(results.getString("endHour"));
			
			Facility facilityObj = new Facility();
			facilityObj = dbFacility.getFacilityById(results.getInt("facilityId"));
			activityLineObj.setFacility(facilityObj);
			
			Instructor instructorObj =new Instructor();
			int instructorID = results.getInt("instructorId");
			if(instructorID == 0)
			{
				activityLineObj.setInstructor(null);
			}
			else
			{
				instructorObj = dbInstructor.getInstructorById(instructorID);
				activityLineObj.setInstructor(instructorObj);
			}
			
			Team teamObj = new Team();
			int teamId = results.getInt("teamId");
			if(teamId == 0)
			{
				activityLineObj.setTeam(null);
			}
			else
			{
				teamObj = dbTeam.getTeamById(teamId);
				activityLineObj.setTeam(teamObj);
			}
			
			activityLineObj.setStatus(results.getString("status"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the activity line object: " + e);
		}
		return activityLineObj;
	}
	
	//single where selection
	private ActivityLine singleWhere(String wClause)
	{
		ResultSet results;
		ActivityLine activityLineObj = new ActivityLine();
		String query = buildQuery(wClause);
		System.out.println("Query: " + query);
		
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
			else
			{
				activityLineObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		return activityLineObj;
	}
	
	//misc where selection
	private LinkedList<ActivityLine> miscWhere(String wClause)
	{
		ResultSet results;
		LinkedList<ActivityLine> activityLineList=new LinkedList<ActivityLine>();
		String query =  buildQuery(wClause);
		System.out.println("Query: " + query);
		
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
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return activityLineList;
	}
	
	@Override
	public ActivityLine getActivityLine(int activityId, int bookingId, int facilityId)
	{
		String wClause = " activityId= '" + activityId + "'" + 
	" AND bookingId= '" + bookingId +"'" + 
			" AND facilityId= '" + facilityId + "'" +
	" AND status<>'Canceled'";
		return singleWhere(wClause);
	}

	@Override
	public LinkedList<ActivityLine> getActivityLinesForActivityBooking(int bookingId)
	{
		String wClause = " bookingId= '" + bookingId + "'" + " AND status<>'Canceled'";
		return miscWhere(wClause);
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
			query = "INSERT INTO ActivityLine(activityId, bookingId, date, startHour, endHour, facilityId, status) VALUES ('" +
					activityTypeObj.getID() + "','" + 
					activityBookingObj.getId() + "','" +
					activityLine.getDate() + "','" + 
					activityLine.getStartHour() + "','" +
					activityLine.getEndHour() + "','" +
					facilityObj.getId() + "','" + 
					activityLine.getStatus() + "')";
			
			System.out.println("Insertion query: " + query);
		}
		
		if(instructorObj != null)
		{
			query = "INSERT INTO ActivityLine(activityId, bookingId, date, startHour, endHour, facilityId, instructorId, status) VALUES ('" +
					activityTypeObj.getID() + "','" + 
					activityBookingObj.getId() + "','" +
					activityLine.getDate() + "','" + 
					activityLine.getStartHour() + "','" +
					activityLine.getEndHour() + "','" +
					facilityObj.getId() + "','" +
					instructorObj.getId() + "','" + 
					activityLine.getStatus() + "')";
			
			System.out.println("Insertion query: " + query);
		}
		
		if(teamObj !=null)
		{
			query = "INSERT INTO ActivityLine(activityId, bookingId, date, startHour, endHour, facilityId, teamId, status) VALUES ('" +
					activityTypeObj.getID() + "','" + 
					activityBookingObj.getId() + "','" +
					activityLine.getDate() + "','" + 
					activityLine.getStartHour() + "','" +
					activityLine.getEndHour() + "','" +
					facilityObj.getId() + "','" +
					teamObj.getId() + "','" + 
					activityLine.getStatus() + "')";
			
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
		int result=-1;
		
		String query = "UPDATE ActivityLine SET " + 
		" status= '" + activityLine.getStatus() + "' "
		+ "WHERE activityId= '" + activityLine.getActivity().getID() + "' " +
		"AND bookingId= '" + activityLine.getActivityBooking().getId() + "' " + 
		"AND facilityId= '" + activityLine.getFacility().getId() + "'";
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
	public int getActivityLineInstances1(int activityId, int bookingId, String date, String startHour, int facilityId, String status)
	{
		int instances = 0;
		
		ResultSet results;
		
		String query = "SELECT COUNT(*) AS activityLineInstances1 FROM ActivityLine " + 
		"WHERE activityId= '" + activityId + "' " +
				"AND bookingId= '" +  bookingId + "' " +
		"AND date= '" + date + "' " +
				"AND startHour= '" + startHour + "' " +
				"AND facilityId= '" + facilityId + "' " +
				"AND status= '" + status + "'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				instances = results.getInt("activityLineInstances1");
				System.out.println("Activity line instances1= " + instances);
			}
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
		String query = "SELECT COUNT(*) AS activityLineInstances2 FROM ActivityLine " + 
		" WHERE date='" + date + "' AND startHour= '" +  startHour + "' AND instructorId= '" + instructorId + "'" +
				" AND status<>'Canceled' ";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);		
			
			while( results.next() )
			{
				instances = results.getInt("activityLineInstances2");
				System.out.println("Activity line instances2= " + instances);
			}
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
		String query = "SELECT COUNT(*) AS activityLineNumbers FROM ActivityLine " + 
		" WHERE bookingId='" + bookingId + "' AND date= '" +  date + "' AND status<>'Canceled' ";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				numbers = results.getInt("activityLineNumbers");
				System.out.println("Number of activity lines per booking= " + numbers);
			}
			stmt.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity line number count: " + e);
		}
		return numbers;		
	}

	@Override
	public LinkedList<ActivityLine> getDateActivityLines(String date) 
	{
		String wClause = " date= '" + date + "' AND status<>'Canceled'";
		return miscWhere(wClause);
	}
	
	@Override
	public LinkedList<ActivityLine> getInstructorActivityLinesSchedule(String date, int instructorId)
	{
		String wClause = " date= '" + date + "' AND instructorId= '" + instructorId + "' AND status<>'Canceled'";
		return miscWhere(wClause);
	}

	@Override
	public int getActivityLineInstances3(int activityId, String date, String startHour, int facilityId)
	{
		int instances = 0;		
		ResultSet results;
		String query = "SELECT COUNT(*) AS activityLineInstances3 FROM ActivityLine " + 
		" WHERE activityId= '" + activityId + "' " +
				"AND date= '" + date + "' " +
		"AND startHour= '" +  startHour + "' " +
				"AND facilityId= '" + facilityId + "' " +
		"AND status<>'Canceled' ";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);		
			
			while( results.next() )
			{
				instances = results.getInt("activityLineInstances3");
				System.out.println("Activity line instances3= " + instances);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity line instance count: " + e);
		}
		return instances;		
	}

	@Override
	public int getActivityLineInstances4(int bookingId, String date, String startHour)
	{
		int instances = 0;		
		ResultSet results;
		String query = "SELECT COUNT(*) AS activityLineInstances3 FROM ActivityLine " + 
		" WHERE bookingId= '" + bookingId + "' " +
				"AND date= '" + date + "' " +
		"AND startHour= '" +  startHour + "' " +
				"AND startHour= '" + startHour + "' " +
		"AND status<>'Canceled' ";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);		
			
			while( results.next() )
			{
				instances = results.getInt("activityLineInstances3");
				System.out.println("Activity line instances3= " + instances);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity line instance count: " + e);
		}
		return instances;	
	}

}