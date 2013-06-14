package DBLayer;

import java.sql.*;
import java.util.LinkedList;

import Model.ActivityType;

public class DBActivityType implements IFDBActivityType
{
	private Connection con;
	
	//constructor - connection
	public DBActivityType()
	{
		con=DBConnection1.getInstance().getDBcon();
	}
	
	//method to build the query
	private String buildQuery(String wClause)
	{
		String query="SELECT * FROM ActivityType";
		
		if (wClause.length()>0)
		{
			query=query+" WHERE "+ wClause;
		}
		
		return query;
	}
	
	//method to build an activity type object
	private ActivityType buildActivityType(ResultSet results)
	{
		ActivityType activityTypeObj=new ActivityType();
		
		try
		{
			activityTypeObj.setID(results.getInt("id"));
			activityTypeObj.setName(results.getString("name"));
			activityTypeObj.setMaxParticipants(results.getInt("maxParticipants"));
		}
		catch(Exception e)
		{
			System.out.println("Exception in building the activity type object: " +e);
		}
		
		return activityTypeObj;
	}
	
	//singleWhere is used when we select only one object
	private ActivityType singleWhere (String wClause)
	{
		ResultSet results;
		ActivityType activityTypeObj=new ActivityType();
		
		String query = buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			if( results.next() )
			{
				activityTypeObj = buildActivityType(results);
				stmt.close();
			}
			else
			{
				activityTypeObj = null;
			}
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		
		return activityTypeObj;
	}
	
	//miscWhere is used when we want to select multiple objects
	private LinkedList<ActivityType> miscWhere (String wClause)
	{
		ResultSet results;
		LinkedList<ActivityType> activityTypeList=new LinkedList<ActivityType>();
		String query =  buildQuery(wClause);
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				ActivityType activityTypeObj=new ActivityType();
				activityTypeObj =buildActivityType(results);
				activityTypeList.add(activityTypeObj);
			}
			
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Multiple selection query exception: " + e);
			e.printStackTrace();
		}
		
		return activityTypeList;
	}

	//get all activity types
	
	public LinkedList<ActivityType> getAllActivityTypes()
	{
		return miscWhere("");
	}

	//get an activity type by id
	
	public ActivityType getActivityTypeByID(int id)
	{
		String wClause = "  id= '" + id + "'";
		return singleWhere(wClause);
	}

	//get an activity type by name
	
	public ActivityType getActivityTypeByName(String name)
	{
		String wClause = " name= '" + name + "'";
		return singleWhere(wClause);
	}
	
	//insert a new activity type into the database
	
	public int insertActivityType(ActivityType activityTypeObj) throws Exception
	{
		//call to get the next activity type id
		int nextActivityTypeId = GetMax.getMaxId("SELECT MAX(id) from ActivityType");
		nextActivityTypeId = nextActivityTypeId + 1;
		System.out.println("Next activity type id = " + nextActivityTypeId);
		
		int result = -1;
		
		String query = "INSERT INTO ActivityType(id, name, maxParticipants) VALUES ('" +
		nextActivityTypeId + "','" +
				activityTypeObj.getName() + "','" +
		activityTypeObj.getMaxParticipants() +  "')";
		System.out.println("Insert query: " + query);
		
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

	//method to update an activity type
	
	public int updateActivityType(ActivityType activityTypeObj)
	{
		ActivityType activityTypeNewObj=activityTypeObj;
		
		String query="UPDATE ActivityType SET " +
		"name= '" + activityTypeNewObj.getName() + "', " +
		"maxParticipants= '" + activityTypeNewObj.getMaxParticipants() + "' " + 
		"WHERE id= '" + activityTypeNewObj.getID() + "'";
		
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

	//method to delete an activity type by its id
	
	public int deleteActivityTypeByID(int id)
	{
		int result=-1;
		  
	  	String query="DELETE FROM ActivityType WHERE id= '" + id + "'";
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

	//method to delete an activity type by its name
	
	public int deleteActivityTypeByName(String name)
	{
		int result=-1;
		  
	  	String query="DELETE FROM ActivityType WHERE name= '" + name + "'";
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
	public int getActivityTypeInstances(int id, String name)
	{
		int instances = 0;
		
		ResultSet results;
		
		String query = "SELECT COUNT(*) AS activityTypeInstances FROM ActivityType " + 
		" WHERE id<> '" +  id + "' AND name= '" + name + "'";
		System.out.println(query);
		
		try
		{
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);
			
			while( results.next() )
			{
				instances = results.getInt("activityTypeInstances");
				System.out.println("Activity type instances: " + instances);
			}
			stmt.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception in returning the activity type instance count: " + e);
		}
		return instances;
	}
}
