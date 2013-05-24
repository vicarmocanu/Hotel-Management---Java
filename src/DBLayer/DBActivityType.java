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
			System.out.println("Error in building the activity type object.");
		}
		
		return activityTypeObj;
	}
	
	//singleWhere is used when we select only one object
	private ActivityType singleWhere (String wClause, boolean retrieveAssociation)
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
		}
		catch(Exception e)
		{
			System.out.println("Single selection query exception: " + e);
		}
		
		return activityTypeObj;
	}
	
	//miscWhere is used when we want to select multiple objects
	private LinkedList<ActivityType> miscWhere (String wClause, boolean retrieveAssociation)
	{
		ResultSet results;
		LinkedList<ActivityType> activityTypeList=new LinkedList<ActivityType>();
		String query =  buildQuery(wClause);
		
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
	@Override
	public LinkedList<ActivityType> getAllActivityTypes(boolean retrieveAssociation)
	{
		return miscWhere("", retrieveAssociation);
	}

	//get an activity type by id
	@Override
	public ActivityType getActivityTypeByID(int id, boolean retrieveAssociation)
	{
		String wClause = "  id= '" + id + "'";
		return singleWhere(wClause, retrieveAssociation);
	}

	//get an activity type by name
	@Override
	public ActivityType getActivityTypeByName(String name, boolean retrieveAssociation)
	{
		String wClause = " name= '" + name + "'";
		return singleWhere(wClause, retrieveAssociation);
	}
	
	//insert a new activity type into the database
	@Override
	public int insertActivityType(ActivityType activityTypeObj) throws Exception
	{
		int result = -1;
		
		String query = "INSERT INTO ActivityType(name, description, maxParticipants, price) VALUES ('" +
				activityTypeObj.getName() + "','" +
				activityTypeObj.getMaxParticipants() +  "')";
		
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
	    	System.out.println("Activity type has not been inserted correctly. Exception: " + e);
	    }
	    
	    return(result);
	}

	//method to update an activity type
	@Override
	public int updateActivityType(ActivityType activityTypeObj)
	{
		ActivityType activityTypeNewObj=activityTypeObj;
		
		String query="UPDATE ActivityType SET " +
		"name= '" + activityTypeNewObj.getName() + "', " +
		"maxParticipants= '" + activityTypeNewObj.getMaxParticipants() + "'";
		
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
			System.out.println("Activity type has not been updated correctly. Exception: " + e);
		}
		
		return(result);
	}

	//method to delete an activity type by its id
	@Override
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
	  		System.out.println("Activity type has not been deleted successfully. Exception: " + e);
	  	}
	  	
	  	return(result);
	}

	//method to delete an activity type by its name
	@Override
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
	  		System.out.println("Activity type has not been deleted successfully. Exception: " + e);
	  	}
	  	
	  	return(result);
	}
}
